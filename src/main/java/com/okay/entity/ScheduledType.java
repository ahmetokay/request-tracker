package com.okay.entity;

import com.okay.core.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rt_scheduled_type")
public class ScheduledType extends BaseEntity {

    @Column(name = "NAME")
    private String name;
}