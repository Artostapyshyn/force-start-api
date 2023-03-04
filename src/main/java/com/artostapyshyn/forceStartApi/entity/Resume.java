package com.artostapyshyn.forceStartApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resume")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resume_id")
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "education", nullable = false)
	private String education;

	@Column(name = "work_history", nullable = false)
	private String workHistory;

	@Column(name = "about", nullable = false)
	private String about;
	
	@Column(name = "skills", nullable = false)
	private String skills;
	
	@Column(name = "languages", nullable = false)
	private String languages;
	
	@Column(name = "personal_info", nullable = false)
	private String personalInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
}
