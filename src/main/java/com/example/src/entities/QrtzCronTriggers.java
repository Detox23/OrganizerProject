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
        name= "qrtz_cron_triggers")
public class QrtzCronTriggers implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="trigger_name", nullable = false)
    private String triggerName;

    @Id
    @Column(name="trigger_group", nullable = false)
    private String triggerGroup;

    @Column(name="cron_expression", nullable = false)
    private String cronExpression;

    @Column(name="time_zone_id", nullable = false)
    private String timeZoneId;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="sched_name", referencedColumnName = "sched_name"),
            @JoinColumn(name="trigger_name", referencedColumnName = "trigger_name"),
            @JoinColumn(name="trigger_group", referencedColumnName = "trigger_group"),

    })
    private QrtzTriggers qrtzTriggers;
}