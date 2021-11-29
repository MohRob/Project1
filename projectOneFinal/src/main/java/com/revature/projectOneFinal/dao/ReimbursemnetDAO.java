package com.revature.projectOneFinal.dao;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.revature.projectOneFinal.model.ReimbursementModel;

public class ReimbursemnetDAO {
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	public static List<ReimbursementModel> findAll() {
		session =connect();
		session.beginTransaction();
		List<ReimbursementModel> reimbursements = session.createQuery("FROM ReimbursementModel", ReimbursementModel.class).list();
		session.getTransaction().commit();
		session.close();
		close();
		return reimbursements;
	}
	
	public static List<ReimbursementModel> findByPendingStatus(){
		session = connect();
		session.beginTransaction();
		String status ="pending";
		Query query = session.createQuery("from ReimbursementModel  where status=:status", ReimbursementModel.class);
		query.setParameter("status", status);
		List<ReimbursementModel> pendingReimbursements = ((org.hibernate.query.Query<ReimbursementModel>) query).list();
		session.getTransaction().commit();
		session.close();
		close();
		return pendingReimbursements;
	}
	public static void save(ReimbursementModel rs) {
		session = connect();
		session.beginTransaction();
		session.save(rs);
		session.getTransaction().commit();
		session.close();
		close();
	}
	
	public static Session connect() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		// configures settings from hibernate.cfg.xml
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}

		return sessionFactory.openSession();
	}

	public static void close() {
		try {
			if (session != null)
				session.close();
			if (sessionFactory != null)
				sessionFactory.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
