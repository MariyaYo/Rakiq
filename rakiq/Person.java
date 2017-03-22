package rakiq;

import rakiq.Person.Plod;

public abstract class Person {
	enum Plod {SLIVI, GROZDE, KAISIQ}
	
	protected String name;
	private int age;
	private Plod plod;
	private Kazan kazan;
	
	public Person (String name, int age, Kazan k){
		if(!name.isEmpty() || name == null){
			this.name = name;
		}else{
			this.name = "Jon";
		}
		if (age > 0){
			this.age = age;
		}
		kazan = k;
	}	
		
	protected void izberiKazan(Kazan k){
		//izberi kazan;
	}

	public String getName() {
		return name;
	}

	public Plod getPlod() {
		return plod;
	}

	public void setPlod(Plod plod) {
		this.plod = plod;
	}

	public Kazan getKazan() {
		return kazan;
	}

	public int getAge() {
		return age;
	}
	
	
	
}