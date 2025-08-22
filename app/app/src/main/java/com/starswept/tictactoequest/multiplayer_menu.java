package com.starswept.tictactoequest;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class multiplayer_menu extends Fragment{
    Button  startGameButton;
    EditText playerOneEditText, playerTwoEditText;
    String playerOne, playerTwo;

    public multiplayer_menu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_multiplayer_menu, container, false);

        playerOneEditText = (EditText) view.findViewById(R.id.player1NameEditText);
        playerTwoEditText = (EditText) view.findViewById(R.id.player2NameEditText);
        startGameButton = (Button) view.findViewById(R.id.startGameButton);

        //Create a bundle to pass parameters
        Bundle transferBundle = new Bundle();

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerOne = playerOneEditText.getText().toString();
                playerTwo = playerTwoEditText.getText().toString();

                //Check string length first them resize
                if (playerOne.length() > 9){
                    playerOne  = playerOne.substring(0, 7);
                    playerOne  = playerOne + "..";
                }
                if (playerTwo.length() > 9){
                    playerTwo  = playerTwo.substring(0, 7);
                    playerTwo  = playerTwo + "..";
                }

                //Handles cases where fields are left empty
                if (playerOne.equals(playerTwo) & !playerOne.isEmpty())  {
                    playerOne = playerOne+" #1";
                    playerTwo = playerTwo+" #2";
                }else {
                    if (playerOne.isEmpty()) {
                        playerOne = "Player #1";
                    }
                    if (playerTwo.isEmpty()) {
                        playerTwo = "Player #2";
                    }
                }

                transferBundle.putString("playerOne", playerOne);
                transferBundle.putString("playerTwo", playerTwo);
                multiplayer_game playerNames = new multiplayer_game();
                playerNames.setArguments(transferBundle);

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, playerNames).commit();
            }
        });
        return view;
    }
}