# TicTacToe Quest - Design Document

## 1. Project Scope
A native Android implementation of the game TicTacToe built in Java. Supporting both PvE against AI and PvP against other players the game will integrate with the Google Play Games Services for earning in-game achievements.

## 2. Development Progress
- [ ] **Single Player (PvE)**
    - [x] Easy Difficulty (Randomization Algorithm)
    - [ ] Medium Difficulty (Probability Switching)
    - [ ] Hard Difficulty (Minimax Algorithm)
- [ ] **Multiplayer (PvP)**
    - [x] Local Device (Pass and play)
    - [ ] Local Network (Google Nearby Connections API)
- [ ] **Systems & UI**
    - [ ] Settings Menu (Sound, Haptics, Theme)
    - [x] In-game Achievements Menu
- [ ] **Integrations**
    - [ ] Google Play Games Services (Achievement Sync)

## 3. Game Logic & Flow
* **State Management**
    * **Data Structure:** A 2D Integer array `mTicTacToeGrid` of size `GRID_SIZE` (3x3).
    * **Turn Order:** Player X always goes first.
    * **Cell Values:**
     
        `0`: Empty cell
      
        `1` : Player 1 (X)
      
        `-1` : Player 2 or AI (O)  
* **Turn Sequence**
    * **Move Validation:** System confirms `mTicTacToeGrid[row][col] == 0` before allowing a move.
    * **Update:** The selected grid space is updated with the active player's integer value.
    * **Game Over Check:** After every move, the system calculates the sums of all 3 rows, 3 columns, and 2 diagonals.
        * **Victory:** If any summation `3` or `-3`, the active player is declared the winner.
        * **Draw:** If the grid is full and no victory is detected.
    * **Resolution:** If the game is not over, the system toggles the active player.

## 4. Game Systems
* **Google Play Games Integration**
    * Sync for player achievements.
* **Achievement System**
    * A collection of milestones that increment/unlock based on gameplay events.
 
        <details>
        <summary><b>View Planned Achievement List</b></summary>

        |      Achievement Name        |                     Hint                     |                        Requirement                           |
        | :--------------------------: | :------------------------------------------: | :----------------------------------------------------------: |
        | **Rookie Winner (P1)**       | Win 5 games as Player One.                   | Win 5 games as Player One                                    |
        | **Veteran Challenger (P1)**  | Win 10 games as Player One                   | Win 10 games as Player One                                   |
        | **Grandmaster (P1)**         | Win 20 games as Player One                   | Win 20 games as Player One                                   |
        | **Rookie Winner (P2)**       | Win 5 games as Player Two                    | Win 5 games as Player Two                                    |
        | **Veteran Challenger (P2)**  | Win 10 games as Player Two                   | Win 10 games as Player Two                                   |
        | **Grandmaster (P2)**         | Win 20 games as Player Two                   | Win 20 games as Player Two                                   |
        | **Stalemate**                | Tie 10 games                                 | Tie 10 games                                                 |
        | **Evenly Matched**           | Tie 20 games                                 | Tie 20 games                                                 |
        | **Perfectly Balanced**       | Tie 50 games.                                | Tie 50 games.                                                |
        | **Casual Player**            | Play 50 games.                               | Play 50 games                                                |
        | **Hardcore Gamer**           | Play 100 games                               | Play 100 games                                               |        
        | **New Game+**                | The end is never the end is never the end    | Press `New Game` 1000 times                                  |
        | **Helpful Guide**            | Knowledge is power                           | Open the Game Guide                                          |
        | **Starcrossed**              | A match written in the stars                 | Play a game as Romeo vs Juliet                               |
        | **Night Owl**                | Burning the midnight oil                     | Play a game between 12 AM and 4 AM                           |
        | **Stinker**                  | Watch your mouth                             | Use Profane Language in player name                          |
        | **Historical Upset**         | A Cold War reversal                          | Bobby vs Borris where Borris Wins                            |
        | **Match of the Century**     | The King of Chess                            | Bobby vs Borris where Bobby Wins.                            |
        | **The 11th Hour**            | Cutting it close                             | Win a game on the last possible move                         |
        | **Minimum Effort**           | Why play more                                | Win a game in the minimum number of moves (5)                |
        | **Speedran**                 | "I'm actually a god at this game"            | Lose a game in the minimum number of moves (5)               |
        | **Cheaty McCheaterson**      | Not today.                                   | The the name `AI Player` as the player #2 name               |
        | **Cornered**                 | Controlling the edges                        | Win by occupying all 4 corners of the grid                   |
        | **Ego Booster**              | Share your thoughts                          | Rate the app in the app store                                |
        | **Deja Vu**                  | Haven't we been here before                  | Finish a game with the exact same layout as the previous one |
        | **Happy Groundhog Day**      | How does this keep happening?                | Two games with the exact same moves.                         |
        | **A Dynamic Game**           | Justice is best served in pairs.             | Play a game as Batman and Robbin                             |
        | **Elementary Triumph**       | Is the game afoot or TicTacToe?              | Play a game as Sherlock Holmes and Dr. Watson                |              
        | **Don't Try It**             | A bold move, but poorly calculated.          | Win as Obi-Wan against Anakin                                |


        </details>
      
* **Settings & Preferences**
    * SharedPreferences for sound, haptics and theme.
      
## 5. Visual Identity
* **Color Palette**
    * **Player X :** Yellow `#F9D070`
    * **Player O :** Blue `#7BA3DE`
    * **Background Gradient:** 3-tier gradient following `#df9bd0` > `#8fc2e1` > `#a7e2af` 
* **Typography**
    * **Main Font:** `Comic Sans`
* **Themes:**
    * Light mode and Dark mode based on Android OS setting.

## 6. Tech Stack
* **Language:** Java
* **Minimum SDK:** API 26
* **Target SDK:** API 36
* **UI:** XML Layouts 
* **Persistence:** SharedPreferences

## 7. Future Features (v2)
* Unlockable cosmetics/sounds/theme from achievements


