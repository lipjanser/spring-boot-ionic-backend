package com.felipejanser.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejanser.cursomc.domain.Pedido;
import com.felipejanser.cursomc.repositories.PedidoRepository;
import com.felipejanser.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Pedido update(Pedido obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Pedido obj) {
		find(obj.getId());
		repo.deleteById(obj.getId());
	}
	
}
