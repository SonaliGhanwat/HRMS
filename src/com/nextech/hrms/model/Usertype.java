package com.nextech.hrms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the usertype database table.
 * 
 */
@Entity
@NamedQuery(name="Usertype.findAll", query="SELECT u FROM Usertype u")
public class Usertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="usertype_name")
	private String usertypeName;
	
	private String description;

	@Column(name="created_date")
	private Timestamp createdDate;

	

	private boolean isActive;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	

	public Usertype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUsertypeName() {
		return this.usertypeName;
	}

	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}

	@Override
	public String toString() {
		return "Usertype id=" + id + "\n usertypeName=" + usertypeName
				+ "\n description=" + description + "\n createdDate="
				+ createdDate + "\n isActive=" + isActive + "\n updatedDate="
				+ updatedDate ;
	}

}