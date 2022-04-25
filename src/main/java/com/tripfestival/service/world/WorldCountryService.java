package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.dto.world.WorldCountryModifyDto;
import com.tripfestival.exception.world.WorldCountryNotFoundException;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryModifyRequest;
import com.tripfestival.request.world.WorldCountryProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryNameListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryService {
    private final WorldCountryRepository worldCountryRepository;

    private final FileService fileService;

    public ResponseVo worldCountryInsert(MultipartFile file, WorldCountryProcessRequest req) {

        WorldCountry worldCountry = WorldCountry.builder()
                .name(req.getName())
                .flagImg(fileService.s3UploadProcess(file))
                .currency("")
                .capital("")
                .exchangeRatio("")
                .build();

        worldCountryRepository.save(worldCountry);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo worldCountryDelete(Long worldCountryId) {
        WorldCountry worldCountry = worldCountryRepository.findById(worldCountryId)
                        .orElseThrow(() -> new WorldCountryNotFoundException());

        worldCountryRepository.delete(worldCountry);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo worldCountryAlert(WorldCountryModifyDto req) {
        WorldCountry worldCountry = worldCountryRepository.findById(req.getWorldCountryId())
                .orElseThrow(() -> new WorldCountryNotFoundException());

        if(req.getName() != null) {
            worldCountry.setName(req.getName());
        }
        if(req.getCapital() != null) {
            worldCountry.setCapital(req.getCapital());
        }
        if(req.getCurrency() != null) {
            worldCountry.setCurrency(req.getCurrency());
        }
        if(req.getExchangeRatio() != null) {
            worldCountry.setExchangeRatio(req.getExchangeRatio());
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    public WorldCountryNameListVo worldCountryNameListSelect() {
        List<WorldCountry> worldCountryList = worldCountryRepository.findAll();

        return new WorldCountryNameListVo(worldCountryList);
    }
}
