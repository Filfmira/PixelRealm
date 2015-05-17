package pixelrealm.entities;

import java.io.Serializable;

import pixelrealm.game.Jogo;
import pixelrealm.graphics.Imagens;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Monstro extends Personagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201805755334726669L;
	private int originalX;
	private int originalY;
	private int transparencia;
	private int incremento;

	/**
	 * Inicaliza um Monstro um x,y
	 * @param x
	 * @param y
	 */
	public Monstro(int x, int y) {
		super(x, y);
		setOriginalX(x);
		setOriginalY(y);
		setVida(5000);
		setVidaInicial(5000);
		setAtaque(40);
		movimento = 20;
		setTransparencia((int) ((Math.random() * 100) + 100));
		incremento = 1;
	}

	/**
	 * Inicializa um Mosntro em x,y
	 * @param x
	 * @param y
	 * @param btm Bitmap do Monstro
	 * @param ataque
	 * @param vida
	 */
	public Monstro(int x, int y, Bitmap btm, int ataque, int vida) {
		super(x, y);
		setOriginalX(x);
		setOriginalY(y);
		this.setAtaque(ataque);
		this.setVidaInicial(vida);
		this.setVida(vida);
		this.setAtaque(ataque);
	}

	/**
	 * Desenha um Monstro com a percentagem de vida em baixo com barras
	 */
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		paint.setColor(Color.RED);
		canvas.drawRect(x * Entidade.tamanhoCelula + Entidade.dx, y
				* tamanhoCelula + tamanhoCelula * 7 / 8 + Entidade.dy, x
				* tamanhoCelula + Entidade.tamanhoCelula + Entidade.dx, y
				* tamanhoCelula + Entidade.tamanhoCelula + Entidade.dy, paint);
		paint.setColor(Color.CYAN);
		canvas.drawRect(
				x * Entidade.tamanhoCelula + Entidade.dx,
				y * tamanhoCelula + tamanhoCelula * 7 / 8 + Entidade.dy,
				((float) (x
						* tamanhoCelula
						+ (Entidade.tamanhoCelula * ((float) (getVida() / getVidaInicial()))) + Entidade.dx)),
				y * tamanhoCelula + Entidade.tamanhoCelula + Entidade.dy, paint);
	}

	/**
	 * Movimento de um monstro
	 * @param direcao
	 * @param jogo
	 */
	public void movimento(int direcao, Jogo jogo) {
		int xAntigo = this.getX();
		int yAntigo = this.getY();
		super.movimento(direcao);
		if(jogo.getParedes()[this.x][this.y]!=null)
		{
			this.setX(xAntigo);
			this.setY(yAntigo);
		}
	}

	/**
	 * Update à transparencia caso o monsto seja um fantasma
	 */
	public void update() {
		if (getTransparencia() > 235)
			incremento = -17;
		if (getTransparencia() <= 150)
			incremento = 17;
		setTransparencia(getTransparencia() + incremento);
	}

	/**
	 * Se o monstro for atacado comeca a seguir o heroi para o atacar
	 * @param heroi
	 */
	public void movimento(Jogo jogo) {
		if (this.getVida() < getVidaInicial()) {
			if (this.x * Entidade.tamanhoCelula + Entidade.dx > jogo.getHeroi().getX())
				this.movimento(0);
			else if (this.x * Entidade.tamanhoCelula + Entidade.dx < jogo.getHeroi().getX())
				this.movimento(1);
			if (this.y * Entidade.tamanhoCelula + Entidade.dy > jogo.getHeroi().getY())
				this.movimento(2);
			else if (this.y * Entidade.tamanhoCelula + Entidade.dy < jogo.getHeroi().getY())
				this.movimento(3);

		} else
			this.movimento((int) (Math.random() * 4),jogo);

	}


	//////////////////////////////////////////
	/////////// GETTERS E SETTERS  ///////////
	//////////////////////////////////////////

	

	public int getOriginalX() {
		return originalX;
	}

	public void setOriginalX(int originalX) {
		this.originalX = originalX;
	}

	public int getOriginalY() {
		return originalY;
	}

	public void setOriginalY(int originalY) {
		this.originalY = originalY;
	}

	public int getTransparencia() {
		return transparencia;
	}

	public void setTransparencia(int transparencia) {
		this.transparencia = transparencia;
	}
	
	public void setMovimento(int mov) {
		movimento = mov;

	}

	@Override
	public Bitmap getImagem() {
		if(getAtaque()<=40)
		return Imagens.getMonstro();
		else
			return Imagens.getMonstro2();
	}


}
