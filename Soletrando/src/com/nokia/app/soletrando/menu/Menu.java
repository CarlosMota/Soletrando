package com.nokia.app.soletrando.menu;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;



public abstract class Menu {
	
	public static final int POINTER_PRESSED = 0;
	public static final int POINTER_DRAGGED = 1;
	public static final int POINTER_RELEASED = 2;
	
	//private String resourcePath = null;
	private MenuItem[] items = null;
	private Listiner listiner = null;
	
	protected Menu(int capacity, int w, int h, Listiner listiner){
		items = new MenuItem[capacity];
		//resourcePath = "/menu/";
		this.listiner = listiner;
	}
	
	protected final int getSize(){
		return items.length;
	}
	
	protected final MenuItem getItem(int i) {
        return items[i];
    }
	
	protected final void setItem(int i, MenuItem item){
		items[i] = item;
	}
	
	public final void render(Graphics g){
		paint(g);
	}
	
	protected void paint(Graphics g){
		for(int i = 0; i < items.length; i++){
			items[i].paint(g);
		}
	}
	
	public void selectItem(int selected){
		for(int i = 0; i < items.length; i++){
			items[i].setSelected(i== selected);
		}
	}
	
	private int getSelected(){
		for(int i = 0; i < items.length; i++){
			if(items[i].isSelected()){
				return i;
			}
		}
		return -1;
	}
	
	public void selectNext(){
		selectItem((getSelected() + 1) % items.length);
	}
	
	public void selectPrev(){
		selectItem((Math.max(getSelected(), 0)-1 + items.length)% items.length);
	}
	
	public void clickSelected() {
        clickItem(getSelected());
    }
	
	private void clickItem(int index) {
        if (index > -1 && index < items.length) {
        	listiner.itemClicked(index);
            items[index].setSelected(false);
        }
    }
	
	 public void pointerEvent(int type, int x, int y) {
	        for (int i = 0; i < items.length; i++) {
	            if (items[i].hits(x, y)) {
	                if (type == POINTER_RELEASED) {
	                    clickItem(i);
	                }
	                else {
	                    selectItem(i);
	                }
	                return;
	            }
	        }
	        selectItem(-1);
	    }
	 
	 protected Sprite loadSprite(String fileName, int lines) {
		 	
		 	Image i = loadImage(fileName);
		 	
	        
	        return new Sprite(i, i.getWidth(), i.getHeight() / lines);
	    }

	 protected Image loadImage(String fileName) {
	        try {
	        	System.out.println(fileName);
			 	System.out.println("Carregando...");
	            return Image.createImage(fileName);
	            
	        }
	        catch (IOException e) {
	        	System.out.println("Não carregou a foto");
	        	//e.printStackTrace();
	            return null;
	            
	        }
	    }
	 
	 public interface Listiner{
		 void itemClicked(int item);
	 }
	

}
