package com.tripfestival.controller;

import com.tripfestival.exception.LandmarkFeeNotFoundException;
import com.tripfestival.exception.LandmarkImgNotFoundException;
import com.tripfestival.exception.LandmarkNotFoundException;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
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
}
