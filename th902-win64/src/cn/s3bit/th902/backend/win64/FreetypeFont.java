package cn.s3bit.th902.backend.win64;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import cn.s3bit.th902.utils.IFont;

public class FreetypeFont implements IFont {
	protected Font freetype;
	protected Graphics graphics;
	protected BufferedImage bufferedImage;
	protected FontRenderContext fontRenderContext;
	protected HashMap<Character, Texture> characters = new HashMap<>();
	
	private int mExtraLineSpace, mExtraCharSpace;

	@Override
	public void initialize(String fontName, boolean isBold, boolean isItalic, int size) {
		int m = 0;
		if (isBold) m |= Font.BOLD;
		if (isItalic) m |= Font.ITALIC;
		freetype = new Font(fontName, m, size);
		fontRenderContext = new FontRenderContext(null, false, false);
		bufferedImage = new BufferedImage((int) Math.ceil(freetype.getMaxCharBounds(fontRenderContext).getWidth()), (int) Math.ceil(freetype.getMaxCharBounds(fontRenderContext).getHeight()), BufferedImage.TYPE_4BYTE_ABGR);
		graphics = bufferedImage.getGraphics();
		graphics.setFont(freetype);
	}

	@Override
	public void draw(Batch batch, String string, int px, int py, float scale, Color color) {
		char[] chars = string.toCharArray();
		int dx = px;
		int dy = py;
		for (int i=0; i<chars.length; i++) {
			if (!characters.containsKey(Character.valueOf(chars[i]))) {
				Rectangle2D bounds = freetype.getStringBounds(chars, i, i + 1, fontRenderContext);
				Pixmap pixmap = new Pixmap((int) Math.ceil(bounds.getWidth()), (int) Math.ceil(bounds.getHeight()), Format.RGBA8888);
				graphics.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
				graphics.setColor(java.awt.Color.white);
				graphics.drawChars(chars, i, 1, 0, freetype.getSize());
				Raster raster = bufferedImage.getData();
				int[] imc = new int[4];
				for (int x=0; x<pixmap.getWidth(); x++)
					for (int y=0; y<pixmap.getHeight(); y++) {
						raster.getPixel(x, y, imc);
						if (imc[0] == 0 && imc[1] == 0 && imc[2] == 0)
							imc[3] = 0;
						pixmap.drawPixel(x, y, Color.argb8888(imc[3] / 255f, imc[0] / 255f, imc[1] / 255f, imc[2] / 255f));
					}
				Texture texture = new Texture(pixmap, Format.RGBA8888, false);
				characters.put(Character.valueOf(chars[i]), texture);
				pixmap.dispose();
			}
			Texture tx = characters.get(Character.valueOf(chars[i]));
			batch.setColor(color);
			batch.draw(tx, dx, dy, tx.getWidth() * scale, tx.getHeight() * scale);
			batch.setColor(Color.WHITE);
			if (chars[i] == '\r' || chars[i] == '\n') {
				dy += bufferedImage.getHeight() * scale + mExtraLineSpace;
				dx = px;
			} else {
				dx += tx.getWidth() * scale + mExtraCharSpace;
			}
		}
	}

	@Override
	public void setExtraLineSpace(int space) {
		mExtraLineSpace = space;
	}

	@Override
	public void setExtraCharSpace(int space) {
		mExtraCharSpace = space;
	}
}
