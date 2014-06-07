package com.pixelrealm.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.example.lolitos2.R;
import com.pixelrealm.controls.GameControls;
import com.pixelrealm.entities.Entidade;
import com.pixelrealm.entities.Heroi;
import com.pixelrealm.graphics.Imagens;
import com.pixelrealm.logic.GameLogic;
import com.pixelrealm.states.GameActivity;
import com.pixelrealm.states.LostActivity;
import com.pixelrealm.states.PauseActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	public Context _context;
	private GameThread _thread;
	private GameControls _controls;
	//private GameJoystick _joystick;
	private int c = 8;
	private Paint paint;// variavel usada para desenhar
	private Jogo jogo;
	GameActivity myActivity = (GameActivity) getContext();
	int counter = 0;
	Intent intent1; //menu de pausa
	private boolean retry;

	/**
	 * Inicializa o GameSurface guardando o seu context
	 * 
	 * @param context
	 */
	public GameSurface(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		_context = context;
		init();

	}


	/**
	 * Função que inicializa o screen holder e a thread
	 */
	public void init() {
		// inicializa o screen holder
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		// inicizlia a thread
		_thread = new GameThread(holder, _context, new Handler(), this);
		setFocusable(true);
		intent1 = new Intent(_context, LostActivity.class);
	}

	/**
	 * Funcao que inicializa o Jogo e qualquer outro objecto ou variavel para a
	 * execução da logica de jogo
	 * 
	 * @param canvas
	 */
	public void iniciaJogo(Canvas canvas) {
		
		//Guarda o comprimento e largua do ecra em duas variaveis static
		Entidade.sw = canvas.getWidth();
		Entidade.sh = canvas.getHeight();
		
		//inicializa o tamanhoCelula, unidade usada para todos os calculos no jogo
		Entidade.tamanhoCelula = Entidade.sw / c;
		//se o ecra tiver na horizontal, modifica a sua inicializacao
		if (Entidade.sw > Entidade.sh)
			Entidade.tamanhoCelula = Entidade.sw / 16;
		
		//inicializa todas as Imagens
		Imagens.inicializarImagens(this.getResources());
		

		//incializa o GameControls aplicanbdo o OnTouchListener
		_controls = new GameControls(this);
		setOnTouchListener(_controls);
		
		//inicializa o paint para desenhar no ecra
		paint = new Paint();

		//Inicializa o dx e dy, respondaveis por mover todos os objectos a volta
		//do heroi
		Entidade.dx = 0;
		Entidade.dy = 0;
		
		//inicializa o jogo, se tiver um heroi ja gravado, inizializa com esse
		Jogo j = new Jogo(Entidade.tamanhoCelula, this.getResources());
		Heroi h = Statistics.loadHeroi(this);
		if (h != null) {
			if (h.getVida() > 0)
				j = new Jogo(h);
		}
		setJogo(j);
	}
	
	/**
	 * Função que trata de desenhar todos os elementos no ecra
	 * @param canvas
	 */
	public void doDraw(Canvas canvas) {

		canvas.drawColor(Color.BLACK);
		
		if (getJogo() == null) {
			this.iniciaJogo(canvas);
		}

		if (_controls.getIdJoystick() != -1) {
			int x2 = _controls.getJsx();
			int x3 = _controls.getJsy();
			_controls.joystickDown(x2, x3);
		}

		canvas.drawBitmap(Imagens.getMapa(), Entidade.dx, Entidade.dy, null);
		GameLogic.desenharEntidades(_context.getAssets(),getJogo(), canvas, paint);

		canvas.drawBitmap(Imagens.getJoystickBig(), _controls.xBJ,
				_controls.yBJ, null);
		
		canvas.drawBitmap(Imagens.getJoystickSmall(),
				_controls._touchingPoint.x, _controls._touchingPoint.y, null);
		
		

	}

	// automaticamente chamados quando a SurfaceView é criada, suspendida ou retomada
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.e("mudada", "mudou a surface");
	}

	

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		retry = true;
		// para acabar o GameLoop
		_thread.state = GameThread.STOPED;
		while (retry) {
			try {
				// matar thread
				_thread.join();
				retry = false;
			} catch (InterruptedException e) {
				Log.e("destruido", "destroyed");
			}
		}

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {

		// Apenas para testes, para ver como esta o estado da thread
		if (_thread.getState() == Thread.State.NEW)
			Log.e("estado", "NEW");
		else if (_thread.getState() == Thread.State.BLOCKED)
			Log.e("estado", "BLOCKED");
		else if (_thread.getState() == Thread.State.RUNNABLE)
			Log.e("estado", "RUNNABLE");
		else if (_thread.getState() == Thread.State.TERMINATED)
			Log.e("estado", "TERMINATED");
		else if (_thread.getState() == Thread.State.TIMED_WAITING)
			Log.e("estado", "TIMED_WAITING");
		else if (_thread.getState() == Thread.State.WAITING)
			Log.e("estado", "WAITING");
		else {
		}
		;

		if (_thread.state == GameThread.PAUSED) {
			// Quando o jogo é novamente aberto no AndroidOS
			Log.e("p", "paused");
			_thread = new GameThread(getHolder(), _context, new Handler(), this);
			_thread.start();
		} else {
			// a quando de criar uma nova thread pela primeira vez, ou a
			// regressar do multitasking
			if (_thread.getState() == Thread.State.NEW
					|| _thread.getState() == Thread.State.TERMINATED) {
				_thread = new GameThread(getHolder(), _context, new Handler(),
						this);
				_thread.start();
				Log.e("f", "first");
			}
		}
	}


	/**
	 * Função que chama todos os updates de Objectos e trata de chamar toda a logica do jogo (GameLogic)
	 */
	public void Update() {

		if (getJogo() == null) {
			return;
		}

		counter++;

		// mexe
		if (counter == 15) {
			for (int i = 0; i < getJogo().getInimigos().size(); i++) {
				jogo.getInimigos().get(i).movimento(jogo);
			}
			counter = 0;
		}

		jogo.getHeroi().update();
		GameLogic.lutar(jogo, this);
		GameLogic.setasUpdate(jogo);
		if(GameLogic.apanharMoedas(jogo) || GameLogic.apanharGems(jogo))
			GameActivity.getInstance().gem.start();
		if(GameLogic.colidePortal(jogo))
			GameLogic.aumentarNivel(this);

		// lança o metodo draw p/desenhar
		postInvalidate();

	}

	/**
	 * Função que guarda o estado do heroi e chama o Menu de Pausa(PauseActivity)
	 */
	public void menuPausa() {
		Intent intent = new Intent(_context, PauseActivity.class);
		Statistics.saveHeroi(jogo.getHeroi(), this);
		_context.startActivity(intent);
	}

	/**
	 * Termina a Activity do jogo e chama o Menu de Perda (LostActivity)
	 */
	public void menuPerder() {
		this._thread.state = _thread.STOPED;
		_context.startActivity(intent1);
		myActivity.finish();
	}

	
	
	
	

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

}
