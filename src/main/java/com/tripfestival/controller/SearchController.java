package com.tripfestival.controller;

import com.tripfestival.service.SearchService;
import com.tripfestival.vo.MainSearchResultListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/mainSearchResult")
    public MainSearchResultListVo mainSearchResult(String searchWorld) {
        System.out.println("searchWorld" + searchWorld.getClass().getSimpleName());
        return searchService.mainSearchResultSelect(searchWorld);
    }
}
