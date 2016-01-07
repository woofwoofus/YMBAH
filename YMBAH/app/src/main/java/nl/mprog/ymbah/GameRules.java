package nl.mprog.ymbah;

import java.util.HashMap;

/**
 * Created by Jan Geestman 10375406.
 */
public class GameRules {

    public static HashMap<String,Integer> requiredResources = new HashMap<>();

    GameRules(int difficulty) {
        fillMap();
        for (String key : requiredResources.keySet()){
            int value = requiredResources.get(key);
            value *= difficulty;
            requiredResources.put(key, value);
        }
    }

    private void fillMap(){
        requiredResources.put("Sand", 2);
        requiredResources.put("Wood", 5);
        requiredResources.put("Planks", 10);
    }

}
