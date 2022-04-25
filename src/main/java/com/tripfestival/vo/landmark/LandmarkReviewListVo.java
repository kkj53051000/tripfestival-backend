package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkReview;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkReviewListVo {

    private List<LandmarkReviewVo> items = null;

    public LandmarkReviewListVo(List<LandmarkReview> landmarkReviewList) {
        items = landmarkReviewList.stream()
                .map(landmarkReview -> new LandmarkReviewVo(landmarkReview))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkReviewVo {
        private String content;
        private Byte score;
        private String userImg;
        private String nickname;

        public LandmarkReviewVo(LandmarkReview landmarkReview) {
            this.content = landmarkReview.getContent();
            this.score = landmarkReview.getScore();
            this.userImg = landmarkReview.getUser().getUserImg();
            this.nickname = landmarkReview.getUser().getNickname();
        }
    }
}
