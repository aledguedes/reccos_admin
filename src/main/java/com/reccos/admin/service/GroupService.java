package com.reccos.admin.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Group;
import com.reccos.admin.repository.GroupRepository;
import com.reccos.admin.repository.LeagueRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository repository;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	public Group listById(Long id) { 
		Optional<Group> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! GROUP ID " + id));
	}

	public List<Group> listAll() {
		return repository.findAll();
	}

	public Group create(Group obj) {
//		obj.setId(null);
		return repository.save(obj);
	}

	public Group update(Long id, Group obj) {
		obj.setId(id);
		return repository.save(obj);
	}
	
	public Group groupByLeague(Group obj, Long id) {
		System.out.println("DEBUD ENTREI SERVICE ID: "+obj.getName_group());
		Group grupo = leagueRepository.findById(id).map(v -> {
			long grpId = obj.getId();
			System.out.println("DEBUD ENTREI SERVICE"+grpId);
			
			if (grpId != 0L) {
				Group _grp = repository.findById(grpId)
						.orElseThrow(() -> new ObjectnotFoundException("Not found Tag with id = " + grpId));
				v.addGroup(_grp);
				leagueRepository.save(v);
				return _grp;
			}
			v.addGroup(obj);
			return repository.save(obj);
		}).orElseThrow(() -> new ObjectnotFoundException("Not found Tutorial with id = " + id));

		return grupo;
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
