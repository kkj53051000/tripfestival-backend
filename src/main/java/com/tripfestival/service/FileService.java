package com.tripfestival.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secrect-key}")
    private String secrectKey;

    public List<String> s3UploadProcess(List<MultipartFile> files)  {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secrectKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            InputStream fis = null;
            try {
                fis = new BufferedInputStream(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/jpeg");

            String uuid = UUID.randomUUID().toString();

            String fileName = uuid + file.getOriginalFilename();

            PutObjectRequest putObjectRequest = new PutObjectRequest("tripfestival-test", fileName, fis, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);



            try {
                s3Client.putObject(putObjectRequest);

                String url = String.format("https://%s.s3.ap-northeast-2.amazonaws.com/%s", "tripfestival-test", fileName);
                urls.add(url);

            } catch (AmazonServiceException e) {
                System.err.println(e.getErrorMessage());
                System.exit(1);
            }
        }
        return urls;
    }
}
