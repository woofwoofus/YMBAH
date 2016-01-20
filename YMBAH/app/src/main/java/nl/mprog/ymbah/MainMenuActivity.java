package nl.mprog.ymbah;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
    private SharedPreferences sharedPrefs;
    FragmentManager mainMenuFragmentManager = getFragmentManager();
    FragmentTransaction mainMenuFragmentTransaction;

    private boolean LoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
    }

    public void StartGame(View view) {
        Intent StartGameIntent = new Intent(this, GameActivity.class);

        startActivity(StartGameIntent);
    }

    public void LoadGame(View view) {
        Intent GameScreenIntent = new Intent(this, GameActivity.class);
        if (!(sharedPrefs.getBoolean("gameInProgress",false))){
            Toast.makeText(this, "No saved game found", Toast.LENGTH_SHORT).show();
        } else {
            GameScreenIntent.putExtra("gameInProgress", true);
            startActivity(GameScreenIntent);
        }
    }

    public void OpenOptions(View view) {
        mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(mainMenuFragmentTransaction, "Options");
    }

    public void LogIn(View view) {
        mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();
        DialogFragment logInFragment = LogInFragment.newInstance();
        logInFragment.show(mainMenuFragmentTransaction, "LogIn");
    }
}
