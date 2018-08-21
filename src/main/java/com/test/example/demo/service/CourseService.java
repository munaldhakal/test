package com.test.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.example.demo.dto.CourseDto;
import com.test.example.demo.model.Course;
import com.test.example.demo.repository.CourseRepository;
import com.test.example.demo.response.CourseResponse;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	@Transactional
	public void createCourse(CourseDto courseDto) {
		Course course = courseRepository.findByName(courseDto.getName());
		if(course!=null)
			throw new ServiceException("Sorry Course already Exists");
		course = new Course();
		course.setName(courseDto.getName());
		course.setDescription(courseDto.getDescription());
		courseRepository.save(course);
	}
	
	@Transactional
	public List<CourseResponse> getAllCourse(){
		List<CourseResponse> response = new ArrayList<>();
		List<Course> courseList = courseRepository.findAll();
		if(courseList.size()==0)
			throw new ServiceException("No Course to Display");
		for (Course course : courseList) {
			response.add(getCourseResponse(course));
		}
		return response;
	}
	
	private CourseResponse getCourseResponse(Course course) {
		CourseResponse c = new CourseResponse();
		c.setDescription(course.getDescription());
		c.setId(course.getId());
		c.setName(course.getName());		
		return c;
	}

	@Transactional
	public CourseResponse getCourse(Long id) {
		Optional<Course> course = courseRepository.findById(id);
		if(course.get()==null)
			throw new ServiceException("No course found of id "+id);
		return getCourseResponse(course.get());
	}
}
