package com.spring.demo.dao;

import com.spring.demo.entity.School;
import com.spring.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchoolDAOImp implements SchoolDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<School> getSchools() {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<School> query=currentSession.createQuery("from School order by name", School.class);
        return query.getResultList();
    }

    @Override
    public void saveSchool(School theSchool) {
        Session currentSession=sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theSchool);

    }

    @Override
    public School getSchool(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        School theSchool = currentSession.get(School.class, theId);

        return theSchool;
    }

    @Override
    public void deleteSchool(int theId) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query query=currentSession.createQuery("delete from School where id= :schoolId");
        query.setParameter("schoolId",theId);
        query.executeUpdate();
    }


}
