package hr.tvz.mkucis.zgradatvz.math;

public class Vec3
{
	public float x;
	public float y;
	public float z;
	
	public Vec3()
	{
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
	}
	
	public Vec3(float x,float y,float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3(double x,double y,double z)
	{
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
	}
	
	public void set(Vec3 v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public void add(Vec3 v)
	{
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
	}
	
	public void subtract(Vec3 v)
	{
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
	}
	
	public void multiply(float f)
	{
		this.x *= f;
		this.y *= f;
		this.z *= f;
	}
	
	public void multiply(Vec3 v)
	{
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
	}
	
	public void divide(float f)
	{
		this.x /= f;
		this.y /= f;
		this.z /= f;
	}
	
	public void divide(Vec3 v)
	{
		this.x /= v.x;
		this.y /= v.y;
		this.z /= v.z;
	}
	
	public Vec3 Copy()
	{
		return new Vec3(x,y,z);
	}
	
}
