package com.learnjava.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.learnjava.hibernate.model.Employee1;
import com.learnjava.hibernate.util.HibernateUtil;

public class HibernateJavaConfigMain {
	public static void main(String[] args) {
		Employee1 emp = new Employee1();
		emp.setName("Lisa");
		emp.setRole("Manager");
		emp.setInsertTime(new Date());
		Session session = null;
		try {
			// Get Session
			session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
			if (null != session) {
				// start transaction
				session.beginTransaction();
				// Save the Model object
				session.save(emp);
				// Commit transaction
				session.getTransaction().commit();
				System.out.println("Employee ID=" + emp.getId());
			}

		} catch (HibernateException hex) {
			hex.printStackTrace();
		} finally {
			// close the session
			if (null != session && session.isConnected()) {
				session.close();
			}
			// close the session factory
			HibernateUtil.getSessionJavaConfigFactory().close();
		}
	}
}
