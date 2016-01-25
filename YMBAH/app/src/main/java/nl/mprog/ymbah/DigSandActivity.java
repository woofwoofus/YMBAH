package nl.mprog.ymbah;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DigSandActivity extends Activity {
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    private boolean SandCD = false;
    private int digCount = 0;

    /**
     * Created by Jan Geestman 10375406.
     * DigSandActivity for the YMBAH app.
     * 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_sand);
        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        editor = sharedPrefs.edit();
//        gGame = (Game)getIntent().getSerializableExtra("Game");
    }

    public static float randInt(int min, int max){
        float range;
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        if(max<0){
            range = Math.abs(min)+max;
        } else {
            range = Math.abs(min) + Math.abs(max);
        }
        return randomFloat * range - Math.abs(min);
    }

    public void playAnim(View view) throws InterruptedException {

        final ImageView sand = (ImageView) findViewById(R.id.Sand_Object);
        sand.setClickable(false);

        float X = randInt(-500, 500);
        float Y = randInt(300, 800);
        System.out.println("X = " + X + "    Y = " + Y);

        Runnable endAction = new Runnable() {
            public void run() {
                if (!SandCD) {
                    sand.animate().alpha(1).scaleX(1).scaleY(1).setDuration(0);
                    sand.setClickable(true);
                }
            }
        };


        sand.animate().rotationBy(1800).alpha(0).translationX(X).translationY(-Y).scaleX(0)
                .scaleY(0).setDuration(2000).withEndAction(endAction);


        editor.putInt("Sand", sharedPrefs.getInt("Sand", 0) + 1);
        editor.commit();
        digCount++;
        TextView v = (TextView)findViewById(R.id.SandCollectedTextView);
        v.setText(""+sharedPrefs.getInt("Sand", 0));
        if (digCount > 2) {
            SandCD = true;
        }
    }

    public void BackToHouse(View view) {
        Intent digSandIntent = new Intent(this, GameActivity.class);
        digSandIntent.putExtra("SandCD", true);
        digSandIntent.putExtra("CallMethod", "InGame");
        editor.putInt("Sand", sharedPrefs.getInt("Sand", 0) + digCount);
//        gGame.saveGame();
//        digSandIntent.putExtra("Game", gGame);
        startActivity(digSandIntent);
    }
}
