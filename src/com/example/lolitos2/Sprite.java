package com.example.lolitos2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

	int x,y;
	int xSpeed, ySpeed;
	int height,width;
	int currentFrame=0;
	int direction=0;
	int counter=0;
	Bitmap b;
	public Sprite(Bitmap bitmap)
	{
		b=bitmap;
		height=b.getHeight();
		width=b.getWidth()/4;
		x=y=0;
		xSpeed=5;
		ySpeed=0;
	}
	int lol=0;
	
	public void update(){
		switch (lol) {
		case 0:
			
			break;
		case 1:
			
			break;

		default:
			break;
		}
		if(counter==3)
		{
			counter=0;
		currentFrame=++currentFrame%4; //4 collums
		}
		counter++;
	}
	
	public void draw(Canvas canvas)
	{
		update();
		int srcY=direction*height;
		int srcX=currentFrame*width;
		Rect src= new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst= new Rect(x,y,x+width,y+height);
		canvas.drawBitmap(b,src,dst,null);
	}
	
}
