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
        name= "qrtz_simprop_triggers")
public class QrtzSimpropTriggers implements Serializable {

    @Id
    @Column(name="sched_name", nullable = false)
    private String schedName;

    @Id
    @Column(name="trigger_name", nullable = false)
    private String triggerName;

    @Id
    @Column(name="trigger_group", nullable = false)
    private String triggerGroup;

    @Column(name="str_prop_1")
    private String strProp1;

    @Column(name="str_prop_2")
    private String strProp2;

    @Column(name="str_prop_3")
    private String strProp3;

    @Column(name="int_prop_1")
    private int intProp1;

    @Column(name="int_prop_2")
    private int intProp2;

    @Column(name="long_prop_1")
    private long longProp1;

    @Column(name="long_prop_2")
    private long longProp2;

    @Column(name="dec_prop_1")
    private float decProp1;

    @Column(name="dec_prop_2")
    private float decProp2;

    @Column(name="bool_prop_1")
    private boolean boolProp1;

    @Column(name="bool_prop_2")
    private boolean boolProp2;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="sched_name", referencedColumnName = "sched_name"),
            @JoinColumn(name="trigger_name", referencedColumnName = "trigger_name"),
            @JoinColumn(name="trigger_group", referencedColumnName = "trigger_group"),

    })
    private QrtzTriggers qrtzTriggers;
}
