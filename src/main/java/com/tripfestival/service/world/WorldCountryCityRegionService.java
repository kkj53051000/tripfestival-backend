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
import com.tripfestival.vo.world.WorldCountryCityRegionNameVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorldCountryCityRegionService {
    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final FileService fileService;

    @Transactional
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

    @Transactional
    public ResponseVo worldCountryCityRegionDelete(Long worldCountryCityId) {
        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(worldCountryCityId)
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

        worldCountryCityRegionRepository.delete(worldCountryCityRegion);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo worldCountryCityRegionNameAlert(WorldCountryCityRegionNameModifyDto req) {
        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());


        worldCountryCityRegion.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public WorldCountryCityRegionListVo worldCountryCityRegionListSelect(Long worldCountryCityId) {
        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(worldCountryCityId)
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findByWorldCountryCity(worldCountryCity);

        if(worldCountryCityRegionList.size() == 0) {
            throw new WorldCountryCityRegionNotFoundException();
        }

        return new WorldCountryCityRegionListVo(worldCountryCityRegionList);
    }

    @Transactional(readOnly = true)
    public WorldCountryCityRegionListVo worldCountryCityRegionAllListSelect() {

        List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findAll();

        return new WorldCountryCityRegionListVo(worldCountryCityRegionList);
    }

    @Transactional(readOnly = true)
    public WorldCountryCityRegionNameVo worldCountryCityRegionNameSelect(Long worldCountryCityRegionId) {
        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(worldCountryCityRegionId)
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

        WorldCountryCityRegionNameVo worldCountryCityRegionNameVo = WorldCountryCityRegionNameVo.builder()
                .worldCountryCityRegionName(worldCountryCityRegion.getName())
                .build();

        return worldCountryCityRegionNameVo;
    }
}
