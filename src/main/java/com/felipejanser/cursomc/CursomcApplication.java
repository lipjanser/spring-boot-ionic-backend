package com.felipejanser.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipejanser.cursomc.domain.Categoria;
import com.felipejanser.cursomc.domain.Cidade;
import com.felipejanser.cursomc.domain.Cliente;
import com.felipejanser.cursomc.domain.Endereco;
import com.felipejanser.cursomc.domain.Estado;
import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.domain.TipoCliente;
import com.felipejanser.cursomc.repositories.CategoriaRepository;
import com.felipejanser.cursomc.repositories.CidadeRepository;
import com.felipejanser.cursomc.repositories.ClienteRepository;
import com.felipejanser.cursomc.repositories.EnderecoRepository;
import com.felipejanser.cursomc.repositories.EstadoRepository;
import com.felipejanser.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "Vitória de Santo Antão", est1);
		Cidade c3 = new Cidade(null, "Gravatá", est1);
		
		Cidade c4 = new Cidade(null, "São Paulo", est2);
		Cidade c5 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1,c2,c3));
		est2.getCidades().addAll(Arrays.asList(c4,c5));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","09574845427", TipoCliente.PESSOAFISICA); 
		
		cli1.getTelefones().addAll(Arrays.asList("1234","5678"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 30","Jardim","50000000",cli1,c1);
		Endereco e2 = new Endereco(null,"Av. Matos","105","Sala 800","Jardim","50000001",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
