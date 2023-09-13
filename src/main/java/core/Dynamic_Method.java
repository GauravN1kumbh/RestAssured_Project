package core;

import Methods.ResusableMethod;
import Payloads.Dynamic_Request;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dynamic_Method {

    @Test(dataProvider = "AddBook")
    public void parameterised_method(String isbn,String aisle){
       // JsonPath js = ResusableMethod.rowToJson(Dynamic_Request.paramaterized());
       // System.out.println(js.getInt("aisle"));

        RestAssured.baseURI="https://rahulshettyacademy.com";

        // Dynamic -1 - Add Book - POST
        String response = given().header("Content-Type","application/json").body(Dynamic_Request.paramaterized(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().statusCode(200).extract().response().asString();

        System.out.println(response);

    }

    @DataProvider(name = "AddBook")
    public Object[][] getData(){

        return new Object[][] {{"GHI","9012"},{"JKL","3145"},{"MNO","0987"}};

    }
}
