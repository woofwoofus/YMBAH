package nl.mprog.ymbah;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainMenuActivity extends Activity {
    private SharedPreferences sharedPrefs;
    FragmentManager mainMenuFragmentManager = getFragmentManager();
    FragmentTransaction mainMenuFragmentTransaction;

    private static ArrayList<String> playerList = new ArrayList<>();
    ArrayAdapter<String> playerSpinnerAdapter;
    private Spinner playerSpinner;

    private boolean LoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        playerSpinner = (Spinner) findViewById(R.id.PlayerSpinner);
        createPlayerList();
        createPlayerSpinners();
        createSpinnerListeners();
    }

    private void createPlayerList() {
        playerList.add("Player"); // Placeholder name for when no name is selected yet.

        try {
            Scanner inputFile = new Scanner(this.openFileInput("players.txt"));
            while (inputFile.hasNext()){
                String line = inputFile.next();
                String[] content = line.split(",");
                playerList.add(content[0]);
            }
            inputFile.close();
        } catch (FileNotFoundException e) {
            File f = new File(getFilesDir(), "players.txt");
            try {
                f.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        playerList.add("Create New Player"); // Final entry allows for the creation of a new player
    }
    private void createSpinnerListeners(){
        playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                if (position == playerList.size() - 1) { // If 'Create New Player' is selected
                    Intent getNewPlayer = new Intent(MainMenuActivity.this, NewPlayerPop.class);
                    getNewPlayer.putExtra("playerList", playerList);
                    getNewPlayer.putExtra("spinnerNum", 0);
                    startActivityForResult(getNewPlayer, 1);
                }

            }

            // Required Override
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * This bit of wizardry reshuffles the entries int the player database so the spinners
     * still maintain their original functionality. (Create New Player stays at the bottom)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if (resultCode == 1){
                final String newPlayerName = data.getExtras().getString("newPlayerName") ;
                playerList.remove(playerList.size() - 1);
                playerList.add(newPlayerName);
                playerList.add("Create New Player");
                playerSpinnerAdapter.notifyDataSetChanged();
                playerSpinner.setSelection(playerList.size()-2);
                updatePlayersFile(newPlayerName);
            } else {
                playerSpinner.setSelection(0);
            }
        }
    }

    private void updatePlayersFile(String newPlayer){
        try {
            FileOutputStream outputStream = openFileOutput("players.txt", Context.MODE_PRIVATE);
            outputStream.write(newPlayer.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPlayerSpinners() {
        playerSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, playerList);
        playerSpinner.setAdapter(playerSpinnerAdapter);}

    public void StartGame(View view) {
        Intent StartGameIntent = new Intent(this, GameActivity.class);
        final String playerName = ((Spinner)findViewById(R.id.PlayerSpinner)).getSelectedItem().toString();
        if (!playerName.equals("Player") || !playerName.equals("Create New Player")){
            StartGameIntent.putExtra("PlayerName", playerName);
            StartGameIntent.putExtra("GameMethod", "NewGame");
            startActivity(StartGameIntent);
        } else {
            Toast.makeText(this, "Please enter a player name", Toast.LENGTH_SHORT).show();
        }
    }

    public void LoadGame(View view) {
        Intent LoadGameIntent = new Intent(this, GameActivity.class);
        if (!(sharedPrefs.getBoolean("gameInProgress",false))){
            Toast.makeText(this, "No saved game found", Toast.LENGTH_SHORT).show();
        } else {
            LoadGameIntent.putExtra("gameInProgress", true);
            LoadGameIntent.putExtra("GameMethod", "LoadGame");
            startActivity(LoadGameIntent);
        }
    }

    public void OpenOptions(View view) {
        mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(mainMenuFragmentTransaction, "Options");
    }
}
