package it.polito.tdp.seriea.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class TestDAO {

	public static void main(String[] args) {
		Map<Integer, Season> idMap= new HashMap<>();
		SerieADAO dao = new SerieADAO();

		List<Season> seasons = dao.listSeasons(idMap);
		System.out.println(idMap);
		System.out.println(seasons);
		

		
	}

}
