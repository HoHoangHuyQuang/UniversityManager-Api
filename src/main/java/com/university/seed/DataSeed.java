package com.university.seed;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.university.model.Course;
import com.university.model.Department;
import com.university.model.Enrollment;
import com.university.model.Instructor;
import com.university.model.Section;
import com.university.model.Student;
import com.university.model.User;
import com.university.services.CourseServices;
import com.university.services.DepartmentServices;
import com.university.services.EnrollmentServices;
import com.university.services.InstructorServices;
import com.university.services.SectionServices;
import com.university.services.StudentServices;
import com.university.services.UserServices;

@SuppressWarnings("unused")
@Configuration
public class DataSeed {

//	@Bean
//	CommandLineRunner commandLineRunner(StudentServices stdservices, EnrollmentServices enrollmentServices, 
//			DepartmentServices departmentServices, InstructorServices  instructorServices,
//			SectionServices sectionServices, CourseServices courseServices, UserServices userServices) {
//		return args -> {
//
//			User u = new User("User", "1",  null, null, null, null);
//			User u1= new User("User", "2",  null, null, null, null);			
//			userServices.saveAll(List.of(u, u1));
//			
//			
//			Department d = new Department("Depart1", BigDecimal.valueOf(9500000), null, u);
//			Department d1 = new Department("Depart2", BigDecimal.valueOf(7500000), null,u);			
//			departmentServices.saveAll(List.of(d, d1));
//			
//			
//			Student s = new Student("Student", "1", null, null, "male", LocalDate.of(1983, 8, 11),
//					LocalDate.of(2002, 3, 9), d1);
//			Student s1 = new Student("Student", "2", null, null, "female", LocalDate.of(1990, 4, 15),
//					LocalDate.of(2002, 3, 9), d1);			
//			stdservices.saveAll(List.of(s, s1));
//			
//			
//			Instructor i = new Instructor("Instructor", "1", null, null, "female", null, null, d1);
//			Instructor i1 = new Instructor("Instructor", "2", null, null, "female", null, null, d1);
//			instructorServices.saveAll(List.of(i, i1));
//			
//			
//			Course c = new Course("course 1", 0, 0, d1);
//			Course c1 = new Course("course 2", 0, 0, d1);
//			courseServices.saveAll(List.of(c, c1));
//			
//			
//			Section sec = new Section(LocalDate.now(), LocalDate.now().plusMonths(2), null, null, null, 2000, c, 1);
//			Section sec1 = new Section(LocalDate.now(), LocalDate.now().plusMonths(3), null, null, null, 2000, c1, 1);
//			Section sec2 = new Section(LocalDate.now(), LocalDate.now().plusMonths(1), null, null, null, 1999, c1, 0);
//			sectionServices.saveAll(List.of(sec, sec1, sec2));
//			
//
//			Enrollment e1 = new Enrollment(LocalDateTime.now(), null, s, sec);
//			Enrollment e2 = new Enrollment(LocalDateTime.now(), null, s, sec1);
//			enrollmentServices.saveAll(List.of(e1, e2));
//
//			
//
//		};
//	};
}
