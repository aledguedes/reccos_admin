package com.reccos.admin.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.MyMatch;
import com.reccos.admin.repository.MyMatchRepository;

@Service
public class MyMatchService {

	@Autowired
	private MyMatchRepository repository;
	
	public MyMatch listById(Long id) {
		Optional<MyMatch> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! MY_MATCH ID " + id));
	}

	public List<MyMatch> listAll() {
		return repository.findAll();
	}

	public MyMatch create(MyMatch matches) {
//		MyMatch c = new MyMatch();
//		Player player = new Player();
//		player = pRepository.save(contract.getPlayer());
//		c.setPlayer(player);
		return repository.save(matches);
	}

	public MyMatch update(long id, MyMatch matches) {
//		matches.setId(id);
		return repository.save(matches);
	}
}
