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
public class StudentDAOImp implements StudentDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getStudents() {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<Student> query=currentSession.createQuery("from Student order by firstName", Student.class);
        return query.getResultList();

    }

    @Override
    public void saveStudent(Student theStudent) {
        Session currentSession=sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theStudent);

    }

    @Override
    public Student getStudent(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Student theStudent = currentSession.get(Student.class, theId);

        return theStudent;
    }

    @Override
    public void deleteStudent(int theId) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query query=currentSession.createQuery("delete from Student where id= :studentId");
        query.setParameter("studentId",theId);
        query.executeUpdate();
    }
}
