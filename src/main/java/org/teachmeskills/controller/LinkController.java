package org.teachmeskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.teachmeskills.entity.Link;
import org.teachmeskills.exceptions.IncorrectUrlException;
import org.teachmeskills.services.LinkService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mainPage")
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public ModelAndView getMainPage(@RequestParam(required = false) String shortUrl,
                                    UriComponentsBuilder urlBuilder) {
        Link link = shortUrl != null ? linkService.getLongUrl(shortUrl) : null;

        URI uri = urlBuilder
                .path("/find/")
                .build()
                .toUri();

        Map<String, Object> map = new HashMap<>();
        map.put("link", link);
        map.put("uri", uri);
        return new ModelAndView("MainPage",
                map);
    }

    @GetMapping("find/{shortUrl}")
    public ResponseEntity<?> redirectOnRealUrl(@PathVariable String shortUrl) {
        Link link = linkService.getLongUrl(shortUrl);
        URI longUrl = link.getLongUrl();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(longUrl)
                .build();
    }

    @PostMapping
    public ResponseEntity<?> getShortUrl(@RequestParam String longUrl, UriComponentsBuilder urlBuilder) {
        System.out.println(longUrl);
        Link link;
        try {
            link = linkService.getShortUrl(longUrl);
        } catch (URISyntaxException exception) {
            throw new IncorrectUrlException("Incorrect url");
        }

        URI uri = urlBuilder
                .path("/mainPage")
                .queryParam("shortUrl", link.getShortUrl())
                .build()
                .toUri();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }
}
