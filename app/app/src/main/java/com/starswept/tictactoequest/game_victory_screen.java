package com.starswept.tictactoequest;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class game_victory_screen extends Fragment {
    public game_victory_screen() {
        // Required empty public constructor
    }


    View view;

    String winningPlayerIs;

    Button new_game_button, exit_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game_victory_screen, container, false);


        Bundle bundle=getArguments();
        if(bundle != null){
            //Extracts the strings from the bundle
            winningPlayerIs = bundle.getString("playerOne", null);
        }
        //Default Values



        //onClick event for the Exit Button
        exit_button = view.findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new home();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.nav_host_fragment, fragment ).commit();

            }
        });

        //onClick event for the New Game Button
        new_game_button = view.findViewById(R.id.new_game_button);
        new_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplayer_game newGameInstance = new multiplayer_game();

                //Assigns the bundle as an argument for the game
                newGameInstance.setArguments(bundle);

                //Creates a new fragment from the game
                Fragment fragment = newGameInstance;
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment ).commit();
            }
        });

        return view;
    }


}






