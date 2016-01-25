package nl.mprog.ymbah;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/**
 * Created by Jan Geestman 10375406.
 */
public class Game{
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    public GameRules mRules;
    private Context gContext;
    public String gUsername;
    private int gDifficulty;

    public static HashMap<String,Integer> collectedResources = new HashMap<>();

    Game(String username, Context context, String gameMethod, int gameDifficulty){
        gContext = context;
        gUsername = username;
        gDifficulty = gameDifficulty;
        sharedPrefs = context.getSharedPreferences("userData", gContext.MODE_PRIVATE);
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

    public int getResource(String rName) {
        return collectedResources.get(rName);
    }

    public void setResource(String rName, int num) {
        collectedResources.put(rName, num);
    }

    public boolean checkFinished() {
        if (collectedResources.get("Sand") >= GameRules.getLimit("Sand")) {
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
