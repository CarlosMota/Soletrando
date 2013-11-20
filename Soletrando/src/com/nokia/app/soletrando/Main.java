package com.nokia.app.soletrando;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Main extends MIDlet {
	
	private static Display display;
	private SoletrandoCanvas soletrandoCanvas = null;
	
	

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		if(soletrandoCanvas == null){
			soletrandoCanvas = new SoletrandoCanvas(this);
			display = Display.getDisplay(this);
			display.setCurrent(soletrandoCanvas);
		}

	}

}
