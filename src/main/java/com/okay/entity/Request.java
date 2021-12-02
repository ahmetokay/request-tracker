package com.okay.entity;

import com.okay.core.BaseEntity;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_scheduled_type_id")
    private ScheduledType scheduledType;

    @Column(name = "url")
    private String url;

    @Column(name = "port")
    private String port;
}