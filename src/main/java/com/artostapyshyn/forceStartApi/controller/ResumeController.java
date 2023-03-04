package com.artostapyshyn.forceStartApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.forceStartApi.entity.Resume;
import com.artostapyshyn.forceStartApi.exceptions.ProjectNotFoundException;
import com.artostapyshyn.forceStartApi.service.ResumeService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/v1/resume")
@AllArgsConstructor
public class ResumeController {

	private final ResumeService resumeService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Object> getAllResumes() {
		List<Object> response = new ArrayList<>();
		response.add(resumeService.findAll());

		log.info("Listing all resumes");
		return response;
	}

	@GetMapping("/find{id}")
	@ResponseStatus(value = HttpStatus.OK)
	List<Object> getProjectById(@PathParam("id") Long id) {
		List<Object> response = new ArrayList<>();
		Optional<Resume> resumeById = resumeService.findById(id);

		if (resumeById.isEmpty()) {
			throw new ProjectNotFoundException(id.toString());
		} else {
			response.add(resumeById.get());
			log.info("Getting resume by id - " + id);

			return response;
		}
	}

	@PostMapping("/add")
	@ResponseStatus(value = HttpStatus.OK)
	List<Object> addAdvertisement(@Valid @RequestBody Resume resume) {
		List<Object> response = new ArrayList<>();
		Resume newResume = resumeService.save(resume);

		response.add(newResume);
		log.info("Resume added");

		return response;
	}
	
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	List<Object> editAdvertisement(@Valid @RequestBody Resume resume, @PathParam("id") Long id) {
		List<Object> response = new ArrayList<>();

		resume.setId(id);
		Resume res = resumeService.findById(resume.getId()).get();

		//edit(project, prj);

		Resume updatedResume = resumeService.save(res);
		response.add(updatedResume);
		log.info("Project updated");
		
		return response;
	}

	@DeleteMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public List<Object> deleteProject(@PathParam("id") Long id) {
		List<Object> response = new ArrayList<>();
		resumeService.findById(id);
		resumeService.deleteById(id);

		response.add("Your project has been deleted");
		log.info("Project - " + id + " has been deleted");

		return response;
	}
}
