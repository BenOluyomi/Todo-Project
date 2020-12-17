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

import com.example.demo.dto.TaskDto;
import com.example.demo.persistence.domain.Task;
import com.example.demo.service.TaskService;

@SpringBootTest
@ActiveProfiles("prod")
public class TaskControllerUnitTest {
	
	@Autowired
	private TaskController controller;
	
	@MockBean
	private TaskService service;
	
	@Autowired
	private  ModelMapper mapper;
	
	private TaskDto maptoDto(Task task) {
		return this.mapper.map(task,TaskDto.class);
		 
	}
	private final Task Test_task_1 = new Task(1L,"Autobiography");
	private final Task Test_task_2 = new Task(2L,"Cooking");
	private final Task Test_task_3 = new Task(3L,"Cars");
	private final Task Test_task_4 = new Task(4L,"Shoes");
	
	private final List<Task> LISTOFTASKS = List.of(Test_task_1,Test_task_2,Test_task_3,Test_task_4);

	@Test 
	void createTest() throws Exception {
		when(this.service.create(Test_task_1)).thenReturn(this.maptoDto(Test_task_1));
		assertThat(new ResponseEntity<TaskDto>(this.maptoDto(Test_task_1),HttpStatus.CREATED)).isEqualTo(this.controller.create(Test_task_1));
		
		verify(this.service, atLeastOnce()).create(Test_task_1);
	} 
	
	@Test
	void readOneTest() throws Exception {
		when(this.service.readOne(Test_task_1.getId())).thenReturn(this.maptoDto(Test_task_1));
		assertThat(new ResponseEntity<TaskDto>(this.maptoDto(Test_task_1),HttpStatus.OK)).isEqualTo(this.controller.readOne(Test_task_1.getId()));
		
		verify(this.service, atLeastOnce()).readOne(Test_task_1.getId());
}
	@Test
	void readAllTest() throws Exception {
		List<TaskDto> dtos = LISTOFTASKS.stream().map(this::maptoDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(LISTOFTASKS.stream().map(this::maptoDto).collect(Collectors.toList()));
		
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos,HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}
	@Test
	void UpdateTest() throws Exception {
		when(this.service.update(this.maptoDto(Test_task_1), Test_task_1.getId())).thenReturn(this.maptoDto(Test_task_1));
		assertThat(new ResponseEntity<TaskDto>(this.maptoDto(Test_task_1),HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(Test_task_1.getId(),this.maptoDto(Test_task_1)));
	verify(this.service, atLeastOnce()).update(this.maptoDto(Test_task_1), Test_task_1.getId());
	}

	@Test
	void deketeTest() throws Exception {
		this.controller.delete(Test_task_1.getId());
		verify(this.service, atLeastOnce()).delete(Test_task_1.getId());
	}
}