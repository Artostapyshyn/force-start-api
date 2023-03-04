package com.artostapyshyn.forceStartApi.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.forceStartApi.entity.Resume;

public interface ResumeService {
	Optional<Resume> findById(Long id);
	
	Resume save(Resume resume);

	List<Resume> findAll();
	
	void deleteById(Long id);
}
