package pixelrealm.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pixelrealm.entities.Heroi;
import pixelrealm.game.GameSurface;
import android.content.Context;
import android.util.Log;

public class Statistics {
	
	
	/**
	 * Guarda um Heroi h , atraves do context de gs
	 * @param h
	 * @param gs
	 */
	public static void saveHeroi(Heroi h, GameSurface gs){
		FileOutputStream fos = null;
		try {
			fos = gs._context.openFileOutput("heroi.dat", Context.MODE_PRIVATE);
		} catch (FileNotFoundException e1) {
			Log.e("save", "FileNotFoundException");
			e1.printStackTrace();
		}
		ObjectOutputStream os = null;
		try{
			os = new ObjectOutputStream (fos);
			
		}
		catch (IOException e){
			Log.e("save", "IOException while new ObjectOutputStream");
			e.printStackTrace();
		}
		
		try {
			os.writeObject(h);
		} catch (IOException e) {
			Log.e("save", "IOException while os.writeObject");
			e.printStackTrace();
		}
		
		finally{
			if (os != null)
				try{
					os.close();
				}
			catch (IOException e){
				Log.e("save", "IOException while writing (closing file)");
			}
		}
		Log.e("save", "deve tger guardado o heroi");
	}
	
	/**
	 * Faz Load de um Heroi que esteja no context de gs
	 * @param gs
	 * @return	Heroi guardado
	 */
	public static Heroi loadHeroi(GameSurface gs){
		FileInputStream fis = null;
		
		try {
			fis = gs._context.openFileInput("heroi.dat");
		} catch (FileNotFoundException e1) {
			Log.e("load", "FileNotFoundException");
			e1.printStackTrace();
			return null;
		}
		ObjectInputStream is = null;
		Heroi heroi = null;
		try {
			is = new ObjectInputStream(fis);
			heroi = (Heroi) is.readObject();
		}
		catch (ClassNotFoundException c){
			Log.e("load", "ClassNotFoundException");
			return null;
		}
		catch (IOException e) {
			Log.e("load", "IOException");
			e.printStackTrace();
			return null; 
		}
		finally { 
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return heroi;
			}
		}
		return null;
	}
}
