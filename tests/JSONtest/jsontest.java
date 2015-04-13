
import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;

public class jsontest {
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("name",		"foo");
		obj.put("num",		new Integer(100));
		obj.put("balance",	new Double(1000.21));
		obj.put("is_vip",	new Boolean(true));
		obj.put("nickname",	null);
		
		StringWriter out = new StringWriter();
		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonText = out.toString();
		System.out.print(jsonText);
	}
}
