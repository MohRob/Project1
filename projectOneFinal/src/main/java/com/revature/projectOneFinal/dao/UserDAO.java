package com.revature.projectOneFinal.dao;



import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.revature.projectOneFinal.model.UserModel;

public class UserDAO {
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	public static UserModel findById(String username) {
		session = connect();
		session.beginTransaction();
		Query query = session.createQuery("from UserModel where username=:username", UserModel.class);
		query.setParameter("username", username);
		UserModel user=(UserModel)((org.hibernate.Query<UserModel>) query).uniqueResult();
		session.getTransaction().commit();
		session.close();
		close();
		return user;
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
