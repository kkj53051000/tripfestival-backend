package com.tripfestival.service;

import com.tripfestival.domain.EventSeason;
import com.tripfestival.dto.EventSeasonImgModifyDto;
import com.tripfestival.dto.EventSeasonNameModifyDto;
import com.tripfestival.exception.EventSeasonNotFoundException;
import com.tripfestival.repository.EventSeasonRepository;
import com.tripfestival.request.EventSeasonNameModifyRequest;
import com.tripfestival.request.EventSeasonProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class EventSeasonService {
    private final EventSeasonRepository eventSeasonRepository;

    private final FileService fileService;

    public ResponseVo eventSeasonInsert(MultipartFile file, EventSeasonProcessRequest req) {
        String url = fileService.s3UploadProcess(file);

        EventSeason eventSeason = EventSeason.builder()
                .name(req.getName())
                .img(url)
                .build();

        eventSeasonRepository.save(eventSeason);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventSeasonDelete(Long eventSeasonId) {
        EventSeason eventSeason = eventSeasonRepository.findById(eventSeasonId)
                .orElseThrow(() -> new EventSeasonNotFoundException());

        eventSeasonRepository.delete(eventSeason);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventSeasonNameAlert(EventSeasonNameModifyDto req) {
        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        eventSeason.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventSeasonImgAlert(EventSeasonImgModifyDto req) {
        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        String url = fileService.s3UploadProcess(req.getFile());

        eventSeason.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
