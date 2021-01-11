package org.example.Dao;

import org.example.Entity.ContractEntity;
import org.example.SessionFactory.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContractEntityDAO {

    public void saveOrUpdateContract (ContractEntity contractEntity){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(contractEntity);
        tx1.commit();
        session.close();

    }

    public List<ContractEntity> getAllContracts() {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ContractEntity.class);
        List<ContractEntity> contracts = criteria.list();
        session.close();
        return contracts;

    }

}
