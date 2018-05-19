package com.learnjava.hibernate.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learnjava.hibernate.model.Employee1;

public class HibernateUtil {
	// XML based configuration
	private static SessionFactory sessionFactory;

	// Annotation based configuration
	private static SessionFactory sessionAnnotationFactory;

	// Property based configuration
	private static SessionFactory sessionJavaConfigFactory;

	private static SessionFactory buildSessionFactory() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("[buildSessionFactory: ] Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		if (null == sessionFactory) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	private static SessionFactory buildSessionAnnotationFactory() {
		try {
			sessionAnnotationFactory = new Configuration().configure("hibernate-annotation.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("[buildSessionAnnotationFactory: ]Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return sessionAnnotationFactory;
	}

	public static SessionFactory getSessionAnnotationFactory() {
		if (null == sessionAnnotationFactory) {
			sessionAnnotationFactory = buildSessionAnnotationFactory();
		}
		return sessionAnnotationFactory;
	}

	private static SessionFactory buildSessionJavaConfigFactory() {
		Properties dbConnectionProperties = new Properties();
		try {
			dbConnectionProperties.load(HibernateUtil.class.getClassLoader().getSystemClassLoader()
					.getResourceAsStream("hibernate.properties"));
			sessionJavaConfigFactory = new Configuration().mergeProperties(dbConnectionProperties)
					.configure("hibernate.cfg.xml").addAnnotatedClass(Employee1.class).buildSessionFactory();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return sessionJavaConfigFactory;
	}

	public static SessionFactory getSessionJavaConfigFactory() {
		if (sessionJavaConfigFactory == null)
			sessionJavaConfigFactory = buildSessionJavaConfigFactory();
		return sessionJavaConfigFactory;
	}
}
