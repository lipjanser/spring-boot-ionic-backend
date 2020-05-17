package com.felipejanser.cursomc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipejanser.cursomc.domain.Categoria;
import com.felipejanser.cursomc.domain.Produto;;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional
	Produto findByNome(String nome);
	
	//@Transactional
	//A anotação Query sobrepõe o método.
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
