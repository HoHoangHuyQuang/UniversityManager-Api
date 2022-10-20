package com.university.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblStudent")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends User {
	private LocalDate enrollmentDate;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToMany(mappedBy = "student", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Enrollment> enrollments;

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public void addEnrollments(Enrollment enrollment) {
		this.enrollments.add(enrollment);
	}

	public void removeEnrollments(Enrollment enrollment) {
		this.enrollments.remove(enrollment);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String email, String phoneNum, String sex, LocalDate dob,
			LocalDate enrollmentDate, Department dep) {
		super(firstName, lastName, email, phoneNum, sex, dob);
		this.enrollmentDate = enrollmentDate;
		this.enrollments = new ArrayList<Enrollment>();
		this.department = dep;
	}

}
