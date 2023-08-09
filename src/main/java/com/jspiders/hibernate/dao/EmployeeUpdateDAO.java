package com.jspiders.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernate.dto.EmplyeeDTO;

public class EmployeeUpdateDAO {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transactiion;
	
	private static void openConnection() {
		
		factory= Persistence.createEntityManagerFactory("hibernate");
		manager = factory.createEntityManager();
		transactiion = manager.getTransaction();
	}
	private static void closeConnection() {
		if (factory!=null) {
			factory.close();
		}
		if (manager!=null) {
			manager.close();
		}
		if (transactiion.isActive()) {
			transactiion.rollback();
		}
	}
	public static void main(String[] args) {
		try {
			openConnection();
			transactiion.begin();
			
			EmplyeeDTO employee = manager.find(EmplyeeDTO.class, 1);
			employee.setDesignation("Inspiration");
			employee.setSalary(220);
			manager.persist(employee);
			
			transactiion.commit();
		} finally {
			closeConnection();
		}
	}
}
