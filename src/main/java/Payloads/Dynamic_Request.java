package Payloads;

public class Dynamic_Request {

    public static String paramaterized(String isbn, String aisle){

        return "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foer\"\n" +
                "}\n";

    }

}
