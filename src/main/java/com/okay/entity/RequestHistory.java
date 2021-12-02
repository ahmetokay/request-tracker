package com.okay.entity;

import com.okay.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rt_request_history")
public class RequestHistory extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_request_id")
    private Request request;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_response_type_id")
    private ResponseType responseType;

    @Column(name = "request_date")
    private Date requestDate;
}