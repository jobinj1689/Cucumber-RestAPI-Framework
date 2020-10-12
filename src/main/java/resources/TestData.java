package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestData {
	
	public AddPlace addPlacePayload(double lat, double lng, String name, String website, String language) {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("FourBunglows");
		p.setLanguage(language);
		p.setPhone_number("9988937838");
		p.setWebsite(website);
		p.setName(name);
		List<String> mylist = new ArrayList<String>();
		mylist.add("Shoe Park");
		mylist.add("Shop");
		p.setTypes(mylist);
		
		location l = new location();
		l.setLat(lat);
		l.setLng(lng);
		p.setLocation(l);
		
		return p;
		
	}

	public String deletePlacePayload(String place_id) {
		
		return "{\r\n\"place_id\":\""+place_id+"\"\r\n}";
	}
	
}
