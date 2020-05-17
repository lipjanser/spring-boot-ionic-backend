package com.felipejanser.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipejanser.cursomc.domain.Categoria;
import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.dto.ProdutoDTO;
import com.felipejanser.cursomc.dto.ProdutoNewDTO;
import com.felipejanser.cursomc.repositories.CategoriaRepository;
import com.felipejanser.cursomc.repositories.ProdutoRepository;
import com.felipejanser.cursomc.services.exceptions.DataIntegrityException;
import com.felipejanser.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
	
	@Transactional
	public Produto insert(Produto obj) {
		obj.setId(null);
		obj = repo.save(obj);
		categoriaRepository.saveAll(obj.getCategorias());
		return obj;
	}
	
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir um Produto com entidades relacionadas! Id: " + id + ", Tipo: " + Produto.class.getName());
		}
	}
	
	public List<Produto> findAll() {
		return repo.findAll();
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Produto fromDTO(ProdutoDTO objDTO) {
		return new Produto(objDTO.getId(),objDTO.getNome(),objDTO.getPreco());
	}
	
	public Produto fromDTO(ProdutoNewDTO objDTO) {
		Produto prd = new Produto(null,objDTO.getNome(),objDTO.getPreco());
		Categoria cat = new Categoria(objDTO.getCategoriaId(), null);
		//Endereco end = new Endereco(null,objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		prd.getCategorias().add(cat);
		//cli.getTelefones().add(objDTO.getTelefone1());
		
		return prd;
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
	}
	
}
