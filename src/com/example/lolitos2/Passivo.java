package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Passivo extends Entidade implements Serializable{

	private static final long serialVersionUID = 3920645217356241594L;

	
	private int tipo;

	public Passivo(int x, int y, int width, int height, int tipo) {
		super(x, y, width, height);
		this.tipo=tipo;
	}


	public Passivo(int x, int y, int tipo) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.tipo=tipo;
	}


	@Override
	public Bitmap getImagem() {
		switch (tipo) {
		case 0:
			return Imagens.relva;

		case 1:
			return Imagens.flor;
			
		case 2:
			return Imagens.chao;
		default:
			break;
		}
		return null;
	}
	
	

}
