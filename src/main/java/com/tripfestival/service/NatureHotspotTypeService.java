package com.tripfestival.service;

import com.tripfestival.domain.NatureHotspotType;
import com.tripfestival.dto.NatureHotspotTypeDto;
import com.tripfestival.exception.NatureHotspotTypeNotFoundException;
import com.tripfestival.repository.NatureHotspotTypeRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NatureHotspotTypeService {
    private final NatureHotspotTypeRepository natureHotspotTypeRepository;

    private final FileService fileService;

    public ResponseVo natureHotspotTypeInsert(NatureHotspotTypeDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        NatureHotspotType natureHotspotType = NatureHotspotType.builder()
                .name(req.getName())
                .img(url)
                .build();

        natureHotspotTypeRepository.save(natureHotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo natureHotspotTypeDelete(Long natureHotspotTypeId) {
        NatureHotspotType natureHotspotType = natureHotspotTypeRepository.findById(natureHotspotTypeId)
                .orElseThrow(() -> new NatureHotspotTypeNotFoundException());

        natureHotspotTypeRepository.delete(natureHotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
