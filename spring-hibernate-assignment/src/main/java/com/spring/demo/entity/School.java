package com.spring.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    @Column(name="name")
    private String name;

    @NotEmpty(message = "Location is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Location can only contain letters and spaces")
    @Column(name="location")
    private String location;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "school")
    private List<Student> students=new ArrayList<>();

    public void addStudent(Student theStudent){
        students.add(theStudent);
    }

}
