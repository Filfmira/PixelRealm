package com.example.lolitos2;

import java.io.Serializable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class GameControls implements OnTouchListener {

	int w, h;
	int xSJ, ySJ; // x e y do small Joystick
	int tpx, tpy;
	GameSurface parent;

	public float initx, inity;
	public int xBJ, yBJ;
	public Point _touchingPoint;
	private Boolean _dragging = false;
	private int sBJ;// size big joystick
	private int sSJ;// size small joystick
	Entidade pontoEntidade;
	public Entidade joystickEntidade;
	public Entidade pausaEntidade = null;
	public Entidade heroiEntidade = null;
	
	int jsx = 0;
	int jsy = 0;
	int idJoystick = -1;
	int c = 0;

	
	/**
	 * Onde todas a variaveis sao iniciadas,como por exemplo o koystick
	 * @param gameSurface Game Surface para ter acesso ao Jogo para poder interagir com ele
	 */
	public GameControls(GameSurface gameSurface) {
		this.parent = gameSurface;

		// Ecra
		WindowManager wm = (WindowManager) parent.getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		w = size.x;
		h = size.y;

		// tamanho dos joysticks
		sBJ = Imagens.joystickBig.getWidth();
		sSJ = Imagens.joystickSmall.getWidth();

		// mudar estes dois para mudar o sitio onde estao os joysticks
		xBJ = (int) (sBJ) / 2;
		yBJ = (int) (h - sBJ * 4 / 3);

		xSJ = (int) (xBJ + (sBJ - sSJ) / 2);
		ySJ = (int) (yBJ + (sBJ - sSJ) / 2);
		tpx = xSJ;
		tpy = ySJ;
		initx = xSJ;
		inity = ySJ;
		_touchingPoint = new Point(tpx, tpy);

		joystickEntidade = new Parede(xBJ, yBJ, sBJ, sBJ);
		pontoEntidade = new Parede(0, 0, 1, 1);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (pausaEntidade == null)
			pausaEntidade=new Parede(Entidade.tamanhoCelula/2,Entidade.tamanhoCelula/2,Entidade.tamanhoCelula/2+Imagens.pausa.getWidth(),Entidade.tamanhoCelula/2+Imagens.pausa.getHeight());

		handleMultitouch(event);
		
		return true;

	}

	/**
	 * Fun��o que trata de processar os toques quando se carrega com o dedo no JoyStick
	 * @param i X onde carregou
	 * @param l	Y onde carregou
	 */
	public void joystickDown(int i, int l) {
		jsx = i;
		jsy = l;
		_touchingPoint.x = (int) i - sSJ / 2;
		_touchingPoint.y = (int) l - sSJ / 2;
		pontoEntidade.setX((int) i - sSJ / 2);
		pontoEntidade.setY((int) l - sSJ / 2);

		if (joystickEntidade.colide(pontoEntidade))
			_dragging = true;

		if (_dragging) {
			// get the pos

			// bound to a box
			if (_touchingPoint.x < xSJ - (sBJ - sSJ) / 2) {
				_touchingPoint.x = (int) (xSJ - (sBJ - sSJ) / 2);
			}
			if (_touchingPoint.x > xSJ + (sBJ - sSJ) / 2) {
				_touchingPoint.x = (int) (xSJ + (sBJ - sSJ) / 2);
			}
			if (_touchingPoint.y < ySJ - (sBJ - sSJ)) {
				_touchingPoint.y = (int) (ySJ - (sBJ - sSJ) / 2);
			}
			if (_touchingPoint.y > ySJ + (sBJ - sSJ)) {
				_touchingPoint.y = (int) (ySJ + (sBJ - sSJ) / 2);
			}

			// get the angle
			double angle = Math.atan2(_touchingPoint.y - inity,
					_touchingPoint.x - initx) / (Math.PI / 180);

			double raio= Math.sqrt(Math.pow(_touchingPoint.y - inity, 2)+Math.pow(_touchingPoint.x - initx, 2))/(sBJ/2);
			// Move the beetle in proportion to how far
			// the joystick is dragged from its center
			int a = (int) (-Math.sin(angle * (Math.PI / 180)) * raio * 35);
			int b = (int) (-Math.cos(angle * (Math.PI / 180)) * raio * 35);

			int[] x = { b, a };
			// Log.e("colide", x[0]+"-"+x[1]);
			if (!GameLogic.verificaMovimento(parent.getJogo(), x)) {
				// ?
			}
			Entidade.dy += x[1];
			Entidade.dx += x[0];

		}
		parent.postInvalidate();

	}

	/**
	 * Fun��o que trata de processar os toques quando se larga o dedo do JoyStick
	 * @param event
	 */
	public void joystickUp(MotionEvent event) {
			_dragging = false;
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;

	}

	/**
	 * Fun��o que trata de processar os toques quando se carrega no mapa para lan�ar setas
	 * @param a	X onde carregou
	 * @param b	Y onde carregou
	 */
	public void setaUp(int a, int b) {
		if (parent.getJogo().getSetas().size() < 20) {
			int c1 = Entidade.sw / 2 - Entidade.tamanhoCelula / 2;
			int c2 = Entidade.sh / 2 - Entidade.tamanhoCelula / 2;
			parent.getJogo().getSetas().add(
					new Projectil(c1, c2, (a - c1) / 10, (b - c2) / 10, parent
							.getJogo().getHeroi()));
		}
	}

	


	/**
	 * Fun��o que trata de todos os toques registados no ecra
	 * @param event
	 */
	public void handleMultitouch(MotionEvent event) {
		int ptrId = -1;
		int action = event.getAction();

		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			pontoEntidade.x = (int) event.getX();
			pontoEntidade.y = (int) event.getY();
			if (pausaEntidade.colide(pontoEntidade)) {
				parent.menuPausa();
				break;
			} 
			
			if (joystickEntidade.colide(pontoEntidade)) {
				c++;
				Log.e("down", event.getPointerId(0) + "");
				idJoystick = action;
				joystickDown(pontoEntidade.x, pontoEntidade.y);
			}
			break;

		case MotionEvent.ACTION_UP:
			pontoEntidade.x = (int) event.getX();
			pontoEntidade.y = (int) event.getY();
			if (pausaEntidade.colide(pontoEntidade)) {
			break;
			} else if (event.getPointerId(0) == idJoystick) {
				c = 0;
				joystickUp(event);
				idJoystick = -1;
			}
			
			 else setaUp(pontoEntidade.x,pontoEntidade.y);
			 
			break;

		case MotionEvent.ACTION_POINTER_DOWN:
			ptrId = action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;

			int ptrIdx = event.findPointerIndex(ptrId);

			pontoEntidade.x = (int) event.getX(ptrIdx);
			pontoEntidade.y = (int) event.getY(ptrIdx);
			if (joystickEntidade.colide(pontoEntidade)) {
				idJoystick = ptrId;
				joystickDown(pontoEntidade.x, pontoEntidade.y);
			} else
				setaUp(pontoEntidade.x, pontoEntidade.y);
			break;

		case MotionEvent.ACTION_POINTER_UP:
			ptrId = action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			pontoEntidade.x = (int) event.getX(ptrId);
			pontoEntidade.y = (int) event.getY(ptrId);
			if (idJoystick == ptrId) {
				joystickDown(pontoEntidade.x, pontoEntidade.y);
			}
			break;

		case MotionEvent.ACTION_MOVE:
			pontoEntidade.x = (int) event.getX();
			pontoEntidade.y = (int) event.getY();
			for (int i = 0; i < event.getPointerCount(); ++i)
				if (event.getPointerId(i) == idJoystick) {
					c--;
					this.jsx=pontoEntidade.x;
					this.jsy=pontoEntidade.y;
				}
			break;
		default:
			break;
		}
	}


}
