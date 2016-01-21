package nl.mprog.ymbah;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class DigSandActivity extends Activity {
    private SharedPreferences sharedPrefs;
    private boolean SandCD = false;
    private int digCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_sand);
    }

    public static float randInt(int min, int max){
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        if(max<0){
            float range = Math.abs(min)+max;
        }
        float range = Math.abs(min)+Math.abs(max);
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

        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("Sand", sharedPrefs.getInt("Sand", 0) + 1);
        editor.commit();
        System.out.println("Sand collected: " + sharedPrefs.getInt("Sand", -1));
        digCount++;
        TextView v = (TextView)findViewById(R.id.SandCollectedTextView);
        v.setText(digCount);
        if (digCount > 3) {
            SandCD = true;
        }
    }

    public void BackToHouse(View view) {
        Intent GameScreenIntent = new Intent(this, GameActivity.class);
        GameScreenIntent.putExtra("SandCD", true);
        GameScreenIntent.putExtra("GameInProgress", true);
        startActivity(GameScreenIntent);
    }
}
