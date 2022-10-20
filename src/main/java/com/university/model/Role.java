package com.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "tblRole")
public class Role {
	@Id	
	@SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	@Column(name = "role_id")
	private long roleID;
	private String roleName;
	private String description;
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Role( String roleName, String description) {
		super();		
		this.roleName = roleName;
		this.description = description;
	}
	
	public Role() {
		super();
	}
	
	
	
}
