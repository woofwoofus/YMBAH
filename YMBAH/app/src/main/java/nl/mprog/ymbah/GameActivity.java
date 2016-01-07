package nl.mprog.ymbah;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GameActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ListView mDrawerList;
    private VelocityTracker mVelocityTracker = null;

    FragmentManager gameFragmentManager = getFragmentManager();
    FragmentTransaction gameFragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proto);

        Game game = new Game();
//        addDrawerItems();
    }

    private void addDrawerItems() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navList);
        String[] activityArray = {"House", "Dig Sand", "Chop Wood", "Saw Planks"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityArray);
        mDrawerList.setAdapter(mAdapter);

    }

    public void selectItem(int position) {

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

    public void StartDigSand(View view) {
        Intent DigSandIntent = new Intent(this, DigSandActivity.class);
        startActivity(DigSandIntent);
    }
    public void BuildHouse(View view) {
        findViewById(R.id.GameBackground).setBackgroundResource(R.drawable.finished);
    }
}
