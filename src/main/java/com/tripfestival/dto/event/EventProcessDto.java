package com.tripfestival.dto.event;

import com.tripfestival.request.event.EventProcessRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class EventProcessDto {
    private MultipartFile file;
    private String startDate;
    private String endDate;
    private String name;
    private String description;
    private String address;
    private int visitor;
    private int inout;
    private Long worldCountryCityRegionId;
    private Long eventCategoryId;
    private Long eventSeasonId;

    public EventProcessDto(MultipartFile file, EventProcessRequest req) {
        this.file = file;
        this.startDate = req.getStartDate();
        this.endDate = req.getEndDate();
        this.name = req.getName();
        this.description = req.getDescription();
        this.address = req.getAddress();
        this.visitor = req.getVisitor();
        this.inout = req.getInout();
        this.worldCountryCityRegionId = req.getWorldCountryCityRegionId();
        this.eventCategoryId = req.getEventCategoryId();
        this.eventSeasonId = req.getEventSeasonId();
    }
}
