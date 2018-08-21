package com.test.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.example.demo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String name);

}
