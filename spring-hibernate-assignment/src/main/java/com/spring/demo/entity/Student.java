package com.spring.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name can only contain letters")
    @Column(name="first_name")
    private String firstName;


    @NotEmpty(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name can only contain letters")
    @Column(name="last_name")
    private String lastName;


    @NotEmpty(message = "Standard is required")
    @Column(name="standard")
    private String standard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="school_id")
    @ToString.Exclude
    private School school;

}
