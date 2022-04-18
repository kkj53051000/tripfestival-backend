package com.tripfestival.service;

import com.tripfestival.domain.WorldCountry;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.exception.WorldCountryCityNotFoundException;
import com.tripfestival.exception.WorldCountryNotFoundException;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.repository.WorldCountryRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryCityService {
    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryRepository worldCountryRepository;

    private final FileService fileService;

    public ResponseVo worldCountryCityInsert(Long worldCountryId, MultipartFile file, String name) {
        WorldCountry worldCountry = worldCountryRepository.findById(worldCountryId)
                .orElseThrow(() -> new WorldCountryNotFoundException());

        WorldCountryCity worldCountryCity = WorldCountryCity.builder()
                .name(name)
                .cityImg(fileService.s3UploadProcess(file))
                .worldCountry(worldCountry)
                .build();

        worldCountryCityRepository.save(worldCountryCity);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo worldCountryCityDelete(Long worldCountryCityId) {

        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(worldCountryCityId)
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        worldCountryCityRepository.delete(worldCountryCity);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
