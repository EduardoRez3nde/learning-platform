package com.rezende.learn.services;

import com.rezende.learn.dtos.WatchTimeDTO;
import com.rezende.learn.entities.Episode;
import com.rezende.learn.entities.User;
import com.rezende.learn.entities.WatchTime;
import com.rezende.learn.entities.WatchTimePK;
import com.rezende.learn.repositories.EpisodeRepository;
import com.rezende.learn.repositories.UserRepository;
import com.rezende.learn.repositories.WatchTimeRepository;
import com.rezende.learn.services.exceptions.DatabaseException;
import com.rezende.learn.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EpisodeService {

    private static final Long SIZE_PER_REQUEST = 5 * 1000 * 1000L; // 5MB
    public static final String ABSOLUTE_PATH = "/app/uploads/videos";

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private WatchTimeRepository watchTimeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public WatchTimeDTO setWatchTime(Long userId, Long episodeId, Long seconds) {
        try {
            User user = userRepository.getReferenceById(userId);
            Episode episode = episodeRepository.getReferenceById(episodeId);
            WatchTimePK id = new WatchTimePK(user, episode);

            Optional<WatchTime> result = watchTimeRepository.findById(id);

            WatchTime watchTime;

            if (result.isPresent()) {
                watchTime = result.get();
                watchTime.setSeconds(seconds);
            } else
                watchTime = new WatchTime(seconds, user, episode);

            watchTime = watchTimeRepository.save(watchTime);
            return new WatchTimeDTO(watchTime);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation Exception");
        }
    }

    @Transactional(readOnly = true)
    public WatchTimeDTO findWatchTime(Long userId, Long episodeId) {
        User user = userRepository.getReferenceById(userId);
        Episode episode = episodeRepository.getReferenceById(episodeId);
        WatchTimePK id = new WatchTimePK(user, episode);
        WatchTime result = watchTimeRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Resource Not Found");
        });
        return new WatchTimeDTO(result);
    }

    public byte[] streamEpisodeResponse(String videoUrl, String rangerHeader) {
        // https://www.zeng.dev/post/2023-http-range-and-play-mp4-in-browser/
        Resource videoResource = getAbsolutePath(ABSOLUTE_PATH, videoUrl);
        long fileSize = getContentSize(videoResource);

        try (InputStream inputStream = videoResource.getInputStream()) {
            if (rangerHeader == null)
                return createPartialResponse(0, Math.min(SIZE_PER_REQUEST, fileSize), fileSize, inputStream);

            Map<String, Long> rangers = getRangeStartAndEnd(rangerHeader, fileSize);
            return createPartialResponse(rangers.get("start"), rangers.get("end"), fileSize, inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] createPartialResponse(long start, long end, long fileSize, InputStream inputStream) {
        try {
            long contentLength = end - start + 1;
            inputStream.skip(start);
            byte[] data = new byte[(int) contentLength];
            int byteRead = inputStream.read(data, 0, (int) contentLength);
            if (byteRead != contentLength)
                throw new IOException("error");
            return data;
        } catch (IOException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    public Resource getAbsolutePath(String path, String videoUrl) {
        try {
            Path pathFile = Path.of(path, videoUrl).normalize().toAbsolutePath();
            return new UrlResource(pathFile.toUri());
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getContentSize(Resource resource) {
        try {
            return resource.contentLength();
        } catch (IOException e) {
            throw new RuntimeException("Failed to determine resource size", e);
        }
    }

    public long getContentSize(String path, String contentPath) {
        Resource resource = getAbsolutePath(path, contentPath);
        return getContentSize(resource);
    }

    public Map<String, Long> getRangeStartAndEnd(String rangerHeader, long fileSize) {

        Map<String, Long> rangers = new HashMap<>(2);

        List<HttpRange> ranges = HttpRange.parseRanges(rangerHeader);
        if (ranges.isEmpty())
            throw new IllegalArgumentException("error ranges");

        HttpRange range = ranges.get(0);
        rangers.put("start", range.getRangeStart(fileSize));
        rangers.put("end", Math.min(rangers.get("start") + SIZE_PER_REQUEST - 1, fileSize - 1));

        return rangers;
    }
}
