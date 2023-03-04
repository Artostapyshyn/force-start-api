package com.artostapyshyn.forceStartApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artostapyshyn.forceStartApi.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
