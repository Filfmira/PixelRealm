package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Catchable extends Entidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4174858591749112083L;
	protected int capacidade=0;
	protected int tempo=200;
	
	Sprite sprite;
	//Construtores 
	public Catchable(int x, int y) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}
	
	public Catchable(int x, int y, int capacidade) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.setCapacidade(capacidade);
	}
	
	public Catchable(Monstro m) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}
	
	public Catchable(Monstro m, int capacidade) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.setCapacidade(capacidade);
	}
	
	public boolean update()
	{
		if(tempo==0)
			return false;
		else if(tempo <50)
			sprite.setDirection(1);
		tempo--;
		return true;
	}

	public void draw(Canvas canvas, Paint paint){
		sprite.draw(canvas, x * tamanhoCelula+Entidade.dx, y * tamanhoCelula+Entidade.dy);
	}
	
	
	
	
	//Gets e Sets
	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	

}
