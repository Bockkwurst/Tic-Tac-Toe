import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        //dieser scanner liest die Eingabe der Spieler//
        Scanner scanner = new Scanner(System.in);
        //Dieses zweidimensionale char Array bildet das Spielfeld. Es wird nach jedem Zug das jeweilige Zeichen auf einem bestimmten Platz der Arrays gespeichert.//
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        //Hier können die Spieler ihre Namen eingeben//
        System.out.println("Spieler 1 Name: ");
        String playerOneName = scanner.nextLine();
        System.out.println("Spieler 2 Name: ");
        String playerTwoName = scanner.nextLine();


        //mittels verlinkung der Methods die Logik des Spiels definieren//
        printBoard(board);

        // diese Schleife wiederholt die Runde jedes mal wenn Spieler 2 seinen Zug gemacht hat//
        while (true) {

            //Das ist die Eingabe von Spieler 1, die direkt auf dem jewiligen Platz gespeichert wird.//
            playerOneMove(board, scanner, 'X', playerOneName);

            //Die beiden if Statements fragen nach jedem Zug ab, ob das Feld voll ist oder ob der jeweilige Spieler gewnnen hat//
            if (isGameFinished(board, playerOneName, playerTwoName) == true) {
                break;
            }

            //Hier wird der Inhalt der Arrays mit dem Grid geprintet//
            printBoard(board);

            playerTwoMove(board, scanner, 'O', playerTwoName);
            if (isGameFinished(board, playerOneName, playerTwoName) == true) {
                break;
            }
            printBoard(board);
        }
        scanner.close();
    }

    /*Arrays als Grid mit Zwischenstrichen anzeigen
    Beispiel:
    +-+-+-+
    | | | |
    +-+-+-+
    | | | |
    +-+-+-+
    | | | |
    +-+-+-+
    */
    private static void printBoard(char[][] board) {
        System.out.println("+-+-+-+");
        System.out.println("|" + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + "|");
        System.out.println("+-+-+-+");
        System.out.println("|" + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + "|");
        System.out.println("+-+-+-+");
        System.out.println("|" + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + "|");
        System.out.println("+-+-+-+");
    }


    //Spieler 1 Zug//

    private static void playerOneMove(char[][] board, Scanner scanner, char symbol, String playerOneName) {

        //das ist die Eingabe des Spielers. Es wurde die String Variable gewählt, da man den inhalt eines Strings in eine int Variable setzen kann, umgekehrt aber nicht.//
        String userInput;

        //Hier wird der Zug des Spielers bestimmt. Die While Schleife prüft ob das Spiel noch läuft,//
        while (true) {

            //während bei einem 'true' dieser Text auf der Komandozeile ausgegeben wird.//
            System.out.println(playerOneName + " ist am Zug.\nWo willst du setzen? (1-9)");

            //Hier gibt der Spieler seine gewünschte Position ein.//
            userInput = scanner.nextLine();

            //Nun wird die Eingabe validiert. Wenn isValidMove ein true zurückgibt, wird die jeweilige Eingabe gespeichert.//
            if (isValidMove(board, userInput)) {

                //Der break beendet die Schleife//
                break;

                //Sollte isValidMove ein false zurückschicken, wird dieser Text ausgegeben. userInput wird auf der Komandozeile zur Eingabe des Spielers//
            } else {
                System.out.println(userInput + " ist nicht möglich.");
            }
        }

        //Das zweidimensionale Array wird mit dem Wert 'X' (oder 'O' bei Spieler 2) an der gewünschten Stelle ausgegeben.//
        placeMove(board, userInput, 'X');
    }

    //Spieler 2 Zug (identisch mit Spieler 1 mit kleinen Anpassungen der Namensgebung)//
    private static void playerTwoMove(char[][] board, Scanner scanner, char symbol, String playerTwoName) {
        String userInput;
        while (true) {
            System.out.println(playerTwoName + " ist am Zug.\nWo willst du setzen? (1-9)");
            userInput = scanner.nextLine();
            if (isValidMove(board, userInput)) {
                break;
            } else {
                System.out.println(userInput + " ist nicht möglich.");
            }
        }
        placeMove(board, userInput, 'O');
    }


    //SwitchCase Method für das Placement der "Spielteine"//
    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
            default:
                System.out.println(":(");
        }
    }

    //Validierung des Zuges//
    private static boolean isValidMove(char[][] board, String position) {
        switch (position) {
            case "1":
                return (board[0][0] == ' ');
            case "2":
                return (board[0][1] == ' ');
            case "3":
                return (board[0][2] == ' ');
            case "4":
                return (board[1][0] == ' ');
            case "5":
                return (board[1][1] == ' ');
            case "6":
                return (board[1][2] == ' ');
            case "7":
                return (board[2][0] == ' ');
            case "8":
                return (board[2][1] == ' ');
            case "9":
                return (board[2][2] == ' ');
            default:
                return false;
        }
    }

    //Check ob das Spiel beendet wurde mittels eigener Methode//
    private static boolean isGameFinished(char[][] board, String playerOneName, String playerTwoName) {
        // Check ob ein Spieler gewonnen hat//
        if (wins(board, 'X')){
            printBoard(board);
            System.out.println(playerOneName + " hat gewonnen!");
            return true;
        } else if (wins(board, 'O')){
            printBoard(board);
            System.out.println(playerTwoName + " hat gewonnen!");
            return true;
        }
        return false;

    }


    //Methode für das ermitteln eines gewinners//
    private static boolean wins(char[][] board, char symbol) {
        if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[2][0] == symbol && board[1][1] == symbol && board[0][2] == symbol)) {
            return true;
        }
        return false;
    }
}


