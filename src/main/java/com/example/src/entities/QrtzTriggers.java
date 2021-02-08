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
        name= "qrtz_triggers",
        indexes = {
                @Index(name="idx_qrtz_t_j", columnList = "sched_name,job_name,job_group"),
                @Index(name="idx_grtz_t_jg", columnList = "sched_name,job_group"),
                @Index(name="idx_grtz_t_c", columnList = "sched_name,calendar_name"),
                @Index(name="idx_grtz_t_g", columnList = "sched_name,trigger_group"),
                @Index(name="idx_grtz_t_state", columnList = "sched_name,trigger_state"),
                @Index(name="idx_grtz_t_n_state", columnList = "sched_name,trigger_name,trigger_group,trigger_state"),
                @Index(name="idx_grtz_t_n_g_state", columnList = "sched_name,trigger_group,trigger_state"),
                @Index(name="idx_grtz_t_next_fire_time", columnList = "sched_name,next_fire_time"),
                @Index(name="idx_grtz_t_nft_st", columnList = "sched_name,trigger_state,next_fire_time"),
                @Index(name="idx_grtz_t_nft_misfire", columnList = "sched_name,misfire_instr,next_fire_time"),
                @Index(name="idx_grtz_t_nft_st_misfire", columnList = "sched_name,misfire_instr,next_fire_time,trigger_state"),
                @Index(name="idx_grtz_t_nft_st_misfire_grp", columnList = "sched_name,misfire_instr,next_fire_time,trigger_group,trigger_state")
        })
public class QrtzTriggers implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="trigger_name", nullable = false)
    private String triggerName;

    @Id
    @Column(name="trigger_group", nullable = false)
    private String triggerGroup;

    @Column(name="job_name", insertable = false, updatable = false, nullable = false)
    private String jobName;

    @Column(name="job_group", insertable = false, updatable = false, nullable = false)
    private String jobGroup;

    @Column(name="description")
    private String description;

    @Column(name="next_fire_time")
    private long nextFireTime;

    @Column(name="prev_fire_time")
    private long prevFireTime;

    @Column(name="priority")
    private int priority;

    @Column(name="trigger_state", nullable = false)
    private String triggerState;

    @Column(name="trigger_type", nullable = false)
    private String triggerType;

    @Column(name="start_time", nullable = false)
    private long startTime;

    @Column(name="end_time")
    private long endTime;

    @Column(name="calendar_name")
    private String calendarName;

    @Column(name="misfire_instr")
    private short misfireInstr;

    @Column(name="job_data")
    private byte[] jobData;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "sched_name", referencedColumnName = "sched_name"),
            @JoinColumn(name = "job_name",  referencedColumnName = "job_name"),
            @JoinColumn(name = "job_group", referencedColumnName = "job_group")
    })
    private QrtzJobDetails qrtzJobDetails;

}
