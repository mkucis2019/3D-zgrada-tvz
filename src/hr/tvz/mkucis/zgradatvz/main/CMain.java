package hr.tvz.mkucis.zgradatvz.main;

import hr.tvz.mkucis.zgradatvz.collision.CollisionDetection;
import hr.tvz.mkucis.zgradatvz.input.CKeyboard;
import hr.tvz.mkucis.zgradatvz.input.CMouse;
import hr.tvz.mkucis.zgradatvz.math.Vec4;
import hr.tvz.mkucis.zgradatvz.objects.CObject;
import hr.tvz.mkucis.zgradatvz.render.CCamera;
import hr.tvz.mkucis.zgradatvz.render.CRender;

import java.applet.Applet;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.jogamp.opengl.util.gl2.GLUT;


public class CMain extends Applet implements GLEventListener
{
	private CRender engine;
	private static final long serialVersionUID = 1L;
	
	private CCamera camera;
	private CollisionDetection cdetect;
	private CMouse mouse;
	
	private CObject test;
	private CObject dvoriste;
	private CObject ulaz;
	private CObject ulaz2;
	private CObject kat1;
	private CObject kat2;
	private CObject stol;
	private CObject krov;
	
	private CKeyboard keyboard;
	
	private boolean skipCollision = false;

	private void CheckKeys()
	{
		if(keyboard.CheckKeyPressed('w') || keyboard.CheckKeyPressed('W'))
		{
            camera.moveForward(1.0);
            camera.look(10);
		}
		
		if(keyboard.CheckKeyPressed('s') || keyboard.CheckKeyPressed('S'))
		{
            camera.moveForward(-1.0);
            camera.look(10);
		}
		
		if(keyboard.CheckKeyPressed('q') || keyboard.CheckKeyPressed('Q'))
		{
            camera.yawLeft(0.06);
            camera.look(10);
		}
		
		if(keyboard.CheckKeyPressed('e') || keyboard.CheckKeyPressed('E'))
		{
            camera.yawRight(0.06);
            camera.look(10);
		}
		
		if(keyboard.CheckKeyPressed('a') || keyboard.CheckKeyPressed('A'))
		{
            camera.strafeLeft(0.7);
            camera.look(10);
		}
		
		if(keyboard.CheckKeyPressed('d') || keyboard.CheckKeyPressed('D'))
		{
            camera.strafeRight(0.7);
            camera.look(10);
		}
		
		if(keyboard.CheckKeyPressed('f'))
		{
			skipCollision = true;
		}
		else
		{
			skipCollision = false;
		}
		
		/*
		if(mouse.isMouseMoved())
		{
			camera.yawLeft(mouse.getMovementX()/-10.0);
			camera.look(10);
			
			camera.pitchUp(mouse.getMovementY()/-10.0);
			camera.look(10);
			
			mouse.resetMouse();
		}
		*/
		
	}
	
	@Override
	public void display(GLAutoDrawable glDrawable) 
	{
		// pripremi novu scenu i postavi 3D naèin rada
		engine.BeginScene(glDrawable);
		
		// provjeri pritisnute tipke i pomakni kameru
		CheckKeys();
		
		//izvrši korekciju kolizije
		if(skipCollision == false)
			cdetect.CollisionCameraCorrection();
		
		// primjeni promjene na scenu
		engine.UpdateView(camera);
		
		// iscrtavanje 3D objekata
        engine.RenderObject(dvoriste);
        engine.RenderObject(ulaz2);
        engine.RenderObject(stol);
        engine.RenderObject(krov);
        engine.RenderObject(kat1);
        engine.RenderObject(kat2);
        engine.RenderObject(ulaz);
        

        // kraj scene i postavi 2D naèin rada
		engine.EndScene();
		
		// ispiši tekst
		String s = String.format("Kamera: %.2f %.2f", camera.getPitch(), camera.getYaw());
		//engine.RenderText(10, 20, new Vec4(1,0,0,1), GLUT.BITMAP_HELVETICA_12, "Beta Verzija!");
		engine.RenderText(10, 20, new Vec4(1,0,0,1), GLUT.BITMAP_HELVETICA_12, s);
		
		s = String.format("Lokacija: %.2f %.2f %.2f", camera.getXPos(), camera.getYPos(),camera.getZPos());
		
		engine.RenderText(10, 40, new Vec4(1,0,0,1), GLUT.BITMAP_HELVETICA_12, s);
	}

