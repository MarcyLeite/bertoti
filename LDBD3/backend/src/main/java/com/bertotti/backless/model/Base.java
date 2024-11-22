package com.bertotti.backless.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @JsonProperty(access = Access.WRITE_ONLY)
    private Timestamp created;
    
    @Getter
    @Setter
    @JsonProperty(access = Access.WRITE_ONLY)
    private Timestamp lastModified;

    @Getter
    @Setter
    @JsonProperty(access = Access.WRITE_ONLY)
    private Boolean active;

    public Base () {
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.lastModified = this.created;
        this.active = true;
    }
}