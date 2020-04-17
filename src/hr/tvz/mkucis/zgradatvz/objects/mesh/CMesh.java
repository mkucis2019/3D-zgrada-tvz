package hr.tvz.mkucis.zgradatvz.objects.mesh;

import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MFace;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MNormal;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MTexCords;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MVertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class CMesh 
{
	private ArrayList<MVertex> VertexList;
	private ArrayList<MTexCords> TexCordsList;
	private ArrayList<MNormal> NormalList;
	private ArrayList<MFace> FaceList;
	
	public ArrayList<MVertex> getVertexList() 
	{
		return VertexList;
	}

	public ArrayList<MTexCords> getTexCordsList() 
	{
		return TexCordsList;
	}

	public ArrayList<MNormal> getNormalList() 
	{
		return NormalList;
	}

	public ArrayList<MFace> getFaceList() 
	{
		return FaceList;
	}
	
	public CMesh()
	{
		VertexList = new ArrayList<MVertex>();
		TexCordsList = new ArrayList<MTexCords>();
		NormalList = new ArrayList<MNormal>();
		FaceList = new ArrayList<MFace>();
	}
	
	private void ProcessLine(String line)
	{
		String id;
		Scanner sc = new Scanner(line);

		try
		{
			id = sc.next();
		}
		catch(Exception e)
		{
			sc.close();
			return;
		}
		
		float x,y,z,u,v,w;
		
		if(id.equals("v"))
		{
			x = Float.parseFloat(sc.next());
			y = Float.parseFloat(sc.next());
			z = Float.parseFloat(sc.next());
			
			VertexList.add(new MVertex(x,y,z));
		}
		
		if(id.equals("vt"))
		{
			u = Float.parseFloat(sc.next());
			v = Float.parseFloat(sc.next());

			if(sc.hasNext())
				w = Float.parseFloat(sc.next());
			else
				w = 0;
				
			TexCordsList.add(new MTexCords(u,v,w));
		}
		
		if(id.equals("vn"))
		{
			if(line.contains("-1#IND00"))
			{
				NormalList.add(new MNormal());
			}
			else
			{
				x = Float.parseFloat(sc.next());
				y = Float.parseFloat(sc.next());
				z = Float.parseFloat(sc.next());

				NormalList.add(new MNormal(x,y,z));
			}
		}
		
		if(id.equals("f"))
		{
			
			int a,b,c; String tmp; Scanner sc2; MFace face = new MFace();
			int iV,iN,iT;
			
			iV = iN = iT = 0;
			
			while(sc.hasNext())
			{
				
				tmp = sc.next();
				
				if(!tmp.contains("/"))
				{
					//face.AddPolygon(new VNT(Integer.parseInt(tmp),0,0));
					face.AddVertex(VertexList.get(Integer.parseInt(tmp) - 1), iV);
					iV++;
					continue;
				}
				
				if(tmp.contains("//"))
				{
					sc2 = new Scanner(tmp.replace("//", " "));
					a = Integer.parseInt(sc2.next());
					b = Integer.parseInt(sc2.next());
					
					//face.AddPolygon(new VNT(a,b,0));
					face.AddVertex(VertexList.get(a - 1), iV);
					face.AddNormal(NormalList.get(b - 1), iN);
					iV ++;
					iN ++;
	
					sc2.close();
					continue;
				}
				
				
				sc2 = new Scanner(tmp.replace("/", " "));
				a = Integer.parseInt(sc2.next());
				c = Integer.parseInt(sc2.next());
				
				if(sc2.hasNext())
					b = Integer.parseInt(sc2.next());
				else
					b = 0;
				
				//face.AddPolygon(new VNT(a,b,c));
				face.AddVertex(VertexList.get(a - 1), iV);
				face.AddNormal(NormalList.get(b - 1), iN);
				face.AddTexCords(TexCordsList.get(c - 1), iT);
				iV ++;
				iN ++;
				iT ++;
				sc2.close();
			}
			
			face.UpdateProperties();
			FaceList.add(face);
			
		}
		
		sc.close();
	}
	
	private void GenerateFlatNormals()
	{
		if(NormalList.size() == 0)
		{
			float len; MNormal n; MVertex v1,v2,v3; 
			
			for(int i = 0; i < FaceList.size(); i++)
			{
				n = new MNormal();
				
				v1 = FaceList.get(i).Vertex[0];
				v2 = FaceList.get(i).Vertex[1];
				v3 = FaceList.get(i).Vertex[2];
				
				n.x = (v2.y - v1.y)*(v3.z - v1.z) - (v2.z - v1.z)*(v3.y - v1.y);
				n.y = (v2.z - v1.z)*(v3.x - v1.x) - (v2.x - v1.x)*(v3.z - v1.z);
				n.z = (v2.x - v1.x)*(v3.y - v1.y) - (v2.y - v1.y)*(v3.x - v1.x);
				
				len = (float) Math.sqrt(n.x*n.x + n.y*n.y + n.z*n.z);
				if(len > 0)
				{
					n.x *= 1/len;
					n.y *= 1/len;
					n.z *= 1/len;
				}
				
				NormalList.add(n);
				for(int j = 0; j < 3; j++)
				{
					FaceList.get(i).Normal[i] = n;
				}
			}
		}
	}

	public void LoadObjFromResource(String path) throws IOException,NumberFormatException
	{
		InputStream res = getClass().getResourceAsStream(path);
		BufferedReader r = new BufferedReader(new InputStreamReader(res));
		String line;
		
		VertexList.clear();
		TexCordsList.clear();
		NormalList.clear();
		FaceList.clear();

		while ((line = r.readLine()) != null) 
		{
			ProcessLine(line);
		}
		
		GenerateFlatNormals();

		res.close();
		r.close();
	}

}