	@Override
	public void dispose(GLAutoDrawable glDrawable) 
	{
		// TODO Auto-generated method stub
	
	}

	@Override
	public void init(GLAutoDrawable glDrawable) 
	{
		engine.InitOpenGL(glDrawable);
		
		// kreiranje objekata
		test = new CObject();
		ulaz = new CObject();
		ulaz2 = new CObject();
		dvoriste = new CObject();
		stol = new CObject();
		krov = new CObject();
		kat1 = new CObject();
		kat2 = new CObject();
		try 
		{			
			ulaz.GetMesh().LoadObjFromResource("/resource/ulaz.obj");
			ulaz.GetTexture().LoadTextureFromResource("/resource/ulaz.png", "png");
			ulaz.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			/*
			ulaz.location.x = -0.032f;
			ulaz.location.y = -10.083f;
			ulaz.location.z = 0.083f;
			*/
			dvoriste.GetMesh().LoadObjFromResource("/resource/dvoriste.obj");
			dvoriste.GetTexture().LoadTextureFromResource("/resource/dvoriste.png", "png");
			dvoriste.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			/*
			dvoriste.location.x = 0f;
			dvoriste.location.y = -11.236f;
			dvoriste.location.z = 1.136f;
			*/
			
			stol.GetMesh().LoadObjFromResource("/resource/stol_stolac.obj");
			stol.GetTexture().LoadTextureFromResource("/resource/stol_stolac.png", "png");
			stol.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			/*
			stol.location.x = 0f;
			stol.location.y = -11.239f;
			stol.location.z = 0f;
			*/
			
			ulaz2.GetMesh().LoadObjFromResource("/resource/ulaz_d2.obj");
			ulaz2.GetTexture().LoadTextureFromResource("/resource/ulaz_d2.png", "png");
			ulaz2.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			
			kat1.GetMesh().LoadObjFromResource("/resource/kat1_d.obj");
			kat1.GetTexture().LoadTextureFromResource("/resource/kat1_d.png", "png");
			kat1.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			
			kat2.GetMesh().LoadObjFromResource("/resource/kat2_d.obj");
			kat2.GetTexture().LoadTextureFromResource("/resource/kat2_d.png", "png");
			kat2.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			
			krov.GetMesh().LoadObjFromResource("/resource/krov.obj");
			krov.GetTexture().LoadTextureFromResource("/resource/krov.png", "png");
			krov.colType = CollisionDetection.COLLISION_STATIC_OBJECT;
			
			ArrayList<CObject> colObjs = new ArrayList<CObject>();
			colObjs.add(ulaz);
			colObjs.add(ulaz2);
			colObjs.add(dvoriste);
			colObjs.add(stol);
			colObjs.add(kat1);
			colObjs.add(kat2);
			
			cdetect = new CollisionDetection(colObjs,camera);
			
			
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch blockEE
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		// kreiraj objekt klase CRender
		engine = new CRender();
		
		// inicijaliziraj applet
		engine.InitApplet(this,keyboard = new CKeyboard(), mouse = new CMouse());
		
		// kreiraj kameru
		camera = new CCamera();
        camera.yawRight(7.82);
        camera.pitchDown(0);
        camera.strafeRight(4.0);
        camera.moveForward(20.0);
        camera.updatePosition(camera.getXPos(), 11.36f, camera.getZPos());
        camera.look(10);
        
	}

	@Override
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int w, int h) 
	{
		// 
		engine.Reshape(this, glDrawable, w, h);
	}

}
