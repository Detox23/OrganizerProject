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
        name= "qrtz_job_details",
        indexes = { @Index(name="idx_qrtz_j_req_recovery", columnList = "sched_name,requests_recovery"),
                    @Index(name="idx_qrtz_j_grp", columnList = "sched_name,job_group")
        })
public class QrtzJobDetails implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="job_name", nullable = false)
    private String jobName;

    @Id
    @Column(name="job_group", nullable = false)
    private String jobGroup;

    @Column(name="description")
    private String description;

    @Column(name="job_class_name", nullable = false)
    private String jobClassName;

    @Column(name="is_durable", nullable = false)
    private boolean isDurable;

    @Column(name="is_nonconcurrent", nullable = false)
    private boolean isNonConcurrent;

    @Column(name="is_update_data", nullable = false)
    private boolean isUpdateData;

    @Column(name="requests_recovery", nullable = false)
    private boolean requestsRecovery;

    @Column(name="job_data")
    private byte[] jobData;

}
