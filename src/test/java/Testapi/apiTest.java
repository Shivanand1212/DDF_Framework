package Testapi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
public class apiTest {
	
//@Test
//	
//public void firstAPItest() {
//	
//	Response response=RestAssured.get("https://restful-booker.herokuapp.com/booking/1");
//	
//	System.out.println(response.getStatusCode());
//	System.out.println(response.getStatusLine());
//	System.out.println(response.getBody().asString());
//	System.out.println(response.getTime());
//
//	
//		
//	}

//@Test
//
//public void GETmethod() {
//	
//	RestAssured.baseURI="https://restful-booker.herokuapp.com";
//	
//     given().get("/booking/1").then().statusCode(200).body("bookingdates.checkin", equalTo("2022-06-08"));
//	
//}

@Test

public static void POstmethod() {
	
	RestAssured.baseURI="https://restful-booker.herokuapp.com";
	
	HashMap<String, String> bookingDates= new HashMap<>();
	bookingDates.put("checkin", "2024-05-24");
	bookingDates.put("checkout", "2026-05-24");
	
	HashMap<String, Object > booking=  new HashMap<>();
	
	 booking.put("firstname", "rama");
     booking.put("lastname", "karpe");
     booking.put("totalprice", 228);
     booking.put("depositpaid", false);
     booking.put("bookingdates", bookingDates);
     
     given().contentType(ContentType.JSON).body(booking).post("\booking").then().statusCode(200)
     .body("booking.firstname", equalTo("rama"))
     .body("booking.lastname", equalTo("karpe"))
     .body("booking.bookingdates.checkin", equalTo("2024-05-24"))
     .body("booking.bookingdates.checkout", equalTo("2026-05-24"))
     .extract().response();
}



}
