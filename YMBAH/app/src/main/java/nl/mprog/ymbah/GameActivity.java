package nl.mprog.ymbah;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ListView mDrawerList;
    private VelocityTracker mVelocityTracker = null;

    private boolean SandCD = false;

//    FragmentManager gameFragmentManager = getFragmentManager();
//    FragmentTransaction gameFragmentTransaction;
    FragmentManager gameFragmentManager = getFragmentManager();
    FragmentTransaction gameFragmentTransaction;

    Game game;
    private SharedPreferences sharedPrefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proto);

        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        game = new Game(sharedPrefs.getString("username", "GenericUser"), GameActivity.this);

        if (getIntent().hasExtra("SandCD")) {
            System.out.println("IK BEN OP COOLDOWN");
            Bundle extras = getIntent().getExtras();
            SandCD = extras.getBoolean("SandCD", false);
            final TextView sandTimer = (TextView) findViewById(R.id.DigSandTimer);
            final Button digSandButton = (Button) findViewById(R.id.DigSandButton);

            if (SandCD) {
                new CountDownTimer(30000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        digSandButton.setClickable(false);
                        sandTimer.setText("" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        digSandButton.setClickable(true);
                        sandTimer.setText("");
                    }
                };
            }
        }
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
        if (game.checkFinished()){
            System.out.println("Sand collected: " + sharedPrefs.getInt("Sand", -1));
            findViewById(R.id.GameBackground).setBackgroundResource(R.drawable.finished);
        } else {
            System.out.println("Sand collected: " + sharedPrefs.getInt("Sand", -1));
            Toast.makeText(this, "Not enough resources collected", Toast.LENGTH_SHORT).show();
        }
    }

    public void gOpenOptions(View view) {
        gameFragmentTransaction = gameFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(gameFragmentTransaction, "Options");
    }
}
