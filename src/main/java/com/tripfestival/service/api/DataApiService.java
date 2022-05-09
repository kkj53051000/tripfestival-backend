package com.tripfestival.service.api;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryNotFoundException;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@Service
@Transactional
@RequiredArgsConstructor

public class DataApiService {

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryRepository worldCountryRepository;

    @PostConstruct
    public void init() {
        if (worldCountryRepository.findByName("대한민국").get() == null) {
            WorldCountry worldCountry = WorldCountry.builder()
                    .name("대한민국")
                    .build();

            worldCountryRepository.save(worldCountry);
        }
    }

    public void updateCountryCity() throws IOException, ParserConfigurationException, SAXException {

        WorldCountry worldCountry = worldCountryRepository.findByName("대한민국")
                .orElseThrow(() -> new WorldCountryNotFoundException());

        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=RSm0QZjj2ewThGrdHHwMIFHBfrgiuKexycgJee4lzm%2FmqxnwzwVTdBEluuLuldFxz%2BSNVdDkRdHbU5x1QHqxCA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*지역코드, 시군구코드*/
        URL url = new URL(urlBuilder.toString());

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(url.toString());

        NodeList nList = doc.getElementsByTagName("item");

        for(int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                System.out.println("######################");

                System.out.println("코드  : " + getTagValue("code", eElement));
                System.out.println("이름  : " + getTagValue("name", eElement));

                // DB에 이미 있는지 확인
                WorldCountryCity checkWorldCountryCity = worldCountryCityRepository.findByName(getTagValue("name", eElement))
                        .orElseThrow(() -> new WorldCountryCityNotFoundException());

                if(checkWorldCountryCity == null) {
                    WorldCountryCity worldCountryCity = WorldCountryCity.builder()
                            .name(getTagValue("name", eElement))
                            .areaCode(Integer.valueOf(getTagValue("code", eElement)))
                            .worldCountry(worldCountry)
                            .build();

                    worldCountryCityRepository.save(worldCountryCity);
                }

            }
        }

    }

    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
