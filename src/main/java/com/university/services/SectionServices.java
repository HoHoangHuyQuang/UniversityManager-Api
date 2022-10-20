package com.university.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Section;
import com.university.repository.SectionRepository;
@Service
@Transactional
public class SectionServices extends GenericServices<Section, Long> {


	private final SectionRepository sectionRepository;

	public SectionServices( SectionRepository sectionRepository) {
		super(sectionRepository);
		this.sectionRepository = sectionRepository;
	}
	public List<Section> getActiveSection(){
		try {
			return sectionRepository.getActiveSection();
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	
}
