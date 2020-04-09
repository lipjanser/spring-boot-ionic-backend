package com.felipejanser.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejanser.cursomc.domain.Cliente;
import com.felipejanser.cursomc.repositories.ClienteRepository;
import com.felipejanser.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
}
