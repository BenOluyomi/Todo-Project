package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DayDto;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.persistence.domain.Day;
import com.example.demo.persistence.repo.DayRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class DayService {

	// this is where our business logic will happen

//	this is also where CRUD logic will take place.

	// implements are crud functionality
	private DayRepo repo;

	// makes object mapping easy by automatically determining how one object model
	// maps to another.
	private ModelMapper mapper;

	// we create our mapToDto

	private DayDto mapToDTO(Day day) {
		return this.mapper.map(day, DayDto.class);
	}

	@Autowired
	public DayService(DayRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create
	public DayDto create(Day day) {
		return this.mapToDTO(this.repo.save(day));
	}

	// read all method
	public List<DayDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		// stream - returns a sequential stream considering collection as its source
		// map - used to map each element to its corresponding result
		// :: - for each e.g. Random random = new Random();
		// random.ints().limit(10).forEach(System.out::println);
		// Collectors - used to return a list or string
	}

	// read one method
	public DayDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TaskNotFoundException::new));
	}

	// update
	public DayDto update(DayDto dayDto, Long id) {
		// check if record exists
		Day toUpdate = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		// set the record to update
		toUpdate.setName(dayDto.getName());
		// check update for any nulls
		SpringBeanUtil.mergeNotNull(dayDto, toUpdate);
		// retun the method from repo
		return this.mapToDTO(this.repo.save(toUpdate));

	}

	// what happenes when you try to merge null stuff?

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);// true
		return !this.repo.existsById(id);// true
	}

}
