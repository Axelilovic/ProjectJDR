package jdr.projet.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import jdr.projet.myapplication.Classes.Enumerations;
import jdr.projet.myapplication.Fragments.CharacterSheetsFragment;
import jdr.projet.myapplication.Fragments.DicerollFragment;
import jdr.projet.myapplication.Fragments.ProcedurallyGeneratedDataFragment;
import jdr.projet.myapplication.Fragments.QuickRulesFragment;
import jdr.projet.myapplication.Fragments.SoundManagerFragment;

public class MainActivity extends AppCompatActivity implements DicerollFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;

    private DicerollFragment dicerollFragment;
    private CharacterSheetsFragment characterSheetsFragment;
    private ProcedurallyGeneratedDataFragment procedurallyGeneratedDataFragment;
    private QuickRulesFragment quickRulesFragment;
    private SoundManagerFragment soundManagerFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String backstackLabel;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction;
            switch (item.getItemId()) {
                case R.id.navigation_charactersheets:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    backstackLabel = "CharacterSheetsFragment";
                    fragmentTransaction.replace(R.id.currentFragmentLayout, characterSheetsFragment);
                    fragmentTransaction.addToBackStack(backstackLabel);
                    fragmentTransaction.commit();
                    return true;
//                case R.id.navigation_chat:
//                    mTextMessage.setText(R.string.title_chat);
//                    return true;
                case R.id.navigation_diceroll:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    backstackLabel = "DicerollFragment";
                    fragmentTransaction.replace(R.id.currentFragmentLayout, dicerollFragment);
                    fragmentTransaction.addToBackStack(backstackLabel);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_generators:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    backstackLabel = "GeneratorsFragment";
                    fragmentTransaction.replace(R.id.currentFragmentLayout, procedurallyGeneratedDataFragment);
                    fragmentTransaction.addToBackStack(backstackLabel);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_quickrules:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    backstackLabel = "QuickRulesFragment";
                    fragmentTransaction.replace(R.id.currentFragmentLayout, quickRulesFragment);
                    fragmentTransaction.addToBackStack(backstackLabel);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_soundmanager:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    backstackLabel = "SoundManagerFragment";
                    fragmentTransaction.replace(R.id.currentFragmentLayout, soundManagerFragment);
                    fragmentTransaction.addToBackStack(backstackLabel);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        Enumerations.UserType ptype = (Enumerations.UserType)intent.getSerializableExtra("PlayerType");
        switch (ptype){
            case PLAYER:
                mTextMessage.setText("ISPLAYER");
                break;
            case GAMEMASTER:
                mTextMessage.setText("ISGAMEMASTER");
                break;
            default:
                mTextMessage.setText("ISNONE");
                break;
        }
        // Instantiate fragments
        dicerollFragment = new DicerollFragment();
        characterSheetsFragment = new CharacterSheetsFragment();
        procedurallyGeneratedDataFragment = new ProcedurallyGeneratedDataFragment();
        quickRulesFragment = new QuickRulesFragment();
        soundManagerFragment = new SoundManagerFragment();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
