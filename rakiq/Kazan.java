package rakiq;

import java.util.Random;

import rakiq.Person.Plod;

public class Kazan{
	
	private Plod plod;
	private int kgPlod;
	private int kgRakiq;
	private Rakidjiq rakidjiq;
	private Berach berach;
	
	private static int grozdova;
	private static int kaisieva;
	
	private static int kgKaisii = 0;
	private static int kgGrozde = 0;
	private static int kgSlivi = 0;
	
	public Kazan (){
		this.kgPlod = 0;
		this.kgRakiq = 0;
		this.rakidjiq = new Rakidjiq("Rakidjiq", new Random().nextInt(30)+20, this);
		this.berach = new Berach("berach", new Random().nextInt(50)+20, this);
	}
	
	public synchronized void addPlod(Plod p, int kg){
		if(this.plod == null){
			this.plod = p;
			System.out.println("Prazen kazan");
		}
		while(this.kgPlod >= 10 || this.plod == null){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("ops");
			}
		}
		if(this.kgPlod <= 10){
			if(this.plod == null){
				this.plod = p;
				System.out.println("Prazen kazan");
			}
			if(this.plod.equals(p)){
				this.kgPlod += kg;	
				this.incrementStaticKg(p, kg);
			}
		}
		notifyAll();
	}

	public synchronized  int varqRakiq() {
		while(kgPlod < 10){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("ops");
			}
		}
		this.kgPlod = 0;
		this.kgRakiq = new Random().nextInt(9) + 1;
		//za da nqma 0kg rakiq!
		System.out.println("Rakidjiqta svari " + this.kgRakiq + " litra rakiq napravena ot " + this.getPlod());
		this.incrementStaticRakiq(this.getPlod(), this.kgRakiq);
		this.plod = null;
		notifyAll();
		return this.kgRakiq;
	}

	public Plod getPlod() {
		return plod;
	}

	public int getKgPlod() {
		return kgPlod;
	}

	public int getKgRakiq() {
		return kgRakiq;
	}
	
	
	public String getNaiBranPlod(){
		if(this.kgKaisii > this.kgGrozde && this.kgGrozde > this.kgSlivi){
			return "kaisiq";
		}
		if(this.kgKaisii > this.kgSlivi && this.kgSlivi > this.kgGrozde){
			return "kaisiq";
		}
		if(this.kgGrozde > this.kgKaisii && this.kgKaisii > this.kgSlivi){
			return "Grozde";
		}
		if(this.kgGrozde > this.kgSlivi && this.kgSlivi > this.kgKaisii){
			return "grozde";
		}
		if(this.kgSlivi > this.kgGrozde && this.kgGrozde> this.kgKaisii){
			return "slivi";
		}
		return "Slivi";
	}
	
	
	private synchronized void incrementStaticKg(Plod p, int kg){
		if(p.equals(Plod.GROZDE)){
			Kazan.kgGrozde += kg;
		}
		if(p.equals(Plod.KAISIQ)){
			Kazan.kgKaisii += kg;
		}
		if(p.equals(Plod.SLIVI)){
			Kazan.kgKaisii += kg;
		}
	}
	
	private synchronized void incrementStaticRakiq(Plod p, int litri){
		if(p.equals(Plod.KAISIQ)){
			Kazan.kaisieva +=litri;
		}
		if(p.equals(Plod.GROZDE)){
			Kazan.grozdova +=litri;
		}
	}
	
	public String getSuotnochenie(){
		return "Kaisieva kum grozdova " + Kazan.kaisieva + ":" + Kazan.grozdova;
	}
	
}