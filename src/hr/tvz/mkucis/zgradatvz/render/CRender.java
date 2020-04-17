package hr.tvz.mkucis.zgradatvz.render;

import hr.tvz.mkucis.zgradatvz.math.Vec4;
import hr.tvz.mkucis.zgradatvz.objects.CObject;
import hr.tvz.mkucis.zgradatvz.objects.mesh.CMesh;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MFace;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MNormal;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MTexCords;
import hr.tvz.mkucis.zgradatvz.objects.mesh.data.MVertex;
import hr.tvz.mkucis.zgradatvz.objects.texture.CTexture;

import java.applet.Applet;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;


import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;


public class CRender 
{
	private GLU glu = null;
	private GLUT glut = null;
	private GL2 gl = null;
	private GLCanvas canvas = null;
	
	public float aspect;
	
	public void InitApplet(Applet a, KeyListener kl, MouseMotionListener ml)
	{
		glu = new GLU();
		glut = new GLUT();
		
        GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
        capabilities.setHardwareAccelerated(true);
        capabilities.setNumSamples(4);
        capabilities.setAlphaBits(8);
        capabilities.setSampleBuffers(true);
        
		canvas = new GLCanvas(capabilities);
		a.add(canvas);
		
		canvas.addGLEventListener((GLEventListener) a);
		canvas.addKeyListener(kl);
		canvas.addMouseMotionListener(ml);
		canvas.reshape(0, 0, a.getWidth(), a.getHeight());
		
		
		final FPSAnimator animator = new FPSAnimator(canvas, 55);
		animator.start();
	}
	
	public void InitOpenGL(GLAutoDrawable glDrawable)
	{
		gl = glDrawable.getGL().getGL2();
		
		/*
        float light_ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
        float light_diffuse[] = { 0.8f, 0.8f, 0.8f, 1.0f };
        float light_specular[] = { 0.5f, 0.5f, 0.5f, 1.0f };

        float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };


        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
               
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
		*/
		
		gl.glDisable(GL2.GL_LIGHTING);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable (GL2.GL_BLEND); 
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        
        gl.glDisable(GL2.GL_TEXTURE_1D);
	}
		
	public void BeginScene(GLAutoDrawable glDrawable)
	{
        int w = glDrawable.getWidth();
        int h = glDrawable.getHeight();
        
	    h = (h == 0) ? 1 : h;
	    aspect = (float)w / h; 
		

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0.0f, 0.5f, 1.0f, 1.0f);
		
		
        gl.glMatrixMode(GL2.GL_PROJECTION);								
		gl.glLoadIdentity();
		
		gl.glViewport(0, 0, w, h);
		glu.gluPerspective(80.0f, aspect, 0.5f, 1000.0f); 
		
		//gl.glEnable(GL2.GL_CULL_FACE);
	}
	
	public void UpdateView(CCamera camera)
	{
        glu.gluLookAt(camera.getXPos(), camera.getYPos() , camera.getZPos(), camera.getXLPos(), camera.getYLPos(), camera.getZLPos(), 0.0, 1.0, 0.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}
	
	public void EndScene()
	{
		gl.glMatrixMode (GL2.GL_PROJECTION);
		gl.glLoadIdentity ();
		gl.glOrtho (0, canvas.getWidth(), canvas.getHeight(), 0, 0f, 1f);
		gl.glMatrixMode (GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void RenderText(int x, int y, Vec4 color,int glTextFont,String text)
	{
		gl.glColor4f(color.r, color.g,color.b, color.a);
		gl.glRasterPos2i(x,y); 
		glut.glutBitmapString(glTextFont, text);
	}
	
	public GL getGL()
	{
		return gl;
	}
	
	public void Reshape(Applet a,GLAutoDrawable glDrawable,int w, int h)
	{
	      GL2 gl2 = glDrawable.getGL().getGL2();
	      
	      h = (h == 0) ? 1 : h;
	      float aspect = (float)w / h; 
	      
	      gl2.glViewport(0, 0, w, h);
	      gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);  
	      gl2.glLoadIdentity();   
	      glu.gluPerspective(65.0f, aspect, 0.1f, 1000.0f); 
	      
	      gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW); 
	      
	      canvas.reshape(0, 0, a.getWidth(), a.getHeight());
	}
	
	public void RenderObject(CObject obj)
	{
		CMesh m; MFace f; CTexture tx;
		
		tx = obj.GetTexture(); m = obj.GetMesh();
		
		if(tx.hasTexture()) 
		{
			tx.apply2Object(gl.getGL());	
		}
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glLoadIdentity(); 
		gl.glTranslatef(obj.location.x, obj.location.y, obj.location.z);
		gl.glRotatef(obj.rotation.x, 1, 0, 0);
		gl.glRotatef(obj.rotation.y, 0, 1, 0);
		gl.glRotatef(obj.rotation.z, 0, 0, 1);
		//gl.glTexEnvi (GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_DECAL);
		gl.glColor4f(1.0f, 1.0f, 1.0f,1.0f);
		
		for(int i = 0; i < m.getFaceList().size();i++)
		{
			f = m.getFaceList().get(i);
			gl.glBegin(GL2.GL_POLYGON);
		
			for(int j = 0; j < 3; j++)
			{
				gl.glNormal3f(f.Normal[j].x, f.Normal[j].y, f.Normal[j].z);
				gl.glTexCoord3f(f.TexCords[j].u,f.TexCords[j].v,f.TexCords[j].w);
				gl.glVertex3f(f.Vertex[j].x, f.Vertex[j].y, f.Vertex[j].z);
			}

			gl.glEnd();
		}
		
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
	}
	
}
