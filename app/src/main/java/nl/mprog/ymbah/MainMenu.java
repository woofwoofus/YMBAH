package nl.mprog.ymbah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void StartGame(View view) {
        Intent StartGameIntent = new Intent(this, DigSandActivity.class);
        startActivity(StartGameIntent);
    }

    public void OpenOptions(View view) {
    }
}
