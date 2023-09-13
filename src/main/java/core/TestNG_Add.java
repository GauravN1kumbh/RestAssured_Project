package core;

import Payloads.Mock_Request;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_Add {

    @Test
    public static void match(){

        JsonPath request = new JsonPath(Mock_Request.dummy_request());
         int sum = 0;
        for (int i = 0; i < request.getInt("courses.size()"); i ++){
           int price =  request.getInt("courses["+i+"].price");
           int copies =  request.getInt("courses["+i+"].copies");

           int multi = price * copies;

           sum = sum + multi;

        }
        System.out.println(sum);
        Assert.assertEquals(sum,request.getInt("dashboard.purchaseAmount"));

    }
}
