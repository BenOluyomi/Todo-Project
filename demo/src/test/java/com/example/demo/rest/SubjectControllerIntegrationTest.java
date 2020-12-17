package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.example.demo.dto.SubjectDto;

import com.example.demo.persistence.domain.Subject;

import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:task-schema.sql", "classpath:task-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "prod")
public class SubjectControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	@Autowired
	private ModelMapper mapper;
	
	private SubjectDto mapToDTO(Subject subject) {
		return this.mapper.map(subject, SubjectDto.class);
	}
	private final Subject Test_sub_1 = new Subject(1L,"English");
	private final Subject Test_sub_2 = new Subject(2L,"Maths");
	private final Subject Test_sub_3 = new Subject(3L,"Art");
	private final Subject Test_sub_4 = new Subject(4L,"Geography");
	
	private final List<Subject> LISTOFSUBJECTS = List.of(Test_sub_1,Test_sub_2,Test_sub_3,Test_sub_4);
	
	private final String URI = "/subject";
	
	@Test
	void createTest() throws Exception {
		SubjectDto testDTO = mapToDTO(new Subject("Woodshop"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		
		RequestBuilder request = post(URI+"/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
		
		System.out.println(testDTOAsJSON);
		
		ResultMatcher checkStatus = status().isCreated();
		
		SubjectDto testSavedDTO = mapToDTO(new Subject("Woodshop"));
		testSavedDTO.setId(5L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
		
		 
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	@Test
	void readTest() throws Exception {
		SubjectDto testDTO = mapToDTO(new Subject("English"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		
		RequestBuilder request = get(URI+"/read/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
		
		System.out.println(testDTOAsJSON);
		
		ResultMatcher checkStatus = status().isCreated();
		
		SubjectDto testSavedDTO = mapToDTO(new Subject("English"));
		testSavedDTO.setId(1L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
		
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	@Test
	void updateTest() throws Exception {
		SubjectDto testDTO = mapToDTO(new Subject("Woodshop"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		
		RequestBuilder request = put(URI+"/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
		
		System.out.println(testDTOAsJSON);
		
		ResultMatcher checkStatus = status().isAccepted();
		
		SubjectDto testSavedDTO = mapToDTO(new Subject("Woodshop"));
		testSavedDTO.setId(1L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
		
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void deleteTest() throws Exception {
		SubjectDto testDTO = mapToDTO(new Subject("Woodshop"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		
		RequestBuilder request = delete(URI+"/delete").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
		
		System.out.println(testDTOAsJSON);
		
		ResultMatcher checkStatus = status().isAccepted();
		
		SubjectDto testSavedDTO = mapToDTO(new Subject("Woodshop"));
		testSavedDTO.setId(5L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
		
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	
	
}
