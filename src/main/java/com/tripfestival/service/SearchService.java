package com.tripfestival.service;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.vo.MainSearchResultListVo;
import com.tripfestival.vo.MainSearchResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final LandmarkRepository landmarkRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Transactional(readOnly = true)
    public MainSearchResultListVo mainSearchResultSelect(String searchWord) {
        // Landmark List
        List<Landmark> landmarkList = landmarkRepository.findAllByNameStartingWith(searchWord);

        // Region List
        List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findByNameStartingWith(searchWord);

        // Make Search Result
        List<MainSearchResultVo> mainSearchResultVoList = new ArrayList<>();

        if (landmarkList.size() > 2) {  // 관광지 사이즈가 3 이상일 때
            for (int i = 0; i < 3; i++) {
                MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                        .title(landmarkList.get(i).getName())
                        .img(landmarkList.get(i).getImg())
                        .build();

                mainSearchResultVoList.add(mainSearchResultVo);
            }
            if (worldCountryCityRegionList.size() > 1) {   // 위 조건 + 지역 사이즈 2 이상일때
                for (int i = 0; i < 2; i++) {
                    MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                            .title(worldCountryCityRegionList.get(i).getName())
                            .build();

                    mainSearchResultVoList.add(mainSearchResultVo);
                }
            }
            else {  // 위 조건 + 지역 사이즈 2 미만일때
                for (WorldCountryCityRegion worldCountryCityRegion : worldCountryCityRegionList) {
                    MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                            .title(worldCountryCityRegion.getName())
                            .build();

                    mainSearchResultVoList.add(mainSearchResultVo);
                }
            }
        }else {  // 관광지 사이즈가 3미만 일 때
            for (Landmark landmark : landmarkList) {
                MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                        .title(landmark.getName())
                        .img(landmark.getImg())
                        .build();

                mainSearchResultVoList.add(mainSearchResultVo);
            }

            // 총 5개 채우도록 노력하는 알고리즘
            int count  = 0;
            for (WorldCountryCityRegion worldCountryCityRegion : worldCountryCityRegionList) {

                if (count > (5-landmarkList.size())) {
                    break;
                }
                count++;

                MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                        .title(worldCountryCityRegion.getName())
                        .build();

                mainSearchResultVoList.add(mainSearchResultVo);
            }
        }
        return new MainSearchResultListVo(mainSearchResultVoList);
    }
}
