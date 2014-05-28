package com.example.lolitos2;

public class Projectil extends Entidade{

	float pdx,pdy;
	float a, b;
	int ataque;
	int xInicial,yInicial;
	public Projectil(int x, int y, float pdx, float pdy) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		xInicial=x;
		yInicial=y;
		imagem=Imagens.seta;
		this.pdx=pdx;
		this.pdy=pdy;
		a=x;
		b=y;
		ataque=500;
	}
	
	public boolean update()
	{
		a+=pdx;
		b+=pdy;
		this.x=(int) a;
		this.y=(int) b;
		if(Math.sqrt((Math.pow((x-xInicial),2)+Math.pow((y-yInicial),2)))>400)
			return false;
		return true;
	}
	
	public void atacar(Monstro m){
		m.setVida(m.getVida()-this.ataque);
	}

}
