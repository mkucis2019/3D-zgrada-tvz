package hr.tvz.mkucis.zgradatvz.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class CKeyboard implements KeyListener
{
	private boolean keys[];
	
	public CKeyboard()
	{
		keys = new boolean[256];
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		char c = e.getKeyChar();
		
		if( c > 0 && c < 256)
		{
			keys[c] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		char c = e.getKeyChar();
		
		if( c > 0 && c < 256)
		{
			keys[c] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public boolean CheckKeyPressed(char key)
	{
		if(key < 0 || key > 255) return false;
		return keys[key];
	}

}
