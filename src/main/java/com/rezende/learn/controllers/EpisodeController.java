package com.rezende.learn.controllers;

import com.rezende.learn.dtos.WatchTimeDTO;
import com.rezende.learn.entities.WatchTimePK;
import com.rezende.learn.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/episodes")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping(value = "/watchTime")
    public ResponseEntity<WatchTimeDTO> findWatchTime(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "episodeId", required = false) Long episodeId) {
        WatchTimeDTO dto = episodeService.findWatchTime(userId, episodeId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/stream")
    public ResponseEntity<byte[]> streamEpisodeResponse(
            @RequestParam(value = "videoUrl", required = false) String videoUrl,
            @RequestHeader(value = "range", required = false) String rangeHeader) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "video/mp4");
        headers.add(HttpHeaders.ACCEPT_RANGES, "bytes");

        byte[] content = episodeService.streamEpisodeResponse(videoUrl, rangeHeader);

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            long fileSize = episodeService.getContentSize(EpisodeService.ABSOLUTE_PATH, videoUrl);
            Map<String, Long> rangers = episodeService.getRangeStartAndEnd(rangeHeader, fileSize);
            headers.add(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d",
                    rangers.get("start"), rangers.get("end"), fileSize));
            headers.setContentLength(content.length);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).headers(headers).body(content);
        }
        headers.setContentLength(content.length);
        return ResponseEntity.ok().headers(headers).body(content);
    }

    @PostMapping(value = "/watchTime")
    public ResponseEntity<WatchTimeDTO> setWatchTime(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "episodeId", required = false) Long episodeId,
            @RequestParam(value = "seconds", required = false) Long seconds) {

        WatchTimeDTO dto = episodeService.setWatchTime(userId, episodeId, seconds);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}/{episodeId}")
                .buildAndExpand(dto.getUser().getId(), dto.getEpisode().getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
