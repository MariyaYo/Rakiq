package rakiq;

import java.util.Random;

public class Berach extends Person implements Runnable{
	
	private int kgPlod;
	private int kgObshto;

	public Berach(String name, int age, Kazan k) {
		super(name, age, k);
		this.kgPlod = 0;
	}

	@Override
	public void run() {
		while(true) {
			this.izberiPlod();
			this.beriPlodove();
			this.slojiPlodove();
	         System.out.println("Slojih " + this.getPlod() + " v kazana v nego ima " + this.getKazan().getKgPlod() ); 
	   }
	}
	
	
	private void beriPlodove(){
		this.kgPlod = 1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("cant sleep");
		}		
	}
	
	private void izberiPlod(){
		if(this.getPlod() == null){
			int plod = new Random().nextInt(3);
			switch (plod) {
			case 1:
				this.setPlod(Plod.GROZDE);
				break;
			case 2:
				this.setPlod(Plod.KAISIQ);
				break;
			default:
				this.setPlod(Plod.SLIVI);
				break;
			}
		}
	}
	
	private void slojiPlodove(){
		this.getKazan().addPlod(this.getPlod(), this.kgPlod);
		this.kgObshto += this.kgPlod;
		if(this.kgObshto == 10){
			this.kgObshto = 0;
			this.setPlod(null);
		}
	}
}