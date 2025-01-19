package com.rezende.learn.controllers;

import com.rezende.learn.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/episode")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<byte[]> streamEpisodeResponse(
            @RequestParam(value = "videoUrl", required = false) String videoUrl,
            @RequestHeader(value = "range", required = false) String rangeHeader) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "video/mp4");
        headers.add(HttpHeaders.ACCEPT_RANGES, "bytes");

        byte[] content = episodeService.streamEpisodeResponse(videoUrl, rangeHeader);

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            long fileSize = episodeService.getContentSize("/app/uploads", videoUrl);
            Map<String, Long> rangers = episodeService.getRangeStartAndEnd(rangeHeader, fileSize);
            headers.add(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d",
                    rangers.get("start"), rangers.get("end"), fileSize));
            headers.setContentLength(content.length);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).headers(headers).body(content);
        }
        headers.setContentLength(content.length);
        return ResponseEntity.ok().headers(headers).body(content);
    }
}
