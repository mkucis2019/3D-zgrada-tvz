package hr.tvz.mkucis.zgradatvz.input;


import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CMouse implements MouseMotionListener
{
	private int oldX, oldY;
	private boolean mouseMoved;
	
	private int movedX;
	private int movedY;

	public CMouse()
	{
		mouseMoved = false;
		
		movedX = 0;
		movedY = 0;
		oldX = 0;
		oldY = 0;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		int newX,newY;
		
		newX = e.getX();
		newY = e.getY();
		
		movedX = newX - oldX;
		movedY = newY - oldY;
		
		if(movedX == 0 && movedY == 0)
			mouseMoved = false;
		else
			mouseMoved = true;
		
		oldX = newX;
		oldY = newY;
	}
	
	public int getMovementX()
	{
		return movedX;
	}
	
	public int getMovementY()
	{
		return movedY;
	}
	
	public boolean isMouseMoved()
	{
		return mouseMoved;
	}
	
	public void resetMouse()
	{
		mouseMoved = false;
		movedX = 0;
		movedY = 0;
	}


}
