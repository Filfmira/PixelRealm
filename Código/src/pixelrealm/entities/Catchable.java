package pixelrealm.entities;

import pixelrealm.graphics.Sprite;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Catchable extends Entidade{

	//capacidade de um catchable
	protected int capacidade=0;
	//tempo que demora a desaparecer
	protected int tempo=200;
	//sprite de um construtor
	Sprite sprite;

	/**
	 * Gera um catchable na posicao x e y
	 * @param x
	 * @param y
	 */
	public Catchable(int x, int y) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}
	
	/**
	 * Gera um catchable na posicao x e y, com capacidade capacidade
	 * @param x
	 * @param y
	 * @param capacidade
	 */
	public Catchable(int x, int y, int capacidade) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.setCapacidade(capacidade);
	}
	
	/**
	 * Gera um catchable na posicao de um monstro
	 * @param m
	 */
	public Catchable(Monstro m) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}
	
	/**
	 * Gera um catchable na posicao do monstro com determinada capacidade
	 * @param m
	 * @param capacidade
	 */
	public Catchable(Monstro m, int capacidade) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.setCapacidade(capacidade);
	}
	
	
	/**
	 * funcao de update que determina se o catchable deve mudar o nivel da sua sprite
	 * (quando comeca a terminar o seu tempo de existencia) e ser destruido
	 * @return true se nao for para destruir e false se tiver que ser destruido
	 */
	public boolean update()
	{
		if(tempo==0)
			return false;
		else if(tempo <50)
			sprite.setDirection(1);
		tempo--;
		return true;
	}

	/**
	 * Override da funcao draw da classe Entidade para desenhar a sprite
	 */
	public void draw(Canvas canvas, Paint paint){
		sprite.draw(canvas, x * tamanhoCelula+Entidade.dx, y * tamanhoCelula+Entidade.dy);
	}
	
	
	
	
	//////////////////////////////////////////
	/////////// GETTERS E SETTERS  ///////////
	//////////////////////////////////////////
	
	
	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public abstract Bitmap getImagem();
	
	

}
