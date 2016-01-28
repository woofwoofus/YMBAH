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

    private HashMap<String,Integer> collectedResources = new HashMap<>();

    Game(String username, Context context, String gameMethod, int gameDifficulty){
        gContext = context;
        gUsername = username;
        gDifficulty = gameDifficulty;
        sharedPrefs = gContext.getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        if (gDifficulty == 0) {
            gDifficulty = sharedPrefs.getInt("Difficulty", 1);
        }
        mRules = new GameRules(gDifficulty); // Creates an instance of GameRules with difficulty gDifficulty

        fillMap(gameMethod);

        saveGame();
    }

    // Fills the hashmap of all resources collected from memory or with 0 values
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

    // Returns the amount of resource rName the user has collected
    public int getResource(String rName) {
        return collectedResources.get(rName);
    }

//    public void setResource(String rName, int num) {
//        collectedResources.put(rName, num);
//    }

    // Checks if the user has collected enough resources
    public boolean checkFinished() {
        if (collectedResources.get("Sand") >= mRules.getLimit("Sand")) {
            editor.putBoolean("GameInProgress", false);
            editor.commit();
            return true;
        }
        return false;
    }

    // Saves the game in progress to the shared preferences
    public void saveGame() {
        for (String key:collectedResources.keySet()) {
            editor.putInt(key, collectedResources.get(key));
        }
        editor.putString("UserName", gUsername);
        editor.putInt("Difficulty", gDifficulty);
        editor.putBoolean("GameInProgress", true);
        editor.commit();
    }

    // Returns the limit of the resource rName needed to complete the game
    public int getLimit(String rName) {
        return mRules.getLimit(rName);
    }
}
