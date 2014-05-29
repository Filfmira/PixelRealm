package com.example.lolitos2;

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
	private Boolean _draggingSeta = false;
	private int sBJ;// size big joystick
	private int sSJ;// size small joystick
	private Entidade pontoEntidade;
	public Entidade joystickEntidade;
	public Entidade heroiEntidade = null;
	private Jogo jogo;
	private boolean JS=false;
	private boolean j=true;
	
	
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
		xBJ = (int) (sBJ);
		yBJ = (int) (h - sBJ * 2);

		xSJ = (int) (xBJ + (sBJ - sSJ) / 2);
		ySJ = (int) (yBJ + (sBJ - sSJ) / 2);
		tpx = xSJ;
		tpy = ySJ;
		initx = xSJ;
		inity = ySJ;
		_touchingPoint = new Point(tpx, tpy);

		joystickEntidade = new Entidade(xBJ, yBJ, sBJ, sBJ);
		pontoEntidade = new Entidade(0, 0, 1, 1);

		// Log.e("joystick",
		// "w:"+w+" h:"+h+" ix:"+xSJ+" iy:"+ySJ+" tpx:"+tpx+" tpy:"+tpy);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//int pointerCount = event.getPointerCount();
		//int pointerId = event.getPointerId(0);
		// Log.e("putas d epontos", pointerCount+"....");
		update(event);
		touchProjetil(event);
		return true;
	}

	private MotionEvent lastEvent;
	private MotionEvent lastEventSetas;
	
	public void update(MotionEvent event) {

		if (event == null && lastEvent == null) {
			return;
		} else if (event == null && lastEvent != null) {
			event = lastEvent;
		} else {
			lastEvent = event;
		}

		
		
		// arrastar
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_touchingPoint.x = (int) event.getX();
			_touchingPoint.y = (int) event.getY();
			pontoEntidade.setX((int) event.getX());
			pontoEntidade.setY((int) event.getY());

			if (joystickEntidade.colide(pontoEntidade))
				_dragging = true;

		}

		
		// largar
		else if (event.getAction() == MotionEvent.ACTION_UP && _dragging) {
			_dragging = false;
		}

		
		
		
		if (_dragging) {
			// get the pos
			j=true;
			JS=true;
			_touchingPoint.x = (int) event.getX();
			_touchingPoint.y = (int) event.getY();

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

			// Move the beetle in proportion to how far
			// the joystick is dragged from its center
			int a = (int) (-Math.sin(angle * (Math.PI / 180)) * 5);
			int b = (int) (-Math.cos(angle * (Math.PI / 180)) * 5);

			int[] x = { b, a };
			// Log.e("colide", x[0]+"-"+x[1]);
			if (!GameLogic.verificaMovimento(parent.getJogo(), x)) {
				// ?
			}
			Entidade.dy += x[1];
			Entidade.dx += x[0];

		} else if (!_dragging) {
			// Snap back to center when the joystick is released
			if(j==false)
			{
				JS=false;
			}
			if(j==true)
			{
				j=false;
			}
			Log.e("dragging false", ""+_touchingPoint.x);
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;
			
			// shaft.alpha = 0;
		}
	}

	public void touchProjetil(MotionEvent event) {
		if(JS) return;
		
		if (event == null && lastEventSetas == null) {
			return;
		} else if (event == null && lastEventSetas != null) {
			event = lastEventSetas;
		} else {
			lastEventSetas = event;
		}
		
		
		int a = (int) event.getX();
		int b = (int) event.getY();

		if (heroiEntidade == null)
			heroiEntidade = new Entidade(a - Entidade.tamanhoCelula / 2, b
					- Entidade.tamanhoCelula / 2, Entidade.tamanhoCelula,
					Entidade.tamanhoCelula);
		// arrastar
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			pontoEntidade.setX(a);
			pontoEntidade.setY(b);

			if (!joystickEntidade.colide(pontoEntidade)
					&& !heroiEntidade.colide(pontoEntidade))
				_draggingSeta = true;

		}

		// largar
		else if (event.getAction() == MotionEvent.ACTION_UP && _draggingSeta) {
			_draggingSeta = false;
		}

		if (_draggingSeta) {
			/*if (!heroiEntidade.colide(pontoEntidade)) {
				if (jogo.getSetas().size() < 20) {
					int c1 = Entidade.sw / 2 - Entidade.tamanhoCelula / 2;
					int c2 = Entidade.sh / 2 - Entidade.tamanhoCelula / 2;
					jogo.getSetas().add(new Projectil(c1, c2, (a - c1) / 10,(b - c2) / 10));
				}
			}*/
		}
		
		else if (!_draggingSeta) {
			if (!heroiEntidade.colide(pontoEntidade) && _touchingPoint.x==(int)initx && _touchingPoint.y == (int)inity ) {
				if (jogo.getSetas().size() < 20) {
					int c1 = Entidade.sw / 2 - Entidade.tamanhoCelula / 2;
					int c2 = Entidade.sh / 2 - Entidade.tamanhoCelula / 2;
					jogo.getSetas().add(new Projectil(c1, c2, (a - c1) / 10,(b - c2) / 10));
				}
			}
		}

	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

}
