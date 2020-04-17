package hr.tvz.mkucis.zgradatvz.render;

import hr.tvz.mkucis.zgradatvz.math.Vec3;

public class CCamera 
{
	private double xPos;
    private double yPos;
    private double zPos;
    
    private double xLPos;
    private double yLPos;
    private double zLPos;
    
    private double pitch;
    private double yaw;
    
    public CCamera()
    {
        xPos = 0;
        yPos = 0;
        zPos = 0;
        
        xLPos = 0;
        yLPos = 0;
        zLPos = 10;
    }
    
    public CCamera(double xPos, double yPos, double zPos, double xLPos, double yLPos, double zLPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        
        this.xLPos = xLPos;
        this.yLPos = yLPos;
        this.zLPos = zLPos;
    }
    
    public void setPitch(double pitch)
    {
    	this.pitch = pitch;
    }
    
    public void setYaw(double yaw)
    {
    	this.yaw = yaw;
    }

    public void updatePosition(double xPos, double yPos, double zPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }
    
    public void lookPosition(double xLPos, double yLPos, double zLPos)
    {
        this.xLPos = xLPos;
        this.yLPos = yLPos;
        this.zLPos = zLPos;
    }

    
    public void moveForward(double magnitude)
    {
        double xCurrent = this.xPos;
        double yCurrent = this.yPos;
        double zCurrent = this.zPos;
        
        double xMovement = magnitude * Math.cos(pitch) * Math.cos(yaw); 
        double yMovement = magnitude * Math.sin(pitch);
        double zMovement = magnitude * Math.cos(pitch) * Math.sin(yaw);
              
        double xNew = xCurrent + xMovement;
        double yNew = yCurrent + yMovement;
        double zNew = zCurrent + zMovement;
        
        updatePosition(xNew, yNew, zNew);
    }
    
    
    public void strafeLeft(double magnitude)
    {
        double pitchTemp = pitch;
        pitch = 0;
        yaw = yaw - (0.5 * Math.PI);
        moveForward(magnitude);

        pitch = pitchTemp;
        yaw = yaw + (0.5 * Math.PI);
    }
    
    public void strafeRight(double magnitude)
    {
        double pitchTemp = pitch;
        pitch = 0;

        yaw = yaw + (0.5 * Math.PI);
        moveForward(magnitude);
        yaw = yaw - (0.5 * Math.PI);

        pitch = pitchTemp;
    }
    
    
    public void look(double distanceAway)
    {
        if(pitch > 1.0)
        pitch = 0.99;
        
        if(pitch < -1.0)
        pitch = -0.99;
         
        moveForward(10);
        
        double xLook = xPos;
        double yLook = yPos;
        double zLook = zPos;
        
        moveForward(-10);
        
        lookPosition(xLook, yLook, zLook);
    }
           
    public Vec3 getPos()
    {
    	return new Vec3(xPos,yPos,zPos);
    }
    
    public void setPos(Vec3 v)
    {
    	updatePosition(v.x,v.y,v.z);
    	look(10);
    }
    
    public double getXPos()
    {
        return xPos;
    }
    
    public double getYPos()
    {
        return yPos;
    }
    
    public double getZPos()
    {
        return zPos;
    }
    
    public double getXLPos()
    {
        return xLPos;
    }
    
    public double getYLPos()
    {
        return yLPos;
    }
    
    public double getZLPos()
    {
        return zLPos;
    }
    
    public double getPitch()
    {
        return pitch;
    }
    
    public double getYaw()
    {
        return yaw;
    }
    
    
    public void pitchUp(double amount)
    {
        this.pitch += amount;
    }
    
    public void pitchDown(double amount)
    {
        this.pitch -= amount;
    }
    
    public void yawRight(double amount)
    {
        this.yaw += amount;
    }
    
    public void yawLeft(double amount)
    {
        this.yaw -= amount;
    }

}
