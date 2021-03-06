package com.nextech.hrms.dao;

import java.sql.SQLException;
import java.text.ParseException;
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
		Employee employee2=	getEmployeeUserId(employee.getUserid());
		if(employee2==null){
		Employee employee3=getEmployeePhoneNumber(employee.getPhoneNumber());
		if(employee3==null){
		Employee employee4=getEmployeeEmailId(employee.getEmailid());
		if(employee4==null){
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
		employee.setUsertype(employee.getUsertype());
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
		}
		}else{
			System.out.println("UserId Phonenumber emailid Is already Avilable");
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
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Deleted Successfully");
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
				employee.setPhoneNumber(data);
				session.update(employee);
				session.getTransaction().commit();
				session.close();
				}else{
					System.out.println("please enter valid id");
				}
			    //tx.commit();
			   
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
			 System.out.println("Update Successfully");
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
					System.out.println("Enter Employee Id to search data.");
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
				
			}
				
	  }
	  
	  private Employee getEmployeeUserId(String userid){
			 Session session=HibernateUtil.getSessionFactory().openSession();
				Transaction tx = null;  
			  Criteria criteria = session.createCriteria(Employee.class);
			  criteria.add(Restrictions.eq("userid", userid));
			  //criteria.add(Restrictions.eq("phoneNumber",phoneNumber));
			  //criteria.add(Restrictions.eq("emailid",emailid));
			  Employee employee = criteria.list().size() > 0 ? (Employee) criteria.list().get(0) : null;
			  return employee;
		 }
	  private Employee getEmployeePhoneNumber(String phoneNumber){
			 Session session=HibernateUtil.getSessionFactory().openSession();
				Transaction tx = null;  
			  Criteria criteria = session.createCriteria(Employee.class);
			  criteria.add(Restrictions.eq("phoneNumber",phoneNumber));
			  Employee employee = criteria.list().size() > 0 ? (Employee) criteria.list().get(0) : null;
			  return employee;
		 }
	  private Employee getEmployeeEmailId(String emailid){
			 Session session=HibernateUtil.getSessionFactory().openSession();
				Transaction tx = null;  
			  Criteria criteria = session.createCriteria(Employee.class);
			  criteria.add(Restrictions.eq("emailid",emailid));
			  Employee employee = criteria.list().size() > 0 ? (Employee) criteria.list().get(0) : null;
			  return employee;
		 }
	  public void integerValidation(){
			while (true) {
				//data = sc.next();
				nullValidation();
				if (EmployeeDao.numberOrNot(data)) {
					break;
				} else {
					System.out.println("please enter only number");
				}

			}
		 }
	  public void phoneNumberValidation(Employee employee) throws ParseException{
			
		  while (true) {
				//data = sc.next();
				nullValidation();
				if (EmployeeMain.isPhoneNumber(data)) {
					break;
				} else {
					System.out.println("please enter only 10 digit mobile number");
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

	private void nullValidation() {
		while (true) {
			data = sc.nextLine();
			if (data != null && data.length() == 0) {
				System.out.println("please enter data");
			} else {
				break;
			}
		}

	}
	

}
