package com.bertotti.backless.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@MappedSuperclass
public abstract class Base implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private Timestamp created;
    
    @Getter
    @Setter
    private Timestamp lastModified;

    @Getter
    @Setter
    private Boolean active;

    public Base () {
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.lastModified = this.created;
        this.active = true;
    }
}