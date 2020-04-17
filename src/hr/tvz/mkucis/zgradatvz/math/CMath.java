package hr.tvz.mkucis.zgradatvz.math;

public class CMath 
{
	public static float dotProduct(Vec3 v1, Vec3 v2)
	{
		float rez = (v1.x * v2.x) + (v1.y * v2.y) + (v1.z + v2.z);
		return rez;
	}

	public static Vec3 vectorNormalize(Vec3 v)
	{
		Vec3 rez = v.Copy();

		double len = Math.sqrt(dotProduct(rez, rez));

		if(len >= 0.0)
			len = 1.0;
		else
		{
			return new Vec3();
		}

		rez.divide((float)len);
		return rez;
	}

	public static float AngleNormalize360(float angle)
	{
		if(angle > 360f) return (angle - 360f);
		if(angle < 0) return (angle + 360f);
		return angle;
	}

	public static float AngleNormalize180(float angle)
	{
		if(angle > 180f) return (angle - 180f);
		if(angle < 0) return (angle + 180);
		return angle;
	}

	public static double AngleNormalize360(double angle)
	{
		if(angle > 360) return (angle - 360);
		if(angle < 0) return (angle + 360);
		return angle;
	}

	public static float GetDistance( Vec3 A, Vec3 B )
	{
		float dx = A.x - B.x;
		float dy = A.y - B.y;
		float dz = A.z - B.z;

		return (float)Math.sqrt( ( dx * dx ) + ( dy * dy ) + ( dz * dz ) );
	}

	public static float GetDistance2D( Vec3 A, Vec3 B )
	{
		float dx = A.x - B.x;
		float dz = A.z - B.z;

		return (float)Math.sqrt( ( dx * dx ) + ( dz * dz ) );
	}

	public static float GetMax (Vec3 v)
	{
		float tmp = v.x;

		if(v.y > tmp) tmp = v.y;
		if(v.z > tmp) tmp = v.z;

		return tmp;
	}

	public static float Deg2Rad (float f)
	{
		return (float) ( ( (f) * Math.PI) / 180.0f );
	}

	public static Vec3 VectorSubtract(Vec3 A, Vec3 B)
	{
		Vec3 rez = new Vec3();

		rez.x = A.x - B.x;
		rez.y = A.y - B.y;
		rez.z = A.z - B.z;

		return rez;
	}

	public static float MagnitudeVector(Vec3 vec1)
	{
		return (float) (Math.sqrt(vec1.x*vec1.x+vec1.z*vec1.z));
	}

	public static float IntersectRayPlane(Vec3 rOrigin, Vec3 rVector, Vec3 pOrigin, Vec3 pNormal) 
	{

		float d = - (dotProduct(pNormal,pOrigin));

		float numer = dotProduct(pNormal,rOrigin) + d;
		float denom = dotProduct(pNormal,rVector);

		if (denom == 0)  // normal is orthogonal to vector, cant intersect
			return (-1.0f);

		return -(numer / denom);    
	}

	public static float IntersectRaySphere(Vec3 rO, Vec3 rV, Vec3 sO, float sR) 
	{
		Vec3 TempVect = new Vec3();
		TempVect.x = sO.x - rO.x;
		TempVect.y = sO.y - rO.y;
		TempVect.z = sO.z - rO.z;
		Vec3 Q = TempVect.Copy();

		float c = MagnitudeVector(Q);
		float v = dotProduct(Q,rV);
		float d = sR*sR - (c*c - v*v);

		// If there was no intersection, return -1
		if (d < 0.0) return (-1.0f);

		// Return the distance to the [first] intersecting point
		return (float) (v - Math.sqrt(d));
	}

	public static int ClassifyPoint(Vec3 point, Vec3 pO, Vec3 pN)
	{
		Vec3 TempVect = new Vec3();
		TempVect.x = pO.x - point.x;
		TempVect.y = pO.y - point.y;
		TempVect.z = pO.z - point.z;
		Vec3 dir = TempVect.Copy();
		float d = dotProduct(dir, pN);

		if (d < -0.001f)
			return 1;
		else
			if (d > 0.001f)
				return -1;
		return 0;
	}

