package com.zeke.crmrestapi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.zeke.crmrestapi.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	//inject session factory from the bean with id="sessionFactory"
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		//get the current session
		Session session = sessionFactory.getCurrentSession();
		//create query ordered by firstName
		Query<Customer> theQuery = session.createQuery("from Customer order by firstName"
								,	Customer.class);
		//execute query to get result list
		List<Customer> customers = theQuery.getResultList();
		//return list
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		//get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer 
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		return currentSession.get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.delete(currentSession.get(Customer.class, theId));
		
	}

}
