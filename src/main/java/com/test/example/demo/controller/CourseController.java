package com.test.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.demo.dto.CourseDto;
import com.test.example.demo.response.CourseResponse;
import com.test.example.demo.service.CourseService;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createCourse(@RequestBody CourseDto courseDto){
		courseService.createCourse(courseDto);
		return new ResponseEntity<Object>("Sucessfully Created",HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getCourse(){
		List<CourseResponse> response =courseService.getAllCourse();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getCourseById(@PathVariable Long id){
		CourseResponse response =courseService.getCourse(id);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
}
