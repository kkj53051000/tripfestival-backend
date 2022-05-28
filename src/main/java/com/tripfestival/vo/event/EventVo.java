package com.tripfestival.vo.event;

import com.tripfestival.domain.event.Event;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class EventVo {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String img;
    private String description;
    private String address;
    private Integer visitor;
    private Boolean inout;

    public EventVo(Event event) {
        this.id = event.getId();
        this.name = event.getName();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDateStr = event.getStartDate().format(formatter);
        this.startDate = startDateStr;

        String endDateStr = event.getEndDate().format(formatter);
        this.endDate = endDateStr;

        this.img = event.getImg();
        this.description = event.getDescription();
        this.address = event.getAddress();
        this.visitor = event.getVisitor();
        this.inout = event.getInout();
    }
}
