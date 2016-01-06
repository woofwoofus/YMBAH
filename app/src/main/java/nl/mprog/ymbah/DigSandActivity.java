package nl.mprog.ymbah;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Random;

public class DigSandActivity extends AppCompatActivity {
    private String[] mActivityList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private VelocityTracker mVelocityTracker = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_sand);

        mActivityList = getResources().getStringArray(R.array.activity_array_slide_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mActivityList));
        mDrawerList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        );


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        System.out.println("Action Index: " + event.getActionIndex());
        System.out.println("Action Masked: " + event.getActionMasked());
        System.out.println("Action Index PointerID: " + event.getPointerId(event.getActionIndex()));

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        float Y = randInt(-800, -300);
        System.out.println("X = " + X + "    Y = " + Y);

        Sand.animate().rotationBy(1800).alpha(0).translationX(X).translationY(Y).scaleX(0)
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
