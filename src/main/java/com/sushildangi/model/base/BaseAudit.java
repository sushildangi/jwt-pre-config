package com.sushildangi.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        allowGetters = true
)
@Setter
@Getter
public abstract class BaseAudit<U> implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String id;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Instant lastModifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private U createdBy;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private U lastModifiedBy;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}