package com.tripfestival.service.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.dto.naturehotspot.NatureHotspotTypeDto;
import com.tripfestival.exception.naturehotspot.NatureHotspotTypeNotFoundException;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotTypeAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NatureHotspotTypeService {
    private final NatureHotspotTypeRepository natureHotspotTypeRepository;

    private final FileService fileService;

    @Transactional
    public ResponseVo natureHotspotTypeInsert(NatureHotspotTypeDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        NatureHotspotType natureHotspotType = NatureHotspotType.builder()
                .name(req.getName())
                .img(url)
                .build();

        natureHotspotTypeRepository.save(natureHotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo natureHotspotTypeDelete(Long natureHotspotTypeId) {
        NatureHotspotType natureHotspotType = natureHotspotTypeRepository.findById(natureHotspotTypeId)
                .orElseThrow(() -> new NatureHotspotTypeNotFoundException());

        natureHotspotTypeRepository.delete(natureHotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public NatureHotspotTypeAllListVo natureHotspotTypeAllSelect() {
        List<NatureHotspotType> natureHotspotTypeList = natureHotspotTypeRepository.findAll();

        return new NatureHotspotTypeAllListVo(natureHotspotTypeList);
    }
}
