/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 3
 * Members  :
 * 1. 5026231052 - Abyansyah Putra Dewanto
 * 2. 5026231003 - Kanayya Shafa Amelia
 * 3. 5026231058 - Rifqi Aufa Thufail
 * ------------------------------------------------------
 */
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How big of a game size do you want?");
        System.out.println("-----------------------------------");
        System.out.println("(1) Regular 100");
        System.out.println("(2) Irregular 200");
        System.out.println("(3) A bigger one 300");
        System.out.println("(4) Jumbo size 400");
        System.out.println("-----------------------------------");
        System.out.println("Enter size 1-4 : ");
        int inputSize = 0;
        switch(sc.nextInt()){
            case 1 :
                inputSize = 100;
            break;
            case 2 :
                inputSize = 200;
                break;
            case 3 :
                inputSize = 300;
                break;
            case 4 :
                inputSize = 400;
                break;
            default :
                inputSize = 100;
        }
        SnL g1 = new SnL(inputSize);
        g1.play();

    }
}