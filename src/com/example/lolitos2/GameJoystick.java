package com.example.lolitos2;

import java.io.Serializable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnLongClickListener;

public class GameJoystick implements OnLongClickListener, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2226677249260438920L;
	private Bitmap _joystick;
	private Bitmap _joystickBg;
	private Bitmap _trigger;
	private Bitmap _triggerDown;
	
	public Bitmap get_triggerDown() {
		return _triggerDown;
	}

	public void set_triggerDown(Bitmap triggerDown) {
		_triggerDown = triggerDown;
	}

	public GameJoystick(Resources res){
		
		_joystick = (Bitmap)BitmapFactory.decodeResource(res, com.example.lolitos2.R.drawable.joystick);
		this._joystick=Bitmap.createScaledBitmap(_joystick, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		_joystickBg = (Bitmap)BitmapFactory.decodeResource(res,com.example.lolitos2.R.drawable.joystick_bg);
		this._joystickBg=Bitmap.createScaledBitmap(_joystickBg, Entidade.tamanhoCelula*2, Entidade.tamanhoCelula*2, true);

	}

	public Bitmap get_joystick() {
		return _joystick;
	}

	public void set_joystick(Bitmap joystick) {
		_joystick = joystick;
	}

	public Bitmap get_joystickBg() {
		return _joystickBg;
	}

	public void set_joystickBg(Bitmap joystickBg) {
		_joystickBg = joystickBg;
	}
	
	public Bitmap get_trigger() {
		return _trigger;
	}

	public void set_trigger(Bitmap trigger) {
		_trigger = trigger;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}
}
