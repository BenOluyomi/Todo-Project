package com.example.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.SubjectDto;
import com.example.demo.persistence.domain.Subject;
import com.example.demo.service.SubjectService;

@SpringBootTest
@ActiveProfiles("prod")
public class SubjectControllerUnitTest {
	
	@Autowired
	private SubjectController controller;
	
	@MockBean
	private SubjectService service;
	
	@Autowired
	private  ModelMapper mapper;
	
	private SubjectDto maptoDto(Subject subject) {
		return this.mapper.map(subject,SubjectDto.class);
		
	}
	private final Subject Test_sub_1 = new Subject(1L,"Autobiography");
	private final Subject Test_sub_2 = new Subject(2L,"Cooking");
	private final Subject Test_sub_3 = new Subject(3L,"Cars");
	private final Subject Test_sub_4 = new Subject(4L,"Shoes");
	
	private final List<Subject> LISTOFSUBJECTS= List.of(Test_sub_1,Test_sub_2,Test_sub_3,Test_sub_4);

	@Test
	void createTest() throws Exception {
		when(this.service.create(Test_sub_1)).thenReturn(this.maptoDto(Test_sub_1));
		assertThat(new ResponseEntity<SubjectDto>(this.maptoDto(Test_sub_1),HttpStatus.CREATED)).isEqualTo(this.controller.create(Test_sub_1));
		
		verify(this.service, atLeastOnce()).create(Test_sub_1);
	}
	
	@Test
	void readOneTest() throws Exception {
		when(this.service.readOne(Test_sub_1.getId())).thenReturn(this.maptoDto(Test_sub_1));
		assertThat(new ResponseEntity<SubjectDto>(this.maptoDto(Test_sub_1),HttpStatus.OK)).isEqualTo(this.controller.readOne(Test_sub_1.getId()));
		
		verify(this.service, atLeastOnce()).readOne(Test_sub_1.getId());
}
	@Test
	void readAllTest() throws Exception {
		List<SubjectDto> dtos = LISTOFSUBJECTS.stream().map(this::maptoDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(LISTOFSUBJECTS.stream().map(this::maptoDto).collect(Collectors.toList()));
		
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos,HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}
	@Test
	void UpdateTest() throws Exception {
		when(this.service.update(this.maptoDto(Test_sub_1), Test_sub_1.getId())).thenReturn(this.maptoDto(Test_sub_1));
		assertThat(new ResponseEntity<SubjectDto>(this.maptoDto(Test_sub_1),HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(Test_sub_1.getId(),this.maptoDto(Test_sub_1)));
	verify(this.service, atLeastOnce()).update(this.maptoDto(Test_sub_1), Test_sub_1.getId());
	}

	@Test
	void deleteTest() throws Exception {
		this.controller.delete(Test_sub_1.getId());
		verify(this.service, atLeastOnce()).delete(Test_sub_1.getId());
	}
}