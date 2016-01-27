package nl.mprog.ymbah;

import java.util.HashMap;

/**
 * Created by Jan Geestman 10375406.
 * GameRules class for the YMBAH app.
 * This class contains the rules for a game of YMBAH and is included in all instances of the
 * Game class.
 */
public class GameRules{
    private static HashMap<String,Integer> requiredResources = new HashMap<>();

    // Creates an instance of the GameRules class. The required amount of resources needed to
    // complete a game is multiplied by the difficulty.
    GameRules(int difficulty) {
        fillMap();
        for (String key : requiredResources.keySet()){
            int value = requiredResources.get(key);
            value *= difficulty;
            requiredResources.put(key, value);
        }
    }

    // Fills the Hashmap with the amount of resources the user has to collect
    private void fillMap(){
        requiredResources.put("Sand", 3);
        requiredResources.put("Glass", 0);
        requiredResources.put("Wood", 0);
        requiredResources.put("Planks", 0);
        requiredResources.put("Clay", 0);
        requiredResources.put("Bricks", 0);
    }

    // Returns the required amount of 'rName' resource
    public int getLimit(String rName) {
        return requiredResources.get(rName);
    }

}
