package com.tripfestival.controller;

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
}
