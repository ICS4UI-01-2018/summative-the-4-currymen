package com.summative4currymen.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//import com.summative4currymen.game.ZombieGame;
import com.summative4currymen.game.ZombieGame;
import com.summative4currymen.game.Temp;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Temp(), config);
    }
}

//“Never underestimate the power of stupid people in large groups.” 
//“Talk sense to a fool and he calls you foolish.” 
//“In politics, stupidity is not a handicap.” 
