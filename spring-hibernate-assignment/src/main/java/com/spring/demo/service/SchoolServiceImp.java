package com.spring.demo.service;

import com.spring.demo.dao.SchoolDAO;
import com.spring.demo.entity.School;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SchoolServiceImp implements SchoolService{

    private final SchoolDAO schoolDAO;

    @Autowired
    public SchoolServiceImp(SchoolDAO schoolDAO) {
        this.schoolDAO = schoolDAO;
    }


    @Override
    @Transactional
    public List<School> getSchools() {
        return schoolDAO.getSchools();
    }

    @Override
    @Transactional
    public void saveSchool(School theSchool) {
        schoolDAO.saveSchool(theSchool);
    }

    @Override
    @Transactional
    public School getSchool(int theId) {
       School school= schoolDAO.getSchool(theId);
       Hibernate.initialize(school.getStudents());
       return school;
    }

    @Override
    @Transactional
    public void deleteSchool(int theId) {
         schoolDAO.deleteSchool(theId);
    }

}
