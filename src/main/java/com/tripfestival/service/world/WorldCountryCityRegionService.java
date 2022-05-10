package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.world.WorldCountryCityRegionNameModifyDto;
import com.tripfestival.dto.world.WorldCountryCityRegionProcessDto;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.world.WorldCountryCityRegionListRequest;
import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.WorldCountryCityRegionListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryCityRegionService {
    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final FileService fileService;

    public ResponseVo worldCountryCityRegionInsert(WorldCountryCityRegionProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name(req.getName())
                .img(url)
                .worldCountryCity(worldCountryCity)
                .build();

        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo worldCountryCityRegionDelete(Long worldCountryCityId) {
        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(worldCountryCityId)
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

        worldCountryCityRegionRepository.delete(worldCountryCityRegion);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo worldCountryCityRegionNameAlert(WorldCountryCityRegionNameModifyDto req) {
        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityId())
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());


        worldCountryCityRegion.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public WorldCountryCityRegionListVo worldCountryCityRegionSelect(Long worldCountryCityId) {
        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(worldCountryCityId)
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findByWorldCountryCity(worldCountryCity);

        if(worldCountryCityRegionList.size() == 0) {
            throw new WorldCountryCityRegionNotFoundException();
        }

        return new WorldCountryCityRegionListVo(worldCountryCityRegionList);
    }

    public WorldCountryCityRegionListVo worldCountryCityRegionListSelect() {

        List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findAll();

        return new WorldCountryCityRegionListVo(worldCountryCityRegionList);
    }
}
