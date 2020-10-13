package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReusableCode {

	public static RequestSpecification reqSpec;

	public RequestSpecification requestSpecification() throws IOException {
	
		if(reqSpec == null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		//getGlobalValue("baseURI"
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
		.addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		
		return reqSpec;
		}
		return reqSpec;
		
	}
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(".//src/main/java/resources/Globaldata.properties");
		prop.load(fis);
		return  prop.getProperty(key);
		
	
	}

	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
	}
	

}
