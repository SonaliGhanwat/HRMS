package com.nextech.hrms.dao;

import java.sql.Date;
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
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.util.HibernateUtil;

public class EmployeeLeaveDao {
	public static Scanner sc = new Scanner(System.in);
	public static String data ="";
	public void addEmployeeLeaveUser(Employeeleave employeeleave) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		
		try{
		 tx=session.beginTransaction();  
		   Employee employee = new Employee();
			//employee.setId(employeeAttendance.getEmployee().getId());
			employee.setId(employeeleave.getEmployee().getId());
			employeeleave.setEmployee(employee);
			employeeleave.setSubject(employeeleave.getSubject());
			employeeleave.setLeavedate(employeeleave.getLeavedate());
			employeeleave.setCreatedDate(employeeleave.getCreatedDate());
			employeeleave.setUpdatedDate(employeeleave.getUpdatedDate());
			employeeleave.setIsActive(true);
			session.save(employeeleave);
			session.getTransaction().commit();
		    session.close();  
		    //tx.commit();
			
			}
			catch(Exception e){
				e.printStackTrace();
				 
			}
		 System.out.println("Insert Successfully");
	}
	
	public void deleteEmployeeLeave() throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		try{
		  tx=session.beginTransaction();  
		  Employeeleave employeeleave1 = new Employeeleave();
		  System.out.println("Enter EmployeeleaveData Id to delete.");
		  integerValidation();
    	  employeeleave1.setId(Integer.parseInt(data));
    	  Employeeleave employeeleave = (Employeeleave)session.get(Employeeleave.class,employeeleave1.getId());
    	if(employeeleave !=null){
    		employeeleave.setIsActive(false);
    	session.update(employeeleave);
		  session.getTransaction().commit();
		  session.close();  
    	}else{
			System.out.println("please enter valid id");
		}
		  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Deleted Successfully");
	}
		
	public void updateEmployeeLeaveUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				 Employeeleave employeeleave1 = new Employeeleave();
				System.out.println("Enter EmployeeleaveData Id to update data.");
				integerValidation();
		    	employeeleave1.setId(Integer.parseInt(data));
			    Employeeleave employeeleave = (Employeeleave)session.get(Employeeleave.class,employeeleave1.getId());
			    if(employeeleave !=null){
			    	Employee employee = new Employee();
			    	System.out.println("Enter Employee Id.");
			    	integerValidation();
					employee.setId(Integer.parseInt(data));
					employeeleave.setEmployee(employee);
			    	System.out.println("Enter Subjet");
			    	stringValidation();
				    employeeleave.setSubject(data);
				    System.out.println("Enter leaveDate");
				    dateValidation();
				    employeeleave.setLeavedate(Date.valueOf(data));
				    System.out.println("Enter After LeaveJoiningdate");
				    dateValidation();
				    employeeleave.setAfterleavejoiningdate(Date.valueOf(data));
				    session.update(employeeleave);
				    session.getTransaction().commit();
				    session.close();  
				    }else{
						System.out.println("please enter valid id");
					}
				    //tx.commit();
				  
				}
				catch(Exception e){
					e.printStackTrace();
					tx.rollback(); 
				}
			  System.out.println("Update Successfully");
		 }
	
	 public List<Employeeleave>getEmployeeLeaveAllUsers() throws ClassNotFoundException, SQLException {
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null; 
		  Criteria criteria = session.createCriteria(Employeeleave.class);
			criteria.add(Restrictions.eq("isActive", true));
			List<Employeeleave> employeeleaves =  (List<Employeeleave>) (criteria.list().size() > 0 ? criteria.list() : null);
			if(employeeleaves !=null && ! employeeleaves.isEmpty()){
				tx=session.beginTransaction(); 
			for (Employeeleave employeeleave : employeeleaves) {
				employeeleave.getId();
				employeeleave.getEmployee();
				employeeleave.getSubject();
				employeeleave.getLeavedate();
				employeeleave.getAfterleavejoiningdate();
				employeeleave.getCreatedDate();
				employeeleave.getUpdatedDate();
				System.out.println(employeeleave);
			}
		       session.getTransaction().commit();
		      session.close();
			}else{
				System.out.println("please Insert Data");
			}
		return employeeleaves;
		  
	  } 
	 public void getEmployeeLeaveById() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee();
			Employeeleave employeeleave1 = new Employeeleave();
			System.out.println("Enter Employee Id.");
			integerValidation();
			employee.setId(Integer.parseInt(data));
			employeeleave1.setEmployee(employee);
			Criteria criteria = session.createCriteria(Employeeleave.class);
			criteria.add(Restrictions.eq("employee.id", employeeleave1.getEmployee().getId()));
			Employeeleave employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
					
			System.out.println("Employe Daily Task:" + employeeleave);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
  }
	 public void integerValidation(){
			while (true) {
				//data = sc.next();
				 nullValidation();
				if (EmployeeMain.numberOrNot(data)) {
					break;
				} else {
					System.out.println("please enter only number");
				}

			}
		 }
	 public void stringValidation(){
			while (true) {
				//data = sc.next();
				 nullValidation();
				if (EmployeeMain.isFullname(data)) {
					break;
				} else {
					System.out.println("please enter only character");
				}

			}
		 }
	 public void dateValidation() throws ParseException{
		 while (true) {
			     //data = sc.next();
			      nullValidation();
				if (EmployeeMain.isDate(data)) {
					break;
				} else {
					System.out.println("please enter date yyyy-mm-dd format");

				}

		 }
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
	
