package com.university.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblEnrollment")

@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "enrollment_iD")
public class Enrollment {
	private enum Grade {
		A, B, C, D, F
	}

	@Id
	@SequenceGenerator(name = "enroll_seq", sequenceName = "enroll_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enroll_seq")
	private Long enrollment_iD;
	private LocalDateTime registeredAt;
	private Grade grade;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumns({ @JoinColumn(name = "section_id", referencedColumnName = "section_iD") })
	private Section section;

	public Long getEnrollment_iD() {
		return enrollment_iD;
	}

	public void setEnrollment_iD(Long enrollment_iD) {
		this.enrollment_iD = enrollment_iD;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Enrollment(LocalDateTime registeredAt, Grade grade, Student student, Section section) {
		super();
		this.registeredAt = registeredAt;
		this.grade = grade;
		this.student = student;
		this.section = section;

	}

	public Enrollment() {
		super();
	}

}
