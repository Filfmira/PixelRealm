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

@SuppressLint("NewApi") public class GameControls implements OnTouchListener{

	int w,h;
	int ix,iy,tpx,tpy;
	GameSurface parent;
	
	public float initx;
	public float inity;
	public Point _touchingPoint;
	private Boolean _dragging = false;
	private float sB;//size big button
	private float sS;//size small button
	
	public GameControls(GameSurface gameSurface) {
		this.parent=gameSurface;
		WindowManager wm = (WindowManager) parent.getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		sB=parent.get_joystick().get_joystickBg().getWidth();
		sS=parent.get_joystick().get_joystick().getWidth();
		Point size = new Point();
		display.getSize(size);
		w = size.x;
		h = size.y;
		ix=	(int) (sB+(sB-sS)/2);
		iy= (int) ((h-sB*1.5)+(sB-sS)/2);
		tpx= ix;
		tpy= iy;
		Log.e("joystick", "w:"+w+" h:"+h+" ix:"+ix+" iy:"+iy+" tpx:"+tpx+" tpy:"+tpy);
		initx=ix;
		inity=iy;
		_touchingPoint = new Point(tpx,tpy);
		
				
	}
	@Override
	
	public boolean onTouch(View v, MotionEvent event) {

		update(event);
		return true;
	}

	private MotionEvent lastEvent;
	
	public void update(MotionEvent event){

		if (event == null && lastEvent == null)
		{
			return;
		}else if(event == null && lastEvent != null){
			event = lastEvent;
		}else{
			lastEvent = event;
		}
		
		
		//drag drop 
		if ( event.getAction() == MotionEvent.ACTION_DOWN ){
			_touchingPoint.x = (int)event.getX();
			_touchingPoint.y = (int)event.getY();
			_dragging = true;
				/*if( _touchingPoint.x < ix-(sB-sS)/2){
					_dragging = false;
				}
				if ( _touchingPoint.x > ix+(sB-sS)/2){
					_dragging = false;
				}
				if (_touchingPoint.y < iy-(sB-sS)){
					_dragging = false;
				}
				if ( _touchingPoint.y > iy+(sB-sS) ){
					_dragging = false;
				}
				*/
		}else if ( event.getAction() == MotionEvent.ACTION_UP){
			_dragging = false;
		}
		

		if ( _dragging ){
			// get the pos
			_touchingPoint.x = (int)event.getX();
			_touchingPoint.y = (int)event.getY();

			
			// bound to a box
			if( _touchingPoint.x < ix-(sB-sS)/2){
				_touchingPoint.x = (int) (ix-(sB-sS)/2);
			}
			if ( _touchingPoint.x > ix+(sB-sS)/2){
				_touchingPoint.x = (int) (ix+(sB-sS)/2);
			}
			if (_touchingPoint.y < iy-(sB-sS)){
				_touchingPoint.y = (int) (iy-(sB-sS)/2);
			}
			if ( _touchingPoint.y > iy+(sB-sS) ){
				_touchingPoint.y = (int) (iy+(sB-sS)/2);
			}

			//get the angle
			double angle = Math.atan2(_touchingPoint.y - inity,_touchingPoint.x - initx)/(Math.PI/180);
			
			// Move the beetle in proportion to how far 
			// the joystick is dragged from its center
			int a=(int) (-Math.sin(angle*(Math.PI/180))*5);
			int b=(int) (-Math.cos(angle*(Math.PI/180))*5);
			if(a<0)
			{
				a=-2;
			}
			else
				a=2;
			if(b<0)
			{
				b=-2;
			}
			else
				b=2;
			
			
			int[] x={b,a};
			Log.e("colide", x[0]+"-"+x[1]);
			if(!GameLogic.verificaMovimento(parent.getJogo(),x))
			{
				/*a=-a;
				b=-b;*/
				Log.e("yooo", x[0]+"-"+x[1]);
			}
			Log.e("colide2", x[0]+"-"+x[1]);
			Entidade.dy+= x[1];
			Entidade.dx+= x[0];


		}else if (!_dragging)
		{
			// Snap back to center when the joystick is released
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;
			//shaft.alpha = 0;
		}
	}
}
