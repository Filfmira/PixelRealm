package com.example.lolitos2;

public class Projectil extends Entidade{

	int pdx,pdy;
	
	public Projectil(int x, int y, int pdx, int pdy) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		imagem=Imagens.seta;
		this.pdx=pdx;
		this.pdy=pdy;
	}

}
