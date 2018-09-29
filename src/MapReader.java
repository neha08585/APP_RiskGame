
//Java Program to illustrate reading from FileReader 
//using BufferedReader 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapReader {

	
	public static void main(String[] args) throws Exception {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		File file = new File("resources/maps/Europe.map");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st, st1;
		Map<String, String> innerMap1 = new HashMap<String, String>();
		
		ArrayList<String> mapDetail = new ArrayList<String>();
		ArrayList<String> continentScore = new ArrayList<String>();
		ArrayList<String> territoriesDetail = new ArrayList<String>();

		while ((st = br.readLine()) != null) {
			if (st.equals("[Map]")) {
				while ((st1 = br.readLine()) != null) {
					if (st1.equals("")) {
						break;
					}
					mapDetail.add(0, st1.split("=")[0]);
					mapDetail.add(1, st1.split("=")[1]);

					innerMap1.put(mapDetail.get(0), mapDetail.get(1));
					mapDetail.clear();
				}
				map.put("Map", innerMap1);
			} else if (st.equals("[Continents]")) {
				while ((st1 = br.readLine()) != null) {
					if (st1.equals("")) {
						break;
					}
					continentScore.add(st1.split("=")[0]);
					continentScore.add(st1.split("=")[1]);
					
					innerMap1.put(continentScore.get(0), continentScore.get(1));
					continentScore.clear();
				}
				map.put("Continents", innerMap1);
			} else if (st.equals("[Territories]")) {
				while ((st1 = br.readLine()) != null) {
					if (st1.equals("")) {
						continue;
					}

					territoriesDetail.add(st1.substring(0, st1.indexOf(",")));
					territoriesDetail.add(st1.substring(st1.indexOf(",") + 1));

					innerMap1.put(territoriesDetail.get(0), territoriesDetail.get(1));
					territoriesDetail.clear();
				}
				map.put("Territories", innerMap1);
			}

		}
		//Displaying Score of Russia continent
		System.out.println("Score of Russia " + map.get("Continents").get("Russia"));
		
		//Displaying author name
		System.out.println("Author name " + map.get("Map").get("author"));
		
		//Displaying Co-ordinates,continent,neighbor countries of Ireland
		System.out.println("Detail of Ireland " + map.get("Territories").get("Ireland"));
		
		//Displaying all countries in one .map file
		System.out.println("All countries in a map file " + map.get("Territories").keySet());
	}	
}
