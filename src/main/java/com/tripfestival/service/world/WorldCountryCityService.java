package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.dto.world.WorldCountryCityNameModifyDto;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryNotFoundException;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryCityProcessRequest;
import com.tripfestival.service.file.FileService;
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

    public ResponseVo worldCountryCityInsert(MultipartFile file, WorldCountryCityProcessRequest req) {
        WorldCountry worldCountry = worldCountryRepository.findById(req.getWorldCountryId())
                .orElseThrow(() -> new WorldCountryNotFoundException());

        WorldCountryCity worldCountryCity = WorldCountryCity.builder()
                .name(req.getName())
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

    public ResponseVo worldCountryCityNameAlert(WorldCountryCityNameModifyDto req) {
        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        worldCountryCity.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }
}
