package chippchirp;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/* 
SOS Game

This classic pen and paper game
is now written in Java! Fill up
the board with SOSs and rack up
a mean scorestreak!

RHHS ICS4U1 - 2020
*/

public class SOS {
    // Generate 2d array for use as a game board
    public static String[][] generateGameBoard(int boardSize) {
        String[][] gameBoard = new String[boardSize][boardSize];
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = "_";
            }
        }
        return gameBoard;
    }
    
    // Print game board in a grid
    public static void printGameBoard(String[][] gameBoard) {
        System.out.println();
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // Check if game board is full
    public static boolean CheckIfFull(String[][] gameBoard) {
        boolean full = true;
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                if((gameBoard[i][j].equals("_"))) {
                    full = false;
                }
            }
        }
        return full;
    }
    
    // Check for incomplete SOSs (For CPU's Turn)
    public static String CheckForIncomplete(String[][] gameBoard, int cpuY, int cpuX, int[][] DIRECTIONS) {
        String symbol = "_";
        // Check for Empty Cell with OS after it.
        for(int direction = 0; direction < 8; direction++) {
            if(!(cpuY + DIRECTIONS[direction][0] > gameBoard.length - 1 || cpuX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || cpuY + DIRECTIONS[direction][0] < 0 || cpuX + DIRECTIONS[direction][1] < 0)) {
                if(gameBoard[cpuY + DIRECTIONS[direction][0]][cpuX + DIRECTIONS[direction][1]].equalsIgnoreCase("o")) {
                    int oCoordY = cpuY + DIRECTIONS[direction][0];
                    int oCoordX = cpuY + DIRECTIONS[direction][1];
                    if(!(oCoordY + DIRECTIONS[direction][0] > gameBoard.length - 1 || oCoordX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || oCoordY + DIRECTIONS[direction][0] < 0 || oCoordX + DIRECTIONS[direction][1] < 0)) {
                        if(gameBoard[oCoordY + DIRECTIONS[direction][0]][oCoordX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                            direction = 8;
                            symbol = "s";
                        }
                    }
                }
            }
        }
        // Check for Empty Cell in the middle of two Ss
        for(int direction = 0; direction < 8; direction++) {
            if(direction <= 3) {
                if(!(cpuY + DIRECTIONS[direction][0] > gameBoard.length - 1 || cpuX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || cpuY + DIRECTIONS[direction][0] < 0 || cpuX + DIRECTIONS[direction][1] < 0)) {
                    if(!(cpuY + DIRECTIONS[direction + 4][0] > gameBoard.length - 1 || cpuX + DIRECTIONS[direction + 4][1] > gameBoard[0].length - 1 || cpuY + DIRECTIONS[direction + 4][0] < 0 || cpuX + DIRECTIONS[direction + 4][1] < 0)) {
                        if(gameBoard[cpuY + DIRECTIONS[direction][0]][cpuX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                            if(gameBoard[cpuY + DIRECTIONS[direction + 4][0]][cpuX + DIRECTIONS[direction + 4][1]].equalsIgnoreCase("s")) {
                                direction += 4;
                                symbol = "o";
                            }
                        }
                    }
                }
            }
            else if(direction > 3) {
                if(!(cpuY + DIRECTIONS[direction][0] > gameBoard.length - 1 || cpuX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || cpuY + DIRECTIONS[direction][0] < 0 || cpuX + DIRECTIONS[direction][1] < 0)) {
                    if(!(cpuY + DIRECTIONS[direction - 4][0] > gameBoard.length - 1 || cpuX + DIRECTIONS[direction - 4][1] > gameBoard[0].length - 1 || cpuY + DIRECTIONS[direction - 4][0] < 0 || cpuX + DIRECTIONS[direction - 4][1] < 0)) {
                        if(gameBoard[cpuY + DIRECTIONS[direction][0]][cpuX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                            if(gameBoard[cpuY + DIRECTIONS[direction - 4][0]][cpuX + DIRECTIONS[direction - 4][1]].equalsIgnoreCase("s")) {
                                symbol = "o";
                            }
                        }
                    }
                }
            }
        }
        return symbol;
    }
    
    // Player's Turn
    public static int PlayerMove(String[][] gameBoard, Scanner playerInput, int playerScore, int[][] DIRECTIONS) {
        //Variables
        boolean enterSymbol = true;
        boolean enterCoordinates = false;
        String playerSymbol = "";
        int playerY = 0;
        int playerX = 0;

        // Enter S or O prompt
        while(enterSymbol == true) {
            System.out.print("\nS or O? : ");
            playerSymbol = playerInput.nextLine();
            if(playerSymbol.equalsIgnoreCase("s") || playerSymbol.equalsIgnoreCase("o")) {
                enterSymbol = false;
            } else {
                System.out.println("\nInvalid input. Please enter S or O.");
            }
        }

        // Enter coordinates prompt
        enterCoordinates = true;
        while(enterCoordinates == true) {
            System.out.print("\nCoordinates to change (format as [y x]): ");
            String[] playerCoordinates = playerInput.nextLine().split(" ");
            playerY = Integer.parseInt(playerCoordinates[0]);
            playerX = Integer.parseInt(playerCoordinates[1]);
            if(playerY < 0 || playerX < 0 || playerY > gameBoard.length - 1 || playerX > gameBoard[0].length - 1) {
                System.out.println("\nInvalid coordinates! Please enter coordinates within the boundaries of the game board.");
            } else if(gameBoard[playerY][playerX].equals("_")) {
                gameBoard[playerY][playerX] = playerSymbol.toLowerCase();
                enterCoordinates = false;
            } else {
                System.out.println("\nSpace is already taken! Please enter coordinates that are not occupied.");
            }
        }
        
        // Return number of SOSs found this turn
        return CheckBoundsSOS(gameBoard, playerY, playerX, DIRECTIONS);
    }
    
    // CPU's Turn
    public static int CpuMove(String[][] gameBoard, int cpuScore, int[][] DIRECTIONS) {
        
        // ArrayLists to keep track of empty cells
        ArrayList<Integer> emptyY = new ArrayList<Integer>();
        ArrayList<Integer> emptyX = new ArrayList<Integer>();
        
        // Placeholder x and y values
        int cpuY = 0;
        int cpuX = 0;

        //Random symbol (S or O)
        String cpuSymbol = "";
        Random random = new Random();
        int randomNum = random.nextInt(2);
        if(randomNum == 0) {
            cpuSymbol = "o";
        } else {
            cpuSymbol = "s";
        }
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                if(gameBoard[i][j] == "_") {
                    emptyY.add(i);
                    emptyX.add(j);
                }

            }
        }
        if(emptyY.size() == 0) {
            return 0;
        }
        
        // Find a cell to fill
        boolean cpuChoosingPoint = true;
        while(cpuChoosingPoint == true) {
            int randomPoint = random.nextInt(emptyY.size());
            cpuY = emptyY.get(randomPoint);
            cpuX = emptyX.get(randomPoint);
    
            //Ensure point is adjacent to already filled cell
            boolean validPoint = false;
            if(validPoint == false) {
                for(int direction = 0; direction < 8; direction++) {
                    // Check if surrounding cells are within gameBoard's boundaries
                    if(!(cpuY + DIRECTIONS[direction][0] > gameBoard.length - 1 || cpuX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || cpuY + DIRECTIONS[direction][0] < 0 || cpuX + DIRECTIONS[direction][1] < 0)) {
                        if(gameBoard[cpuY + DIRECTIONS[direction][0]][cpuX + DIRECTIONS[direction][1]].equalsIgnoreCase("o") || gameBoard[cpuY + DIRECTIONS[direction][0]][cpuX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                            validPoint = true;
                            direction = 8;
                            cpuChoosingPoint = false;
                        }
                    }
                }
            }
        }

        // If incomplete SOS is found, complete it. If not, use random symbol
        String chosenSymbol = CheckForIncomplete(gameBoard, cpuY, cpuX, DIRECTIONS);
        if(chosenSymbol != ("_")) {
            cpuSymbol = chosenSymbol;
        }
        gameBoard[cpuY][cpuX] = cpuSymbol;

        // Return number of SOSs found this turn
        return CheckBoundsSOS(gameBoard, cpuY, cpuX, DIRECTIONS);
    }
    
    // Check for boundaries and SOSs
    public static int CheckBoundsSOS(String[][] gameBoard, int playerY, int playerX, int[][] DIRECTIONS) {
        int sos = 0;
        // Check for SOS starting from S
        if(gameBoard[playerY][playerX].equalsIgnoreCase("s")) {
            for(int direction = 0; direction < 8; direction++) {
                if(!(playerY + DIRECTIONS[direction][0] > gameBoard.length - 1 || playerX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || playerY + DIRECTIONS[direction][0] < 0 || playerX + DIRECTIONS[direction][1] < 0)) {
                    if(gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]].equalsIgnoreCase("o")) {
                        int oCoordY = playerY + DIRECTIONS[direction][0];
                        int oCoordX = playerX + DIRECTIONS[direction][1];
                        if(!(oCoordY + DIRECTIONS[direction][0] > gameBoard.length - 1 || oCoordX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || oCoordY + DIRECTIONS[direction][0] < 0 || oCoordX + DIRECTIONS[direction][1] < 0)) {
                            if(gameBoard[oCoordY + DIRECTIONS[direction][0]][oCoordX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                                gameBoard[playerY][playerX] = gameBoard[playerY][playerX].toUpperCase();
                                gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]] = gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]].toUpperCase();
                                gameBoard[oCoordY + DIRECTIONS[direction][0]][oCoordX + DIRECTIONS[direction][1]] = gameBoard[oCoordY + DIRECTIONS[direction][0]][oCoordX + DIRECTIONS[direction][1]].toUpperCase();
                                sos++;
                            }
                        }
                    }
                }
            }
        } 
        // Check for SOS starting from O
        if(gameBoard[playerY][playerX].equalsIgnoreCase("o")) {
            for(int direction = 0; direction < 8; direction++) {
                if(direction <= 3) {
                    if(!(playerY + DIRECTIONS[direction][0] > gameBoard.length - 1 || playerX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || playerY + DIRECTIONS[direction][0] < 0 || playerX + DIRECTIONS[direction][1] < 0)) {
                        if(!(playerY + DIRECTIONS[direction + 4][0] > gameBoard.length - 1 || playerX + DIRECTIONS[direction + 4][1] > gameBoard[0].length - 1 || playerY + DIRECTIONS[direction + 4][0] < 0 || playerX + DIRECTIONS[direction + 4][1] < 0)) {
                            if(gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                                if(gameBoard[playerY + DIRECTIONS[direction + 4][0]][playerX + DIRECTIONS[direction + 4][1]].equalsIgnoreCase("s")) {
                                    gameBoard[playerY][playerX] = gameBoard[playerY][playerX].toUpperCase();
                                    gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]] = gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]].toUpperCase();
                                    gameBoard[playerY + DIRECTIONS[direction + 4][0]][playerX + DIRECTIONS[direction + 4][1]] = gameBoard[playerY + DIRECTIONS[direction + 4][0]][playerX + DIRECTIONS[direction + 4][1]].toUpperCase();
                                    sos++;
                                    direction += 4;
                                }
                            }
                        }
                    }
                }
                else if(direction > 3) {
                    if(!(playerY + DIRECTIONS[direction][0] > gameBoard.length - 1 || playerX + DIRECTIONS[direction][1] > gameBoard[0].length - 1 || playerY + DIRECTIONS[direction][0] < 0 || playerX + DIRECTIONS[direction][1] < 0)) {
                        if(!(playerY + DIRECTIONS[direction - 4][0] > gameBoard.length - 1 || playerX + DIRECTIONS[direction - 4][1] > gameBoard[0].length - 1 || playerY + DIRECTIONS[direction - 4][0] < 0 || playerX + DIRECTIONS[direction - 4][1] < 0)) {
                            if(gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]].equalsIgnoreCase("s")) {
                                if(gameBoard[playerY + DIRECTIONS[direction - 4][0]][playerX + DIRECTIONS[direction - 4][1]].equalsIgnoreCase("s")) {
                                    gameBoard[playerY][playerX] = gameBoard[playerY][playerX].toUpperCase();
                                    gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]] = gameBoard[playerY + DIRECTIONS[direction][0]][playerX + DIRECTIONS[direction][1]].toUpperCase();
                                    gameBoard[playerY + DIRECTIONS[direction - 4][0]][playerX + DIRECTIONS[direction - 4][1]] = gameBoard[playerY + DIRECTIONS[direction - 4][0]][playerX + DIRECTIONS[direction - 4][1]].toUpperCase();
                                    sos++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return sos;
    }
    
    // Main method
    public static void main(String[] args) throws Exception {
        // Constants
        int[][] DIRECTIONS = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

        // Variables
        Scanner playerInput = new Scanner(System.in);
        int boardSize = 0;
        int playerScore = 0;
        int cpuScore = 0;
        boolean playing = true;

        // Ask for board size (minimum of 3)
        boolean boardSizePrompt = true;
        while(boardSizePrompt == true) {
            System.out.print("Board size: ");
            boardSize = playerInput.nextInt();
            playerInput.nextLine();
            if(boardSize < 3) {
                System.out.println("\nToo small! Minimum size is 3.\n");
            } else {
                boardSizePrompt = false;
            }
        }
        String[][] gameBoard = generateGameBoard(boardSize);
        printGameBoard(gameBoard);

        // Playing the game!
        boolean playerTurn = true;
        boolean cpuTurn = false;
        while(playing == true) {
            while (playerTurn == true) {
                int tempScore = PlayerMove(gameBoard, playerInput, playerScore, DIRECTIONS);
                if(tempScore == 0) {
                    playerTurn = false;
                    cpuTurn = true;
                } else {
                    playerScore += tempScore;
                } if(CheckIfFull(gameBoard) == true) {
                    System.out.println("\nBoard full!");
                    playerTurn = false;
                    playing = false;
                }
                printGameBoard(gameBoard);
                System.out.println("\nPlayer Score: " + playerScore);
                System.out.println("\n   CPU Score: " + cpuScore);
            }
            while (cpuTurn == true) {
                Thread.sleep(1000);
                int tempScore = CpuMove(gameBoard, playerScore, DIRECTIONS);
                if(tempScore == 0) {
                    cpuTurn = false;
                    playerTurn = true;
                } else {
                    cpuScore += tempScore;
                } if(CheckIfFull(gameBoard) == true) {
                    System.out.println("\nBoard full!");
                    cpuTurn = false;
                    playing = false;
                }
                printGameBoard(gameBoard);
                System.out.println("\nPlayer Score: " + playerScore);
                System.out.println("\n   CPU Score: " + cpuScore);
                }
            }
        System.out.println("\nGame over!\n");
        if(playerScore > cpuScore) {
            System.out.println("Player wins with a score of: " + playerScore);
        } else if(playerScore == cpuScore) {
            System.out.println("Draw! Both players had a score of: " + playerScore);
        } else {
            System.out.println("CPU wins with a score of: " + cpuScore);
        }
    }
}