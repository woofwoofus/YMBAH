package nl.mprog.ymbah;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.MotionEventCompat;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    private boolean SandCD = false;

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

        Intent intent = getIntent();
        if (intent.getBooleanExtra("GameInProgress", false)){
            game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this);
        } else {
            game = (Game) intent.getSerializableExtra("Game");
        }
        System.out.println("Creating new game");

        if (getIntent().hasExtra("SandCD")) {
            System.out.println("IK BEN OP COOLDOWN");
            Bundle extras = getIntent().getExtras();
            SandCD = extras.getBoolean("SandCD", false);
            final TextView sandTimer = (TextView) findViewById(R.id.DigSandTimer);
            final Button digSandButton = (Button) findViewById(R.id.DigSandButton);

            if (SandCD) {
                digSandButton.setClickable(false);
                sandTimer.setBackgroundColor(Color.RED);
                System.out.println("SANDCD");
                new CountDownTimer(10000, 1000) {
                    public void onTick(long m) {
                        sandTimer.setText("" + m/1000);
                    }

                    public void onFinish() {
                        digSandButton.setClickable(true);
                        sandTimer.setBackgroundColor(Color.TRANSPARENT);
                        sandTimer.setText("");
                    }
                }.start();
            }
        }
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
        startActivityForResult(DigSandIntent, 1);
    }
    public void BuildHouse(View view) {
        if (game.checkFinished()){
            System.out.println("Sand collected: " + game.getResource("Sand"));
            findViewById(R.id.GameBackground).setBackgroundResource(R.drawable.finished);
        } else {
            System.out.println("Sand collected: " + game.getResource("Sand"));
            Toast.makeText(this, "Not enough resources collected", Toast.LENGTH_SHORT).show();
        }
    }

    public void gOpenOptions(View view) {
        gameFragmentTransaction = gameFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(gameFragmentTransaction, "Options");
    }

    @Override
    public void onDestroy() {
        game.saveGame();
        super.onDestroy();
    }
}
