package com.example.lolitos2;

import java.io.InputStream;

import com.example.lolitos2.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	private Context _context;
	private GameThread _thread;
	private GameControls _controls;

	private GameJoystick _joystick;

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


		set_joystick(new GameJoystick(getContext().getResources()));
		//contols
		_controls = new GameControls(this);
		setOnTouchListener(_controls);
		
		paint = new Paint();
		
		
	}
	

	public void iniciaJogo(Canvas canvas) {
		Entidade.tamanhoCelula = (int) canvas.getWidth()/8;
		Imagens.inicializarImagens(this.getResources());
		Entidade.sw=canvas.getWidth();
		Entidade.sh=canvas.getHeight();
		Log.e("tC", Entidade.tamanhoCelula+"");
		InputStream inputStream = this.getResources().openRawResource(
				R.raw.teste);
		setJogo(new Jogo(Entidade.tamanhoCelula, inputStream));
	}

	public void doDraw(Canvas canvas) {

		canvas.drawColor(Color.rgb(50, 100, 10));

		// //////////////////////////
		if (getJogo() == null) {
			this.iniciaJogo(canvas);
		}
		_controls.setJogo(jogo);
		///////////////////////////////
		
		GameLogic.desenharEntidades(getJogo(), canvas, paint);

		
		_controls.update(null);
		
		
		// draw the joystick background
		canvas.drawBitmap(get_joystick().get_joystickBg(), _controls.xBJ,
				_controls.yBJ, null);
		

		// draw the dragable joystick
		canvas.drawBitmap(get_joystick().get_joystick(),
				_controls._touchingPoint.x, _controls._touchingPoint.y, null);
		
		
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		canvas.drawText("life:"+jogo.getHeroi().getVida(), 50, 50, paint);
		
		canvas.drawText(""+jogo.getSetas().size(), 50, 200, paint);
		

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
			//GameLogic.movimentaHeroi(jogo, x, y, this);
			for (int i = 0; i < getJogo().getInimigos().size(); i++) {
				jogo.getInimigos().get(i).movimento((int) (Math.random() * 4));
			}
			counter = 0;
		}
		
		GameLogic.lutar(jogo.getInimigos(),jogo.getHeroi());
		
		GameLogic.apanharGems(jogo.getGemsVida(), jogo.getHeroi());
		
		// lança o metodo draw p/desenhar
		postInvalidate();

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
