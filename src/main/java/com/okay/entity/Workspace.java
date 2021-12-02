package com.okay.entity;

import com.okay.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "rt_workspace")
public class Workspace extends BaseEntity {

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "workspace")
    private List<Request> requestList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;
}