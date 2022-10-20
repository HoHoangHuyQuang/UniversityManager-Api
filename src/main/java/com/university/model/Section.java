package com.university.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblSection")
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "section_iD")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_seq")
	@SequenceGenerator(name = "section_seq", sequenceName = "section_seq", allocationSize = 1, initialValue = 0)
	@Column(name = "section_id")
	private Long section_iD;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private LocalDate startDate;
	private LocalDate endDate;
	private String room;
	private String schedule;
	private String semester;

	@Column(nullable = true)
	private int year;
	@Column(nullable = true)
	private int status;

	@ManyToMany(mappedBy = "sectionAssignment",  cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Instructor> instructorAssign;

	@OneToMany(mappedBy = "section", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Enrollment> enrollments;

	public Long getSection_iD() {
		return section_iD;
	}

	public void setSection_iD(Long section_iD) {
		this.section_iD = section_iD;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Instructor> getInstructor() {
		return instructorAssign;
	}

	public void setInstructor(List<Instructor> instructor) {
		this.instructorAssign = instructor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public void addInstrutor(Instructor ins) {
		this.instructorAssign.add(ins);
	}
	

	public Section(LocalDate startDate, LocalDate endDate, String room, String schedule, String semester, int year,
			Course course, int status) {
		super();

		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
		this.schedule = schedule;
		this.semester = semester;
		this.year = year;
		this.status = status;

		this.instructorAssign = new ArrayList<Instructor>();
		this.course = course;
		this.enrollments = new ArrayList<Enrollment>();
	}

	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}

}
