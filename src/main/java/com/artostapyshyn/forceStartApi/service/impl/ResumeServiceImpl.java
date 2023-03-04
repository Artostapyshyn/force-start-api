package com.artostapyshyn.forceStartApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artostapyshyn.forceStartApi.entity.Resume;
import com.artostapyshyn.forceStartApi.repository.ResumeRepository;
import com.artostapyshyn.forceStartApi.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeRepository resumeRepository;
	
	@Override
	public Optional<Resume> findById(Long id) {
		return resumeRepository.findById(id);
	}

	@Override
	public Resume save(Resume resume) {
		return resumeRepository.save(resume);
	}

	@Override
	public List<Resume> findAll() {
		return resumeRepository.findAll().stream().toList();
	}

	@Override
	public void deleteById(Long id) {
		resumeRepository.deleteById(id);
	}

}
