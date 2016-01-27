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
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jan Geestman 10375406.
 * Main menu activity for the YMBAH app.
 * From here the user can either start a new game or load an existing one.
 * The difficulty for the new game can be chosen using the number picker and the username is chosen
 * with the spinner below the 'load game' button. The button on the bottom right of the screen opens
 * the options menu.
 * The playerlist is not persistent through game resets because the code that saved them to memory
 * generated too many errors
 *
 */
public class MainMenuActivity extends Activity {
    private SharedPreferences sharedPrefs;
    private FragmentManager mainMenuFragmentManager = getFragmentManager();

    private static ArrayList<String> playerList = new ArrayList<>();
    private ArrayAdapter<String> playerSpinnerAdapter;
    private Spinner playerSpinner;
    private NumberPicker difficultyNumberPicker;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        editor = sharedPrefs.edit();

        playerSpinner = (Spinner) findViewById(R.id.PlayerSpinner);

        createDifficultyNumberPicker();
        createPlayerList();
        createPlayerSpinners();
        createSpinnerListener();
    }

    // Configures the difficulty number picker
    private void createDifficultyNumberPicker() {
        difficultyNumberPicker = (NumberPicker) findViewById(R.id.DifficultyNumberPicker);
        difficultyNumberPicker.setMaxValue(3);
        difficultyNumberPicker.setMinValue(1);

    }

    // Creates the list of known players from a file in memory. Makes the file if none exists yet.
    private void createPlayerList() {
        playerList.add("Player"); // Placeholder name for when no name is selected yet.

        try {
            Scanner inputFile = new Scanner(this.openFileInput("players.txt"));
            while (inputFile.hasNext()) {
                String line = inputFile.next();
                String[] content = line.split(",");
                playerList.add(content[0]);
            }
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            File f = new File(getFilesDir(), "players.txt");
            try {
                f.createNewFile();
                System.out.println("Created players.txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        playerList.add("Create New Player"); // Final entry allows for the creation of a new player
    }

    // Configures the player spinner listener
    private void createSpinnerListener() {
        playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                if (position == playerList.size() - 1) { // If 'Create New Player' is selected
                    Intent getNewPlayer = new Intent(MainMenuActivity.this, NewPlayerPopup.class);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == 1) {
                final String newPlayerName = data.getExtras().getString("newPlayerName");
                playerList.remove(playerList.size() - 1);
                playerList.add(newPlayerName);
                playerList.add("Create New Player");
                playerSpinnerAdapter.notifyDataSetChanged();
                playerSpinner.setSelection(playerList.size() - 2);
                updatePlayersFile(newPlayerName);
            } else {
                playerSpinner.setSelection(0);
            }
        }
    }

    // Updates the known players file with the newly created username
    private void updatePlayersFile(String newPlayer) {
        try {
            FileOutputStream outputStream = openFileOutput("players.txt", Context.MODE_PRIVATE);
            outputStream.write(newPlayer.getBytes());
            outputStream.close();
        } catch (Exception e) {
            System.out.println("No players.txt found");
            e.printStackTrace();
        }
    }

    // Configures the player spinners
    private void createPlayerSpinners() {
        playerSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, playerList);
        playerSpinner.setAdapter(playerSpinnerAdapter);
    }

    // Starts a new game with selected username and dificulty. If no player is selected, a Toast is
    // displayed prompting the user to pick a username
    public void StartGame(View view) {
        editor.clear();
        editor.commit();
        Intent StartGameIntent = new Intent(this, GameActivity.class);
        final String playerName = ((Spinner) findViewById(R.id.PlayerSpinner)).getSelectedItem().toString();
        if (!playerName.equals("Player") || !playerName.equals("Create New Player")) {
            StartGameIntent.putExtra("PlayerName", playerName);
            StartGameIntent.putExtra("CallMethod", "NewGame");
            StartGameIntent.putExtra("Difficulty", difficultyNumberPicker.getValue());
            startActivity(StartGameIntent);
        } else {
            Toast.makeText(this, "Please enter a player name", Toast.LENGTH_SHORT).show();
        }
    }

    // Allows a game to be restored from memory. If none is found, a Toast is displayed.
    public void LoadGame(View view) {
        Intent LoadGameIntent = new Intent(this, GameActivity.class);
        if (!(sharedPrefs.getBoolean("gameInProgress", false))) {
            Toast.makeText(this, "No saved game found", Toast.LENGTH_SHORT).show();
        } else {
            LoadGameIntent.putExtra("gameInProgress", true);
            LoadGameIntent.putExtra("CallMethod", "LoadGame");
            startActivity(LoadGameIntent);
        }
    }

    // Opens the options menu fragment.
    public void OpenOptions(View view) {
        FragmentTransaction mainMenuFragmentTransaction = mainMenuFragmentManager.beginTransaction();
        DialogFragment optionsFragment = OptionsMenuFragment.newInstance();
        optionsFragment.show(mainMenuFragmentTransaction, "Options");
    }
}
