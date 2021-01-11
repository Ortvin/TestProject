package org.example.Dao;

import org.example.Entity.ConstructionyearEntity;
import org.example.SessionFactory.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ConstructionyearEntityDAO {

    public List<ConstructionyearEntity> getConstructionyearByPeriod(Integer constructionYear) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ConstructionyearEntity.class);
        criteria.add(Restrictions.le("beginPeriod", constructionYear));
        criteria.add(Restrictions.ge("endPeriod", constructionYear));
        List<ConstructionyearEntity> constructionYearList = criteria.list();
        session.close();
        return constructionYearList;

    }

}
