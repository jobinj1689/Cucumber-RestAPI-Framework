package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.location;
import resources.Enum_Usage;
import resources.ReusableCode;
import resources.TestData;

import static org.junit.Assert.*;

public class StepDefination extends ReusableCode{

	ResponseSpecification respSpec;
	RequestSpecification res;
	Response response;
	static String  created_Placeid_Using_AddPlaceAPI;
	JsonPath js;
	TestData td = new TestData();;
	
	@Given("Add Place Payload with {double} {double} {string} {string} {string}")
	public void add_place_payload_with(double lat, double lng, String name, String website, String language) throws IOException {
		
		//ReusableCode r = new ReusableCode();

		 res = given().spec(requestSpecification())
		.body(td.addPlacePayload(lat,lng,name,website,language));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String APIResourceType, String httpMethod) {
		
		Enum_Usage e = Enum_Usage.valueOf(APIResourceType);
		System.out.println(e.getResource());
		
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("Post")){
		response = res.when().post(e.getResource())
				.then().spec(respSpec).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("Get")) {
			response = res.when().get(e.getResource());
		}

	}
	
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	    
	}


	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    String resp = response.asString();
	    js = new JsonPath(resp);
	    
	    assertEquals(js.get(key).toString(),Expectedvalue);
	    
	    
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String apiResourceName) throws IOException {
	    
		created_Placeid_Using_AddPlaceAPI = getJsonPath(response, "place_id");
		 res = given().spec(requestSpecification()).queryParam("place_id", created_Placeid_Using_AddPlaceAPI);
		 user_calls_with_http_request(apiResourceName, "Get");
		 String name_From_GetAPI = getJsonPath(response, "name");
		 assertEquals(name_From_GetAPI,name);
		 
	}

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    
		res = given().spec(requestSpecification()).body(td.deletePlacePayload(created_Placeid_Using_AddPlaceAPI));
		
	}


	
}
