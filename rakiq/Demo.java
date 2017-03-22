package rakiq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Demo {
	public static int count = 1;
	
	public static void main(String[] args) {
		
		Kazan kazanche = new Kazan();
		ArrayList<Thread> rakidjii = new ArrayList<>();
		ArrayList<Thread> berachi = new ArrayList<>();
		ArrayList<Kazan> kazani = new ArrayList<>();
		for(int i = 0 ; i <5; i++){
			Kazan k = new Kazan();
			kazani.add(k);
		}
		
		for(int i = 0; i < 7; i++){
			Berach b = new Berach("berach"+ (i+1), new Random().nextInt(30)+20, kazani.get(new Random().nextInt(5)));
			berachi.add(new Thread(b));
			addToDB(b);
		}
		for(int i = 0; i < 4; i++){
			Rakidjiq r = new Rakidjiq("rakidjiq"+ (i+1), new Random().nextInt(50)+20, kazani.get(new Random().nextInt(5)));
			rakidjii.add(new Thread(r));
			addToDB(r);
		}
		
		
		for (Thread b : berachi){
			b.start();
		}
		for (Thread r : rakidjii){
			r.start();
		}
	
		Thread demon = new Thread(){
			@Override
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("cant sleep");
				}
				File f = new File("File" + count + ".txt");
				try {
					f.createNewFile();
				} catch (IOException e) {
					System.out.println("ops im interrupted");
				}
				PrintWriter fos = null;
				try {
					fos = new PrintWriter(f);
				fos.write("Nai -braniqt plod e " + kazanche.getNaiBranPlod());
				fos.write(kazanche.getSuotnochenie());
				} catch (FileNotFoundException e) {
					System.out.println("ops no file");
				} finally{
					fos.flush();
					fos.close();
				}
				
			}
		};
		demon.setDaemon(true);
		demon.start();	
	}	
	static void addToDB(Person p){

		String sql = " INSERT INTO People (name, age, job) VALUES (?, ?, ?);";
		PreparedStatement st = null;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		} catch (SQLException e2) {
			System.out.println( e2.getMessage());
		}
		ResultSet result = null;
		//get result
		
		try {
			result = st.executeQuery();
			st.setString(1, p.getName());
			st.setInt(2, p.getAge());
			if(p instanceof Berach){
				st.setString(3, "Berach");
			}else{
				st.setString(3, "Rakidjiq");
			}
		} catch (SQLException e) {
			System.out.println("ops");
		}
	}
	
	static void getLitriRakiq(){
		String sql = "SELECT SUM(litri), plod FROM rakiq GROUP BY name";
		PreparedStatement st = null;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		} catch (SQLException e2) {
			System.out.println( e2.getMessage());
		}
		ResultSet result = null;
		try {
			result = st.executeQuery();
		} catch (SQLException e) {
			System.out.println("ops");
		}
		try {
			while(result.next()){
				System.out.println(result.getInt(1) + " litri rakiq ot " + result.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void getNaiRabotliv(){
		String sql = "SELECT p.name form people p join rakiq on people_id = rakidjiq " +
				     "UNION SELECT SUM(litri) FROM rakiq GROUP BY rakidjiq DESR Limit 1;";
		PreparedStatement st = null;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		} catch (SQLException e2) {
			System.out.println( e2.getMessage());
		}
		ResultSet result = null;
		try {
			result = st.executeQuery();
		} catch (SQLException e) {
			System.out.println("ops");
		}
		try {
			while(result.next()){
				System.out.println(result.getString(1) + "svari nai-mnogo rakiq");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void getNaiMladiqt(){
		String sql = "SELECT name FROM people Group by name HAVING MIN(age) and job = 'Berach';";
		PreparedStatement st = null;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		} catch (SQLException e2) {
			System.out.println( e2.getMessage());
		}
		ResultSet result = null;
		try {
			result = st.executeQuery();
		} catch (SQLException e) {
			System.out.println("ops");
		}
		try {
			while(result.next()){
				System.out.println(result.getString(1) + "e nai-mladiqt ot vsi4ki");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}