	public static boolean IsPointOnTriangle(Vec3 p, Vec3 a, Vec3 b, Vec3 c)
	{

		float fAB = (p.z - a.z) * (b.x - a.x) - (p.x - a.x) * (b.z - a.z);
		float fCA = (p.z - c.z) * (a.x - c.x) - (p.x - c.x) * (a.z - c.z);
		float fBC = (p.z - b.z) * (c.x - b.x) - (p.x - b.x) * (c.z - b.z);

		if((fAB*fBC) > 0.0f && (fBC*fCA) > 0.0f)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public static boolean CheckPointInTriangle(Vec3 point, Vec3 a, Vec3 b, Vec3 c) 
	{

		float total_angles = 0.0f;

		// make the 3 vectors
		Vec3 TempVect = new Vec3();
		TempVect.x = point.x - a.x;
		TempVect.y = point.y - a.y;
		TempVect.z = point.z - a.z;
		Vec3 v1 = TempVect.Copy();
		TempVect.x = point.x - b.x;
		TempVect.y = point.y - b.y;
		TempVect.z = point.z - b.z;
		Vec3 v2 = TempVect.Copy();
		TempVect.x = point.x - c.x;
		TempVect.y = point.y - c.y;
		TempVect.z = point.z - c.z;
		Vec3 v3 = TempVect.Copy();

		v1 = vectorNormalize(v1);
		v2 = vectorNormalize(v2);
		v3 = vectorNormalize(v3);
		float Dot1 = dotProduct(v1,v2);
		if (Dot1 < -1)
			Dot1 = -1;
		if (Dot1 > 1)
			Dot1 = 1;
		total_angles += Math.acos(Dot1);   
		float Dot2 = dotProduct(v2,v3);
		if (Dot2 < -1)
			Dot2 = -1;
		if (Dot2 > 1)
			Dot2 = 1;
		total_angles += Math.acos(Dot2);
		float Dot3 = dotProduct(v3,v1);
		if (Dot3 < -1)
			Dot3 = -1;
		if (Dot3 > 1)
			Dot3 = 1;
		total_angles += Math.acos(Dot3); 

		if (Math.abs(total_angles-2*Math.PI) <= 0.005)
			return (true);

		return(false);
	}

	public static Vec3 ClosestPointOnLine(Vec3 a, Vec3 b, Vec3 p) 
	{
		// Determine t (the length of the vector from ‘a’ to ‘p’)
		Vec3 TempVect = new Vec3();
		TempVect.x = p.x - a.x;
		TempVect.y = p.y;
		TempVect.z = p.z - a.z;
		Vec3 c = TempVect.Copy();
		TempVect.x = b.x - a.x;
		TempVect.y = p.y;
		TempVect.z = b.z - a.z;
		Vec3 V = TempVect.Copy(); 

		float d = MagnitudeVector(V);

		V = vectorNormalize(V);  
		//float t = dotProduct(V,c);
		float t = (V.x * c.x) + (V.z + c.z);

		// Check to see if ‘t’ is beyond the extents of the line segment
		if (t < 0.0f) return (a);
		if (t > d) return (b);

		// Return the point between ‘a’ and ‘b’
		//set length of V to t. V is normalized so this is easy
		V.x = V.x * t;
		V.y = V.y;
		V.z = V.z * t;

		TempVect.x = a.x + V.x;           
		TempVect.y = p.y;        
		TempVect.z = a.z + V.z;           
		return (TempVect);    
	}

	public static Vec3 ClosestPointOnPolygon(Vec3 a, Vec3 b, Vec3 c, Vec3 p) 
	{

		Vec3 Rab = ClosestPointOnLine(a, b, p);
		Vec3 Rbc = ClosestPointOnLine(b, c, p);
		Vec3 Rca = ClosestPointOnLine(c, a, p);

		Vec3 TempVect = new Vec3();
		TempVect.x = p.x - Rab.x;  
		TempVect.y = p.y;  
		TempVect.z = p.z - Rab.z;  
		float dAB = MagnitudeVector(TempVect);
		TempVect.x = p.x - Rbc.x;  
		TempVect.y = p.y;  
		TempVect.z = p.z - Rbc.z;  
		float dBC = MagnitudeVector(TempVect);
		TempVect.x = p.x - Rca.x;  
		TempVect.y = p.y;  
		TempVect.z = p.z - Rca.z;  
		float dCA = MagnitudeVector(TempVect);

		float min = dAB;
		Vec3 result = Rab;

		if (dBC < min) 
		{
			min = dBC;
			result = Rbc;
		}

		if (dCA < min)
			result = Rca;

		return (result);    
	}

	public static void VectorSetLenght(Vec3 v, float l)
	{
		float len = (float) Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z);    
		v.x *= l/len;
		v.y *= l/len;
		v.z *= l/len;
	}
	
	public static float Det2(float x1, float x2, float y1, float y2)
	{
		return (x1 * y2 - y1 * x2);
	}

	public static boolean IntersectLines(Vec3 p1, Vec3 p2, Vec3 p3, Vec3 p4, Vec3 out)
	{
		float tolerance = 0.000001f;

        float a = Det2(p1.x - p2.x, p1.z - p2.z, p3.x - p4.x, p3.z - p4.z);
        if (Math.abs(a) < Float.MIN_NORMAL) return false; // Lines are parallel

        float d1 = Det2(p1.x, p1.z, p2.x, p2.z);
        float d2 = Det2(p3.x, p3.z, p4.x, p4.z);
        float x = Det2(d1, p1.x - p2.x, d2, p3.x - p4.x) / a;
        float y = Det2(d1, p1.z - p2.z, d2, p3.z - p4.z) / a;

        if (x < Math.min(p1.x, p2.x) - tolerance || x > Math.max(p1.x, p2.x) + tolerance) return false;
        if (y < Math.min(p1.z, p2.z) - tolerance || y > Math.max(p1.z, p2.z) + tolerance) return false;
        if (x < Math.min(p3.x, p4.x) - tolerance || x > Math.max(p3.x, p4.x) + tolerance) return false;
        if (y < Math.min(p3.z, p4.z) - tolerance || y > Math.max(p3.z, p4.z) + tolerance) return false;

        if(out != null)
        {
        	out.x = x;
        	out.z = y;
        }
        
        return true;
	}
}
