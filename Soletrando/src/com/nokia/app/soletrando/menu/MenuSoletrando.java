package com.nokia.app.soletrando.menu;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MenuSoletrando extends Menu {
	
	
	public static final int COMEÇAR = 0;
	public static final int CONTINUAR = 1;
	private final Image background;
	private int width;
	private int height;

	public MenuSoletrando(int w, int h, Listiner listiner) {
		super(2, w, h, listiner);
		background = loadImage("/background.png");
		setItem(COMEÇAR, new MenuItem(loadSprite("/btcomecar.png", 2)));
		setItem(CONTINUAR,new MenuItem(loadSprite("/btcontinuar.png", 2)));
		setSize(w, h);
		 
	}
	
	public final void setSize(int w, int h)
	{
		width = w;
		height = h;
		
		final int bgW = background.getWidth();
		final int bgH = background.getHeight();
		final int leftOffSet = (w - bgW) / 2;
		final int topOffSet = (h - bgH) /2;
		final int menuW = Math.min(w, bgW);
		final int menuH = Math.min(h, bgH);
		int x = leftOffSet + menuW / 2;
		int y = topOffSet + menuH / 9;
		for (int i = 0; i < getSize(); i++) {
            MenuItem item = getItem(i);
            item.setCenter(x, y);
            y += item.getHeight();
		}
	}
		
	protected void paint(Graphics g) {
	    g.setColor(0x00000000);
	    g.fillRect(0, 0, width, height);
	    g.drawImage(background, width / 2, height / 2, Graphics.HCENTER | Graphics.VCENTER);
	    super.paint(g);
	}
}
