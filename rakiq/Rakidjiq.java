package rakiq;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rakiq.Person.Plod;

public class Rakidjiq extends Person implements Runnable{
	
	private int litriRakiq;

	public Rakidjiq(String name, int age, Kazan k) {
		super(name, age, k);
		this.litriRakiq = 0;
		
	}

	@Override
	public void run() {	
	      while(true){
			this.variRakiq();
	   }
	}
	
	private void variRakiq(){	
		this.litriRakiq = this.getKazan().varqRakiq();	
		this.setPlod(this.getKazan().getPlod());
		this.getKazan().varqRakiq();
		this.alterDB(this.getKazan().getKgRakiq(), this.getPlod(), this);
	}
	
	private void alterDB(int litri, Plod plod, Person p){
		String sql = "UPDATE rakiq SET prod = " + plod + " , litri = " + litri + " , rakidjiq = " +
				"(SELECT person_id FORM people p WHERE p.name = " + p.getName() + " AND p.age = " +p.getAge() ;
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
		} catch (SQLException e) {
			System.out.println("ops");
		}		
	}
}
