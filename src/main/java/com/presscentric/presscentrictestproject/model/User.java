package com.presscentric.presscentrictestproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.io.Serializable;

@Entity
@Table(name = "PRESS_USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

        public User() {
                super();
        }

        public User(final String name) {
                super();
                this.name = name;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "USER_ID")
        @ReadOnlyProperty
        private Integer id;
        @Column(name = "EMAIL")
        @NotNull(message = "Email can not be null")
        @NotEmpty(message = "Email can not be empty")
        @NotBlank(message = "Email can not be blank")
        @Email(message = "Email is not valid", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        private String email;
        @Column(name = "NAME")
        @NotNull(message = "Name can not be null")
        @NotEmpty(message = "Name can not be empty")
        @NotBlank(message = "Name can not be blank")
        private String name;
}
