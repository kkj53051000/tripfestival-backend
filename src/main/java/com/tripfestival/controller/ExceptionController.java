package com.tripfestival.controller;

import com.tripfestival.exception.JwtVerifyFailException;
import com.tripfestival.exception.event.*;
import com.tripfestival.exception.hotSight.HotSightLandmarkNotFoundException;
import com.tripfestival.exception.hotSight.HotSightOneNotFoundException;
import com.tripfestival.exception.hotSight.HotSightTwoNotFoundException;
import com.tripfestival.exception.hotspot.HotspotNotFoundException;
import com.tripfestival.exception.hotspot.HotspotTypeNotFoundException;
import com.tripfestival.exception.landmark.*;
import com.tripfestival.exception.naturehotspot.NatureHotspotNotFoundException;
import com.tripfestival.exception.naturehotspot.NatureHotspotTypeNotFoundException;
import com.tripfestival.exception.user.UserNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.exception.world.WorldCountryNotFoundException;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(LandmarkNotFoundException.class)
    public ResponseVo landmarkNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "LandmarkNotFound");
    }

    @ExceptionHandler(LandmarkImgNotFoundException.class)
    public ResponseVo landmarkImgNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "LandmarkImgNotFound");
    }

    @ExceptionHandler(LandmarkFeeNotFoundException.class)
    public ResponseVo landmarkFeeNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "LandmarkFeeNotFound");
    }

    @ExceptionHandler(LandmarkTimeNotFoundException.class)
    public ResponseVo landmarkTimeNotFoundException() {
        return new ResponseVo(Response.FAILURE, "LandmarkTimeNotFound");
    }

    @ExceptionHandler(WorldCountryNotFoundException.class)
    public ResponseVo worldCountryNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "WorldCountryNotFound");
    }

    @ExceptionHandler(WorldCountryCityNotFoundException.class)
    public ResponseVo worldCountryCityNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "WorldCountryCityNotFound");
    }

    @ExceptionHandler(EventCategoryNotFoundException.class)
    public ResponseVo eventCategoryNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventCategoryNotFound");
    }

    @ExceptionHandler(EventSeasonNotFoundException.class)
    public ResponseVo eventSeasonNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventSeasonNotFound");
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseVo eventNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventNotFound");
    }

    @ExceptionHandler(EventImgNotFoundException.class)
    public ResponseVo eventImgNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventImgNotFound");
    }

    @ExceptionHandler(LandmarkReviewNotFoundException.class)
    public ResponseVo landmarkReviewNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "LandmarkReviewNotFound");
    }

    @ExceptionHandler(LandmarkReviewImgNotFoundException.class)
    public ResponseVo landmarkReviewImgNotFoundHadnelr() {
        return new ResponseVo(Response.FAILURE, "LandmarkReviewImgNotFound");
    }

    @ExceptionHandler(EventTimeNotFoundException.class)
    public ResponseVo eventTimeNotFoundHandelr() {
        return new ResponseVo(Response.FAILURE, "EventTimeNotFound");
    }

    @ExceptionHandler(EventFeeNotFoundException.class)
    public ResponseVo eventFeeNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventFeeNotFound");
    }

    @ExceptionHandler(EventReviewNotFoundException.class)
    public ResponseVo eventReviewNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventReviewNotFound");
    }

    @ExceptionHandler(EventReviewImgNotFoundException.class)
    public ResponseVo eventReviewImgNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventReviewImgNotFound");
    }

    @ExceptionHandler(HotspotNotFoundException.class)
    public ResponseVo hotspotNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "HotspotNotFound");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseVo userNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "UserNotFound");
    }

    @ExceptionHandler(HotspotTypeNotFoundException.class)
    public ResponseVo hotspotTypeNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "HotspotTypeNotFound");
    }

    @ExceptionHandler(NatureHotspotTypeNotFoundException.class)
    public ResponseVo natureHotspotTypeNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "NatureHotspotTypeNotFound");
    }

    @ExceptionHandler(NatureHotspotNotFoundException.class)
    public ResponseVo natureHotspotNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "NatureHotspotNotFound");
    }

    @ExceptionHandler(HotSightOneNotFoundException.class)
    public ResponseVo hotSightOneNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "HotSightOneNotFound");
    }

    @ExceptionHandler(HotSightTwoNotFoundException.class)
    public ResponseVo hotSightTwoNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "HotSightTwoNotFound");
    }

    @ExceptionHandler(HotSightLandmarkNotFoundException.class)
    public ResponseVo hotSightLandmarkNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "HotSightLandmarkNotFound");
    }

    @ExceptionHandler(WorldCountryCityRegionNotFoundException.class)
    public ResponseVo worldCountryCityRegionNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "WorldCountryCityRegionNotFound");
    }

    @ExceptionHandler(EventHashTagNotFoundException.class)
    public ResponseVo eventHashTagNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "EventHashTagNotFound");
    }

    @ExceptionHandler(LandmarkHashTagNotFoundException.class)
    public ResponseVo landmarkHashTagNotFoundHandler() {
        return new ResponseVo(Response.FAILURE, "LandmarkHashTagNotFound");
    }

    @ExceptionHandler(JwtVerifyFailException.class)
    public ResponseVo jwtVerifyFailHandler() {
        return new ResponseVo(Response.FAILURE, "JwtVerifyFail");
    }
}
