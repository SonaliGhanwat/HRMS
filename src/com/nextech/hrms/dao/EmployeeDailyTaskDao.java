package com.nextech.hrms.dao;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.util.HibernateUtil;

public class EmployeeDailyTaskDao {
	public static Scanner sc = new Scanner(System.in);
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
			 System.out.println("Insert Successfully");
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback();  
			}
	}
	public void deleteEmployeeDailyTask() throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		try{
		  tx=session.beginTransaction();  
		  Employeedailytask employeedailytask1=new Employeedailytask();
    	  System.out.println("Enter Employee Id to delete.");
    	  int id = Integer.parseInt(sc.nextLine());
    	  employeedailytask1.setId(id);
    	  Employeedailytask employeedailytask = (Employeedailytask)session.get(Employeedailytask.class,employeedailytask1.getId());
    	if(employeedailytask !=null){
    		employeedailytask.setIsActive(false);
    	session.delete(employeedailytask);
		  session.getTransaction().commit();
		  session.close();  
    	}else{
			System.out.println("please enter valid id");
		}
		  System.out.println("Deleted Successfully");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void updateDailyTaskUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				Employeedailytask employeedailytask1 = new Employeedailytask();
				System.out.println("Enter Employee Id to update data.");
			    int id = Integer.parseInt(sc.nextLine());
			    employeedailytask1.setId(id);
			    Employeedailytask employeedailytask = (Employeedailytask)session.get(Employeedailytask.class,employeedailytask1.getId());
			    if(employeedailytask !=null){
			    	System.out.println("Enter TaskName");
					String tsakname=(sc.nextLine());
					employeedailytask.setTaskName(tsakname);
					employeedailytask.setTaskName(employeedailytask.getTaskName());
					System.out.println("Enter EstimationTime");
					Time estimation_time=(Time.valueOf(sc.nextLine()));
					employeedailytask.setEstimationTime(estimation_time);;
					employeedailytask.setEstimationTime(employeedailytask.getEstimationTime());
					System.out.println("Enter StartTime");
					Time starttime=(Time.valueOf(sc.nextLine()));
					employeedailytask.setStarttime(starttime);
					employeedailytask.setStarttime(employeedailytask.getStarttime());
					System.out.println("Enter EndTime");
					Time endtime=(Time.valueOf(sc.nextLine()));
					employeedailytask.setEndtime(endtime);
					employeedailytask.setEndtime(employeedailytask.getEndtime());
					long totaltime = endtime.getTime() - starttime.getTime();
					long diffHours = totaltime / (60 * 60 * 1000) % 24;
					System.out.print("Totaltime\n"  +diffHours);
					employeedailytask.setTakenTime(diffHours);
			   
			    session.update(employeedailytask);
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
				System.out.println("Enter Employee Id .");
			    int id = Integer.parseInt(sc.nextLine());
			    employeedailytask1.setId(id);
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
			int id = Integer.parseInt(sc.nextLine());
			employee.setId(id);
			employeedailytask1.setEmployee(employee);
			Criteria criteria = session.createCriteria(Employeedailytask.class);
			criteria.add(Restrictions.eq("employee.id", employeedailytask1.getEmployee().getId()));
			Employeedailytask employeedailytask2 = criteria.list().size() > 0 ? (Employeedailytask) criteria.list().get(0) : null;
					
			System.out.println("Employe Daily Task:" + employeedailytask2);
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback(); 
		}
  }
	

}