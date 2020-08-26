package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Relogio {
	
	public int hora, minuto, segundo;
	public String zero[] = {"", "", ""};
	
	public Relogio(int h, int m, int s) {
		this.hora = h;
		this.minuto = m;
		this.segundo = s;
	}
	public void update() {
		this.segundo++;
		if(this.segundo == 60) {
			minuto++;
			segundo = 0;
			if(minuto == 60) {
				hora++;
				minuto = 0;
				if(hora == 24) {
					hora = 0;
					Main.data.incrementDay();
				}
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("MS Comic Sans", Font.BOLD, 150));
		
		if(this.hora < 10) {
			zero[0] = "0";
		}else{
			zero[0] = "";
		}if(this.minuto < 10) {
			zero[1] = "0";
		}else{
			zero[1] = "";
		}if(this.segundo < 10) {
			zero[2] = "0";
		}else{
			zero[2] = "";
		}
		
		g.drawString(zero[0] + this.hora + " : " + zero[1] + this.minuto + " : " + zero[2] + this.segundo, Main.WIDTH / 3 - 240, Main.HEIGHT / 3 - 30);
		Main.data.render(g);
	}
}
