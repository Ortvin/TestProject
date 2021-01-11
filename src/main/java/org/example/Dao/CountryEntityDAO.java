package org.example.Dao;

import org.example.Entity.CountryEntity;
import org.example.SessionFactory.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class CountryEntityDAO {

    public List<CountryEntity> getAllCountries() {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(CountryEntity.class);
        List<CountryEntity> countriesList = criteria.list();
        session.close();
        return countriesList;

    }
}
