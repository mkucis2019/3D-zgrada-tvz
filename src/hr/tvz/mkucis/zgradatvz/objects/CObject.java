package hr.tvz.mkucis.zgradatvz.objects;

import hr.tvz.mkucis.zgradatvz.math.Vec3;
import hr.tvz.mkucis.zgradatvz.objects.mesh.CMesh;
import hr.tvz.mkucis.zgradatvz.objects.texture.CTexture;

public class CObject
{
	  private CMesh mesh;
	  private CTexture texture;
	  public Vec3 location;
	  public Vec3 rotation;
	  public int colType;
	  
	  public CObject()
	  {
		  mesh = new CMesh();
		  texture = new CTexture();
		  location = new Vec3(0,0,0);
		  rotation = new Vec3(0,0,0);
		  colType = 0;
	  }
	  
	  public CMesh GetMesh()
	  {
		  return mesh;
	  }
	  
	  public CTexture GetTexture()
	  {
		  return texture;
	  }
}
