package com.felipejanser.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipejanser.cursomc.domain.Cliente;
import com.felipejanser.cursomc.repositories.ClienteRepository;
import com.felipejanser.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cli = repo.findByEmail(email);
		if(cli == null) {
			throw new ObjectNotFoundException("E-mail não encontrado.");
		}
		
		String newPass = newPassword();
		cli.setSenha(passwordEncoder.encode(newPass));
		repo.save(cli);
		emailService.sendNewPasswordEmail(cli, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0;i < 10;i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) { //Gera um dígito.
			return (char) (rand.nextInt(10) + 48);
		} else if(opt == 1) { // Gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // Gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
}
