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
import java.util.Random;


public class singleplayer_game extends Fragment {
    public singleplayer_game() {
        // Required empty public constructor
    }

    View view;
    String humanPlayerIs;
    final String aiPlayerIs = "AI Player";
    String currentPlayerIs;
    public static final String IS_TIE_KEY = "isTie";

    //XML Imports
    TextView CurrentPlayerSymbol;
    Button button00, button01, button02;
    Button button10, button11, button12;
    Button button20, button21, button22;
    Button exit_button;
    TicTacToeGame currentGameInstance;
    private final Random random = new Random();

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
            humanPlayerIs = bundle.getString("playerOneNameIs", "Player One");
        }

        currentPlayerIs = humanPlayerIs;
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

    public void onButtonClick(int row, int col, Button currentButton) {
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
                CurrentPlayerSymbol.setText(currentPlayerIs);
                if (currentPlayerIs.equals(aiPlayerIs)) {
                    aiMakeMove();
                }
            }
        }
    }

    private void aiMakeMove() {
        int row, col;
        int gridSize = TicTacToeGame.GRID_SIZE;
        Button aiButton = null;
        do {
            row = random.nextInt(gridSize);
            col = random.nextInt(gridSize);
        } while (currentGameInstance.isSelected(row, col));
        if (row == 0 && col == 0) aiButton = button00;
        else if (row == 0 && col == 1) aiButton = button01;
        else if (row == 0 && col == 2) aiButton = button02;
        else if (row == 1 && col == 0) aiButton = button10;
        else if (row == 1 && col == 1) aiButton = button11;
        else if (row == 1 && col == 2) aiButton = button12;
        else if (row == 2 && col == 0) aiButton = button20;
        else if (row == 2 && col == 1) aiButton = button21;
        else if (row == 2 && col == 2) aiButton = button22;
        if (aiButton != null) {
            currentGameInstance.selectGridSpace(row, col);
            aiButton.setText(getCurrentPlayerIcon());
            aiButton.setTextColor(getCurrentPlayerColor());
            if (currentGameInstance.isGameOver()) {
                game_victory_screen victory_screen_components = getGameVictoryScreen();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, victory_screen_components).commit();
            } else {
                changeCurrentPlayer();
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
            transferBundle.putString("playerOneNameIs", humanPlayerIs);
            transferBundle.putString("playerTwoNameIs", aiPlayerIs);
            transferBundle.putBoolean(IS_TIE_KEY, false);
            victory_screen_components.setArguments(transferBundle);
        } else if (currentGameInstance.isTie()) {
            transferBundle.putBoolean(IS_TIE_KEY, true);
            victory_screen_components.setArguments(transferBundle);
        }
        return victory_screen_components;
    }

    public void changeCurrentPlayer() {
        if (currentPlayerIs.equals(humanPlayerIs)) {
            currentPlayerIs = aiPlayerIs;
        } else {
            currentPlayerIs = humanPlayerIs;
        }
    }

    public String getCurrentPlayerIcon(){
        if (currentPlayerIs.equals(humanPlayerIs)) {
            return getString(R.string.x);
        } else if (currentPlayerIs.equals(aiPlayerIs)) {
            return getString(R.string.o);
        } else {
            return "Error";
        }
    }

    public Integer getCurrentPlayerColor(){
        if (currentPlayerIs.equals(humanPlayerIs)) {
            return this.getResources().getColor(R.color.edittext_border_yellow);
        } else {
            return this.getResources().getColor(R.color.edittext_border_blue);
        }
    }

    public class TicTacToeGame {

        public static final int GRID_SIZE = 3;
        private Integer[][] mTicTacToeGrid;
        public TicTacToeGame() {
            mTicTacToeGrid = new Integer[GRID_SIZE][GRID_SIZE];
        }

        public void newGame() {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    mTicTacToeGrid[row][col] = 0;
                }
            }
        }

        public Boolean isSelected(int row, int col) {
            if (mTicTacToeGrid[row][col] != 0){
                return true;
            }
            return false;
        }

        public void selectGridSpace(int row, int col) {
            if(currentPlayerIs.equals(humanPlayerIs)) {
                mTicTacToeGrid[row][col] = 1;
            }
            if(currentPlayerIs.equals(aiPlayerIs)) {
                mTicTacToeGrid[row][col] = -1;
            }
        }

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

        public boolean isGameOver() {
            return isWinner() || isTie();
        }

        public boolean isTie() {
            return isFull() && !isWinner();
        }


    }




}






