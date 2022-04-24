package com.tripfestival.service.hotsight;

import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.dto.hotSight.HotSightOneImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightOneNameModifyDto;
import com.tripfestival.dto.hotSight.HotSightOneProcessDto;
import com.tripfestival.exception.hotSight.HotSightOneNotFoundException;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.HotSightOneListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightOneService {
    private final HotSightOneRepository hotSightOneRepository;

    private final FileService fileService;

    public ResponseVo hotSightOneInsert(HotSightOneProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        HotSightOne hotSightOne = HotSightOne.builder()
                .img(url)
                .name(req.getName())
                .build();

        hotSightOneRepository.save(hotSightOne);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightOneDelete(Long hotSightOneId) {
        HotSightOne hotSightOne = hotSightOneRepository.findById(hotSightOneId)
                .orElseThrow(() -> new HotSightOneNotFoundException());

        hotSightOneRepository.delete(hotSightOne);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightOneNameAlert(HotSightOneNameModifyDto req) {
        HotSightOne hotSightOne = hotSightOneRepository.findById(req.getHotSightOneId())
                .orElseThrow(() -> new HotSightOneNotFoundException());

        hotSightOne.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightOneImgAlert(HotSightOneImgModifyDto req) {

        String url = fileService.s3UploadProcess(req.getFile());

        HotSightOne hotSightOne = hotSightOneRepository.findById(req.getHotSightOneId())
                .orElseThrow(() -> new HotSightOneNotFoundException());

        hotSightOne.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public HotSightOneListVo hotSightOneAllListSelect() {
        List<HotSightOne> hotSightOneList = hotSightOneRepository.findAll();

        return new HotSightOneListVo(hotSightOneList);
    }
}
