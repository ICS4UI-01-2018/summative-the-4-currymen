package com.summative4currymen.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//import com.summative4currymen.game.ZombieGame;
import com.summative4currymen.game.ZombieGame;
import com.summative4currymen.game.Temp3;
import com.summative4currymen.game.Temp2;


public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Temp3(), config);
    }
}
