package com.example.collegeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.collegeservice.model.Attendance;
import com.example.collegeservice.model.Student;
import com.example.collegeservice.model.StudentData;

@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${student-service.url}")
	private String studentServiceUrl;

	@Value("${attendance-service.url}")
	private String attendanceServiceUrl;

	@Override
	public StudentData getStudentData(Integer studentId) {
		Student student = restTemplate.getForObject(studentServiceUrl + studentId, Student.class);
		Attendance attendance = restTemplate.getForObject(attendanceServiceUrl + studentId, Attendance.class);
		return new StudentData(studentId, student, attendance);
	}
}
