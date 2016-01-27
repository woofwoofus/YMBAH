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
     * This is a mini-game activity that allows the user to collect sand for building their house.
     * The sand is collected by clicking the image of the pile of sand in the acitvity. It then moves
     * to a new location and the user can press it again. This can be done a maximum of three times,
     * after which the sand disappears. By pressing the "back" button the user is brought back to
     * the main game screen and will have to wait for a countdown to expire to collect more sand.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_sand);
        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        editor = sharedPrefs.edit();
    }

    // Provides a random int between 'min' and 'max'
    private static float randInt(int min, int max){
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

    // Animates the sand image to spin around, disappear and then reappear at a different location.
    // When the user has done this 3 times the sand disappears for good.
    public void playAnim(View view) {

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

    // Opens the Game activity and notifies it that the Dig Sand activity must go on a cooldown.
    public void BackToHouse(View view) {
        Intent digSandIntent = new Intent(this, GameActivity.class);
        digSandIntent.putExtra("SandCD", true);
        digSandIntent.putExtra("CallMethod", "InGame");
        editor.putInt("Sand", sharedPrefs.getInt("Sand", 0) + digCount);
        startActivity(digSandIntent);
    }
}
