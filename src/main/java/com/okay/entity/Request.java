package com.okay.entity;

import com.okay.core.BaseEntity;
import com.okay.enm.EnumScheduledType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rt_request")
public class Request extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_workspace_id")
    private Workspace workspace;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_request_type_id")
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    @Column(name = "scheduled_type")
    private EnumScheduledType scheduledType;

    @Column(name = "url")
    private String url;

    @Column(name = "port")
    private String port;
}