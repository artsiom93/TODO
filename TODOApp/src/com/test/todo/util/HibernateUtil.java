package com.test.todo.util;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import com.test.todo.domain.LogTask;
import com.test.todo.domain.Person;
import com.test.todo.domain.Task;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(Person.class);
			configuration.addAnnotatedClass(Task.class);
			configuration.addAnnotatedClass(LogTask.class);
			configuration.configure("hibernate.cfg.xml");

			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());

			sessionFactory = configuration.buildSessionFactory(ssrb.build());
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}