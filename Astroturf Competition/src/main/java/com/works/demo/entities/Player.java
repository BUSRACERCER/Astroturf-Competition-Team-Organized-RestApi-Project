package com.works.demo.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    @Size(min = 1, max = 50)
    @NotEmpty
    @NotNull
    private String name;

    @Size(min = 1, max = 50)
    @NotEmpty
    @NotNull
    private String surname;

    @Min(value = 18, message = "18 yaşından büyük olmalıdır!")
    @NotNull
    private Integer age;

    @Email
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    private String password;


}



