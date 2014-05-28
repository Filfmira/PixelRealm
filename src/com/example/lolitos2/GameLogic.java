package com.example.lolitos2;

import java.util.ArrayList;

import jogo.view.GameView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GameLogic {

	// trata de lutar o heroi contra os monstros
	static public void lutar(ArrayList<Monstro> inimigos, Heroi heroi) {
		for (int i = 0; i < inimigos.size(); i++) {

			if (testaColisao(heroi, inimigos.get(i))) {
				heroi.lutar(inimigos.get(i));
				// Log.e("lutar", ""+heroi.getVida());
				if (inimigos.get(i).getVida() < 0)
					inimigos.remove(i);
			}
		}
	}
	


	static public void apanharGems(ArrayList<GemsVida> gemsVida, Heroi heroi) {
		for (int i = 0; i < gemsVida.size(); i++) {

			if (testaColisao(heroi, gemsVida.get(i))) {
				heroi.apanharGemsVida(gemsVida.get(i));
				gemsVida.remove(i);
			}
		}
	}

	public static boolean testaColisao(Entidade heroi, Entidade objecto) {
		int tamanho = Entidade.tamanhoCelula;
		int xHeroi = heroi.getX(), yHeroi = heroi.getY();
		int xObjecto = objecto.getX() * tamanho + Entidade.dx;
		int yObjecto = objecto.getY() * tamanho + Entidade.dy;

		boolean colideHorDir = xObjecto <= xHeroi + tamanho
				&& xObjecto >= xHeroi;
		boolean colideHorEsq = xObjecto + tamanho >= xHeroi
				&& xObjecto + tamanho <= xHeroi + tamanho;
		boolean colideVerBaixo = yObjecto <= yHeroi + tamanho
				&& yObjecto >= yHeroi;
		boolean colideVerCima = yObjecto + tamanho >= yHeroi
				&& yObjecto + tamanho <= yHeroi + tamanho;

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
			Log.e("ajusteDX", x[0] + "#" + x[1] + "#" + Entidade.dx + "#"
					+ Entidade.dy);
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
			Log.e("ajusteDY", x[0] + "#" + x[1] + "#" + Entidade.dx + "#"
					+ Entidade.dy);
			return true;
		}

		x[0] = dx;
		x[1] = dy;
		Log.e("ajusteDXDY", x[0] + "#" + x[1] + "#" + Entidade.dx + "#"
				+ Entidade.dy);
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

	// ///////////////desenhar mini mapa ///////////////////////////////////
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

	public static void drawMap(Jogo jogo, Canvas canvas, Paint paint) {

		int x1 = jogo.getHeroi().getX();
		int y1 = jogo.getHeroi().getY();

		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			jogo.getInimigos().get(i).draw(canvas, paint);
		}
		for (int i = 0; i < jogo.getGemsVida().size(); i++) {
			jogo.getGemsVida().get(i).draw(canvas, paint);
		}

		for (int i = 0; i < jogo.getParedes().length; i++) {
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				if (jogo.getParedes()[i][j] != null) {
					jogo.getParedes()[i][j].draw(canvas, paint);

				}
			}
		}
		

	}

	// desenha tudo no mapa
	public static void desenharEntidades(Jogo jogo, Canvas canvas, Paint paint) {
		// desenhar mini mapa
		drawMap(jogo, canvas, paint);
		// drawMiniMap(jogo,canvas,paint);

		jogo.getHeroi().draw(canvas, paint);
		
		paint.setColor(Color.YELLOW);
		for (int i = 0; i < jogo.getSetas().size(); i++) {
			canvas.drawLine(Entidade.sw/2, Entidade.sh/2, jogo.getSetas().get(i).x, jogo.getSetas().get(i).y, paint);
			jogo.getSetas().get(i).drawDirect(canvas, paint);
			jogo.getSetas().get(i).update();
		}
		/*for (int i = 0; i < jogo.getSetas().size(); i++) {
			if(jogo.getSetas().get(i).x>Entidade.sw || 
					jogo.getSetas().get(i).x<0 ||
					jogo.getSetas().get(i).y>Entidade.sh ||
					jogo.getSetas().get(i).y<0 )
			jogo.getSetas().remove(i);
		}*/
		//drawMiniMap(jogo, canvas, paint);
	}
}
