/***
 * Rest Assured worked in Given, When & then only -
 * Given - Pre-Requisites like what we have - [Query Parameter , Headers & Body]
 * When - The Actions / Conditions - [HTTP Method & Resource Name]
 * Then - Validate the Response - [assertions for Status Code check or may be Response check]
 * * * *
 *
 * * * * *
 */

package core;

import Methods.ResusableMethod;
import Payloads.POST_payload;
import Payloads.PUT_payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import javax.xml.transform.stream.StreamSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Basics {

    public static void main(String[] args) {

        // Task 1 - Add New Place - POST
        RestAssured.baseURI= "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(POST_payload.AddPlace())
                .when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        JsonPath request_Parse = ResusableMethod.rowToJson(POST_payload.AddPlace());
        String req_address = request_Parse.getString("address");
        System.out.println(req_address);


        System.out.println("=============== Extracted Response ==========================");
        System.out.println(response);

        JsonPath js = new JsonPath(response); // For Parsing JSON
        String Place_ID = js.getString("place_id");
        System.out.println(Place_ID);

        //Task 2 - Get place id - Verify the address - [29, side layout, cohen 09] - GET
        // DIff Ways to verify the Address -
        // Way - 1 -  Direct into the method Chain
        given().log().all().queryParam("key","qaclick123").queryParam("place_id",Place_ID).when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).body("address",equalTo("29, side layout, cohen 09")).header("Server","Apache/2.4.52 (Ubuntu)");

        // Way - 2 - Parse the JSON Response & Compare it with Place_ID.
        String address_fetched = given().log().all().queryParam("key","qaclick123").queryParam("place_id",Place_ID)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println(address_fetched);

        JsonPath js_add = ResusableMethod.rowToJson(address_fetched);;
        String address_added = js_add.getString("address");

//        if (address_added.equals(req_address)){
//            System.out.println("Both the Address are Equal - Before Change");
//        }else {
//            System.out.println("Both the address are Not Equal - Before Change");
//        }

        Assert.assertEquals(address_added,req_address);  // Using TestNg Assertions

        // Task 3 - Update the place with New - Address - PUT
        given().log().all().queryParam("key","qaclick123").queryParam("place_id",Place_ID).header("Content-Type","application/json")
                .body(PUT_payload.update_place(Place_ID))
                .when().put("/maps/api/place/update/json").then().assertThat().statusCode(200);

        given().log().all().queryParam("key","qaclick123").queryParam("place_id",Place_ID).when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).body("address",equalTo("70 winter walk, USA")).header("Server","Apache/2.4.52 (Ubuntu)");
    }
}
