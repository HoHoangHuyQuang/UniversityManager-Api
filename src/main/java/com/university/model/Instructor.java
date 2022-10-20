package com.university.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblInstructor")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Instructor extends User {
	private LocalDate hireDate;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	@JoinTable(name = "instructor_assign", joinColumns = @JoinColumn(name = "instructor_id"), inverseJoinColumns = {
			@JoinColumn(name = "section_id", referencedColumnName = "section_iD")
	})
	private List<Section> sectionAssignment;

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public List<Section> getSections() {
		return sectionAssignment;
	}

	public void setSections(List<Section> sections) {
		this.sectionAssignment = sections;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department_id) {
		this.department = department_id;
	}

	public Instructor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instructor(String firstName, String lastName, String email, String phoneNum, String sex, LocalDate dob,
			LocalDate hireDate, Department department) {
		super(firstName, lastName, email, phoneNum, sex, dob);
		this.hireDate = hireDate;
		this.department = department;
		this.sectionAssignment = new ArrayList<Section>();
	}

}
