package com.okay.entity;

import com.okay.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rt_menu")
public class Menu extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_role_id")
    private Role role;

    @Column(name = "name")
    private String name;

    @Column(name = "ordering")
    private Integer ordering;
}