package com.spring.demo.dao;

import com.spring.demo.entity.School;
import com.spring.demo.entity.Student;

import java.util.List;

public interface SchoolDAO {
    public List<School> getSchools();

    public void saveSchool(School theSchool);

    public School getSchool(int theId);

    public void deleteSchool(int theId);
}
