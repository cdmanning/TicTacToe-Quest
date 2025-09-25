package com.starswept.tictactoequest;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class multiplayer_game extends Fragment {
    public multiplayer_game() {
        // Required empty public constructor
    }


    View view;

    String playerOneNameIs, playerTwoNameIs;

    String currentPlayerIs;

    //XML Imports
    TextView CurrentPlayerSymbol;

    Button button00, button01, button02;
    Button button10, button11, button12;
    Button button20, button21, button22;

    Button new_game_button, exit_button;

    // create  objects
    TicTacToeGame currentGameInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_multiplayer_game, container, false);

        CurrentPlayerSymbol = (TextView) view.findViewById(R.id.CurrentPlayerSymbol);

        button00 = (Button) view.findViewById(R.id.button00);
        button01 = (Button) view.findViewById(R.id.button01);
        button02 = (Button) view.findViewById(R.id.button02);
        button10 = (Button) view.findViewById(R.id.button10);
        button11 = (Button) view.findViewById(R.id.button11);
        button12 = (Button) view.findViewById(R.id.button12);
        button20 = (Button) view.findViewById(R.id.button20);
        button21 = (Button) view.findViewById(R.id.button21);
        button22 = (Button) view.findViewById(R.id.button22);

        Bundle bundle=getArguments();
        if(bundle != null){
            //Extracts the strings from the bundle
            playerOneNameIs = bundle.getString("playerOneNameIs", null);
            playerTwoNameIs = bundle.getString("playerTwoNameIs", null);
        }
        //Default Values
        currentPlayerIs = playerOneNameIs;
        CurrentPlayerSymbol.setText(currentPlayerIs);

        currentGameInstance =  new TicTacToeGame();
        currentGameInstance.newGame();

        button00.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 0;
                int col = 0;
                onButtonClick(row, col,button00);
            }
        });

        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 0;
                int col = 1;
                onButtonClick(row, col,button01);
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 0;
                int col = 2;
                onButtonClick(row, col,button02);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 1;
                int col = 0;
                onButtonClick(row, col,button10);
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 1;
                int col = 1;
                onButtonClick(row, col,button11);
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 1;
                int col = 2;
                onButtonClick(row, col,button12);
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 2;
                int col = 0;
                onButtonClick(row, col,button20);
            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 2;
                int col = 1;
                onButtonClick(row, col,button21);
            }
        });

        button22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 2;
                int col = 2;
                onButtonClick(row, col,button22);
            }
        });

        //onClick event for the Exit Button
        exit_button = view.findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new home();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.nav_host_fragment, fragment ).commit();

            }
        });



        return view;
    }


    public void onButtonClick(int row, int col,Button currentButton) {
        if (!currentGameInstance.isSelected(row, col)) {
            currentGameInstance.selectGridSpace(row, col);
            currentButton.setText(getCurrentPlayerIcon());
            currentButton.setTextColor(getCurrentPlayerColor());
            if (currentGameInstance.isGameOver()) {
                game_victory_screen victory_screen_components = getGameVictoryScreen();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, victory_screen_components).commit();
            } else {
                changeCurrentPlayer();
                getCurrentPlayerColor();
                CurrentPlayerSymbol.setText(currentPlayerIs);
            }
        }
    }

    @NonNull
    private game_victory_screen getGameVictoryScreen() {
        Bundle transferBundle = new Bundle();
        game_victory_screen victory_screen_components = new game_victory_screen();
        if (currentGameInstance.isWinner()) {
            transferBundle.putString("winningPlayer", currentPlayerIs);
            transferBundle.putString("playerOneNameIs", playerOneNameIs);
            transferBundle.putString("playerTwoNameIs", playerTwoNameIs);
            victory_screen_components.setArguments(transferBundle);
        }
        return victory_screen_components;
    }


    public void changeCurrentPlayer() {
        if (currentPlayerIs.equals(playerOneNameIs)) {
            currentPlayerIs = playerTwoNameIs;
        } else if (currentPlayerIs.equals(playerTwoNameIs)){
            currentPlayerIs = playerOneNameIs;
        } else{
            currentPlayerIs = playerOneNameIs;
        }
    }


    public String getCurrentPlayerIcon(){
        if (currentPlayerIs.equals(playerOneNameIs)) {
            return getString(R.string.x);
        } else if (currentPlayerIs.equals(playerTwoNameIs)) {
            return getString(R.string.o);
        } else {
            return "Error";
        }

    }


    public Integer getCurrentPlayerColor(){
        if (currentPlayerIs.equals(playerOneNameIs)) {
            return this.getResources().getColor(R.color.edittext_border_yellow);
        } else {
            return this.getResources().getColor(R.color.edittext_border_blue);
        }
    }


    public void createPopUp(int gameOverState){
        if (gameOverState == 1){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());

            // Set the message show for the Alert time
            alertBuilder.setMessage(currentPlayerIs+ " is the winner!");

            // Set Alert Title
            alertBuilder.setTitle("Congratulations!");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            alertBuilder.setCancelable(true);

            // Create the Alert dialog
            AlertDialog alertDialog = alertBuilder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }
        else if (gameOverState == 2){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());

            // Set Alert Title
            alertBuilder.setTitle("It's a Tie!");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            alertBuilder.setCancelable(true);

            // Create the Alert dialog
            AlertDialog alertDialog = alertBuilder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }

    }

    public class TicTacToeGame {

        public static final int GRID_SIZE = 3;

        // Creates an array of integer values
        private Integer[][] mTicTacToeGrid;

        //Resizes the array using the GRID_SIZE
        public TicTacToeGame() {
            mTicTacToeGrid = new Integer[GRID_SIZE][GRID_SIZE];
        }

        //Initializes the values in the array to 0.
        public void newGame() {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    mTicTacToeGrid[row][col] = 0;
                }
            }
        }

        //Returns a boolean value depending on if a player has already selected the button
        public Boolean isSelected(int row, int col) {
            if (mTicTacToeGrid[row][col] != 0){
                return true;
            }
            return false;
        }


        //Identifies the turn player and changes the button accordingly
        public void selectGridSpace(int row, int col) {

            //We handle these checks here before passing them to GameFragment.java to obfuscate
            if(currentPlayerIs.equals(playerOneNameIs)) {
                mTicTacToeGrid[row][col] = 1;
            }
            if(currentPlayerIs.equals(playerTwoNameIs)) {
                mTicTacToeGrid[row][col] = -1;
            }
        }

        //Checks if the array is full leading to a tie
        private boolean isFull() {
            int row, col;
            boolean status = true;
            for ( row = 0; row < GRID_SIZE; row++) {
                for ( col = 0; col < GRID_SIZE; col++) {
                    if (mTicTacToeGrid[row][col] == 0) {
                        status = false;
                    }
                }
            }
            return status;
        }

        //Checks if there is a winner
        public boolean isWinner(){

            //Checking each row
            for (int row = 0; row < GRID_SIZE; row++) {
                int line_total = 0;
                for (int col = 0; col < GRID_SIZE; col++) {
                    line_total = line_total +  mTicTacToeGrid[row][col];
                    if (line_total == 3 || line_total == -3) {

                        return true;
                    }
                }
            }

            //Checking each column
            for (int col = 0; col < GRID_SIZE; col++) {
                int line_total = 0;
                for (int row = 0; row < GRID_SIZE; row++) {
                    line_total = line_total +  mTicTacToeGrid[row][col];
                    if (line_total == 3 || line_total == -3) {

                        return true;
                    }
                }
            }

            //Checking downward sloping diagonal
            int downwardSumAngle = 0;
            for (int i = 0; i < GRID_SIZE; i++) {
                downwardSumAngle = downwardSumAngle + mTicTacToeGrid[i][i];
            }
            if (downwardSumAngle == -3 || downwardSumAngle == 3) {

                return true;
            }

            //Checking upward sloping diagonal
            int upwardSumAngle = 0;
            upwardSumAngle = mTicTacToeGrid[0][2] + mTicTacToeGrid[1][1] + mTicTacToeGrid[2][0];
            if (upwardSumAngle == -3 || upwardSumAngle == 3) {

                return true;
            }
            return false;
        }

        //Handles logic between isWinner() and isFull() to decide when the game is over
        //Called by the onClick button in GameFragment.java
        public boolean isGameOver() {
            //If the grid is full and there is not a winner: end the game
            if (isFull() && !(isWinner())){
                return true;
            }
            //If the there is a winner: end the game
            return isWinner();
        }
    }




}






