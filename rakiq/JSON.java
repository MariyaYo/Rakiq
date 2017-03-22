//package rakiq;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.file.FileAlreadyExistsException;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//public class JSON {
//	public static void main(String[] args) throws JSONException {
//		
//		String name1 = "pesho";
//		String name2 = "tesho";
//		String name3 = "gesho";
//		String name4 = "fesho";
//		String name5 = "wesho";
//		String name6 = "kesho";
//		String name7 = "lesho";
//		
//		int age1 = 49;
//		int age2 = 19;
//		int age3 = 24;
//		int age4 = 25;
//		int age5 = 30;
//		int age6 = 18;
//		int age7 = 31;
//		
//		JSONObject root = new JSONObject();
//	
//		JSONObject obj = new JSONObject();
//		obj.put("name", name1);
//		obj.put("age", age1);
//		
//		JSONArray array = new JSONArray();
//		array.put(obj);
//		
//		JSONObject obj1 = new JSONObject();
//		obj1.put("name", name2);
//		obj1.put("age", age2);
//		
//		array.put(obj1);
//		
//		
//		
//		root.put("Berachi", array);	
//		
//		JSONObject obj3 = new JSONObject();
//		obj3.put("name", name3);
//		obj3.put("age", age3);
//		
//		JSONArray array1 = new JSONArray();
//		array1.put(obj);
//		
//		JSONObject obj4 = new JSONObject();
//		obj4.put("name", name4);
//		obj4.put("age", age4);
//		
//		array1.put(obj4);
//		
//		root.put("rakijii", array1);
//		
//		System.out.println(root.toString());
//		
//		File f = new File("json.txt");
//		try {
//			f.createNewFile();
//		} catch (IOException e1) {
//			System.out.println(e1.getMessage());
//		}
//		
//		PrintWriter pr = null;;
//		try {
//			pr = new PrintWriter(f);
//		} catch (FileNotFoundException e) {
//			System.out.println("cant find file");
//		}
//		pr.write(root.toString());
//		
//	}
//}
