package com.example.lolitos2;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.Log;

public class GameLogic implements Serializable {

	private static final long serialVersionUID = 5148514249177048314L;
	// trata de lutar o heroi contra os monstros
	static int t = 0;

	/**
	 * Função que trata de lutar o heroi contra todos os monstors
	 * @param jogo
	 * @param gameSurface
	 */
	static public void lutar(Jogo jogo, GameSurface gameSurface) {
		boolean lutar = false;
		for (int i = 0; i < jogo.getInimigos().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getInimigos().get(i))) {
				lutar = true;
				jogo.getHeroi().lutar(jogo.getInimigos().get(i));
				// Log.e("lutar", ""+heroi.getVida());
				if (jogo.getInimigos().get(i).getVida() <= 0) {
					int x = (int) (Math.random() * 4);
					if (x == 1)
						jogo.getGemsAtaque().add(
								new GemsAtaque(jogo.getInimigos().get(i)));
					else
						jogo.getMoedas().add(
								new Moeda(jogo.getInimigos().get(i)));
					jogo.matarMonstro(i);
					if(jogo.getHeroi().getVida()<=0)
					{
						gameSurface.menuPerder();
						return;
					}
					
						
				}
			}
		}
		if (lutar) {
			Vibrator v = (Vibrator) gameSurface.getContext().getSystemService(
					Context.VIBRATOR_SERVICE);

			// Vibrate for 400 milliseconds
			v.vibrate(10);
			if (t == 1) {
				t = 0;
				Entidade.dx = Entidade.dx - 4;
			} else {
				t = 1;
				Entidade.dx = Entidade.dx + 4;
			}
		}
	}

	/**
	 * Funcao para fazer update a posicao das setas do heroi e se estas devem
	 * deixar de existir, consoante o tempo decorrido desde que foram lançadas
	 * @param jogo
	 */
	static public void setasUpdate(Jogo jogo) {
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			for (int j = 0; j < jogo.getSetas().size(); j++)
				if (testaColisao(jogo.getSetas().get(j), jogo.getInimigos()
						.get(i))) {
					jogo.getSetas().get(j).atacar(jogo.getInimigos().get(i));
					if (jogo.getInimigos().get(i).getVida() <= 0) {
						int x = (int) (Math.random() * 4);
						if (x == 1)
							jogo.getGemsAtaque().add(
									new GemsAtaque(jogo.getInimigos().get(i)));
						else
							jogo.getMoedas().add(
									new Moeda(jogo.getInimigos().get(i)));
						jogo.matarMonstro(i);
					}
					jogo.getSetas().remove(j);
				}
		}

		for (int i = 0; i < jogo.getParedes().length; i++)
			for (int k = 0; k < jogo.getParedes()[i].length; k++)
				for (int j = 0; j < jogo.getSetas().size(); j++) {
					if (jogo.getParedes()[i][k] != null
							&& testaColisao(jogo.getSetas().get(j),
									jogo.getParedes()[i][k])) {
						jogo.getSetas().remove(j);
					}
				}
	}

	/**
	 * Funcao que verifica se o jogador nao apanhou qualquer tipo de Gems
	 * @param jogo
	 */
	static public void apanharGems(Jogo jogo) {
		boolean apanhou=false;
		for (int i = 0; i < jogo.getGemsVida().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getGemsVida().get(i))) {
				apanhou=true;
				jogo.getHeroi().apanharCatchable(jogo.getGemsVida().get(i));
				jogo.getGemsVida().remove(i);
			}
		}

		for (int i = 0; i < jogo.getGemsAtaque().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getGemsAtaque().get(i))) {
				apanhou=true;
				jogo.getHeroi().apanharCatchable(jogo.getGemsAtaque().get(i));
				jogo.getGemsAtaque().remove(i);
			}
		}
		
		if(apanhou)
			GameActivity.instance.gem.start();
	}

	/**
	 * Funcao que verifica se o jogador apanhou moedas
	 * @param jogo
	 */
	static public void apanharMoedas(Jogo jogo) {
		boolean apanhou=false;
		for (int i = 0; i < jogo.getMoedas().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getMoedas().get(i))) {
				apanhou=true;
				jogo.getHeroi().apanharCatchable(jogo.getMoedas().get(i));
				jogo.getMoedas().remove(i);
			}
		}
		if(apanhou)
		GameActivity.instance.gem.start();
	}

	/**
	 * Funcao que trata das colisoes entre o heroi e o portal
	 * @param jogo
	 */
	static public void colidePortal(Jogo jogo, GameSurface c)
	{
		if(testaColisao(jogo.getHeroi(),jogo.getPortal()))
		{
			if(jogo.getMonstrosMortos()>=jogo.getPortalNum())
			{
				aumentarNivel(c);
			}
		}
	}
	
	
	static public void aumentarNivel(GameSurface c)
	{
		c.setJogo(new Jogo(c.getJogo().getHeroi()));
		c.getJogo().getHeroi().nivel++;
	}
	/**
	 * Funcao que testa a colisao entre um "heroi" e um outro "objeto"
	 * @param heroi
	 * @param objecto
	 * @return
	 */
	public static boolean testaColisao(Entidade heroi, Entidade objecto) {
		int tamanho = Entidade.tamanhoCelula;
		int xHeroi = heroi.getX(), yHeroi = heroi.getY();
		int xObjecto = objecto.getX() * tamanho + Entidade.dx;
		int yObjecto = objecto.getY() * tamanho + Entidade.dy;

		boolean colideHorDir = xObjecto <= xHeroi + tamanho
				&& xObjecto >= xHeroi;
		boolean colideHorEsq = xObjecto + objecto.getWidth() >= xHeroi
				&& xObjecto + objecto.getWidth() <= xHeroi + tamanho;
		boolean colideVerBaixo = yObjecto <= yHeroi + tamanho
				&& yObjecto >= yHeroi;
		boolean colideVerCima = yObjecto + objecto.getHeight() >= yHeroi
				&& yObjecto + objecto.getHeight() <= yHeroi + tamanho;

		return ((colideVerCima || colideVerBaixo) && (colideHorEsq || colideHorDir));

	}

	public static void adjustDx(int[] x) {

	}

	/**
	 * Funcao que testa se o heroi foi contra um objecto, tendo em conta o deslocamento x
	 * @param heroi
	 * @param objecto
	 * @param x array de delocamento, em que x[0] é o dx e x[1] o dy
	 * @return
	 */
	public static boolean testaColisao(Entidade heroi, Entidade objecto, int[] x) {
		int tamanho = Entidade.tamanhoCelula;
		int xHeroi = heroi.getX(), yHeroi = heroi.getY();
		int xObjecto = objecto.getX() * tamanho + Entidade.dx;
		int yObjecto = objecto.getY() * tamanho + Entidade.dy;

		boolean colideHorDir = xObjecto + x[0] <= xHeroi + tamanho
				&& xObjecto + x[0] >= xHeroi;
		boolean colideHorEsq = xObjecto + tamanho + x[0] >= xHeroi
				&& xObjecto + tamanho + x[0] <= xHeroi + tamanho;
		boolean colideVerBaixo = yObjecto + x[1] <= yHeroi + tamanho
				&& yObjecto + x[1] >= yHeroi;
		boolean colideVerCima = yObjecto + tamanho + x[1] >= yHeroi
				&& yObjecto + tamanho + x[1] <= yHeroi + tamanho;

		return ((colideVerCima || colideVerBaixo) && (colideHorEsq || colideHorDir));

	}

	/**
	 * Se um "heroi" colidir com o "objecto" a sua deslocacao "x" sera alterada
	 * @param heroi
	 * @param objecto
	 * @param x	array de delocamento, em que x[0] é o dx e x[1] o dy
	 * @return	true se colidir e false se nao
	 */
	public static boolean col(Entidade heroi, Entidade objecto, int[] x) {
		int tamanho = Entidade.tamanhoCelula;
		int xHeroi = heroi.getX(), yHeroi = heroi.getY();
		int xObjecto = objecto.getX() * tamanho + Entidade.dx;
		int yObjecto = objecto.getY() * tamanho + Entidade.dy;

		boolean colideHorDir = xObjecto + x[0] <= xHeroi + tamanho
				&& xObjecto + x[0] >= xHeroi;
		boolean colideHorEsq = xObjecto + tamanho + x[0] >= xHeroi
				&& xObjecto + tamanho + x[0] <= xHeroi + tamanho;
		boolean colideVerBaixo = yObjecto + x[1] <= yHeroi + tamanho
				&& yObjecto + x[1] >= yHeroi;
		boolean colideVerCima = yObjecto + tamanho + x[1] >= yHeroi
				&& yObjecto + tamanho + x[1] <= yHeroi + tamanho;

		if (!((colideVerCima || colideVerBaixo) && (colideHorEsq || colideHorDir)))
			return false;

		if (!testaColisao(heroi, objecto, x))
			return false;

		int dx = x[0], dy = x[1];
		// testa ajuste horizontal
		/*
		 * if (colideHorDir) dx = xHeroi+tamanho - xObjecto; else dx = xHeroi -
		 * (xObjecto+tamanho);
		 * 
		 * if (colideVerBaixo) dy = yHeroi + tamanho - yObjecto; else dy =
		 * yHeroi - (xObjecto+tamanho);
		 */

		dx = 0;
		dy = 0;

		// int[] temp = {0, x[1]};
		int[] temp = { dx, x[1] };
		// teste
		if (!testaColisao(heroi, objecto, temp)) { // se for suficiente
			x[0] = dx; // ajusta x[0] e retorna
			/*
			 * Log.e("ajusteDX", x[0] + "#" + x[1] + "#" + Entidade.dx + "#" +
			 * Entidade.dy);
			 */
			return true;
		}

		// senao tem de testar ajuste vertical
		// ajusta
		dy = 0;
		// testa novamente
		temp[0] = x[0];
		temp[1] = dy;
		if (!testaColisao(heroi, objecto, temp)) {
			x[1] = dy;// se funcionar, retorna
			/*
			 * Log.e("ajusteDY", x[0] + "#" + x[1] + "#" + Entidade.dx + "#" +
			 * Entidade.dy);
			 */
			return true;
		}

		x[0] = dx;
		x[1] = dy;
		/*
		 * Log.e("ajusteDXDY", x[0] + "#" + x[1] + "#" + Entidade.dx + "#" +
		 * Entidade.dy);
		 */
		;
		return true;
	}

	/**
	 * Precorre todos os objectos do mapa com que o heroi nao pode colidir e , em caso disso,
	 * processa funcoes como lutar contra monstros
	 * @param jogo
	 * @param x	array de delocamento, em que x[0] é o dx e x[1] o dy
	 * @return
	 */
	public static boolean verificaMovimento(Jogo jogo, int[] x) {

		// precorre todas as paredes e compara
		for (int i = 0; i < jogo.getParedes().length; i++)
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				if (jogo.getParedes()[i][j] != null) {
					if (col(jogo.getHeroi(), jogo.getParedes()[i][j], x))
					{
						
					}

				}
			}
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			if (col(jogo.getHeroi(), jogo.getInimigos().get(i), x)) {
				jogo.getHeroi().lutar(jogo.getInimigos().get(i));
				if (jogo.getInimigos().get(i).getVida() < 0)
					jogo.matarMonstro(i);
			}

		}
		return true;
		// se algum der true, poe o dx e dy a 0

	}

	
	////////////////////////////////////////
	///////////   DESENHAR      ////////////
	////////////////////////////////////////
	
	/**
	 * desenha um minimapa no canto sup direito do mapa para efeitos de testes
	 * @param jogo
	 * @param canvas
	 * @param paint
	 */
	public static void drawMiniMap(Jogo jogo, Canvas canvas, Paint paint) {

		int x1 = jogo.getHeroi().getX();
		int y1 = jogo.getHeroi().getY();

		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			int x = jogo.getInimigos().get(i).getX();
			int y = jogo.getInimigos().get(i).getY();
			canvas.drawRect(x * 4, y * 4, x * 4 + 4, y * 4 + 4, paint);
		}
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText(jogo.getInimigos().get(0).getX() + "-"
				+ jogo.getInimigos().get(0).getY(), 60, 200, paint);

		for (int i = 0; i < jogo.getParedes().length; i++) {
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				paint.setColor(Color.RED);
				if (jogo.getParedes()[i][j] != null) {
					int x = jogo.getParedes()[i][j].getX();
					int y = jogo.getParedes()[i][j].getY();
					canvas.drawRect(x * 4, y * 4, 4 + x * 4, 4 + y * 4, paint);

				}
				paint.setColor(Color.BLUE);
				if (i == x1 && j == y1)
					canvas.drawRect(x1 * 4, y1 * 4, x1 * 4 + 4, y1 * 4 + 4,
							paint);
			}
		}

		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText(x1 + "-" + y1, 60, 250, paint);
	}

	/**
	 * Desenhar o mapa todo
	 * @param jogo
	 * @param canvas
	 * @param paint
	 */
	public static void drawMap(Jogo jogo, Canvas canvas, Paint paint) {

		int x1 = jogo.getHeroi().getX();
		int y1 = jogo.getHeroi().getY();

		Paint paintMonstros = new Paint();

		// Paredes
		for (int i = 0; i < jogo.getParedes().length; i++) {
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				if (jogo.getParedes()[i][j] != null) {
					jogo.getParedes()[i][j].draw(canvas, paint);

				}
			}
		}
		for(int i=0;i<jogo.getFundo().size();i++)
			jogo.getFundo().get(i).draw(canvas, paint);
		// Monstros
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			jogo.getInimigos().get(i).update();
			paintMonstros
					.setAlpha(jogo.getInimigos().get(i).getTransparencia());
			if(jogo.getInimigos().get(i).ataque<=40)
			jogo.getInimigos().get(i).draw(canvas, paintMonstros);
			else
				jogo.getInimigos().get(i).draw(canvas, paint);
				
		}

		jogo.getPortal().draw(canvas, paint,jogo);
		
		drawCatchables(jogo, canvas, paint);

		jogo.getHeroi().draw(canvas, paint);
		
		drawSetas(jogo, canvas, paint);
		

	}

	/**
	 * desenha todos os catchables em jogo
	 * @param jogo
	 * @param canvas
	 * @param paint
	 */
	private static void drawCatchables(Jogo jogo, Canvas canvas, Paint paint) {
		// GemsVida
		for (int i = 0; i < jogo.getGemsVida().size(); i++) {
			if (jogo.getGemsVida().get(i).update())
				jogo.getGemsVida().get(i).draw(canvas, paint);
			else
				jogo.getGemsVida().remove(i);
		}

		// GemsAtaque
		for (int i = 0; i < jogo.getGemsAtaque().size(); i++) {
			if (jogo.getGemsAtaque().get(i).update())
				jogo.getGemsAtaque().get(i).draw(canvas, paint);
			else
				jogo.getGemsAtaque().remove(i);
		}

		// Moedas
		for (int i = 0; i < jogo.getMoedas().size(); i++) {
			if (jogo.getMoedas().get(i).update())
				jogo.getMoedas().get(i).draw(canvas, paint);
			else
				jogo.getMoedas().remove(i);
		}
	}

	/**
	 * desenha todas as setas em jogo
	 * @param jogo
	 * @param canvas
	 * @param paint
	 */
	private static void drawSetas(Jogo jogo, Canvas canvas, Paint paint) {
		paint.setColor(Color.YELLOW);
		for (int i = 0; i < jogo.getSetas().size(); i++) {
			// canvas.drawLine(Entidade.sw/2, Entidade.sh/2,
			// jogo.getSetas().get(i).x, jogo.getSetas().get(i).y, paint);
			jogo.getSetas().get(i).drawDirect(canvas, paint);
			if (!jogo.getSetas().get(i).update())
				jogo.getSetas().remove(i);
		}
	}

	/**
	 * desenha todo o ecra
	 * @param jogo
	 * @param canvas
	 * @param paint
	 */
	public static void desenharEntidades(Jogo jogo, Canvas canvas, Paint paint) {
		// desenhar mini mapa
		drawMap(jogo, canvas, paint);
		// drawMiniMap(jogo,canvas,paint);

		desenharUpdates(jogo, canvas, paint);
	}

	/** coisas como pause, barras de estado, etc
	 * desenha 
	 * @param jogo
	 * @param canvas
	 * @param paint
	 */
	public static void desenharUpdates(Jogo jogo, Canvas canvas, Paint paint) {
		// Desenhar Barra de ataque++
		int x = Entidade.tamanhoCelula * 2 / 3;
		int y = Entidade.tamanhoCelula * 2;
		Paint p = new Paint();
		p.setColor(Color.YELLOW);
		if (jogo.getHeroi().incAtaque > 0)
			canvas.drawRect(
					x,
					y,
					x
							+ (Entidade.tamanhoCelula * 3 / 2 * ((float) (jogo
									.getHeroi().contadorAtaque) / 200)), y
							+ Entidade.tamanhoCelula * 1 / 8, p);
		
		
		//vida
		y = (int) (Entidade.tamanhoCelula * 1.6);
		p.setColor(Color.BLACK);
		canvas.drawRect(x,y,x+ (Entidade.tamanhoCelula * 3 / 2) , y+ Entidade.tamanhoCelula * 1 / 8, p);
		float temp=((float) jogo.getHeroi().getVida() / jogo.getHeroi().getVidaInicial());
		if(temp>=0)
		{
		p.setColor(Color.CYAN);
			canvas.drawRect(x,y,
					x+ (Entidade.tamanhoCelula * 3 / 2 * temp), 
					y+ Entidade.tamanhoCelula * 1 / 8, p);
		}
		
		
		//desenhar monstros mortos
		x=Entidade.sw/4;
		y=Entidade.tamanhoCelula * 1 / 2;
		p.setColor(Color.MAGENTA);
		canvas.drawRect(x,y,x+ x*2, y+ Entidade.tamanhoCelula * 1 / 8, p);
		p.setColor(Color.WHITE);

		
		if(jogo.getMonstrosMortos()>0)
		{
			temp=(int) ((float)(((float)(jogo.getMonstrosMortos()/20.0))*(x*2)));
			if(temp>x*2)
				temp=x*2;
				
			canvas.drawRect(x,y,x+ temp, y+ Entidade.tamanhoCelula * 1 / 8, p);

		}

		// desenhar pausa
		x = Entidade.tamanhoCelula * 1 / 2;
		y = Entidade.tamanhoCelula * 1 / 2;
		canvas.drawBitmap(Imagens.pausa, x, y, paint);
	}
}
