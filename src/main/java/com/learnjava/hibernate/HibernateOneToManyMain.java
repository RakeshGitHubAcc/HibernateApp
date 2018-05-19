package com.learnjava.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.learnjava.hibernate.model.Cart;
import com.learnjava.hibernate.model.Items;
import com.learnjava.hibernate.util.HibernateUtil;

public class HibernateOneToManyMain {
	public static void main(String[] args) {
		Cart cart = new Cart();
		cart.setName("MyCart");

		Items item1 = new Items();
		item1.setItemId("I1");
		item1.setQuantity(1);
		item1.setItemTotal(10);
		item1.setCart(cart);

		Items item2 = new Items();
		item2.setItemId("I2");
		item2.setQuantity(2);
		item2.setItemTotal(20);
		item2.setCart(cart);

		Set<Items> itemsSet = new HashSet<Items>();
		itemsSet.add(item1);
		itemsSet.add(item2);

		cart.setItems(itemsSet);
		cart.setTotal(10 * 1 + 20 * 2);

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created");
			// start transaction
			tx = session.beginTransaction();

			// Save the Model objects
			session.save(cart);
			session.save(item1);
			session.save(item2);

			// Commit transaction
			tx.commit();
			System.out.println("Cart ID=" + cart.getId());

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

}
