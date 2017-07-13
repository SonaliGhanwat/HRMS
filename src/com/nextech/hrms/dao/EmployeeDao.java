package com.nextech.hrms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nextech.hrms.main.EmployeeMain;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.util.HibernateUtil;

public class EmployeeDao {
	public static Scanner sc = new Scanner(System.in);
	public static String data ="";
	public void addUser(Employee employee) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		try{
		tx=session.beginTransaction(); 
		employee.setUserid(employee.getUserid());
		employee.setPassword(employee.getPassword());
	    employee.setFirstName(employee.getFirstName());
	    employee.setLastName(employee.getLastName());
	    employee.setPhoneNumber(employee.getPhoneNumber());
	    employee.setEmailid(employee.getEmailid());
	    employee.setDateOfJoining(employee.getDateOfJoining());
		employee.setDateOfBirth(employee.getDateOfBirth());
		employee.setAddress(employee.getAddress());
		employee.setDepartment(employee.getDepartment());
		employee.setSalary(employee.getSalary());
		employee.setIsActive(true);
		session.save(employee);
		session.getTransaction().commit();
		//tx.commit();
		 session.close();  
		 System.out.println("Insert Successfully");
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback(); 
		}
	
	}
	 public void deleteUser() throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		try{
			tx=session.beginTransaction(); 
			Employee employee1 = new Employee();
			System.out.println("Enter Employee Id to delete.");
			integerValidation();		
			employee1.setId(Integer.parseInt(data));
			Employee employee = (Employee)session.get(Employee.class,employee1.getId());
			if(employee !=null){
			employee.setIsActive(false);
		    session.update(employee);
		    session.getTransaction().commit();
		    session.close();  
			}else{
				System.out.println("please enter valid id");
			}
		  //tx.commit();
		  System.out.println("Deleted Successfully");
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback(); 
		}
	 }
	 public void updateUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				Employee employee1 = new Employee();
				System.out.println("Enter Employee Id to update data.");
				integerValidation();
				employee1.setId(Integer.parseInt(data));
				Employee employee = (Employee)session.get(Employee.class,employee1.getId());
			if (employee != null) {
				System.out.println("Enter Employee phoneNumber");
				phoneNumberValidation(employee);
				employee.setFirstName(employee1.getPhoneNumber());
				session.update(employee);
				session.getTransaction().commit();
				session.close();
				}else{
					System.out.println("please enter valid id");
				}
			    //tx.commit();
			    System.out.println("Update Successfully");
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback(); 
			}
	 }
	  public List<Employee> getAllUsers() throws ClassNotFoundException, SQLException {
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null;  
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("isActive", true));
		List<Employee> employees =  (List<Employee>) (criteria.list().size() > 0 ? criteria.list() : null);
		
		
		if(employees !=null && ! employees.isEmpty()){
			tx=session.beginTransaction(); 
		for (Employee employee : employees) {
			  employee.getId();
			  employee.getUserid();
			  employee.getPassword();
			  employee.getFirstName();
			  employee.getLastName();
			  employee.getPhoneNumber();
			  employee.getEmailid();
			  employee.getDateOfJoining();
			  employee.getDateOfBirth();
			  employee.getAddress();
			  employee.getDepartment();
			  employee.getSalary();
			  System.out.println(employee);	
		}
		session.getTransaction().commit();
		session.close();  
		//tx.commit();
			
		}else{
			System.out.println("please Insert Data");
		}
		return employees;
		  
	  } 
	  public void getUserById() throws ClassNotFoundException {
			 Session session=HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = null; 
				try{
					tx=session.beginTransaction(); 
					Employee employee1 = new Employee();
					System.out.println("Enter Employee Id to update data.");
					integerValidation();
					employee1.setId(Integer.parseInt(data));
					Employee employee = (Employee)session.get(Employee.class,employee1.getId());
					if(employee !=null){
				    session.getTransaction().commit();
				    session.close();  
				    //tx.commit();
				    System.out.println(employee);
					}else{
						System.out.println("please enter valid id");
					}
				
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback(); 
			}
				
	  }
	  public void integerValidation(){
			while (sc.hasNext()) {
				data = sc.next();
				if (EmployeeDao.numberOrNot(data)) {
					break;
				} else {
					System.out.println("please enter only number");
				}

			}
		 }
	  public void phoneNumberValidation(Employee employee){
			
	        while(sc.hasNext()){
	        	String	phoneNumber=sc.next();
	        
	        if(EmployeeMain.numberOrNot(phoneNumber) && (phoneNumber.length() == 10)){
	        	employee.setPhoneNumber(phoneNumber);
	            break;
	            
	        }else{
	            System.out.println("please enter 10 digit mobile number");
	        }
	        }
			
		}
	  static boolean numberOrNot(String input)
	    {
	        try
	        {
	            Integer.parseInt(input);
	        }
	        catch(NumberFormatException ex)
	        {
	            return false;
	        }
	        return true;
	    }
	  
}
