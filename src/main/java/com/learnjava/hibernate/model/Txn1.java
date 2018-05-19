package com.learnjava.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Transaction")
public class Txn1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "txn_id")
	private long id;
	@Column(name = "txn_date")
	private Date date;
	@Column(name = "txn_total")
	private double total;
	@OneToOne(mappedBy = "txn")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Customer1 customer;

	@Override
	public String toString() {
		return "Txn1 [id=" + id + ", date=" + date + ", customer=" + customer + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer1 getCustomer() {
		return customer;
	}

	public void setCustomer(Customer1 customer) {
		this.customer = customer;
	}

}
