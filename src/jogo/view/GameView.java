package jogo.view;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import com.example.lolitos2.Entidade;
import com.example.lolitos2.GameControls;
import com.example.lolitos2.GameJoystick;
import com.example.lolitos2.GameLogic;
import com.example.lolitos2.GameThread;
import com.example.lolitos2.Jogo;
import com.example.lolitos2.R;
import com.example.lolitos2.R.raw;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable {

	
	//variaveis de desenho
	private static final int INTERVAL = 10; // pausa de 10ms
	private boolean running = true; // se esta a ser executado ou nao
	private Paint paint;// variavel usada para desenhar
	private int FRAMES_PER_SECOND = 25;
	private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private long next_game_tick = System.currentTimeMillis();
	private int sleep_time = 0;
	private int tamanhoCelula = 200;
	
	
	private Jogo jogo;
	
	
	
	private Context _context;
	private GameThread _thread;
	private GameControls _controls;
	private GameJoystick _joystick;


	private Bitmap _pointer;

	public GameView(Context context) {
		super(context);
		// instaciamento das variaveis
		paint = new Paint();
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);

		minhaThread.start();// comeca o loop da class

	}

	

	// poe a correr o programa
	public void run() {
		while (running) {
			update();
			postInvalidate();
			next_game_tick += SKIP_TICKS;
			sleep_time = (int) (next_game_tick - System.currentTimeMillis());
			if (sleep_time >= 0) {
				try {
					java.lang.Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	//trata de iniciar o jogo
	public void iniciaJogo() {
		Entidade.tamanhoCelula = this.getWidth() / 11;
		InputStream inputStream = this.getResources().openRawResource(
				R.raw.teste);
		jogo = new Jogo(Entidade.tamanhoCelula, inputStream);
	}

	int counter = 0;

	private void update() {

		counter++;
		if (jogo == null) {
			return;
		}

		// mexe
		if (counter == 15) {
			//GameLogic.movimentaHeroi(jogo, x, y, this);
			for (int i = 0; i < jogo.getInimigos().size(); i++) {
				//jogo.getInimigos().get(i).movimento((int) (Math.random() * 4));
			}
			counter = 0;
		}
		
		// lança o metodo draw p/desenhar
		postInvalidate();
	}

	public void draw(Canvas canvas) {

		super.draw(canvas);
		if (jogo == null) {
			this.iniciaJogo();
		}

		canvas.drawColor(Color.rgb(50, 100, 10));

		GameLogic.desenharEntidades(jogo, canvas, paint);

		//GameLogic.lutar(jogo.getInimigos(), jogo.getHeroi());

	}

	// para o jogo parar o jogo
	public void release() {
		running = false;
	}
	
	int x;
	int y;
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			x = (int) event.getX();
			y = (int) event.getY();
			// faz p movimento do heroi
			GameLogic.movimentaHeroi(jogo, x, y, this);
		}

		return super.onTouchEvent(event);
	}

}
