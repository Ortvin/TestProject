package org.example.Dao;

import org.example.SessionFactory.HibernateSessionFactory;
import org.example.Entity.SizeEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SizeEntityDAO {

    public List<SizeEntity> getSizeEntityBySize(Double size) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(SizeEntity.class);
        criteria.add(Restrictions.le("startValue", size));
        criteria.add(Restrictions.ge("endValue", size));
        List<SizeEntity> sizeList = criteria.list();
        session.close();
        return sizeList;
    }

}
