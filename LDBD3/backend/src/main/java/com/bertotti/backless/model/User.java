package com.bertotti.backless.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "backless_user")
public class User extends Base {
    @Getter
    @Setter
    @NotNull(message = "email is required")
    private String email;

    @Getter
    @Setter
    @NotNull(message = "name is required")
    private String name;

    @Getter
    @Setter
    @NotNull(message = "password is required")
    @Size(min = 8, message = "password must be at least 8 characters")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}
