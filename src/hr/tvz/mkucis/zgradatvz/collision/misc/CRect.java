package hr.tvz.mkucis.zgradatvz.collision.misc;

import hr.tvz.mkucis.zgradatvz.math.Vec3;

public class CRect 
{
	public Vec3 c1;
	public Vec3 c2;
	public Vec3 c3;
	public Vec3 c4;
	
	public CRect()
	{
		c1 = new Vec3(0,0,0);
		c2 = new Vec3(0,0,0);
		c3 = new Vec3(0,0,0);
		c4 = new Vec3(0,0,0);
	}
}
