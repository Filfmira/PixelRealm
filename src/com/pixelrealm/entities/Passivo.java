package com.pixelrealm.entities;

import java.io.Serializable;

import com.pixelrealm.graphics.Imagens;

import android.graphics.Bitmap;

public class Passivo extends Entidade implements Serializable{

	private static final long serialVersionUID = 3920645217356241594L;

	
	private int tipo;

	/**
	 * Inicializa um Passivo em x,y
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param tipo	de passivo(icon)
	 */
	public Passivo(int x, int y, int width, int height, int tipo) {
		super(x, y, width, height);
		this.tipo=tipo;
	}

	/**
	 * Inicializa o passivo em x,y
	 * @param x
	 * @param y
	 * @param tipo de passivo(icon)
	 */
	public Passivo(int x, int y, int tipo) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.tipo=tipo;
	}


	@Override
	public Bitmap getImagem() {
		switch (tipo) {
		case 0:
			return Imagens.getRelva();

		case 1:
			return Imagens.getFlor();
			
		case 2:
			return Imagens.getChao();
		default:
			break;
		}
		return null;
	}
	
	

}
