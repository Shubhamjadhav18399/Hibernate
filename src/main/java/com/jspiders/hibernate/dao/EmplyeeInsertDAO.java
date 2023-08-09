package com.jspiders.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernate.dto.EmplyeeDTO;

public class EmplyeeInsertDAO {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transection;
	
	private static void openConnection()
	{
		factory=Persistence.createEntityManagerFactory("hibernate");
		manager=factory.createEntityManager();
		transection= manager.getTransaction();
	}
    private static void closeConnection()
    {
    	if (factory!=null) {
    		factory.close();
		}
    	if (manager!=null) {
			manager.close();
		}
    	if (transection.isActive()) {
			transection.rollback();
		}
    }
    public static void main(String[] args) {
		try {
			openConnection();
			transection.begin();
			
			EmplyeeDTO emp1=new EmplyeeDTO();
			emp1.setId(1);
			emp1.setName("sara");
			emp1.setDesignation("motivation");
			emp1.setSalary(120);
			
			EmplyeeDTO emp2=new EmplyeeDTO();
			emp2.setId(2);
			emp2.setName("shubman");
			emp2.setDesignation("Bastman");
			emp2.setSalary(420);
			
			EmplyeeDTO emp3=new EmplyeeDTO();
			emp3.setId(3);
			emp3.setName("shubham");
			emp3.setDesignation("Student");
			emp3.setSalary(620);
			
			manager.persist(emp1);
			manager.persist(emp2);
			manager.persist(emp3);
			
			transection.commit();
			
		}finally {
			closeConnection();
		}
	}
}
