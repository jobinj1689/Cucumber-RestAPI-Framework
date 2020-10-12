package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination sd = new StepDefination();
		if (StepDefination.created_Placeid_Using_AddPlaceAPI == null)
		{		
		sd.add_place_payload_with(-33.842030, 34.0838280, "XYZ House", "Test Site", "Bengali");
		sd.user_calls_with_http_request("AddPlaceAPI", "Post");
		sd.verify_place_id_created_maps_to_using("XYZ House", "GetPlaceAPI");
		
		}

	}
}
