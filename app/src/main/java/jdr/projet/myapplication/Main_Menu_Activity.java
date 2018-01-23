package jdr.projet.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import jdr.projet.myapplication.Classes.Enumerations;

public class Main_Menu_Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Check clicks on the view
        findViewById(R.id.button_player_mode).setOnClickListener(this);
        findViewById(R.id.button_gamemaster_mode).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.button_player_mode:
                // We clicked on add comment
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("PlayerType", Enumerations.UserType.PLAYER);
                startActivity(intent);
                break;
            case R.id.button_gamemaster_mode:
                // We clicked on add comment
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("PlayerType", Enumerations.UserType.GAMEMASTER);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

}
