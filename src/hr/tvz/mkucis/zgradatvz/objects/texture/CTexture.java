package hr.tvz.mkucis.zgradatvz.objects.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class CTexture 
{
	private Texture texture;
	private boolean loaded;
	
	public CTexture()
	{
		loaded = false;
	}
	
	public void LoadTextureFromResource(String res,String type) throws IOException
	{
		
        InputStream stream = getClass().getResourceAsStream(res);
        TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),stream,GL2.GL_RGBA,GL2.GL_RGBA, false, type);
        this.texture = TextureIO.newTexture(data);


        this.loaded = true;
	}
	
	public boolean hasTexture()
	{
		return loaded;
	}
	
	public void apply2Object(GL gl)
	{
		texture.enable(gl);
		texture.bind(gl);
	}
	
}
