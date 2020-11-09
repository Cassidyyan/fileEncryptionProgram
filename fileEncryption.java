import java.util.*;
import java.io.*;


public class fileEncryption {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        Scanner in = new Scanner(System.in);

        String input, output;
        String letters = "AEIJOPRSTVX ";
        String substitutes = "@=!?*#&$+^%_";
        String rightSpace = "", leftSpace = "";
        char temp;

        // start of loop, prompts the user for input
        while(true) {
            System.out.print("Would you like to encrypt or decrypt a message (E or D): ");
            String choice = in.nextLine();

            // main program for encryption
            if (choice.equalsIgnoreCase("E")) {
                System.out.print("Enter a file to read from (please include the .txt extension): ");
                input = in.nextLine();
                System.out.print("Enter a file to output to (please include the .txt extension): ");
                output = in.nextLine();
                System.out.println("Your message has been encrypted\n");

                // opening the file
                Scanner inFile = new Scanner(new File(input));
                // reading all the lines of the file
                while(inFile.hasNextLine()) {
                    String s = inFile.nextLine();

                    // finds all spaces on the right of the string and then convert it to "/"
                    for(int i = 0; i < s.length(); i++) {
                        temp = s.charAt(i);
                        if (temp ==' '){
                            rightSpace += "" + "/";
                        }
                        else {
                            break;
                        }

                    }

                    // finds all spaces on the left side of the string
                    for (int i = s.length() - 1; i >= 0; i--) {
                        temp = s.charAt(i);
                        if (temp == ' ') {
                            leftSpace += "" + "/";

                        }
                        else {
                            break;
                        }

                    }

                    PrintWriter outFile = new PrintWriter(new FileWriter(output, true));
                    outFile.print(rightSpace);
                    outFile.close();
                    rightSpace = "";

                    //1-2. (Trimming and changing to Upper case)
                    s = s.trim();
                    s = s.toUpperCase();

                    //3.
                    // checks over all the letters and substitutes
                    for (int i = 0; i < s.length(); i++) {
                        temp = s.charAt(i);
                        int indexOfLetter = letters.indexOf(temp);

                        if (indexOfLetter > -1) {
                            s = s.substring(0, i) + substitutes.substring(indexOfLetter, indexOfLetter+1) + s.substring(i + 1);

                        }
                    }
//					System.out.println(s);

                    //4.
                    int mid = (s.length() + 1) / 2;
                    s = s.substring(mid) + s.substring(0, mid);
//					System.out.println(s);

                    //5.
                    s = s.substring(s.length() - 2) + s.substring(2, s.length() - 2) + s.substring(0,2);
//					System.out.println(s);

                    //6.
                    s = s.substring(0, mid - 3) + s.substring(mid - 1, mid + 1) + s.substring(mid - 3, mid -1) + s.substring(mid + 1);
//					System.out.println(s);

                    //7.
                    for (int i = 0; i < s.length() -1; i += 2) {
                        s = s.substring(0, i) + s.substring(i + 1, i + 2) + s.substring(i, i+ 1) + s.substring(i + 2);

                    }

                    // opens the output file and then stores the sentence in the .txt file
                    outFile = new PrintWriter(new FileWriter(output, true));
                    outFile.print(s);

                    // prints the remaining spaces of the sentence
                    outFile.println(leftSpace);

                    // resets the string for the next line
                    leftSpace = "";
                    outFile.close();
                }
                // closing the read file
                inFile.close();
            }


            // decrypt file program
            else if (choice.equalsIgnoreCase("D")){
                System.out.print("Enter a file to read from (please include the .txt extension): ");
                input = in.nextLine();
                System.out.print("Enter a file to output to (please include the .txt extension): ");
                output = in.nextLine();
                System.out.println("Your message has been decrypted\n");

                // reading in the output txt
                Scanner inFile = new Scanner(new File(input));
                while (inFile.hasNextLine()) {
                    String s = inFile.nextLine();

                    // changes back the "/" to spaces and then storing it in a variable
                    for (int i = 0; i < s.length(); i ++) {
                        temp = s.charAt(i);
                        if (temp == '/') {
                            rightSpace += "" + " ";
                        }
                        else {
                            break;
                        }
                    }

                    // changes back the "/" to spaces and then storing it in a variable
                    for (int i = s.length() - 1; i >= 0; i--) {
                        temp = s.charAt(i);
                        if (temp == '/') {
                            leftSpace += "" + " ";
                        }
                        else {
                            break;
                        }
                    }

                    // changes all the "/" to " " in the string
                    for (int i = 0; i < s.length(); i++) {
                        temp = s.charAt(i);
                        if (temp == '/') {
                            s = s.substring(0, i) + " " + s.substring(i + 1);
                        }

                    }

                    // triming the string to be decrypted
                    s = s.trim();

                    // opening the output file and printing the spaces on the right side
                    PrintWriter outFile = new PrintWriter(new FileWriter(output, true));
                    outFile.print(rightSpace);
                    outFile.close();
                    rightSpace = "";


                    // Decryption program

                    //7.
                    for (int i = 0; i < s.length() -1; i += 2) {
                        s = s.substring(0, i) + s.substring(i + 1, i + 2) + s.substring(i, i+ 1) + s.substring(i + 2);

                    }
//					System.out.println(s);

                    //6.
                    int mid = (s.length() + 1) / 2;
                    s = s.substring(0, mid - 3) + s.substring(mid - 1, mid + 1) + s.substring(mid - 3, mid -1) + s.substring(mid + 1);
//					System.out.println(s);


                    //5.
                    s = s.substring(s.length() - 2) + s.substring(2, s.length() - 2) + s.substring(0,2);
//					System.out.println(s);

                    //4.
                    if (s.length() % 2 ==1) {
                        s = s.substring(mid - 1) + s.substring(0, mid - 1);
                    }
                    else
                        s = s.substring(mid) + s.substring(0, mid);

//					System.out.println(s);

                    //3.
                    for (int i = 0; i < s.length(); i++) {
                        temp = s.charAt(i);
                        int indexOfSymbols = substitutes.indexOf(temp);

                        if (indexOfSymbols > -1) {
                            s = s.substring(0, i) + letters.substring(indexOfSymbols, indexOfSymbols+1) + s.substring(i + 1);

                        }
                    }

                    //2.
                    s = s.toLowerCase();

                    // opening the output once more to print the reverted string
                    outFile = new PrintWriter(new FileWriter(output, true));
                    outFile.print(s);

                    // prints the remaining spaces
                    outFile.println(leftSpace);
                    leftSpace = "";
                    outFile.close();

                }
                inFile.close();
            }

            // if user inputs "done" then break
            else if (choice.equalsIgnoreCase("done")) {
                System.out.println("Thank you for using the cypher");
                break;
            }

            // prompts the user to input a valid input
            else {
                System.out.println("please choose a valid option\n");
            }
        }
    }
}
