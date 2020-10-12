package Cucumber_Framework.PlaceAPI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.location;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class AddPlace_Test {

	public static void main(String[] args) {	
		
		
	
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress("FourBunglows");
	p.setLanguage("Hindi");
	p.setPhone_number("9988937838");
	p.setWebsite("www.test.com");
	p.setName("Jobins House");
	List<String> mylist = new ArrayList<String>();
	mylist.add("Shoe Park");
	mylist.add("Shop");
	p.setTypes(mylist);
	
	location l = new location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	p.setLocation(l);
	
	RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	
	ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	//RestAssured.baseURI = "https://rahulshettyacademy.com";
	RequestSpecification res = given().spec(reqSpec)
	.body(p);
	
	Response response = res.when().post("/maps/api/place/add/json")
	.then().spec(respSpec).extract().response();
	
	String jsonresponse = response.asString();
	System.out.println(jsonresponse);
	}
}
