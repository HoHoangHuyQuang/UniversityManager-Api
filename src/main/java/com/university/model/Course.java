package com.university.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblCourse")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "course_iD")
public class Course {
	@Id
	@SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
	private long course_iD;

	private String title;
	private int durations; // số tiết
	private int credits; // số tín chỉ

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "department_id")
	@JsonIgnore
	private Department department; // khoa

	@OneToMany(mappedBy = "course", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Section> sections;

	public Long getCourse_iD() {
		return course_iD;
	}

	public void setCourse_iD(Long course_iD) {
		this.course_iD = course_iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDurations() {
		return durations;
	}

	public void setDurations(int durations) {
		this.durations = durations;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Course(String title, int durations, int credits, Department department) {
		super();
		this.title = title;
		this.durations = durations;
		this.credits = credits;
		this.department = department;
		this.sections = new ArrayList<Section>();
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

}
