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
        name= "qrtz_scheduler_state")
public class QrtzSchedulerState implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="instance_name", nullable = false)
    private String instanceName;

    @Column(name="last_checkin_time", nullable = false)
    private long lastCheckinTime;

    @Column(name="checkin_interval", nullable = false)
    private long checkinInterval;
}