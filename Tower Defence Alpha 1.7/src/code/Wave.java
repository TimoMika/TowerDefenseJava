package code;

import java.util.ArrayList;

import processing.core.PVector;

public class Wave {
	
	Main main;
	Spiel spiel;	
	ArrayList<int[]> gegenerGruppen;
	int offsetTime;
	int waveTimer = 0;
	int gruppenTimer = 0;
	int aktiveGruppeIndex = 0;

	public Wave(Main ma, ArrayList<int[]> gruppenList, int offset) {
		main = ma;
		gegenerGruppen = gruppenList;
		offsetTime = offset;
		spiel = main.spiel();
	}
	
	void neuerGegner(int offsetPos, int type) {
		Gegner g = new Gegner(main);
		if(type == 1){g = new GegnerType1(main);}
		if(type == 2){g = new GegnerType2(main);}
		
		g.bewegungsRichtung = spiel.wendepunkte_dir.get(0);
		PVector spawnPosition = new PVector();
		spawnPosition.x = spiel.wendepunkte_pos.get(0).x + (spiel.wendepunkte_dir.get(0).x * -offsetPos);
		spawnPosition.y = spiel.wendepunkte_pos.get(0).y + (spiel.wendepunkte_dir.get(0).y * -offsetPos);
		g.setzePosition(spawnPosition);
		spiel.gegners.add(g);
		g.init_ausrichtung();
	}
	void neueWelle(int type, int laenge, int offset) {
		for(int i = 1; i <= laenge; i++) {
			neuerGegner(i*offset, type);
		}
	}

	public void machDeinDing() {
		try {
			if(waveTimer > spiel.waveOffsetTime) {
			
				int[] gruppe = gegenerGruppen.get(aktiveGruppeIndex);
			
				if(gruppenTimer > gruppe[3]) {
					neueWelle(gruppe[0], gruppe[1], gruppe[2]);
					gruppenTimer = 0;
					if(aktiveGruppeIndex >= gegenerGruppen.size() - 1) {
						spiel.aktiveWave = spiel.waves.get(spiel.waves.indexOf(spiel.aktiveWave) + 1);
					} else {aktiveGruppeIndex ++;}
			
				} else {gruppenTimer ++;}
			
			}	else {waveTimer ++;}
		
		} catch (Exception e) {
			System.err.println(e);
			spiel.aktiveWave = null;
		}
	}
	
}
