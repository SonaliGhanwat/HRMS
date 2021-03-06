package com.nextech.hrms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nextech.hrms.main.EmployeeMain;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.util.HibernateUtil;

public class EmployeeDailyTaskDao {
	public static Scanner sc = new Scanner(System.in);
	public static String data ="";
	public void addEmployeedailytaskUser(Employeedailytask employeedailytask) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		
		try{
		 tx=session.beginTransaction();  
		   Employee employee = new Employee();
			//employee.setId(employeeAttendance.getEmployee().getId());
			employee.setId(employeedailytask.getEmployee().getId());
			employeedailytask.setEmployee(employee);
			employeedailytask.setDate(employeedailytask.getDate());
			employeedailytask.setTaskName(employeedailytask.getTaskName());
			employeedailytask.setEstimationTime(employeedailytask.getEstimationTime());
			employeedailytask.setStarttime(employeedailytask.getStarttime());
			employeedailytask.setEndtime(employeedailytask.getEndtime());
			employeedailytask.setTakenTime(employeedailytask.getTakenTime());
			employeedailytask.setCreatedDate(employeedailytask.getCreatedDate());
			employeedailytask.setUpdatedDate(employeedailytask.getUpdatedDate());
			employeedailytask.setIsActive(true);
			session.save(employeedailytask);
			session.getTransaction().commit();
		    session.close();  
			
		    //tx.commit();
		
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback();  
			}
		 System.out.println("Insert Successfully");
	}
	public void deleteEmployeeDailyTask() throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		try{
		  tx=session.beginTransaction();  
		  Employeedailytask employeedailytask1=new Employeedailytask();
    	  System.out.println("Enter EmployeeDailytask Id to delete.");
    	  integerValidation();
    	  employeedailytask1.setId(Integer.parseInt(data));
    	  Employeedailytask employeedailytask = (Employeedailytask)session.get(Employeedailytask.class,employeedailytask1.getId());
    	if(employeedailytask !=null){
    		employeedailytask.setIsActive(false);
    	  session.update(employeedailytask);
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
	public void updateDailyTaskUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				Employeedailytask employeedailytask1 = new Employeedailytask();
				System.out.println("Enter EmployeeDailytask Id to update data.");
				 integerValidation();
		    	 employeedailytask1.setId(Integer.parseInt(data));
			    Employeedailytask employeedailytask = (Employeedailytask)session.get(Employeedailytask.class,employeedailytask1.getId());
			    if(employeedailytask !=null){
			    	System.out.println("Enter TaskName");
			    	stringValidation();
					employeedailytask.setTaskName(data);
					employeedailytask.setTaskName(employeedailytask.getTaskName());
					System.out.println("Enter EstimationTime");
					timeValidation();
					employeedailytask.setEstimationTime(Time.valueOf(data));;
					System.out.println("Enter StartTime");
					timeValidation();
					employeedailytask.setStarttime(Time.valueOf(data));
					Time starttime= employeedailytask.getStarttime();
					System.out.println("Enter EndTime");
					timeValidation();
					employeedailytask.setEndtime(Time.valueOf(data));
					Time endtime = employeedailytask.getEndtime();
					long totaltime = endtime.getTime() - starttime.getTime();
					long diffHours = totaltime / (60 * 60 * 1000) % 24;
					System.out.print("Totaltime\n"  +diffHours);
					employeedailytask.setTakenTime(diffHours);
					System.out.println();
			   
			    session.update(employeedailytask);
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
	 public List<Employeedailytask> getDailyTaskAllUsers() throws ClassNotFoundException, SQLException {
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null; 
		  Criteria criteria = session.createCriteria(Employeedailytask.class);
			criteria.add(Restrictions.eq("isActive", true));
			List<Employeedailytask> employeedailytasks =  (List<Employeedailytask>) (criteria.list().size() > 0 ? criteria.list() : null);
			if(employeedailytasks !=null && ! employeedailytasks.isEmpty()){
				tx=session.beginTransaction(); 
			for (Employeedailytask employeedailytask : employeedailytasks) {
				employeedailytask.getId();
				employeedailytask.getEmployee();
				employeedailytask.getDate();
				employeedailytask.getTaskName();
				employeedailytask.getEstimationTime();
				employeedailytask.getStarttime();
				employeedailytask.getEndtime();
				employeedailytask.getTakenTime();
				employeedailytask.getCreatedDate();
				employeedailytask.getUpdatedDate();
				System.out.println(employeedailytask);
			}
		       session.getTransaction().commit();
		      session.close();
			}else{
				System.out.println("please Insert Data");
			}
		return employeedailytasks;
		  
	  } 
	 public void getDailyTaskUserById() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null; 
			try{
				tx=session.beginTransaction(); 
				Employeedailytask employeedailytask1 = new Employeedailytask();
				System.out.println("Enter Employeedailytask Id .");
				integerValidation();
		    	employeedailytask1.setId(Integer.parseInt(data));			    
		    	Employeedailytask employeedailytask = (Employeedailytask)session.get(Employeedailytask.class,employeedailytask1.getId());
				if(employeedailytask !=null){
			    session.getTransaction().commit();
			    session.close();  
			    //tx.commit();
			    System.out.println(employeedailytask);
				}else{
					System.out.println("please enter valid id");
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback(); 
		}
			
  }
	 
	 public void getUserTaskName() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee();
			Employeedailytask employeedailytask1 = new Employeedailytask();
			System.out.println("Enter Employee Id.");
			integerValidation();
			employee.setId(Integer.parseInt(data));
			employeedailytask1.setEmployee(employee);
			Criteria criteria = session.createCriteria(Employeedailytask.class);
			criteria.add(Restrictions.eq("employee.id", employeedailytask1.getEmployee().getId()));
			//criteria.add(Restrictions.eq("isActive",true));
			Employeedailytask employeedailytask2 = criteria.list().size() > 0 ? (Employeedailytask) criteria.list().get(0) : null;
					
			System.out.println("Employe Daily Task:" + employeedailytask2);
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback(); 
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
					System.out.println("please enter minimum or maximum text 2 to 255");
				}

			}
		 }
	 public void timeValidation() throws ParseException{
		 while (true) {
			     //data = sc.next();
			      nullValidation();
				if (EmployeeMain.isTime(data)) {
					break;
				} else {
					System.out.println("please enter date hh-mm-ss format");

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

	

