package DataDrivenTesting;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDatDromJsonTes {
public static void main(String[] args) throws Throwable {
	//Step 1 : Parse the physical file into the Java object using JsonParser class
	JSONParser pars=new JSONParser();
	Object obj=  pars.parse(new FileReader("./src/test/resources/CommonData.json"));
	//Step 2 : Covert java object into JsonObject using downcasting
	JSONObject jsobj=(JSONObject) obj;
	//Step 3 : Get the value from using JsonObject from the jsonfile using key
	
	System.out.println(jsobj.get("URL"));
	System.out.println(jsobj.get("Browser"));
	System.out.println(jsobj.get("UserName"));
	System.out.println(jsobj.get("Password"));
	
	
	
}

}
