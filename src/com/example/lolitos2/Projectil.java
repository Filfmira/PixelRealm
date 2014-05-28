package com.example.lolitos2;

public class Projectil extends Entidade{

	float pdx,pdy;
	float a, b;
	public Projectil(int x, int y, float pdx, float pdy) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		imagem=Imagens.seta;
		this.pdx=pdx;
		this.pdy=pdy;
		a=x;
		b=y;
	}
	
	public void update()
	{
		a+=pdx;
		b+=pdy;
		this.x=(int) a;
		this.y=(int) b;
	}

}
