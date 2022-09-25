package com.dauo.project.common.base;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class BaseEntity<PK extends Serializable> implements Serializable {
    private static final long serialVersionUID = -5524127175086243458L;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    protected LocalDateTime createdAt;
    @Column(name = "UPDATED_AT", nullable = false)
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onPersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}