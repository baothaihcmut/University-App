package com.universityapp.common.entities;

import java.util.UUID;

import com.universityapp.common.enums.DayOfWeek;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseScheduleId {
    private UUID courseId;
    private DayOfWeek dayOfWeek;
    private Integer session;

}
