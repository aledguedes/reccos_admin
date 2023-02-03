package com.reccos.admin.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.reccos.admin.model.League;
import com.reccos.admin.model.Team;
import com.reccos.admin.model.User;
import com.reccos.admin.repository.LeagueRepository;
import com.reccos.admin.repository.TeamRepository;
import com.reccos.admin.repository.UserRepository;

@Service
public class DBService {

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void InstanciaDB() {
		User user = new User(null, "kokhym", "aledguedes@gmail.com", "123456");
		User user1 = new User(null, "alexandre", "aledguedes@gmail.com", "123456");
		
		Team tm1 = new Team
		(1, "São Paulo Futebol Clube", "São Paulo", null, "SPFC", "Santo Paulo", "01100-000", "Rua São Paulo", 1, 
				"Morumbi", null, "São Paulo", "SP",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Brasao_do_Sao_Paulo_Futebol_Clube.svg/2054px-Brasao_do_Sao_Paulo_Futebol_Clube.svg.png",
				null, "(00) 12345-6789", true, null, null);
		Team tm2 = new Team
				(2, "Clube de Regatas Flamento", "Flamengo", null, "CRF", "Ninho do Urubu", "01100-000", "Rua do Aterro", 1, 
						"Aterro", null, "Rio de Janeiro", "RJ",
						"https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Flamengo_braz_logo.svg/800px-Flamengo_braz_logo.svg.png",
						null, "(00) 12345-6789", true, null, null);
		Team tm3 = new Team
				(3, "Cruzeiro Futebol Clube", "Cruzeiro", null, "CFC", "Toca da Raposa", "01100-000", "Rua da Toca", 1, 
						"Toca da Raposa", null, "Belo Hotizonte", "MG",
						"https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Cruzeiro_Esporte_Clube_%28logo%29.svg/2048px-Cruzeiro_Esporte_Clube_%28logo%29.svg.png",
						null, "(00) 12345-6789", true, null, null);
		Team tm4 = new Team
				(4, "Clube Atlético Mineiro", "Atlético Mineiro", null, "CAM", "Galo Bravo", "01100-000", "Rua do Galo", 1, 
						"Galo Bravo", null, "Belo Hotizonte", "MG",
						"https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Clube_Atl%C3%A9tico_Mineiro_logo.svg/1810px-Clube_Atl%C3%A9tico_Mineiro_logo.svg.png",
						null, "(00) 12345-6789", true, null, null);

		Team tm5 = new Team
				(5, "Grêmio Football Portoalegrense", "Grêmio", null, "GFPA", "Olímpico", "01100-000", "Rua do Imortal", 1, 
						"O Imortal", null, "Porto Alegre", "RS",
						"https://upload.wikimedia.org/wikipedia/it/thumb/f/f1/Gremio.svg/1200px-Gremio.svg.png",
						null, "(00) 12345-6789", true, null, null);
		Team tm6 = new Team
				(6, "Sport Clube Internacional", "Inter", null, "SCI", "Gigante da Beira Rio", "01100-000", "Rua do Beira Rio", 1, 
						"Beira Rio", null, "Porto Alegre", "RS",
						"https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Escudo_do_Sport_Club_Internacional.svg/1024px-Escudo_do_Sport_Club_Internacional.svg.png",
						null, "(00) 12345-6789", true, null, null);
		
		League lg1 = new League(1, "El Potato League", "Potato", null, null, "Mata-mata", "Canindé", true, null);
//		League lg2 = new League(2, "El Potato League B", "Potato B", null, null, "Mata-mata", "Canindé", true, null);
		
		userRepository.saveAll(Arrays.asList(user));
		teamRepository.saveAll(Arrays.asList(tm1, tm2, tm3, tm4, tm5, tm6));
		leagueRepository.saveAll(Arrays.asList(lg1));
	}
}
