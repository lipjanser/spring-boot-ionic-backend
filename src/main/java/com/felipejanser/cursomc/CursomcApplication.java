package com.felipejanser.cursomc;

import java.text.SimpleDateFormat;
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
import com.felipejanser.cursomc.domain.Pagamento;
import com.felipejanser.cursomc.domain.PagamentoComBoleto;
import com.felipejanser.cursomc.domain.PagamentoComCartao;
import com.felipejanser.cursomc.domain.Pedido;
import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.enums.EstadoPagamento;
import com.felipejanser.cursomc.enums.TipoCliente;
import com.felipejanser.cursomc.repositories.CategoriaRepository;
import com.felipejanser.cursomc.repositories.CidadeRepository;
import com.felipejanser.cursomc.repositories.ClienteRepository;
import com.felipejanser.cursomc.repositories.EnderecoRepository;
import com.felipejanser.cursomc.repositories.EstadoRepository;
import com.felipejanser.cursomc.repositories.PagamentoRepository;
import com.felipejanser.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO.getCod(), ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE.getCod(), ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
