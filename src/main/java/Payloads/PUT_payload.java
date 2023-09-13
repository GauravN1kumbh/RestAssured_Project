package Payloads;

public class PUT_payload {

    public static String update_place(String place_id_new){

        return "{\n" +
                "\"place_id\":\"" + place_id_new + "\",\n" +
                "\"address\":\"70 winter walk, USA\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }
}

