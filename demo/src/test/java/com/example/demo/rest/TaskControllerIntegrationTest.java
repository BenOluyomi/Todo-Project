package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.dto.TaskDto;
import com.example.demo.persistence.domain.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:task-schema.sql", "classpath:task-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "prod")
public class TaskControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	@Autowired
	private ModelMapper mapper;
	
	private TaskDto mapToDTO(Task task) {
		return this.mapper.map(task, TaskDto.class);
	}
	private final Task Test_task_1 = new Task(1L,"Finish Essay");
	private final Task Test_task_2 = new Task(2L,"Answer Questions");
	private final Task Test_task_3 = new Task(3L,"Do Project");
	private final Task Test_task_4 = new Task(4L,"Draw Map");
	
	private final List<Task> LISOFTASKS = List.of(Test_task_1,Test_task_2,Test_task_3,Test_task_4);
	
	private final String URI = "/task";
	
	@Test
	void createTest() throws Exception {
		TaskDto testDTO = mapToDTO(new Task("make shoes"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		
		RequestBuilder request = post(URI+"/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
		
		System.out.println(testDTOAsJSON);
		
		ResultMatcher checkStatus = status().isCreated();
		
		TaskDto testSavedDTO = mapToDTO(new Task("fix phone"));
		testSavedDTO.setId(5L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
		
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	
}
