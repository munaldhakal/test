package com.test.example.demo.dto;

import javax.validation.constraints.NotNull;

public class CourseDto {
	@NotNull(message="Name cannot be null")
	private String name;
	@NotNull(message="descriptionn cannot be null")
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
