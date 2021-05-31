package Assessment_API.Assessment_API;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.datafromexcel;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAssuured_Assignment3 {

	@Test(dataProvider="datafromexcel_sample")
	public void jsonBody(String name1,String name2, String name3) throws JsonProcessingException {

		RestAssured.baseURI="http://localhost:3000/" ;

		JSONObject rootbodyobject = new JSONObject();
		JSONObject categoryobject = new JSONObject();
		JSONObject tagsobject = new JSONObject();


		rootbodyobject.put("id", 0);
		rootbodyobject.put("name", name1);
		rootbodyobject.put("status", "available");


		categoryobject.put("id",0);
		categoryobject.put("name", name2);

		tagsobject.put("id", 0);
		tagsobject.put("name", name3);

		//Adding the Category and Tags object into the Rootbody
		rootbodyobject.put("category", categoryobject);
		rootbodyobject.put("tags", tagsobject);


		//JSON Array Body 
		JSONArray arraybody = new JSONArray();
		arraybody.add("abc");
		arraybody.add("values1");

		rootbodyobject.put("photoUrls", arraybody);

	
		System.out.println(rootbodyobject);
		Response rs =given()
		.body(rootbodyobject.toJSONString())
		.headers("content-type", "Application/JSON")
		.when()
		.post("students")
		.then().log().all().statusCode(201).extract().response();
		
		int statusCode = rs.getStatusCode();
		Assert.assertEquals(201, statusCode);

	}	

	@DataProvider(name = "datafromexcel_sample")
	public Object[][] exceldata() throws IOException {
		Object[][] data = datafromexcel.gettestdata();

		return data;
	}

}
