package com.nokia.app.soletrando;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import com.nokia.app.soletrando.menu.Menu;
import com.nokia.app.soletrando.menu.MenuSoletrando;

public class SoletrandoCanvas extends GameCanvas {

	private static final int MAX_RENDERING_FPS = 12;
    private static final int LEFT_SOFTKEY = -6;
    private static final int RIGHT_SOFTKEY = -7;
    private volatile int pointerKeyState = 0;
    private Main main;
    //Menu
    private MenuSoletrando menu = null;
    //current visible menu
    private Menu visibleMenu;
    //touch handler
    
    // the game loop that is run MAX_RENDERING_FPS timer per second
    private GameThread gameLoop;
    
	
	protected SoletrandoCanvas(Main main) {
		super(false);
		setFullScreenMode(true);
		this.main = main;
		createMenu();
	}
	
	public void showMenu(){
		if(visibleMenu == menu){
			return;
		}
		visibleMenu = menu;
		menu.selectItem(hasPointerEvents()? -1 : 0);
	}
	
	protected void showNotify() {
        startGameLoop();
        // show menu view first
        showMenu();
    }
	
	private void startGameLoop() {
        stopGameLoop();
        final Graphics g = getGraphics();
        gameLoop = new GameThread(g);
        gameLoop.start();
    }
	
	private void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.cancel();
        }
    }
	
	public void hideCurrentMenu(){
		visibleMenu = null;
	}
	
	private void createMenu() {
		menu = new MenuSoletrando(getWidth(), getHeight(), new Menu.Listiner() {
			public void itemClicked(int item) {
				switch(item){
					case MenuSoletrando.COMEÇAR:
						hideCurrentMenu();
						break;
					case MenuSoletrando.CONTINUAR:
						hideCurrentMenu();
						break;
				}
				
			}
		});
	}
	
	private void runGameLoop(Graphics g) {
        if (visibleMenu != null) {
            visibleMenu.render(g);
        }
        else {
            //game.update(getKeyStates());
            //game.render(g);
        }

        flushGraphics();
    }
	
	private class GameThread
    extends Thread {

    private boolean run = true;
    private final Graphics g;

    public GameThread(Graphics g) {
        this.g = g;
    }

    public void run() {
        long lastTime = 0;
        while (run) {
            long now = System.currentTimeMillis();
            long delay = (1000 / MAX_RENDERING_FPS) + lastTime - now;
            if (delay < 1) {
                delay = 1;
            }
            // force-sleep at least 1 ms to ensure time for the UI thread
            try {
                sleep(delay);
            }
            catch (InterruptedException e) {
            }
            runGameLoop(g);
            lastTime = now;
        }
    }

    public void cancel() {
        run = false;
    }
}

}
