package com.example.lolitos2;

import java.io.Serializable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;

public class GameLogic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5148514249177048314L;
	// trata de lutar o heroi contra os monstros
	static int t = 0;

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
					jogo.getInimigos().remove(i);
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
						jogo.getInimigos().remove(i);
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

	static public void apanharGems(Jogo jogo) {
		for (int i = 0; i < jogo.getGemsVida().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getGemsVida().get(i))) {
				jogo.getHeroi().apanharGemsVida(jogo.getGemsVida().get(i));
				jogo.getGemsVida().remove(i);
			}
		}

		for (int i = 0; i < jogo.getGemsAtaque().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getGemsAtaque().get(i))) {
				jogo.getHeroi().apanharGemsAtaque(jogo.getGemsAtaque().get(i));
				jogo.getGemsAtaque().remove(i);
			}
		}
	}

	static public void apanharMoedas(Jogo jogo) {
		for (int i = 0; i < jogo.getMoedas().size(); i++) {

			if (testaColisao(jogo.getHeroi(), jogo.getMoedas().get(i))) {
				jogo.getHeroi().apanharMoeda(jogo.getMoedas().get(i));
				jogo.getMoedas().remove(i);
			}
		}
	}

	static public void colidePortal(Jogo jogo)
	{
		if(testaColisao(jogo.getHeroi(),jogo.getPortal()))
		{
			jogo.getPortal().addGems(jogo.getHeroi().getDinheiro());
			jogo.getHeroi().setDinheiro(0);
		}
	}
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

	// public static boolean col(int ax1,int ay1, int bx1,int by1, int[] x)
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

	public static boolean verificaMovimento(Jogo jogo, int[] x) {

		// precorre todas as paredes e compara
		for (int i = 0; i < jogo.getParedes().length; i++)
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				if (jogo.getParedes()[i][j] != null) {
					if (col(jogo.getHeroi(), jogo.getParedes()[i][j], x))
						jogo.getParedes()[i][j].color = Color.CYAN;

				}
			}
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			if (col(jogo.getHeroi(), jogo.getInimigos().get(i), x)) {
				jogo.getHeroi().lutar(jogo.getInimigos().get(i));
				if (jogo.getInimigos().get(i).getVida() < 0)
					jogo.getInimigos().remove(i);
			}

		}
		return true;
		// se algum der true, poe o dx e dy a 0

	}

	
	////////////////////////////////////////
	///////////   DESENHAR      ////////////
	////////////////////////////////////////
	
	/*
	 * desenha um minimapa no canto sup direito do mapa para efeitos de testes
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

	/*
	 * Desenha 
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
		// Monstros
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			jogo.getInimigos().get(i).update();
			paintMonstros
					.setAlpha(jogo.getInimigos().get(i).getTransparencia());
			jogo.getInimigos().get(i).draw(canvas, paintMonstros);
		}

		jogo.getPortal().draw(canvas, paint);
		
		drawCatchables(jogo, canvas, paint);

		jogo.getHeroi().draw(canvas, paint);
		
		drawSetas(jogo, canvas, paint);
		

	}

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

	// desenha tudo no mapa
	public static void desenharEntidades(Jogo jogo, Canvas canvas, Paint paint) {
		// desenhar mini mapa
		drawMap(jogo, canvas, paint);
		// drawMiniMap(jogo,canvas,paint);

		desenharUpdates(jogo, canvas, paint);
	}

	public static void desenharUpdates(Jogo jogo, Canvas canvas, Paint paint) {
		// Desenhar Barra de ataque++
		int x = Entidade.tamanhoCelula * 2 / 3;
		int y = Entidade.tamanhoCelula * 4 / 3;
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

		// desenhar pausa
		x = Entidade.tamanhoCelula * 1 / 2;
		y = Entidade.tamanhoCelula * 1 / 2;
		canvas.drawBitmap(Imagens.pausa, x, y, paint);
	}
}
