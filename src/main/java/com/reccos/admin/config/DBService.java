package com.reccos.admin.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.reccos.admin.model.Federation;
import com.reccos.admin.model.Group;
import com.reccos.admin.model.League;
import com.reccos.admin.model.Match;
import com.reccos.admin.model.Player;
import com.reccos.admin.model.Team;
import com.reccos.admin.model.User;
import com.reccos.admin.repository.FederationRepository;
import com.reccos.admin.repository.GroupRepository;
import com.reccos.admin.repository.LeagueRepository;
import com.reccos.admin.repository.MatchRepository;
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
	private FederationRepository federationRepository;

//	@Autowired
//	private PlayerRepository playerRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void InstanciaDB() {

		User user1 = new User(null, "kokhym", "aledguedes@gmail.com", encoder.encode("12345"), 1, null, null, "admin");
		User user2 = new User(null, "Xandão", "xandao@teste.com", encoder.encode("abcd1234"), 1, null, null, "user");

		Team tm1 = new Team(1, "São Paulo Futebol Clube", "São Paulo", null, "SPFC", "Santo Paulo",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Brasao_do_Sao_Paulo_Futebol_Clube.svg/2054px-Brasao_do_Sao_Paulo_Futebol_Clube.svg.png",
				true, null, null, null, null);

		Team tm2 = new Team(2, "Clube de Regatas Flamento", "Flamengo", null, "CRF", "Ninho do Urubu",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Flamengo_braz_logo.svg/800px-Flamengo_braz_logo.svg.png",
				true, null, null, null, null);

		Team tm3 = new Team(3, "Cruzeiro Futebol Clube", "Cruzeiro", null, "CFC", "Toca da Raposa",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Cruzeiro_Esporte_Clube_%28logo%29.svg/2048px-Cruzeiro_Esporte_Clube_%28logo%29.svg.png",
				true, null, null, null, null);

		Team tm4 = new Team(4, "Clube Atlético Mineiro", "Atlético Mineiro", null, "CAM", "Galo Bravo",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Clube_Atl%C3%A9tico_Mineiro_logo.svg/1810px-Clube_Atl%C3%A9tico_Mineiro_logo.svg.png",
				true, null, null, null, null);

		Team tm5 = new Team(5, "Grêmio Football Portoalegrense", "Grêmio", null, "GFPA", "Olímpico",
				"https://upload.wikimedia.org/wikipedia/it/thumb/f/f1/Gremio.svg/1200px-Gremio.svg.png", true, null,
				null, null, null);

		Team tm6 = new Team(6, "Sport Clube Internacional", "Inter", null, "SCI", "Gigante da Beira Rio",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Escudo_do_Sport_Club_Internacional.svg/1024px-Escudo_do_Sport_Club_Internacional.svg.png",
				true, null, null, null, null);

		Team tm7 = new Team(7, "Esporte Clube Bahia", "Bahia", null, "ECB", "Arena Fonte Nova",
				"https://upload.wikimedia.org/wikipedia/en/thumb/2/2c/Esporte_Clube_Bahia_logo.svg/1200px-Esporte_Clube_Bahia_logo.svg.png",
				true, null, null, null, null);

		Team tm8 = new Team(8, "Goiás Esporte Clube", "Goiás", null, "GEC", "Serra Dourada",
				"https://upload.wikimedia.org/wikipedia/commons/f/ff/Goi%C3%A1sLogo21.png", true, null, null, null,
				null);

		Team tm9 = new Team(9, "Sport Club do Recife", "Sport", null, "SCS", "Ilha do Retiro",
				"https://upload.wikimedia.org/wikipedia/commons/4/47/Sportrecife.png", true, null, null, null, null);

		Team tm10 = new Team(10, "Fluminense Football Club", "Fluminense", null, "FFC", "Estádio das Laranjeiras",
				"https://upload.wikimedia.org/wikipedia/pt/thumb/a/a3/FFC_escudo.svg/1200px-FFC_escudo.svg.png", true,
				null, null, null, null);

		Team tm11 = new Team(11, "Associação Chapecoense de Futebol", "Chapecoense", null, "ACF", "Arena Condá",
				"https://upload.wikimedia.org/wikipedia/pt/e/e4/Novo_escudo_da_Associa%C3%A7%C3%A3o_Chapecoense_de_Futebol.png",
				true, null, null, null, null);

		Team tm12 = new Team(12, "Associação Portuguesa de Desportos", "Portuguesa", null, "SCI", "Canindé",
				"https://upload.wikimedia.org/wikipedia/pt/6/65/Associa%C3%A7%C3%A3o_Portuguesa_de_Desportos.png", true,
				null, null, null, null);

		Federation f1 = new Federation(1, "LIGA NORDESTINA DE FUTEBOL", "AGRESTE", null, null, true, null, null, null);
//		federationRepository.saveAll(Arrays.asList(f1)); 

		League lg1 = new League(1, "El Potato League A", null, null, 1, "Mata-mata", "Futsal", 12, 6, 1, 1, true, null,
				null, f1);

		League lg2 = new League(2, "El Potato League B", null, null, 1, "Mata-mata", "Futsal", 12, 6, 1, 1, true, null,
				null, f1);

		userRepository.saveAll(Arrays.asList(user1, user2));
//		teamRepository.saveAll(Arrays.asList(tm1, tm2, tm3, tm4, tm5, tm6, tm7, tm8, tm9, tm10, tm11, tm12));
//		leagueRepository.saveAll(Arrays.asList(lg1));
//		playerRepository.saveAll(Arrays.asList(p1));
//		matchRepository.saveAll(Arrays.asList(m1));
	}
}
