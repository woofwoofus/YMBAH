package nl.mprog.ymbah;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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

import java.util.Random;

public class DigSandActivity extends Activity {

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
        final ImageView Sand = (ImageView) findViewById(R.id.Sand_Object);
        Sand.setClickable(false);

        float X = randInt(-500, 500);
        float Y = randInt(300, 800);
        System.out.println("X = " + X + "    Y = " + Y);

        Sand.animate().rotationBy(1800).alpha(0).translationX(X).translationY(-Y).scaleX(0)
                .scaleY(0).setDuration(2000).withEndAction(new Runnable() {
            public void run() {
                Sand.animate().x(0).y(0).alpha(1).setDuration(0);
                Sand.setClickable(true);
            }
        });

        ImageView Sand_Plus = (ImageView) findViewById(R.id.Sand_Plus);

        Sand_Plus.setImageAlpha(1);
        Sand_Plus.animate().translationY(-10).alpha(0).setDuration(2000);

    }

}
