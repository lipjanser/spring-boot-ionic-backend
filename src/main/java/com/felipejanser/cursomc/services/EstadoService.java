package com.felipejanser.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipejanser.cursomc.domain.Estado;
import com.felipejanser.cursomc.dto.EstadoDTO;
import com.felipejanser.cursomc.repositories.EstadoRepository;
import com.felipejanser.cursomc.services.exceptions.DataIntegrityException;
import com.felipejanser.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll() {
		return repo.findAll();
	}
	
}
