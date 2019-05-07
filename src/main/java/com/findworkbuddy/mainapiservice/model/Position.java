package com.findworkbuddy.mainapiservice.model;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private Integer id;
    private Integer companyId;
    private String title;
    private String summary;
    private DateTime startDate;
    private DateTime endDate;
    private boolean isCurrent;
}
