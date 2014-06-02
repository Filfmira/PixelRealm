package com.example.lolitos2;

import java.io.Serializable;

public class Portal extends Entidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8718855254106828232L;
	int numGems;
	public Portal(int x, int y) {
		super(x, y, Entidade.tamanhoCelula*2, Entidade.tamanhoCelula*2);
		imagem=Imagens.portal;
		numGems=0;
	}

	public void addGems(int x)
	{
		numGems+=x;
	}
}
