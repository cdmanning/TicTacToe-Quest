package com.starswept.tictactoequest;

import android.content.Context;
import android.content.SharedPreferences;

public class achievement_tracker {
    private static final String PREFS_NAME = "GameAchievementsPrefs";
    private static final String KEY_P1_WINS = "playerOneWins";
    private static final String KEY_P2_WINS = "playerTwoWins";
    private static final String KEY_TIES = "tiesCount";
    private static final String KEY_COMPLETED_GAMES = "completedGames";
    private static final String KEY_NEW_GAMES_STARTED = "newGamesStartedCount";
    private static final String KEY_PROFANITY_UNLOCKED = "profanityAchievementUnlocked";
    private SharedPreferences sharedPrefs;

    public achievement_tracker(Context context) {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public int getPlayerOneWins() {
        return sharedPrefs.getInt(KEY_P1_WINS, 0);
    }

    public int getPlayerTwoWins() {
        return sharedPrefs.getInt(KEY_P2_WINS, 0);
    }

    public int getTiesCount() {
        return sharedPrefs.getInt(KEY_TIES, 0);
    }

    public int getCompletedGames() {
        return sharedPrefs.getInt(KEY_COMPLETED_GAMES, 0);
    }

    public int getNewGamesStarted() {
        return sharedPrefs.getInt(KEY_NEW_GAMES_STARTED, 0);
    }

    public boolean isProfanityAchievementUnlocked() {
        return sharedPrefs.getBoolean(KEY_PROFANITY_UNLOCKED, false);
    }

    public void incrementPlayerOneWins() {
        int newCount = getPlayerOneWins() + 1;
        sharedPrefs.edit().putInt(KEY_P1_WINS, newCount).apply();
    }

    public void incrementPlayerTwoWins() {
        int newCount = getPlayerTwoWins() + 1;
        sharedPrefs.edit().putInt(KEY_P2_WINS, newCount).apply();
    }

    public void incrementTiesCount() {
        int newCount = getTiesCount() + 1;
        sharedPrefs.edit().putInt(KEY_TIES, newCount).apply();
    }

    public void incrementCompletedGames() {
        int newCount = getCompletedGames() + 1;
        sharedPrefs.edit().putInt(KEY_COMPLETED_GAMES, newCount).apply();
    }

    public void incrementNewGamesStarted() {
        int newCount = getNewGamesStarted() + 1;
        sharedPrefs.edit().putInt(KEY_NEW_GAMES_STARTED, newCount).apply();
    }

    public void unlockProfanityAchievement() {
        sharedPrefs.edit().putBoolean(KEY_PROFANITY_UNLOCKED, true).apply();
    }

}