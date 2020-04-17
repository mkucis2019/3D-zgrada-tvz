package hr.tvz.mkucis.zgradatvz.objects.mesh.data;

import hr.tvz.mkucis.zgradatvz.math.Vec3;

import java.util.ArrayList;

public class MFace 
{
	public MVertex Vertex[];
	public MNormal Normal[];
	public MTexCords TexCords[];
	public Vec3 centerLocation;
	public Vec3 size;
	
	public MFace()
	{
		Vertex = new MVertex[3];
		Normal = new MNormal[3];
		TexCords = new MTexCords[3];
	}
	
	public void AddVertex(MVertex m, int index)
	{
		Vertex[index] = m;
	}
	
	public void AddNormal(MNormal n, int index)
	{
		Normal[index] = n;
	}
	
	public void AddTexCords(MTexCords t, int index)
	{
		TexCords[index] = t;
	}
	
	public void UpdateProperties()
	{
		Vec3 tmp = new Vec3(0,0,0);
		Vec3 minSize, maxSize;
		
		//centerLocation = new Vec3(0,0,0);
		size = new Vec3(0,0,0);
				
		minSize = new Vec3(0,0,0);
		
		minSize.x = Vertex[0].x;
		minSize.y = Vertex[0].y;
		minSize.z = Vertex[0].z;
		
		maxSize = minSize.Copy();
		centerLocation = minSize.Copy();
		
		for(int i = 1; i < 3; i++)
		{
			tmp.x = Vertex[i].x;
			tmp.y = Vertex[i].y;
			tmp.z = Vertex[i].z;
			
			if(tmp.x < minSize.x) minSize.x = tmp.x;
			if(tmp.y < minSize.y) minSize.y = tmp.y;
			if(tmp.z < minSize.z) minSize.z = tmp.z;
			
			if(tmp.x > maxSize.x) maxSize.x = tmp.x;
			if(tmp.y > maxSize.y) maxSize.y = tmp.y;
			if(tmp.z > maxSize.z) maxSize.z = tmp.z;
			
			centerLocation.x += tmp.x;
			centerLocation.y += tmp.y;
			centerLocation.z += tmp.z;
			
			size.x = Math.abs(minSize.x - maxSize.x);
			size.y = Math.abs(minSize.y - maxSize.y);
			size.z = Math.abs(minSize.z - maxSize.z);
		}
		
		centerLocation.divide(3.0f);
	}
	
	public Vec3 GetFlatNormal()
	{
		Vec3 temp = new Vec3(); 
		float ux;
		float uy;
		float uz;
		float vx;
		float vy;
		float vz;
		ux = Vertex[1].x - Vertex[0].x;
		uy = Vertex[1].y - Vertex[0].y;
		uz = Vertex[1].z - Vertex[0].z;
		vx = Vertex[2].x - Vertex[0].x;
		vy = Vertex[2].y - Vertex[0].y;
		vz = Vertex[2].z - Vertex[0].z;
		temp.x = (uy*vz)-(vy*uz);
		temp.y = (uz*vx)-(vz*ux);
		temp.z = (ux*vy)-(vx*uy);
		return temp;
	}
}
