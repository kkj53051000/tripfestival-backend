package com.tripfestival.service;

import com.tripfestival.domain.HotSightOne;
import com.tripfestival.dto.HotSightOneImgModifyDto;
import com.tripfestival.dto.HotSightOneNameModifyDto;
import com.tripfestival.dto.HotSightOneProcessDto;
import com.tripfestival.exception.HotSightOneNotFoundException;
import com.tripfestival.repository.HotSightOneRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
