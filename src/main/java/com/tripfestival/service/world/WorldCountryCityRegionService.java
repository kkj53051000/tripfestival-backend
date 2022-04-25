package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.world.WorldCountryCityRegionNameModifyDto;
import com.tripfestival.dto.world.WorldCountryCityRegionProcessDto;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryCityRegionService {
    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final FileService fileService;

    public ResponseVo worldCountryCityRegionInsert(WorldCountryCityRegionProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name(req.getName())
                .img(url)
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
}
