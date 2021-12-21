package com.okay.entity;

import com.okay.core.BaseEntity;
import com.okay.enm.EnumProtocolType;
import com.okay.enm.EnumRequestType;
import com.okay.enm.EnumScheduledType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "rt_request")
public class Request extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_workspace_id")
    private Workspace workspace;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    private EnumRequestType requestType;

    @Enumerated(EnumType.STRING)
    @Column(name = "scheduled_type")
    private EnumScheduledType scheduledType;

    @Enumerated(EnumType.STRING)
    @Column(name = "protocol")
    private EnumProtocolType protocol;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "port")
    private String port;

    @Column(name = "body")
    private String body;

    @Column(name = "try_count")
    private Integer tryCount;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<RequestHistory> requestHistoryList;
}