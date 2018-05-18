package com.learnjava.hibernate.main;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.learnjava.hibernate.model.Employee1;
import com.learnjava.hibernate.util.HibernateUtil;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
		HibernateAnnotationMain hibernateAnnotationMain = new HibernateAnnotationMain();
		Employee1 emp = new Employee1();
		emp.setName("Brijesh");
		emp.setRole("Sr.Developer");
		emp.setInsertTime(new Date());
		Session session = null;
		try {
			// get session
			session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
			// begin transaction
			session.beginTransaction();
			// save data
			session.save(emp);
			// commit
			session.getTransaction().commit();
			System.out.println("Employee id= " + emp.getId());
		} catch (HibernateException hex) {
			hex.printStackTrace();
		} finally {
			// close session
			if (null != session && session.isConnected()) {
				session.close();
			}
			// close session factory
			HibernateUtil.getSessionAnnotationFactory().close();
		}

	}
}
