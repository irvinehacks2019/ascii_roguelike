import java.util.Scanner;

public class app {

    public static void main (String[] args) {
        Scanner scanner;

        player p = new player();

        while (true) {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            int dir;
            if (input == "w") {
                dir = 1;
            } else if (input == "s") {
                dir = 2;
            } else if (input == "a") {
                dir = 3;
            } else if (input == "d") {
                dir = 4;
            }
            player.move(dir);


        }

    }
}