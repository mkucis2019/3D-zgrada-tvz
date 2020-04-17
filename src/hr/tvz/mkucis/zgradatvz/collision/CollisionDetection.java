package hr.tvz.mkucis.zgradatvz.collision;

import hr.tvz.mkucis.zgradatvz.collision.misc.CRect;
import hr.tvz.mkucis.zgradatvz.math.CMath;
import hr.tvz.mkucis.zgradatvz.math.Vec3;
import hr.tvz.mkucis.zgradatvz.objects.CObject;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MFace;
import hr.tvz.mkucis.zgradatvz.render.CCamera;

import java.util.ArrayList;

public class CollisionDetection 
{
	private ArrayList<CObject> objList;
	private CCamera camera;
	private Vec3 oldCameraPos;
	private int lastColIndex = -1;

	public static final int COLLISION_NULL_OBJECT = 0;
	public static final int COLLISION_STATIC_OBJECT = 1;
	public static final int COLLISION_LIFT_OBJECT = 2;
	public static final int COLLISION_EVENT_EXECUTE = 3;

	public CollisionDetection(ArrayList<CObject> objs, CCamera camera)
	{
		this.objList = objs;
		this.camera = camera;
	}


	public void CollisionCameraCorrection()

	{
		Vec3 cameraPos = camera.getPos();
				
		ArrayList<MFace> tris = new ArrayList<MFace>();

		if(oldCameraPos == null)
		{
			oldCameraPos = cameraPos;
		}
		
		int colFaceIndex = -1;
		
		boolean collision = false;
		
		// ubaci trokute u listu

		for(CObject c : objList)
		{
			for(MFace tr : c.GetMesh().getFaceList())
			{
				//samo ravni polygoni koji su blizu
				if(tr.size.y == 0.0f && Math.abs(cameraPos.y - tr.centerLocation.y) < 20.0f)
					tris.add(tr);
			}
		}
		
		Vec3 a = null,b = null,c = null;
		MFace curTriangle;
		float newY = -1f;
		float dist = -999999;
		
		// iznad kojeg jesam
		for(int i = 0; i < tris.size(); i++)
		{
			a = new Vec3(); b = new Vec3(); c = new Vec3();
			
			curTriangle = tris.get(i);
			
			a.x = curTriangle.Vertex[0].x;
			a.y = curTriangle.Vertex[0].y;
			a.z = curTriangle.Vertex[0].z;
			b.x = curTriangle.Vertex[1].x;
			b.y = curTriangle.Vertex[1].y;
			b.z = curTriangle.Vertex[1].z;
			c.x = curTriangle.Vertex[2].x;
			c.y = curTriangle.Vertex[2].y;
			c.z = curTriangle.Vertex[2].z;
			
			if(CMath.IsPointOnTriangle(cameraPos, a, b, c))
			{
				if(curTriangle.centerLocation.y > dist)
				{
					dist = curTriangle.centerLocation.y;
					newY = dist  + 12.387f;
					colFaceIndex = i;
				}
			}
		}
		
		
		// ako nema kolizije
		if(colFaceIndex != -1&& Math.abs(cameraPos.y - newY) < 4f)
		{
			collision = false;
			
			Vec3 newCameraPos = camera.getPos();
			oldCameraPos = cameraPos.Copy();
			newCameraPos.y = newY;
			
			camera.setPos(newCameraPos);
			
			lastColIndex = colFaceIndex;
		}
		else
		{
			collision = true;
			
			/*
			curTriangle = tris.get(lastColIndex);
			
			Vec3 newCameraPos = cameraPos.Copy();
			
			a.x = curTriangle.Vertex[0].x;
			a.z = curTriangle.Vertex[0].z;
			b.x = curTriangle.Vertex[1].x;
			b.z = curTriangle.Vertex[1].z;
			c.x = curTriangle.Vertex[2].x;
			c.z = curTriangle.Vertex[2].z;
			
			Vec3 p1 = null,p2 = null;
			
			if(CMath.IntersectLines(a, b, oldCameraPos, cameraPos, null))
			{
				p1 = a; 
				p2 = b;
			}
			if(CMath.IntersectLines(b, c, oldCameraPos, cameraPos, null))
			{
				p1 = b; 
				p2 = c;
			}
			
			if(CMath.IntersectLines(c, a, oldCameraPos, cameraPos, null))
			{
				p1 = c; 
				p2 = a;
			}

			if(p1 != null && p2 != null)
			{ 
				Vec3 diff = CMath.VectorSubtract(p1, p2);
				Vec3 velocity = CMath.VectorSubtract(oldCameraPos, cameraPos);
				float lineAngle = (float) (Math.atan2(diff.z, diff.x));
				float entryAngle = (float) (Math.atan2(velocity.z, velocity.x));
				
				
				float speed = (float) (-Math.sin(entryAngle));
				
				if(speed > 1f) speed = 1f;
				if(speed < -1f) speed = -1f;
				
				newCameraPos.x = (float) (oldCameraPos.x +  (speed * Math.cos(lineAngle)));
				newCameraPos.z = (float) (oldCameraPos.z +  (speed * Math.sin(lineAngle)));

				if(CMath.IsPointOnTriangle(newCameraPos, a, b, c))
				{
					oldCameraPos = newCameraPos.Copy();
				}



			}
			*/
			camera.setPos(oldCameraPos);
		}
		
	}
}
