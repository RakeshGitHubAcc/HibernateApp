package com.learnjava.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.learnjava.hibernate.model.Employee;
import com.learnjava.hibernate.util.HibernateUtil;

public class HibernateMain {

	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setName("Rakesh");
		emp.setRole("CEO");
		emp.setInsertTime(new Date());
		Session session = null;
		try {
			// Get Session
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			// start transaction
			session.beginTransaction();
			// Save the Model object
			session.save(emp);
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Employee ID=" + emp.getId());
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			// close session
			if (null != session) {
				session.close();
			}
			// terminate session factory, otherwise program won't end
			HibernateUtil.getSessionFactory().close();
		}
	}

}
