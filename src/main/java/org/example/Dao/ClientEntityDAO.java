package org.example.Dao;

import org.example.Entity.ClientEntity;
import org.example.SessionFactory.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientEntityDAO {

    public void saveClient (ClientEntity clientEntity){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(clientEntity);
        transaction.commit();
        session.close();
    }

    public List<ClientEntity> getClientEntityByFIO(String lastName, String firstName, String patronymic){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ClientEntity.class);
        criteria.add(Restrictions.like("lastname", '%' + lastName + '%'));
        criteria.add(Restrictions.like("lastname", '%' + firstName + '%'));
        criteria.add(Restrictions.like("lastname", '%' + patronymic + '%'));
        List<ClientEntity> clientList = criteria.list();
        session.close();
        return clientList;
    }
}
