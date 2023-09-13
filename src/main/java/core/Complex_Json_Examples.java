package core;

import Payloads.Mock_Request;
import io.restassured.path.json.JsonPath;

public class Complex_Json_Examples {

    public static void main(String[] args) {

        JsonPath request_parse = new JsonPath(Mock_Request.dummy_request());

        // Task 1 - Print No of courses returned by API
        System.out.println(request_parse.getInt("courses.size()"));

        // Task 2 - Print Purchase Amount
        System.out.println(request_parse.getInt("dashboard.purchaseAmount"));

        // Task 3 - Print Title of the first course
        System.out.println(request_parse.getString("courses[0].title"));

        // Task 4 -  Print All course titles and their respective Prices
        for (int i = 0 ; i < request_parse.getInt("courses.size()") ; i++ ){
            System.out.println(request_parse.getString("courses["+i+"].title"));
            System.out.println(request_parse.getString("courses["+i+"].price"));
        }

        // Task 5 - Print no of copies sold by RPA Course
        for (int i = 0 ; i < request_parse.getInt("courses.size()") ; i++ ) {
            String RPA_Course = request_parse.getString("courses[" + i + "].title");
            if (RPA_Course.equals("RPA")){
                System.out.println(request_parse.getString("courses[" + i + "].copies"));
                break;
            }
        }
    }
}
