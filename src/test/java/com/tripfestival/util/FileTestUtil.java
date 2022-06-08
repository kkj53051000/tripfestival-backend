package com.tripfestival.util;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTestUtil {

    public static String FILE_PATH = "resources" + File.separator + "img" + File.separator + "test.png";

    public static MockMultipartFile getMockMultipartFile() {

        MockMultipartFile mockMultipartFile = null;
        try {
            MultipartFile multipartFile = new MockMultipartFile("test.png", new FileInputStream(new File(FILE_PATH)));

            mockMultipartFile = new MockMultipartFile(
                    "file",
                    "test.png",
                    String.valueOf(MediaType.IMAGE_PNG),
                    multipartFile.getBytes()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mockMultipartFile;
    }

    public static List<MultipartFile> getMockMultipartFileList() {
        List<MultipartFile> mockMultipartFileList = new ArrayList<>();

        try {
            MultipartFile multipartFile = new MockMultipartFile("test.png", new FileInputStream(new File(FILE_PATH)));

            MockMultipartFile mockMultipartFile1 = new MockMultipartFile(
                    "file",
                    "test.png",
                    String.valueOf(MediaType.IMAGE_PNG),
                    multipartFile.getBytes());
            mockMultipartFileList.add(mockMultipartFile1);

            MockMultipartFile mockMultipartFile2 = new MockMultipartFile(
                    "file",
                    "test.png",
                    String.valueOf(MediaType.IMAGE_PNG),
                    multipartFile.getBytes());
            mockMultipartFileList.add(mockMultipartFile2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mockMultipartFileList;
    }
}
