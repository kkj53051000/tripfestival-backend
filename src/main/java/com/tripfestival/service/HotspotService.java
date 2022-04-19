package com.tripfestival.service;

import com.tripfestival.domain.Hotspot;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.dto.HotspotProcessDto;
import com.tripfestival.exception.HotspotNotFoundException;
import com.tripfestival.exception.WorldCountryCityNotFoundException;
import com.tripfestival.repository.HotspotRepository;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.request.HotspotProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotspotService {
    private final HotspotRepository hotspotRepository;

    private final FileService fileService;

    private WorldCountryCityRepository worldCountryCityRepository;

    public ResponseVo hotspotInsert(HotspotProcessDto req) {
        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        String url = fileService.s3UploadProcess(req.getFile());

        Hotspot hotspot = Hotspot.builder()
                .name(req.getName())
                .img(url)
                .worldCountryCity(worldCountryCity)
                .build();

        hotspotRepository.save(hotspot);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotspotDelete(Long hotspotId) {
        Hotspot hotspot = hotspotRepository.findById(hotspotId)
                .orElseThrow(() -> new HotspotNotFoundException());

        hotspotRepository.delete(hotspot);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
