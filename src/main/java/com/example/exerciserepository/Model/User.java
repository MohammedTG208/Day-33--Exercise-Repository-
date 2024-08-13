package com.example.exerciserepository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role='admin' or role='user'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    , name , username , password, email ,role, age
    @Size(min = 4,message = "name can not be null Length more than 4")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Size(min = 4,message = "Cannot be null Length more than 4")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @Pattern(regexp = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$", message = "enter valid Password")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;
    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotNull(message = "role is requirement")
    @Pattern(regexp = "(admin|user)+$",message = "enter admin or user for role")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;
    @Column(columnDefinition = "int not null")
    @Positive(message = "Cannot be null must be integer ")
    private int age;
}
