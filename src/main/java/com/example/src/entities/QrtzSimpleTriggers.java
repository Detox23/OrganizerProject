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
        name= "qrtz_simple_triggers")
public class QrtzSimpleTriggers implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="trigger_name", nullable = false)
    private String triggerName;

    @Id
    @Column(name="trigger_group", nullable = false)
    private String triggerGroup;

    @Column(name="repeat_count", nullable = false)
    private long repeatCount;

    @Column(name="repeat_interval", nullable = false)
    private long repeatInterval;

    @Column(name="times_triggered", nullable = false)
    private long timesTriggered;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="sched_name", referencedColumnName = "sched_name"),
            @JoinColumn(name="trigger_name", referencedColumnName = "trigger_name"),
            @JoinColumn(name="trigger_group", referencedColumnName = "trigger_group"),

    })
    private QrtzTriggers qrtzTriggers;
}
