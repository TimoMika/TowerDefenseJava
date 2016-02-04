package code;

public class Roboter {
	Main main;
	
	String name;
	int exp;
	double rangeMulti;
	double dmgMulti;
	double slowMulti;
	int energySkill;
	int metallSkill;
	int geldSkill;
	int veredelnSkill;
	
	Roboter getrobo(String na){
		if(na == name){
			return this;
		}
		return null;
	}

	
	public Roboter(Main ma,String na) {
		main = ma;
		name = na;
	}
}
