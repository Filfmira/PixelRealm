package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Parede extends Entidade {

	/**
	 * Inicializa uma Parede em x,y
	 * @param x
	 * @param y
	 */
	public Parede(int x, int y) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}

	/**
	 * Inicializa uma Parede em x,y com largura x1 e altura y1
	 * @param x
	 * @param y
	 */
	public Parede(int x, int y, int x1, int y1) {
		super(x, y, x1, y1);

	}

	@Override
	public Bitmap getImagem() {
		return Imagens.parede;
	}

}
