package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Data {
	
	public int dia, mes, ano;
	public String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	public String diasSemana[] = {"Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sabado"};
	public int diasSemanaInterator;
	public int diasMeses[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public String zero[] = {"", ""};
	
	public Data(int d, int m, int a, int i) {
		this.dia = d;
		this.mes = m;
		this.ano = a;
		this.diasSemanaInterator = i;
	}
	
	public void incrementDay() {
		this.dia++;
		this.diasSemanaInterator++;
		
		if(this.diasSemanaInterator > 6) {
			this.diasSemanaInterator = 0;
		}
		
		if(this.dia > diasMeses[mes - 1]) {
			this.dia = 1;
			this.incrementMonth();
		}
	}
	public void incrementMonth() {
		this.mes++;
		
		if(this.mes > 12) {
			this.mes = 1;
			this.incrementYear();
		}
	}
	public void incrementYear() {
		this.ano++;
		if(this.ano % 4 == 0) {
			this.diasMeses[1] = 29;
		} else {
			this.diasMeses[1] = 28;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("MS Comic Sans", Font.BOLD, 110));
		
		if(this.dia < 10) {
			zero[0] = "0";
		}else{
			zero[0] = "";
		}if(this.mes < 10) {
			zero[1] = "0";
		}else{
			zero[1] = "";
		}
		
		g.drawString(zero[0] + this.dia + " / " + zero[1] + this.mes + " / " + this.ano, Main.WIDTH / 3 - 200, Main.HEIGHT / 3 + 150);
		if(this.diasSemana[diasSemanaInterator] == "Sabado" || this.diasSemana[diasSemanaInterator] == "Domingo") {
			g.drawString(this.diasSemana[diasSemanaInterator], Main.WIDTH / 3 - 70, Main.HEIGHT / 3 + 250);
		}else {
			g.drawString(this.diasSemana[diasSemanaInterator], Main.WIDTH / 3 - 200, Main.HEIGHT / 3 + 250);
		}
		
	}
		
}
