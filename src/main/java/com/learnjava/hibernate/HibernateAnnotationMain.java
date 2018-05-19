package com.learnjava.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.learnjava.hibernate.model.Employee1;
import com.learnjava.hibernate.util.HibernateUtil;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
		Employee1 emp1 = new Employee1();
		emp1.setName("Rajesh");
		emp1.setRole("MD");
		emp1.setInsertTime(new Date());
		Session session = null;
		try {
			// Get Session
			session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
			// start transaction
			session.beginTransaction();
			// Save the Model object
			session.save(emp1);
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Employee ID=" + emp1.getId());
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			// close session
			if (null != session && session.isConnected()) {
				session.close();
			}
			// terminate session factory, otherwise program won't end
			HibernateUtil.getSessionFactory().close();
		}

	}

}
