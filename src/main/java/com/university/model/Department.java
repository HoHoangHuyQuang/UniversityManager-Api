package com.university.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblDepartment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "department_iD")

public class Department {
	@Id
	@SequenceGenerator(name = "dep_seq", sequenceName = "dep_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dep_seq")
	private long department_iD;
	private String name;
	private BigDecimal budget;
	private LocalDate startDate;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "admin_id")
	private User administrator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public User getAdministrator() {
		return administrator;
	}

	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public long getDepartment_iD() {
		return department_iD;
	}

	public void setDepartment_iD(long department_iD) {
		this.department_iD = department_iD;
	}

	public Department(String name, BigDecimal budget, LocalDate startDate, User administrator) {
		super();
		this.name = name;
		this.budget = budget;
		this.startDate = startDate;
		this.administrator = administrator;

	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

}
