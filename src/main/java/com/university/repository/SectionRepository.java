package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.model.Section;
@Repository
public interface SectionRepository  extends GenericRepository<Section, Long>{

	@Query("SELECT sec FROM Section sec WHERE sec.status > 0")
	public List<Section> getActiveSection();
	
	
	
}
