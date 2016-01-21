package nl.mprog.ymbah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Jan Geestman 10375406.
 */
public class Game implements Serializable{

    private SharedPreferences sharedPrefs;
    GameRules mRules;
    private Context gContext;

    public static HashMap<String,Integer> collectedResources = new HashMap<>();

    Game(String username, Context context){
        gContext = context;
        System.out.println("current usename: " + username);
        mRules = new GameRules(1);
        sharedPrefs = context.getSharedPreferences("userData", gContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        fillMap();
        for (String key:collectedResources.keySet()) {
            editor.putInt(key,collectedResources.get(key));
        }
        editor.putBoolean("GameInProgress", true);
        editor.commit();
    }

    private void fillMap() {
        if (sharedPrefs.getBoolean("GameInProgress", false)) {
            collectedResources.put("Sand", sharedPrefs.getInt("Sand", 0));
            collectedResources.put("Glass", sharedPrefs.getInt("Sand", 0));
            collectedResources.put("Wood", sharedPrefs.getInt("Sand", 0));
            collectedResources.put("Planks", sharedPrefs.getInt("Sand", 0));
            collectedResources.put("Clay", sharedPrefs.getInt("Sand", 0));
            collectedResources.put("Bricks", sharedPrefs.getInt("Sand", 0));
        } else {
            collectedResources.put("Sand", 0);
            collectedResources.put("Glass", 0);
            collectedResources.put("Wood", 0);
            collectedResources.put("Planks", 0);
            collectedResources.put("Clay", 0);
            collectedResources.put("Bricks", 0);
        }
    }

    public int getResource(String rName) {
        return collectedResources.get(rName);
    }

    public void setResource(String rName, int num) {
        collectedResources.put(rName, num);
    }

    public boolean checkFinished() {
        if (collectedResources.get("Sand") >= GameRules.getLimit("Sand")){

            sharedPrefs = gContext.getSharedPreferences("userData", gContext.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean("GameInProgress", false);
            return true;
        }
        return false;
    }

    public void saveGame() {
        System.out.println("SAVING GAME");
    }
}
