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
import com.felipejanser.cursomc.domain.ItemPedido;
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
import com.felipejanser.cursomc.repositories.ItemPedidoRepository;
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
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama");
		Categoria cat4 = new Categoria(null,"Mesa");
		Categoria cat5 = new Categoria(null,"Banho");
		Categoria cat6 = new Categoria(null,"Jardim");
		Categoria cat7 = new Categoria(null,"Cozinha");		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		Produto p4 = new Produto(null,"Mese de Escritório",300.00);
		Produto p5 = new Produto(null,"Toalha",50.00);
		Produto p6 = new Produto(null,"Colcha",200.00);
		Produto p7 = new Produto(null,"TV true color",1200.00);
		Produto p8 = new Produto(null,"Roçadeira",800.00);
		Produto p9 = new Produto(null,"Abajour",100.00);
		Produto p10 = new Produto(null,"Pendente",180.00);
		Produto p11 = new Produto(null,"Shampoo",90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
		Cliente cli2 = new Cliente(null,"João Silva","joao@gmail.com","98765432127", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("1234","5678"));
		cli2.getTelefones().addAll(Arrays.asList("1234","5678","12342134"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 30","Jardim","50000000",cli1,c1);
		Endereco e2 = new Endereco(null,"Av. Matos","105","Sala 800","Jardim","50000001",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1,p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
