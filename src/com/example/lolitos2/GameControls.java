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
public class GameControls implements OnTouchListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4852945977582535988L;
	int w, h;
	int xSJ, ySJ; // x e y do small Joystick
	int tpx, tpy;
	GameSurface parent;

	public float initx, inity;
	public int xBJ, yBJ;
	public Point _touchingPoint;
	private Boolean _dragging = false;
	private Boolean _draggingSeta = false;
	private int sBJ;// size big joystick
	private int sSJ;// size small joystick
	Entidade pontoEntidade;
	public Entidade joystickEntidade;
	public Entidade pausaEntidade = null;
	public Entidade heroiEntidade = null;
	private Jogo jogo;

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
		sBJ = parent.get_joystick().get_joystickBg().getWidth();
		sSJ = parent.get_joystick().get_joystick().getWidth();

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

	public void JSdown(int i, int l) {
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

	public void JSup(MotionEvent event) {
		// largar
		if (event.getAction() == MotionEvent.ACTION_UP && _dragging) {
			_dragging = false;
		}

		if (!_dragging) {
			// Snap back to center when the joystick is released
			// Log.e("dragging false", ""+_touchingPoint.x);
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;

			// shaft.alpha = 0;
		}
	}

	public void Sup(int a, int b) {
		if (jogo.getSetas().size() < 20) {
			int c1 = Entidade.sw / 2 - Entidade.tamanhoCelula / 2;
			int c2 = Entidade.sh / 2 - Entidade.tamanhoCelula / 2;
			jogo.getSetas().add(
					new Projectil(c1, c2, (a - c1) / 10, (b - c2) / 10, parent
							.getJogo().getHeroi()));
		}
	}

	int jsx = 0;
	int jsy = 0;
	int idJoystick = -1;
	int c = 0;

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
				JSdown(pontoEntidade.x, pontoEntidade.y);
			}
			break;

		case MotionEvent.ACTION_UP:
			pontoEntidade.x = (int) event.getX();
			pontoEntidade.y = (int) event.getY();
			if (pausaEntidade.colide(pontoEntidade)) {
			break;
			} else if (event.getPointerId(0) == idJoystick) {
				c = 0;
				JSup(event);
				idJoystick = -1;
			}
			
			 else Sup(pontoEntidade.x,pontoEntidade.y);
			 
			break;

		case MotionEvent.ACTION_POINTER_DOWN:
			ptrId = action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			// Log.e("gg", 2+"");

			int ptrIdx = event.findPointerIndex(ptrId);

			pontoEntidade.x = (int) event.getX(ptrIdx);
			pontoEntidade.y = (int) event.getY(ptrIdx);
			if (joystickEntidade.colide(pontoEntidade)) {
				idJoystick = ptrId;
				JSdown(pontoEntidade.x, pontoEntidade.y);
			} else
				Sup(pontoEntidade.x, pontoEntidade.y);
			/*
			 * Log.e("gg", 3+""); //Log.e("APD",
			 * (int)event.getX(ptrIdx)+"-"+(int)event.getY(ptrIdx)); for(int i =
			 * 0; i < event.getPointerCount(); ++i) { Log.e("APD",
			 * event.getPointerId(i)+""); }
			 */
			break;

		case MotionEvent.ACTION_POINTER_UP:
			ptrId = action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			/*
			 * if (joystickEntidade.colide(pontoEntidade)) JSup(event); else
			 */
			pontoEntidade.x = (int) event.getX(ptrId);
			pontoEntidade.y = (int) event.getY(ptrId);
			if (idJoystick == ptrId) {
				JSdown(pontoEntidade.x, pontoEntidade.y);
			}
			/* Log.e("APU", (int)event.getX(ptrId)+"-"+(int)event.getY(ptrId)); */
			break;

		case MotionEvent.ACTION_MOVE:
			pontoEntidade.x = (int) event.getX();
			pontoEntidade.y = (int) event.getY();
			for (int i = 0; i < event.getPointerCount(); ++i)
				if (event.getPointerId(i) == idJoystick) {
					c--;
					this.jsx=pontoEntidade.x;
					this.jsy=pontoEntidade.y;
					//JSdown(pontoEntidade.x, pontoEntidade.y);
				}
			break;
		default:
			break;
		}
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

}
