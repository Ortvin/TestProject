package org.example.Dao;

import org.example.SessionFactory.HibernateSessionFactory;
import org.example.Entity.ObjecttypeEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ObjectTypeEntityDAO {

    public List<ObjecttypeEntity> getObjectTypeByName(String objectTypeName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ObjecttypeEntity.class);
        criteria.add(Restrictions.eq("objecTypeName", objectTypeName));
        List<ObjecttypeEntity> objectType = criteria.list();
        session.close();
        return objectType;
    }

    public List<ObjecttypeEntity> getAllObjectTypes() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ObjecttypeEntity.class);
        List<ObjecttypeEntity> objectTypes = criteria.list();
        session.close();
        return objectTypes;

    }

}
