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

    private FragmentManager gameFragmentManager = getFragmentManager();

    private Game game;
    private SharedPreferences sharedPrefs;

    /**
     * Created by Jan Geestman 10375406.
     * Main Game activity for the YMBAH app.
     * This screen is the main screen for the game. It holds the navigation to all activities
     * within the game as well as the button for completing the game and an options menu button.
     * The backgrounds shows an image of an unfinished house. When enough resources are collected
     * the user can press the 'build house' button which changes the image to that of a completed
     * house.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proto);

        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Intent intent = getIntent();

        // Handles the creation of the Game class for the activity. Uses a string saved in the
        // Intent to determine the method.
        String callMethod = intent.getStringExtra("CallMethod");
        switch (callMethod) {
            case "NewGame":  // When the player pressed 'New Game' on the main menu
                game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this, "NewGame", intent.getIntExtra("Difficulty", 1));
                System.out.println("Creating new game");
                break;
            case "LoadGame":  // When the player pressed 'Load Game' on the main menu
                game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this, "LoadGame", 0);
                System.out.println("Loading Existing Game");
                break;
            default:  // All other times
                game = new Game(intent.getStringExtra("PlayerName"), GameActivity.this, "LoadGame", 0);
                System.out.println("Loading Existing Game (Back Button?)");
                break;
        }

        editor.putBoolean("GameInProgress", true); // Game is created and in progress
        editor.commit();
        if (getIntent().hasExtra("SandCD")) { // If the user returns from the DigSandActivity
            System.out.println("IK BEN OP COOLDOWN");
            Bundle extras = getIntent().getExtras();
            boolean sandCD = extras.getBoolean("SandCD", false);
            final TextView sandTimer = (TextView) findViewById(R.id.DigSandTimer);
            final Button digSandButton = (Button) findViewById(R.id.DigSandButton);

            // This disables the 'Dig Sand' button until the countdown has reached zero
            if (sandCD) {
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

    // This starts the DigSandActivity
    public void StartDigSand(View view) {
        Intent DigSandIntent = new Intent(this, DigSandActivity.class);
        game.saveGame();
        startActivity(DigSandIntent);
    }

    // This button signals the completion of the game. If the user has collected enough resources
    // the button can be pressed and the game is finished.
    public void BuildHouse(View view) {
        if (game.checkFinished()){
            System.out.println("Sand collected: " + game.getResource("Sand"));
            findViewById(R.id.GameBackground).setBackgroundResource(R.drawable.finished);
        } else {
            System.out.println("Sand collected: " + game.getResource("Sand"));
            Toast.makeText(this, "You have collected: " + game.getResource("Sand") +
                    " out of " + game.getLimit("Sand") + "Sand", Toast.LENGTH_SHORT).show();
        }
    }

    // Displays the options menu fragment
    public void gOpenOptions(View view) {
        FragmentTransaction gameFragmentTransaction = gameFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(gameFragmentTransaction, "Options");
    }

    // Opens the main menu
    public void OpenMainMenu(View view) {
        Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
        startActivity(mainMenuIntent);
    }

    // Saves the game to the shared preferences when GameActivity stops
    @Override
    public void onStop() {
        System.out.println("Stopping GameActivity");
        game.saveGame();
        System.out.println(sharedPrefs.getString("Username", "nothing"));
        super.onStop();
    }
}
