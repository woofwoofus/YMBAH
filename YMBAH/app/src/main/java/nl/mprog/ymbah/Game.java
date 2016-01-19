package nl.mprog.ymbah;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by woofw_000 on 07/01/2016.
 */
public class Game {

    private SharedPreferences sharedPrefs;
    GameRules mRules;

    public static HashMap<String,Integer> collectedResources = new HashMap<>();

    Game(String username, Context context){
        System.out.println("current usename: " + username);
        mRules = new GameRules(1);
        sharedPrefs = context.getSharedPreferences("userData", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        fillMap();
        for (String key:collectedResources.keySet()) {
            editor.putInt(key,collectedResources.get(key));
        }
        editor.commit();
    }

    // ALTIJD TRUE OP HET MOMENT TOT IK EEN ELEGANTERE OPLOSSING KAN BEDENKEN
    private void fillMap() {
        if (sharedPrefs.getBoolean("gameInProgress", true)) {
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
        return collectedResources.get("Sand") == GameRules.getLimit("Sand");
    }
}
