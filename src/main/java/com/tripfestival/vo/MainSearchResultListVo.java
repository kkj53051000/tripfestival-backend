package com.tripfestival.vo;

import lombok.Getter;

import java.util.List;

@Getter
public class MainSearchResultListVo {
    private List<MainSearchResultVo> items = null;

    public MainSearchResultListVo(List<MainSearchResultVo> mainSearchResultVoList) {
        this.items = mainSearchResultVoList;
    }
}
