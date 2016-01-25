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
//        editor.clear();
//        editor.commit();

        Intent intent = getIntent();
        String callMethod = intent.getStringExtra("CallMethod");
//        if (intent.getSerializableExtra("Game") == null){
////        if (intent.getBooleanExtra("GameInProgress", false)){
        if (callMethod.equals("NewGame")){
            game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this, "NewGame", intent.getIntExtra("Difficulty", 1));
            System.out.println("Creating new game");
        } else if (callMethod.equals("LoadGame")) {
            game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this, "LoadGame", 0);
            System.out.println("Loading Existing Game");
//        } else if (callMethod.equals("InGame")) {
//            game = (Game) intent.getSerializableExtra("Game");
//            System.out.println("Game passed from activity");
        } else {
            game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this, "LoadGame", 0);
            System.out.println("Loading Existing Game (Back Button?)");
        }

        editor.putBoolean("GameInProgress", true);
        editor.commit();
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


    public void StartDigSand(View view) {
        Intent DigSandIntent = new Intent(this, DigSandActivity.class);
        game.saveGame();
        startActivity(DigSandIntent);
    }
    public void BuildHouse(View view) {
        if (game.checkFinished()){
            System.out.println("Sand collected: " + game.getResource("Sand"));
            findViewById(R.id.GameBackground).setBackgroundResource(R.drawable.finished);
        } else {
            System.out.println("Sand collected: " + game.getResource("Sand"));
            Toast.makeText(this, "You have collected: " + game.getResource("Sand") +
                    " out of " + game.mRules.getLimit("Sand") + "Sand", Toast.LENGTH_SHORT).show();
        }
    }

    public void gOpenOptions(View view) {
        gameFragmentTransaction = gameFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(gameFragmentTransaction, "Options");
    }

    @Override
    public void onStop() {
        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        System.out.println("Stopping GameActivity");
        game.saveGame();
        System.out.println(sharedPrefs.getString("Username", "nothing"));
        super.onStop();
    }
}
