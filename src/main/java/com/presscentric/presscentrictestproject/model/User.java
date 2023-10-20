package com.presscentric.presscentrictestproject.model;

import com.presscentric.presscentrictestproject.annotations.AlphaCharactersOnly;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class User implements Serializable {

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
        @Size(min = 1, max = 30, message = "Name can be 30 characters long max")
        @AlphaCharactersOnly(message = "Name must contain only alphabetical characters")
        private String name;
}
