package nl.mprog.ymbah;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends Activity {
    FragmentManager mainMenuFragmentManager = getFragmentManager();
    FragmentTransaction mainMenuFragmentTransaction;
    OptionsMenuFragment optionsMenuFragment = new OptionsMenuFragment();
    MainMenuFragment mainMenuFragment = new MainMenuFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_menu);

        mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();
        mainMenuFragmentTransaction.replace(android.R.id.content, mainMenuFragment);
        mainMenuFragmentTransaction.commit();

    }

    public void StartGame(View view) {
        Intent StartGameIntent = new Intent(this, GameActivity.class);
        startActivity(StartGameIntent);
    }

    public void OpenOptions(View view) {
        mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();

        mainMenuFragmentTransaction.replace(android.R.id.content, optionsMenuFragment);

        mainMenuFragmentTransaction.commit();
    }

    public void CloseOptions(View view) {
        mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();

        mainMenuFragmentTransaction.replace(android.R.id.content, mainMenuFragment);

        mainMenuFragmentTransaction.commit();
    }
}
