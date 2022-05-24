package com.tripfestival.service;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
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

    private final WorldCountryCityRepository worldCountryCityRepository;

    @Transactional(readOnly = true)
    public MainSearchResultListVo mainSearchResultSelect(String searchWord) {

        // City List
        List<WorldCountryCity> worldCountryCityList = worldCountryCityRepository.findByNameStartsWith(searchWord);

        // Region List
        List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findByNameStartsWith(searchWord);


        List<MainSearchResultVo> mainSearchResultVoList = new ArrayList<>();

        for (WorldCountryCity worldCountryCity : worldCountryCityList) {
            if (mainSearchResultVoList.size() >= 5) {
                break;
            }
            MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                    .title(worldCountryCity.getName())
                    .img(worldCountryCity.getCityImg())
                    .build();

            mainSearchResultVoList.add(mainSearchResultVo);
        }

        for (WorldCountryCityRegion worldCountryCityRegion : worldCountryCityRegionList) {
            if (mainSearchResultVoList.size() >= 5) {
                break;
            }

            MainSearchResultVo mainSearchResultVo = MainSearchResultVo.builder()
                    .title(worldCountryCityRegion.getWorldCountryCity().getName() + " " + worldCountryCityRegion.getName())
                    .img(worldCountryCityRegion.getImg())
                    .build();

            mainSearchResultVoList.add(mainSearchResultVo);
        }


        return new MainSearchResultListVo(mainSearchResultVoList);
    }
}
