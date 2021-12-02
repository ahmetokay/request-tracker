package com.okay.entity;

import com.okay.core.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rt_role")
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;
}