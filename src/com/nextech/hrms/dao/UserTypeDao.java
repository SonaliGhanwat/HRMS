package com.nextech.hrms.dao;

import java.util.Scanner;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.nextech.hrms.main.EmployeeMain;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.util.HibernateUtil;

public class UserTypeDao {
	public static Scanner sc = new Scanner(System.in);
	public static String data ="";
	public void addUserType(Usertype usertype) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		Usertype usertype2 = getEmployeeByEmployeeIdAndDate(usertype.getUsertypeName());
		if(usertype2==null){
		try{
		 tx=session.beginTransaction();  
		 usertype.setId(usertype.getId());
		 usertype.setUsertypeName(usertype.getUsertypeName());
		 usertype.setDescription(usertype.getDescription());
		 usertype.setCreatedDate(usertype.getCreatedDate());
		 usertype.setUpdatedDate(usertype.getUpdatedDate());
		 
		 usertype.setIsActive(true);
			session.save(usertype);
			session.getTransaction().commit();
		    session.close();  
		    //tx.commit();
			
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback();  
			}
			 System.out.println("Insert Successfully");
		}else{
			System.out.println("Id and UserTypeName Already Avilabe ");
		}
		
		}
	 public void deleteUserType() throws ClassNotFoundException {
 		Session session=HibernateUtil.getSessionFactory().openSession();
 		Transaction tx = null;  
 		try{
 		  tx=session.beginTransaction();  
 		 Usertype usertype1=new Usertype();
	    	  System.out.println("Enter userType Id to delete.");
	    	  integerValidation();
	    	  usertype1.setId(Integer.parseInt(data));
	    	  Usertype usertype = (Usertype)session.get(Usertype.class,usertype1.getId());
	    	if(usertype !=null){
	    		usertype.setIsActive(false);
	    	session.update(usertype);
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
	 
	 public void updateUserType() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				Usertype usertype1 = new Usertype();
				System.out.println("Enter userType Id to update data.");
				integerValidation();
				usertype1.setId(Integer.parseInt(data));
				Usertype usertype = (Usertype)session.get(Usertype.class,usertype1.getId());
			    if(usertype !=null){
			    	 System.out.println("Enter Usertype name");
			    	 stringValidation();
			    	 usertype.setUsertypeName(data);
			    	 System.out.println("Enter Description");
			    	 stringValidation();
			    	 usertype.setDescription(data);
			    	 session.update(usertype);
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
	 public void getUserById() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null; 
			try{
				tx=session.beginTransaction(); 
				Usertype usertype1 = new Usertype();
				System.out.println("Enter Employee Id to search data.");
				integerValidation();
				usertype1.setId(Integer.parseInt(data));
				Usertype usertype = (Usertype)session.get(Usertype.class,usertype1.getId());
				if(usertype !=null){
			    session.getTransaction().commit();
			    session.close();  
			    //tx.commit();
			    System.out.println(usertype);
				}else{
					System.out.println("please enter valid id");
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
			
  }
	 private Usertype getEmployeeByEmployeeIdAndDate(String Username){
		 Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;  
		  Criteria criteria = session.createCriteria(Usertype.class);
		  criteria.add(Restrictions.eq("usertypeName",Username));
		  Usertype usertype = criteria.list().size() > 0 ? (Usertype) criteria.list().get(0) : null;
		  return usertype;
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
	 private void stringValidation(){
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
}
