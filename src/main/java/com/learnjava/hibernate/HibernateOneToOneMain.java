package com.learnjava.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learnjava.hibernate.model.Customer;
import com.learnjava.hibernate.model.Txn;
import com.learnjava.hibernate.util.HibernateUtil;

public class HibernateOneToOneMain {

	public static void main(String[] args) {
		Txn txn = buildDemoTransaction();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(txn);
			session.getTransaction().commit();
			System.out.println("Transaction ID=" + txn.getId());

			// Get Saved Trasaction Data
			printTransactionData(txn.getId(),sessionFactory);
		} catch (HibernateException hex) {
			hex.printStackTrace();
		} finally {
			if (null != session && session.isConnected()) {
				session.close();
			}
			if (null != sessionFactory) {
				sessionFactory.close();
			}
		}

	}

	private static void printTransactionData(long id,SessionFactory sessionFactory) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Txn txn = session.get(Txn.class, id);
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction Details=\n" + txn);
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		}finally {
			if (null != session && session.isConnected()) {
				session.close();
			}	
		}
	}

	private static Txn buildDemoTransaction() {
		Txn txn = new Txn();
		txn.setDate(new Date());
		txn.setTotal(100);

		Customer cust = new Customer();
		cust.setAddress("Bangalore, India");
		cust.setEmail("pankaj@gmail.com");
		cust.setName("Pankaj Kumar");

		txn.setCustomer(cust);

		cust.setTxn(txn);
		return txn;
	}

}
