package com.example.lolitos2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameJoystick {
	
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
		this._joystick=Bitmap.createScaledBitmap(_joystick, Entidade.tamanhoCelula/2, Entidade.tamanhoCelula/2, true);
		_joystickBg = (Bitmap)BitmapFactory.decodeResource(res,com.example.lolitos2.R.drawable.joystick_bg);
		this._joystickBg=Bitmap.createScaledBitmap(_joystickBg, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);

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
}
