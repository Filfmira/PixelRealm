package com.example.lolitos2;

public class GemsAtaque extends Entidade{

	int capacidade;
	public GemsAtaque(Monstro m) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		imagem=Imagens.gemsataque;
		capacidade=5000;

	}
}
