package hr.tvz.mkucis.zgradatvz.math;

public class Vec4
{
	public float r;
	public float g;
	public float b;
	public float a;
	
	public Vec4()
	{
		this.r = 0.0f;
		this.g = 0.0f;
		this.b = 0.0f;
		this.a = 0.0f;
	}
	
	public Vec4(float r,float g,float b,float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void set(Vec4 v)
	{
		this.r = v.r;
		this.g = v.g;
		this.b = v.b;
		this.a = v.a;
	}
	
	public void add(Vec4 v)
	{
		this.r += v.r;
		this.g += v.g;
		this.b += v.b;
		this.a += v.a;
	}
	
	public void subtract(Vec4 v)
	{
		this.r -= v.r;
		this.g -= v.g;
		this.b -= v.b;
		this.a -= v.a;
	}
	
	public void multiply(float f)
	{
		this.r *= f;
		this.g *= f;
		this.b *= f;
		this.a *= f;
	}
	
	public void multiply(Vec4 v)
	{
		this.r *= v.r;
		this.g *= v.g;
		this.b *= v.b;
		this.a *= v.a;
	}
	
	public void divide(float f)
	{
		this.r /= f;
		this.g /= f;
		this.b /= f;
		this.a /= f;
	}
	
	public void divide(Vec4 v)
	{
		this.r /= v.r;
		this.g /= v.g;
		this.b /= v.b;
		this.a /= v.a;
	}
	
}
