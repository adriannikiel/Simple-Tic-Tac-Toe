package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String[] state = scanner.next().split("");

        String[][] grid = makeGrid(state);

        printGrid(grid);

        boolean inputOK = false;

        String xCoords = null;
        String yCoords = null;

        do {
            System.out.print("Enter the coordinates: ");

            xCoords = scanner.next();
            yCoords = scanner.next();

            inputOK = analyzeInput(state, xCoords, yCoords);

        } while (!inputOK);

        int x = Integer.parseInt(xCoords);
        int y = Integer.parseInt(yCoords);

        grid[x - 1][y - 1] = "X";

        printGrid(grid);

        //analyzeState(state);
    }

    public static void printGrid(String[][] grid) {
        System.out.println("---------");

        for (int i = 0; i < grid.length; i++) {

            System.out.print("| ");

            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }

    public static boolean analyzeInput(String[] state, String xCoords, String yCoords) {
        final String OCCUPIED = "This cell is occupied! Choose another one!";
        final String IS_NOT_NUMBER = "You should enter numbers!";
        final String WRONG_COORDINATES = "Coordinates should be from 1 to 3";

        String[][] grid = makeGrid(state);

        int x;
        int y;

        try {
            x = Integer.parseInt(xCoords);
            y = Integer.parseInt(yCoords);
        } catch (NumberFormatException e) {
            System.out.println(IS_NOT_NUMBER);
            return false;
        }

        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println(WRONG_COORDINATES);
            return false;
        }

        if (isOccupied(grid, x, y)) {
            System.out.println(OCCUPIED);
            return false;
        }

        return true;
    }

    public static void analyzeState(String[] state) {

        boolean isImpossible = checkImpossible(state);

        if (isImpossible) {
            System.out.println("Impossible");
            return;
        }

        if (isWinner(state, "X")) {
            System.out.println("X wins");
            return;
        }

        if (isWinner(state, "O")) {
            System.out.println("O wins");
            return;
        }

        if (countLetterInGrid(state, "X") + countLetterInGrid(state, "O") == 9) {
            System.out.println("Draw");
            return;
        }

        System.out.println("Game not finished");
    }

    private static boolean isOccupied(String[][] grid, int x, int y) {

        String actual = grid[x - 1][y - 1];

        if (actual.equals(" ") || actual.equals("_")) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkImpossible(String[] state) {
        int countX = countLetterInGrid(state, "X");
        int countY = countLetterInGrid(state, "O");

        if (Math.abs(countX - countY) > 1) {
            return true;
        }

        boolean isWinnerX = isWinner(state, "X");
        boolean isWinnerY = isWinner(state, "O");

        return isWinnerX && isWinnerY;
    }

    private static boolean isWinner(String[] state, String letter) {
        String[][] grid = makeGrid(state);
        int count = 0;

        // check rows
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals(letter)) {
                    count++;
                }
            }

            if (count == 3) {
                return true;
            }

            count = 0;
        }

        // check columns
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i].equals(letter)) {
                    count++;
                }
            }

            if (count == 3) {
                return true;
            }

            count = 0;
        }

        // check diagonals
        if (grid[0][0].equals(letter) && grid[1][1].equals(letter) && grid[2][2].equals(letter)) {
            return true;
        }

        if (grid[0][2].equals(letter) && grid[1][1].equals(letter) && grid[2][0].equals(letter)) {
            return true;
        }

        return false;

    }

    private static int countLetterInGrid(String[] state, String letter) {

        int result = 0;

        for (String s : state) {
            if (letter.equals(s)) {
                result++;
            }
        }

        return result;
    }

    private static String[][] makeGrid(String[] state) {

        return new String[][]{
                {state[0], state[1], state[2]},
                {state[3], state[4], state[5]},
                {state[6], state[7], state[8]}
        };
    }
}