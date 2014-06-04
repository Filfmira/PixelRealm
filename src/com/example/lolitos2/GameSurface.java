package com.example.lolitos2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.example.lolitos2.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{


	public Context _context;
	private GameThread _thread;
	private GameControls _controls;
	private GameJoystick _joystick;
	private int c=8;
	private Sprite sprite;
	
	//////////////////////////
	//private static final int INTERVAL = 10; // pausa de 10ms
	//private boolean running = true; // se esta a ser executado ou nao
	private Paint paint;// variavel usada para desenhar
	//private int FRAMES_PER_SECOND = 25;
	//private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	//private long next_game_tick = System.currentTimeMillis();
	//private int sleep_time = 0;
	//private int tamanhoCelula = 200;
	
	private Jogo jogo;
////////////////////////////////
	//private Bitmap _pointer;

	public GameSurface(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		_context = context;
		init();
		
	}

	private void init(){
		//initialize our screen holder
		SurfaceHolder holder = getHolder();
		holder.addCallback( this);

		//initialize our game engine

		//initialize our Thread class. A call will be made to start it later
		_thread = new GameThread(holder, _context, new Handler(),this);
		setFocusable(true);
	}
	

	public void iniciaJogo(Canvas canvas) {
		Entidade.tamanhoCelula = (int) canvas.getWidth()/c;

		set_joystick(new GameJoystick(getContext().getResources()));
		//contols
		_controls = new GameControls(this);
		setOnTouchListener(_controls);
		
		paint = new Paint();
		
		
		Imagens.inicializarImagens(this.getResources());
		Entidade.sw=canvas.getWidth();
		Entidade.sh=canvas.getHeight();
		Log.e("tC", Entidade.tamanhoCelula+"");
		InputStream inputStream = this.getResources().openRawResource(
				R.raw.teste);
		/*Jogo j = Jogo.loadGame();
		if (j == null)
			setJogo(new Jogo(Entidade.tamanhoCelula, inputStream));
		else setJogo(j);*/
		Jogo j = new Jogo (Entidade.tamanhoCelula, this.getResources());
		Heroi h = Statistics.loadHeroi(this);
		if (h != null){
			Log.e("load", "heroi nao null"+h.getDinheiro());
			j.setHeroi(h);
		}
		setJogo(j);
	}

	public void doDraw(Canvas canvas) {

		canvas.drawColor(Color.rgb(50, 100, 10));
		//canvas.drawBitmap(Imagens.mapa, 2, 2, null);
		// //////////////////////////
		if (getJogo() == null) {
			this.iniciaJogo(canvas);
			_controls.setJogo(jogo);
		}
		
		///////////////////////////////
		
		
		if(_controls.idJoystick!=-1)
		{
			int x2=_controls.jsx;
			int x3=_controls.jsy;
			_controls.JSdown(x2,x3);
		}
		
		canvas.drawBitmap(Imagens.mapa,Entidade.dx,Entidade.dy,null);
		//_controls.update(null);
		GameLogic.desenharEntidades(getJogo(), canvas, paint);
		
		// draw the joystick background
		canvas.drawBitmap(get_joystick().get_joystickBg(), _controls.xBJ,
				_controls.yBJ, null);
		

		// draw the dragable joystick
		canvas.drawBitmap(get_joystick().get_joystick(),
				_controls._touchingPoint.x, _controls._touchingPoint.y, null);
		
		/*paint.setColor(Color.YELLOW);
		canvas.drawCircle(_controls.jsx, _controls.jsy, 50, paint);*/
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		canvas.drawText("life:"+jogo.getHeroi().getVida(), 50, 50, paint);
		canvas.drawText("points:"+jogo.getHeroi().getDinheiro(), 50, 100, paint);
		
		canvas.drawText("Setas:"+jogo.getSetas().size(), 50, 200, paint);
		canvas.drawText("ataque:"+jogo.getHeroi().ataque, 50, 250, paint);
		canvas.drawText("JSID:"+_controls.c, 50, 300, paint);
	}


	//these methods are overridden from the SurfaceView super class. They are automatically called 
	//when a SurfaceView is created, resumed or suspended.
	@Override 
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.e("mudada", "mudou a surface");
	}
	
	
	private boolean retry;
	
	@Override 
	public void surfaceDestroyed(SurfaceHolder arg0) {
		retry = true;
		//code to end gameloop
		_thread.state = GameThread.STOPED;
		while (retry) {
			try {
				//code to kill Thread
				_thread.join();
				retry = false;
			} catch (InterruptedException e) {
				Log.e("destruido", "destroyed");
			}
		}

	}

	@Override 
	public void surfaceCreated(SurfaceHolder arg0) {
		
		//Apenas para testes, para ver como esta o estado da thread
		if(_thread.getState()==Thread.State.NEW )
		Log.e("estado", "NEW");
		else if(_thread.getState()==Thread.State.BLOCKED )
			Log.e("estado", "BLOCKED");
		else if(_thread.getState()==Thread.State.RUNNABLE )
			Log.e("estado", "RUNNABLE");
		else if(_thread.getState()==Thread.State.TERMINATED )
			Log.e("estado", "TERMINATED");
		else if(_thread.getState()==Thread.State.TIMED_WAITING )
			Log.e("estado", "TIMED_WAITING");
		else if(_thread.getState()==Thread.State.WAITING )
			Log.e("estado", "WAITING");
		else{};
		
		
		
		if(_thread.state==GameThread.PAUSED){
			//When game is opened again in the Android OS
			Log.e("p", "paused");
			_thread = new GameThread(getHolder(), _context, new Handler(),this);
			_thread.start();
		}else{
			//a quando de criar uma nova thread pela primeira vez, ou a regressar do multitasking
			if(_thread.getState()==Thread.State.NEW ||_thread.getState()==Thread.State.TERMINATED)
				{_thread = new GameThread(getHolder(), _context, new Handler(),this);
			_thread.start();
			Log.e("f", "first");
				}
		}
	}

	
	int counter = 0;

	public void Update() {
		
		
		if (getJogo() == null) {
			return;
		}

		
		counter++;
		
		// mexe
		if (counter == 15) {
			
			//_thread.state=_thread.PAUSED;
			//GameLogic.movimentaHeroi(jogo, x, y, this);
			// Get instance of Vibrator from current Context
			for (int i = 0; i < getJogo().getInimigos().size(); i++) {
				jogo.getInimigos().get(i).movimento(jogo.getHeroi());
			}
			counter = 0;
		}
		
		jogo.getHeroi().update();
		GameLogic.lutar(jogo,this);
		GameLogic.setasUpdate(jogo);
		GameLogic.apanharGems(jogo);
		GameLogic.apanharMoedas(jogo);
		GameLogic.colidePortal(jogo);
		
		// lança o metodo draw p/desenhar
		postInvalidate();

	}

	
	
	
	public void menuPausa()
	{
		Intent intent = new Intent(_context, PauseActivity.class);
		Statistics.saveHeroi(jogo.getHeroi(), this);
		_context.startActivity(intent);
	}
	
	
	
	
	public GameJoystick get_joystick() {
		return _joystick;
	}

	public void set_joystick(GameJoystick _joystick) {
		this._joystick = _joystick;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

}
