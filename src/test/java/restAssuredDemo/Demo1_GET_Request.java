package restAssuredDemo;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class Demo1_GET_Request {
	@Test
	public void getListUsers() 
	{
//		String url = "https://reqres.in/api/users?page=2";
//		
//		Response response = RestAssured.get(url);
//		
//		Assert.assertEquals(response.getStatusCode(), 200);
//		int total_pages = response.jsonPath().get("total_pages");
//		Assert.assertEquals(total_pages, 2);
		
		
		given()
		.when()
			.get("http://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void getSingleUser()
	{
		given()
		.when()
			.get("http://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void getSingleUserNotFound()
	{
		given()
		.when()
			.get("http://reqres.in/api/users/23")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void getList()
	{
		given()
		.when()
			.get("http://reqres.in/api/unknown")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void getSingleUserResource() 
	{
		given()
		.when()
			.get("http://reqres.in/api/unknown/2")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK");
	}

	@Test
	public void postApiRequest() {
		String url = "https://reqres.in/api/users";
		String contentType= "application/json";
		String bodyData = "{\r\n" + 
				"    \"name\": \"morpheus\",\r\n" + 
				"    \"job\": \"leader\"\r\n" + 
				"}";
		
		Response response = RestAssured.given().
				contentType(contentType).
				body(bodyData).
				when().
				post(url).
				then().
				extract().
				response();
		Assert.assertEquals(response.getStatusCode(), 201);
		String name = response.jsonPath().get("name");
		Assert.assertEquals(name, "morpheus");
	}
	
	public static HashMap map = new HashMap(); 
	
	@Test
	public void putUpdateUserData()
	{
		String first_name = "Hrittwik";
		String last_name = "Barua";
		String email = "hrittwik@gmail.com";
		
		//String url = "https://reqres.in/api/users/2";
		
		int id = 11;
		
		map.put("first_name", first_name);
		map.put("last_name", last_name);
		map.put("email", email);
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/users/2";
		
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.put()
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deleteUser() 
	{
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/users/2";
		
		given()
		.when()
			.delete()
		.then()
			.statusCode(204);
	}
	
	
	//email and password
	public static HashMap registerMap = new HashMap();
	@Test
	public void setRegisterSuccessful()
	{
		String email = "eve.holt@reqres.in";
		String password = "pistol";
		
		registerMap.put("email", email);
		registerMap.put("password", password);
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/register";
		
		//System.out.println(registerMap);
		
		given()
			.contentType("application/json")
			.body(registerMap)
		.when()
			.post()
		.then()
			.statusCode(200);
			
			
	}
	
	public static HashMap newRegisterMap = new HashMap();
	@Test
	public void registerUnsuccessful()
	{
		String email = "hrittwik@gmail.com";
		
		newRegisterMap.put("email", email);
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/register";
		
		//System.out.println(registerMap);
		
		given()
			.contentType("application/json")
			.body(registerMap)
		.when()
			.post()
		.then()
			.statusCode(400);
			
	}
	
	
	public static HashMap loginMap = new HashMap();
	@Test
	public void successfulLogin()
	{
		String email = "eve.holt@reqres.in";
		String password = "pistol";
		
		loginMap.put("email", email);
		loginMap.put("password", password);
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/login";
		
		
		given()
			.contentType("application/json")
			.body(loginMap)
		.when()
			.post()
		.then()
			.statusCode(200);
	}
	
	
	public static HashMap failedLoginMap = new HashMap();
	@Test
	public void unSuccessfulLogin()
	{
		String email = "eve.holt@reqres.in";
		
		
		failedLoginMap.put("email", email);
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/login";
		
		
		given()
			.contentType("application/json")
			.body(failedLoginMap)
		.when()
			.post()
		.then()
			.statusCode(400);
			
	}
	
	public void getDelayedResponse()
	{
		given()
			.contentType("application/json")
		.when()
			.get("/api/users?delay=3")
		.then()
			.statusCode(200);
	}
	
	
	
	
	
}
