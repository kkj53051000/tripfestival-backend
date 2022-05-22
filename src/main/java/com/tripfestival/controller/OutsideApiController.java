package com.tripfestival.controller;

import com.tripfestival.service.api.DataApiService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OutsideApiController {
    private final DataApiService dataApiService;


    @GetMapping("/updateCountryCityKorea")
    public ResponseVo updateCountryCityKorea() throws IOException, ParserConfigurationException, SAXException {
        return dataApiService.updateCountryCityKorea();
    }

    @GetMapping("/updateCountryCityRegionKorea")
    public ResponseVo updateCountryCityRegionKorea() throws IOException, ParserConfigurationException, SAXException {
        return dataApiService.updateCountryCityRegionKorea();
    }

    @GetMapping("/updateLandmarkKorea")
    public ResponseVo updateLandmarkKorea() throws IOException, ParserConfigurationException, SAXException {
        return dataApiService.updateLandmarkKorea();
    }

    @GetMapping("/updateLandmarkDescriptionKorea")
    public ResponseVo updateLandmarkDescriptionKorea() throws IOException, ParserConfigurationException, SAXException {
        return dataApiService.updateLandmarkDescriptionKorea();
    }

    @GetMapping("/updateLandmarkImgKorea")
    public ResponseVo updateLandmarkImgKorea() throws IOException, ParserConfigurationException, SAXException {
        return dataApiService.updateLandmarkImgKorea();
    }
}
