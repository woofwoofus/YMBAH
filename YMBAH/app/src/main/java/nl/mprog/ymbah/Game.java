package nl.mprog.ymbah;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/**
 * Created by Jan Geestman 10375406.
 * Game class for the YMBAH app.
 * This class holds all of the information about the user's game.
 * This is mainly the collected resources until now and an instance of the GameRules class
 */
public class Game{
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    private String gUsername;
    private GameRules mRules;
    private int gDifficulty;
    private Context gContext;

    private static HashMap<String,Integer> collectedResources = new HashMap<>();

    Game(String username, Context context, String gameMethod, int gameDifficulty){
        gContext = context;
        gUsername = username;
        gDifficulty = gameDifficulty;
        sharedPrefs = gContext.getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        if (gDifficulty == 0) {
            gDifficulty = sharedPrefs.getInt("Difficulty", 1);
        }
        mRules = new GameRules(gDifficulty);


        fillMap(gameMethod);

        for (String key:collectedResources.keySet()) {
            editor.putInt(key,collectedResources.get(key));
        }
        editor.putBoolean("GameInProgress", true);
        editor.commit();
        saveGame();
    }

    private void fillMap(String gameMethod) {
        if (gameMethod.equals("LoadGame")){
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

    public int getResource(@SuppressWarnings("SameParameterValue") String rName) {
        return collectedResources.get(rName);
    }

    public void setResource(String rName, int num) {
        collectedResources.put(rName, num);
    }

    public boolean checkFinished() {
        if (collectedResources.get("Sand") >= mRules.getLimit("Sand")) {
            editor.putBoolean("GameInProgress", false);
            editor.commit();
            return true;
        }
        return false;
    }

    public void saveGame() {
        System.out.println("SAVING GAME");
        for (String key:collectedResources.keySet()) {
            editor.putInt(key, collectedResources.get(key));
        }
        editor.putString("UserName", gUsername);
        editor.putInt("Difficulty", gDifficulty);
        editor.putBoolean("GameInProgress", true);
        editor.commit();
    }
}
