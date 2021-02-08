package com.example.src.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(
        schema = "public",
        name= "qrtz_calendars")
public class QrtzCalendars implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="calendar_name", nullable = false)
    private String calendarName;

    @Column(name="calendar", nullable = false)
    private byte[] calendar;

}
