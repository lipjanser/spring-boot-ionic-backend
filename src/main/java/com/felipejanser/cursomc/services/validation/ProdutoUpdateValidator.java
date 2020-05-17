package com.felipejanser.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.dto.ProdutoDTO;
import com.felipejanser.cursomc.repositories.ProdutoRepository;
import com.felipejanser.cursomc.resources.exceptions.FieldMessage;

public class ProdutoUpdateValidator implements ConstraintValidator<ProdutoUpdate, ProdutoDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProdutoRepository repo;
	
	@Override
	public void initialize(ProdutoUpdate ann) {
	}

	public boolean isValid(ProdutoDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Produto aux = repo.findByNome(objDto.getNome());
		if(aux != null && !aux.getId().equals(uriId)) {
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
