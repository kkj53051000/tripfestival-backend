package com.tripfestival.service.api;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkImg;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.exception.world.WorldCountryNotFoundException;
import com.tripfestival.repository.landmark.LandmarkImgRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DataApiService {

    String serviceName = "TripFestival";
    @Value("${data.go.kr.service.key}")
    String serviceKey;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryRepository worldCountryRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final LandmarkRepository landmarkRepository;

    private final LandmarkImgRepository landmarkImgRepository;

    @PostConstruct
    public void insertWorldCountry() {
        if (!worldCountryRepository.findByName("대한민국").isPresent()) {
            WorldCountry worldCountry = WorldCountry.builder()
                    .name("대한민국")
                    .build();

            worldCountryRepository.save(worldCountry);
        }
    }

    public ResponseVo updateCountryCityKorea() throws IOException, ParserConfigurationException, SAXException {

        WorldCountry worldCountry = worldCountryRepository.findByName("대한민국")
                .orElseThrow(() -> new WorldCountryNotFoundException());

        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode(serviceName, "UTF-8")); /*서비스명=어플명*/
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
                Boolean checkWorldCountryCity = worldCountryCityRepository.findByName(getTagValue("name", eElement))
                        .isPresent();

                if(!checkWorldCountryCity) {
                    WorldCountryCity worldCountryCity = WorldCountryCity.builder()
                            .name(getTagValue("name", eElement))
                            .areaCode(Integer.valueOf(getTagValue("code", eElement)))
                            .worldCountry(worldCountry)
                            .build();

                    worldCountryCityRepository.save(worldCountryCity);
                }

            }
        }
        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo updateCountryCityRegionKorea() throws IOException, ParserConfigurationException, SAXException {


        List<WorldCountryCity> worldCountryCityList = worldCountryCityRepository.findAll();


        if(worldCountryCityList.size() == 0) {
            throw new WorldCountryCityNotFoundException();
        }

        for (WorldCountryCity worldCountryCity : worldCountryCityList) {
            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode(serviceName, "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(Integer.toString(worldCountryCity.getAreaCode()), "UTF-8")); /*지역코드, 시군구코드*/
            URL url = new URL(urlBuilder.toString());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url.toString());

            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    int areaCode = Integer.parseInt(getTagValue("code", eElement));
                    String name = getTagValue("name", eElement);

                    Boolean checkWorldCountryCityRegion = worldCountryCityRegionRepository.findByNameAndWorldCountryCity(name, worldCountryCity)
                            .isPresent();

                    if(!checkWorldCountryCityRegion) {
                        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder()
                                .name(name)
                                .areaCode(areaCode)
                                .worldCountryCity(worldCountryCity)
                                .build();

                        worldCountryCityRegionRepository.save(worldCountryCityRegion);
                    }
                }

            }
        }
        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo updateLandmarkKorea() throws IOException, ParserConfigurationException, SAXException {

        List<WorldCountryCity> worldCountryCityList = worldCountryCityRepository.findAll();

        if(worldCountryCityList.size() == 0) {
            throw new WorldCountryCityNotFoundException();
        }

        for (WorldCountryCity worldCountryCity : worldCountryCityList) {

            List<WorldCountryCityRegion> worldCountryCityRegionList = worldCountryCityRegionRepository.findByWorldCountryCity(worldCountryCity);

            if (worldCountryCityList.size() == 0) {
                throw new WorldCountryCityRegionNotFoundException();
            }

            for (WorldCountryCityRegion worldCountryCityRegion : worldCountryCityRegionList) {
                String areaCode = String.valueOf(worldCountryCity.getAreaCode());
                String sigunguCode = String.valueOf(worldCountryCityRegion.getAreaCode());
                StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000000", "UTF-8")); /*한 페이지 결과수*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
                urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
                urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode(serviceName, "UTF-8")); /*서비스명=어플명*/
                urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8")); /*제목순 정렬*/
                urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
                urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
                urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8")); /*지역코드*/
                urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode(sigunguCode, "UTF-8")); /*시군구코드(areaCode 필수)*/

                URL url = new URL(urlBuilder.toString());

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url.toString());

                NodeList nList = doc.getElementsByTagName("item");

                for(int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        // 관광지 정보 Insert
                        String title = getTagValue("title", eElement);
                        String address = getTagValue("addr1", eElement);
                        String img = getTagValue("firstimage", eElement);
                        String contentId = getTagValue("contentid", eElement);

                        Landmark landmark = Landmark.builder()
                                .name(title)
                                .img(img)
                                .address(address)
                                .contentId(contentId)
                                .worldCountryCityRegion(worldCountryCityRegion)
                                .build();

                        landmarkRepository.save(landmark);
                    }
                }
            }
        }
        return new ResponseVo(Response.SUCCESS, null);
    }


    public ResponseVo updateLandmarkDescriptionKorea() throws IOException, ParserConfigurationException, SAXException {
        List<Landmark> landmarkList = landmarkRepository.findAll();

        if(landmarkList.size() == 0) {
            throw new LandmarkNotFoundException();
        }

        for (Landmark landmark : landmarkList) {
            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode(serviceName, "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("overviewYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*콘텐츠 개요 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(landmark.getContentId(), "UTF-8")); /*콘텐츠ID*/


            URL url = new URL(urlBuilder.toString());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url.toString());

            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    // Landmark Description Add
                    String description = getTagValue("overview", eElement);
                    System.out.println(description);
                    landmark.setDescription(description);
                }
            }
        }
        return new ResponseVo(Response.SUCCESS, null);
    }


    public ResponseVo updateLandmarkImgKorea() throws IOException, ParserConfigurationException, SAXException {

        List<Landmark> landmarkList = landmarkRepository.findAll();

        if(landmarkList.size() == 0) {
            throw new LandmarkNotFoundException();
        }

        for (Landmark landmark : landmarkList) {
            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode(serviceName, "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("imageYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*Y=콘텐츠 이미지 조회, N='음식점'타입의 음식메뉴 이미지*/
            urlBuilder.append("&" + URLEncoder.encode("subImageYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*Y=원본,썸네일 이미지, 조회 N=Null*/
            urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(landmark.getContentId(), "UTF-8")); /*콘텐츠 ID*/

            URL url = new URL(urlBuilder.toString());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url.toString());

            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String img = getTagValue("originimgurl", eElement);

                    LandmarkImg landmarkImg = LandmarkImg.builder()
                            .img(img)
                            .landmark(landmark)
                            .build();

                    landmarkImgRepository.save(landmarkImg);
                }
            }

        }

        return new ResponseVo(Response.SUCCESS, null);
    }


    private static String getTagValue(String tag, Element eElement) {

        if(eElement.getElementsByTagName(tag).item(0) != null) {
            NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);

            return nValue.getNodeValue();
        }else {
            return null;
        }


//        if(nValue == null)
//            return null;

    }
}
