package com.starswept.tictactoequest;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class achievements_menu extends Fragment {
    public achievements_menu() {
        // Required empty public constructor
    }

    ImageButton home_button;

    // Declaration of Achievement XML Elements
    private TextView recordPlayerOneWinFive;
    private TextView recordPlayerOneWinTen;
    private TextView recordPlayerOneWinTwenty;
    private TextView recordPlayerTwoWinFive;
    private TextView recordPlayerTwoWinTen;
    private TextView recordPlayerTwoWinTwenty;
    private TextView recordTieTen;
    private TextView recordTieTwenty;
    private TextView recordTieFifty;
    private TextView recordPlayFifty;
    private TextView recordPlayOneHundred;
    private TextView recordNewGamePlus;
    private achievement_tracker achievementtracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements_menu, container, false);

        // Connects Achievement TextView Objects to XML Elements
        recordPlayerOneWinFive = view.findViewById(R.id.recordPlayerOneWinFive);
        recordPlayerOneWinTen = view.findViewById(R.id.recordPlayerOneWinTen);
        recordPlayerOneWinTwenty = view.findViewById(R.id.recordPlayerOneWinTwenty);
        recordPlayerTwoWinFive = view.findViewById(R.id.recordPlayerTwoWinFive);
        recordPlayerTwoWinTen = view.findViewById(R.id.recordPlayerTwoWinTen);
        recordPlayerTwoWinTwenty = view.findViewById(R.id.recordPlayerTwoWinTwenty);
        recordTieTen = view.findViewById(R.id.recordTieTen);
        recordTieTwenty = view.findViewById(R.id.recordTieTwenty);
        recordTieFifty = view.findViewById(R.id.recordTieFifty);
        recordPlayFifty = view.findViewById(R.id.recordPlayFifty);
        recordPlayOneHundred = view.findViewById(R.id.recordPlayOneHundred);
        recordNewGamePlus = view.findViewById(R.id.recordNewGamePlus);

        // Fetch and display achievement data
        achievementtracker = new achievement_tracker(requireContext());
        displayAchievements();
        home_button = view.findViewById(R.id.homeButton);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new home();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment ).commit();
            }
        });
        return view;
    }
    
    private void displayAchievements() {
        int playerOneWinCount = achievementtracker.getPlayerOneWins();
        int playerTwoWinCount = achievementtracker.getPlayerTwoWins();
        int totalTieCount = achievementtracker.getTiesCount();
        int totalCompletedGames = achievementtracker.getCompletedGames();
        int totalNewGamesStarted = achievementtracker.getNewGamesStarted();
        if (playerOneWinCount < 5) {
            String progress = playerOneWinCount + " / 5";
            recordPlayerOneWinFive.setText(progress);
        } else {
            recordPlayerOneWinFive.setText(R.string.completed_achievement);
        }
        if (playerOneWinCount < 10) {
            String progress = playerOneWinCount + " / 10";
            recordPlayerOneWinTen.setText(progress);
        } else {
            recordPlayerOneWinTen.setText(R.string.completed_achievement);
        }
        if (playerOneWinCount < 20) {
            String progress = playerOneWinCount + " / 20";
            recordPlayerOneWinTwenty.setText(progress);
        } else {
            recordPlayerOneWinTwenty.setText(R.string.completed_achievement);
        }
        if (playerTwoWinCount < 5) {
            String progress = playerTwoWinCount + " / 5";
            recordPlayerTwoWinFive.setText(progress);
        } else {
            recordPlayerTwoWinFive.setText(R.string.completed_achievement);
        }
        if (playerTwoWinCount < 10) {
            String progress = playerTwoWinCount + " / 10";
            recordPlayerTwoWinTen.setText(progress);
        } else {
            recordPlayerTwoWinTen.setText(R.string.completed_achievement);
        }
        if (playerTwoWinCount < 20) {
            String progress = playerTwoWinCount + " / 20";
            recordPlayerTwoWinTwenty.setText(progress);
        } else {
            recordPlayerTwoWinTwenty.setText(R.string.completed_achievement);
        }
        if (totalTieCount < 10) {
            String progress = totalTieCount + " / 10";
            recordTieTen.setText(progress);
        } else {
            recordTieTen.setText(R.string.completed_achievement);
        }
        if (totalTieCount < 20) {
            String progress = totalTieCount + " / 20";
            recordTieTwenty.setText(progress);
        } else {
            recordTieTwenty.setText(R.string.completed_achievement);
        }
        if (totalTieCount < 50) {
            String progress = totalTieCount + " / 50";
            recordTieFifty.setText(progress);
        } else {
            recordTieFifty.setText(R.string.completed_achievement);
        }
        if (totalCompletedGames < 50) {
            String progress = totalCompletedGames + " / 50";
            recordPlayFifty.setText(progress);
        } else {
            recordPlayFifty.setText(R.string.completed_achievement);
        }
        if (totalCompletedGames < 100) {
            String progress = totalCompletedGames + " / 100";
            recordPlayOneHundred.setText(progress);
        } else {
            recordPlayOneHundred.setText(R.string.completed_achievement);
        }
        if (totalNewGamesStarted < 1000) {
            String progress = totalNewGamesStarted + " / 1000";
            recordNewGamePlus.setText(progress);
        } else {
            recordNewGamePlus.setText(R.string.completed_achievement);
        }
        // TODO: Change the icon color as wins increases
        // TODO: Add conditionals to hide/reveal new tiers
    }
}