package com.okay.core;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created", updatable = false)
    private Date created;

    @Column(name = "created_by", updatable = false)
    private Integer createdBy;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.active = Boolean.TRUE;
        this.created = now;
        this.updated = now;
    }

    @PreUpdate
    public void setUpdateDate() {
        this.updated = new Date();
    }
}