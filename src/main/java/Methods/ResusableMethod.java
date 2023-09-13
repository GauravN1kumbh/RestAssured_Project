package Methods;

import Payloads.POST_payload;
import io.restassured.path.json.JsonPath;

public class ResusableMethod {

    public static JsonPath rowToJson(String reqponse){

        return new JsonPath(reqponse);

    }



}
