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
    String playerOneNameIs, playerTwoNameIs;

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

                playerOneNameIs = playerOneEditText.getText().toString();
                playerTwoNameIs = playerTwoEditText.getText().toString();


                //Check string length first them resize
                if (playerOneNameIs.length() > 20){
                    playerOneNameIs  = playerOneNameIs.substring(0, 17);
                    playerOneNameIs  = playerOneNameIs + "..";
                }
                if (playerTwoNameIs.length() > 20){
                    playerTwoNameIs  = playerTwoNameIs.substring(0, 17);
                    playerTwoNameIs  = playerTwoNameIs + "..";
                }

                //Handles cases where fields are left empty
                if (playerOneNameIs.equals(playerTwoNameIs) & !playerOneNameIs.isEmpty())  {
                    playerOneNameIs = playerOneNameIs+" #1";
                    playerTwoNameIs = playerTwoNameIs+" #2";
                }else {
                    if (playerOneNameIs.isEmpty()) {
                        playerOneNameIs = "Player #1";
                    }
                    if (playerTwoNameIs.isEmpty()) {
                        playerTwoNameIs = "Player #2";
                    }
                }

                transferBundle.putString("playerOneNameIs", playerOneNameIs);
                transferBundle.putString("playerTwoNameIs", playerTwoNameIs);
                multiplayer_game playerNames = new multiplayer_game();
                playerNames.setArguments(transferBundle);

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, playerNames).commit();
            }
        });
        return view;
    }
}