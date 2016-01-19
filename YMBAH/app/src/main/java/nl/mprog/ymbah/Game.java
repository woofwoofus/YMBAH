package nl.mprog.ymbah;

/**
 * Created by woofw_000 on 07/01/2016.
 */
public class Game {

    int Sand, Glass, Wood, Planks, Clay, Bricks;

    Game(String username){
        System.out.println("current usename: " + username);
        GameRules mRules = new GameRules(1);
    }

    public int getResource(String rName) {
        switch (rName) {
            case "Sand":
                return Sand;
            case "Glass":
                return Glass;
            case "Wood":
                return Wood;
            case "Planks":
                return Planks;
            case "Clay":
                return Clay;
            case "Bricks":
                return Bricks;
            default:
                return 0;
        }
    }

    public void setResource(String rName, int num) {
        switch (rName) {
            case "Sand":
                Sand = num;
                break;
            case "Glass":
                Glass = num;
                break;
            case "Wood":
                Wood = num;
                break;
            case "Planks":
                Planks = num;
                break;
            case "Clay":
                Clay = num;
                break;
            case "Bricks":
                Bricks = num;
                break;
            default:
                break;
        }
    }

}
