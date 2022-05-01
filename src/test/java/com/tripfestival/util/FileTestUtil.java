package com.tripfestival.util;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTestUtil {

    public static MockMultipartFile getMockMultipartFile() {
        String filePath = "resources" + File.separator + "img" + File.separator + "test.png";

        MockMultipartFile mockMultipartFile = null;
        try {
            MultipartFile multipartFile = new MockMultipartFile("test.png", new FileInputStream(new File(filePath)));

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
}
