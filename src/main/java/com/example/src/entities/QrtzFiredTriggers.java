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
        name= "qrtz_fired_triggers",
        indexes = {
                @Index(name="idx_qrtz_ft_trig_inst_name", columnList = "sched_name,instance_name", unique = false),
                @Index(name="idx_grtz_ft_inst_job_req_rcvry", columnList = "sched_name,instance_name,requests_recovery", unique = false),
                @Index(name="idx_grtz_ft_j_g", columnList = "sched_name,job_name,job_group", unique = false),
                @Index(name="idx_grtz_ft_jg", columnList = "sched_name,job_group", unique = false),
                @Index(name="idx_grtz_ft_t_g", columnList = "sched_name,trigger_name,trigger_group", unique = false),
                @Index(name="idx_grtz_ft_tg", columnList = "sched_name,trigger_group", unique = false)
        })
public class QrtzFiredTriggers implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="entry_id", nullable = false)
    private String entryId;

    @Column(name="trigger_name", nullable = false)
    private String triggerName;

    @Column(name="trigger_group", nullable = false)
    private String triggerGroup;

    @Column(name="instance_name", nullable = false)
    private String instanceName;

    @Column(name="fired_time", nullable = false)
    private long firedTime;

    @Column(name="sched_time", nullable = false)
    private long sched_time;

    @Column(name="priority", nullable = false)
    private int priority;

    @Column(name="state", nullable = false)
    private String state;

    @Column(name="job_name")
    private String jobName;

    @Column(name="job_group")
    private String jobGroup;

    @Column(name="is_nonconcurrent")
    private boolean isNonconcurrent;

    @Column(name="requests_recovery")
    private boolean requestRecovery;


}
