package hr.tvz.mkucis.zgradatvz.objects.mesh.data;

public class MTexCords 
{
	public float u,v,w;
	
	public MTexCords()
	{
		u = 0;
		v = 0;
		w = 0;
	}
	
	public MTexCords(float x, float y, float z)
	{
		this.u = x;
		this.v = y;
		this.w = z;
	}
}
