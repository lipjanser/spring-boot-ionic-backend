package com.felipejanser.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.dto.ProdutoNewDTO;
import com.felipejanser.cursomc.repositories.ProdutoRepository;
import com.felipejanser.cursomc.resources.exceptions.FieldMessage;

public class ProdutoInsertValidator implements ConstraintValidator<ProdutoInsert, ProdutoNewDTO> {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Override
	public void initialize(ProdutoInsert ann) {
	}

	@Override
	public boolean isValid(ProdutoNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Produto aux = repo.findByNome(objDto.getNome());
		if(aux != null) {
			list.add(new FieldMessage("nome","Nome já existente."));
		}
		
		if(objDto.getPreco() > 0.0) {
			list.add(new FieldMessage("preco","Preço deve ser maior que zero."));
		}		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
