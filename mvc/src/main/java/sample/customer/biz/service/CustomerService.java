package sample.customer.biz.service;

import java.util.List;

import sample.customer.biz.domain.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int id) throws DataNotFoundException;

	public Customer register(Customer customer);

	public void update(Customer customer) throws DataNotFoundException;

	public void delete(int id) throws DataNotFoundException;

	public boolean isFreeEmailCustomer(Customer customer);
}
