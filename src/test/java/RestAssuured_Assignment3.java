import static io.restassured.RestAssured.given;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuured_Assignment3 {

	@Test
	public void jsonBody() {

		RestAssured.baseURI ="http://localhost:3000/";
		JSONObject rootbodyobject = new JSONObject();
		JSONObject categoryobject = new JSONObject();
		JSONObject tagsobject = new JSONObject();
		rootbodyobject.put("id", 0);
		rootbodyobject.put("name", "amit");
		rootbodyobject.put("status", "available");
		
		String name = "vineeta";
		categoryobject.put("id",0);
		categoryobject.put("name", "abcd");
		
		tagsobject.put("id", 0);
		tagsobject.put("name", name);
		
		//Adding the Category and Tags object into the Rootbody
		rootbodyobject.put("category", categoryobject);
		rootbodyobject.put("tags", tagsobject);
		
		
		//JSON Array Body 
		JSONArray arraybody = new JSONArray();
		arraybody.add("abc");
		arraybody.add("values1");
		
		//Adding the Arrayobject into Root body
		rootbodyobject.put("photoUrls", arraybody);
		
		
		
		
		
		//System.out.println(rootbodyobject);
	
		given()
			.body(rootbodyobject.toJSONString())
			.headers("content-type","Application/JSON").
		when()
			.post("students").
		then()
			.log()
			.all();
		
	}
}
