package com.reccos.admin.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.reccos.admin.model.User;
import com.reccos.admin.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private EmailService emailService;

//	@Autowired
//	private BCryptPasswordEncoder encoder;

	public User recuperarUser(User original) {
		User user = repository.findByEmailOrPassword(original.getEmail(), original.getPassword());
		if (user != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(original.getPassword(), user.getPassword())) {
//				user.setPassword(null);
//				user.setEmail(null);
				System.out.println("deu match!!!");
				return user;
			}
		}
		return null;
	}
	
	public User userByEmail(String user) {
		return repository.findByEmail(user);
	}

	public ArrayList<User> getAll() {
		return (ArrayList<User>) repository.findAll();
	}

	public User newUser(User novo) {
		if (novo.getLogin().length() > 0 && novo.getPassword().length() > 0) {
//			novo.setAtivo(1);
			try {
				novo.setId(null);
//				novo.setPassword(encoder.encode(novo.getPassword()));
				novo.setPassword(novo.getPassword());
				emailService.sendEmail(novo.getEmail(), "Cadastro de Clientes",
						"Seu cadastro foi efetuado com sucesso!");
				repository.save(novo);
				return novo;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public User atualizarUser(User user) {

		User newUser = repository.findByEmailAndCodToken(user.getEmail(), user.getCodToken());
		if (newUser != null) {

			Date diff = new Date(new Date().getTime() - newUser.getValidationToken().getTime());

			if (diff.getTime() / 1000 < 1800) {
//				newUser.setPassword(encoder.encode(user.getPassword()));
				newUser.setPassword(newUser.getPassword());
//				newUser.setCodToken(null);
				return repository.save(newUser);

			} else {
				return null;
			}
		}
		return null;

	}

	public String recuperarSenha(String email) {

		User user = repository.findByEmail(email);
		System.out.println("DEBUG" + user);
		if (user != null) {
			user.setCodToken(createTokenCod(user.getId()));
			user.setValidationToken(new Date());
			repository.save(user);
//			emailService.sendEmail(user.getEmail(), "RECCOS! Resetar Senha",
//					"Seu código para redefinição de senha é: " + user.getCodToken());
			return "Código enviado! " + user.getCodToken();

		}
		return "E-mail não encontrado!";
	}

	public User getById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	private String createTokenCod(Long id) {
		DateFormat format = new SimpleDateFormat("yyyyHHmmssmmddMM");
		String token = RandomString.make(10);
		System.out.println("DEBUG: " + token + format.format(new Date()) + id);
		return id + "R3c" + token + "c0$" + format.format(new Date());
	}
}
