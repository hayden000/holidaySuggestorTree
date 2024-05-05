import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class HolidaySuggester {
    public static Scanner kbd = new Scanner(System.in);

    public static void main (String[] args) throws FileNotFoundException {
        Scanner kbd = new Scanner(System.in);
        TreeNode treeRoot =
                new TreeNode("Do you like cold weather?" ,
                        new TreeNode("Are you a keen hiker? " ,
                                new TreeNode("The Scottish Highlands") ,
                                new TreeNode("Moscow")) ,
                        new TreeNode("Goa"));
        TreeNode treeNodePointer;
        treeNodePointer = treeRoot;
        try {
            FileInputStream tree = new FileInputStream("suggestions.txt");
            int val;
            String file;
            file = "";
            do {
                val = tree.read();
                if (val >= 0X20 && val <= 0x7F) {
                    file = file + (char) val;
                }
            } while (val != - 1);
            file = file.substring(0 , file.length() - 1);
            String[] arrOfStr = file.split(",");
            Boolean start;
            start = true;
            for (String i : arrOfStr) {
                //FIXME
                if (start == true) {
                    treeRoot = (new TreeNode(i.substring(1 , i.length())));
                    start = false;
                    treeNodePointer = treeRoot;
                }
                else if (i.charAt(0) == ('0')) {
                    treeNodePointer.setLeft(new TreeNode(i.substring(1 , i.length())));
                }
                else {
                    treeNodePointer.setRight(new TreeNode(i.substring(1 , i.length())));
                }

            }
        } catch (IOException e) {
            System.err.println("File not found exception");
        }
        System.out.println("Welcome to the Post-COVID Holiday Destination Suggester.");
        while (treeNodePointer != null) {
            if (askYesNo(treeNodePointer.value)) {
                treeNodePointer = treeNodePointer.getLeft();
                if (treeNodePointer.isLeaf()) {
                    System.out.println("Perhaps you would like to go to " + treeNodePointer.value + ".");
                    if (! (askYesNo("Is this satisfactory? "))) {
                        System.out.println("What would you prefer instead? ");
                        String userInput = kbd.nextLine();
                        System.out.println("Tell me a question that distinguishes " +
                                           treeNodePointer.value +
                                           " from " +
                                           userInput);
                        String inputQuestion = kbd.nextLine();
                        treeNodePointer.setRight(new TreeNode(treeNodePointer.value));
                        treeNodePointer.value = inputQuestion;
                        treeNodePointer.setLeft(new TreeNode(userInput));
                        treeNodePointer = treeNodePointer.getRight();
                    }
                }
            }
            else {
                treeNodePointer = treeNodePointer.getRight();
                if (treeNodePointer.isLeaf()) {
                    System.out.println("Perhaps you would like to go to " + treeNodePointer.value + ".");
                    if (! (askYesNo("Is this satisfactory? "))) {
                        System.out.println("What would you prefer instead? ");
                        String userInput = kbd.nextLine();
                        System.out.println("Tell me a question that distinguishes " +
                                           treeNodePointer.value +
                                           " from " +
                                           userInput);
                        String question = kbd.nextLine();
                        treeNodePointer.setRight(new TreeNode(treeNodePointer.value));
                        treeNodePointer.value = question;
                        treeNodePointer.setLeft(new TreeNode(userInput));
                        treeNodePointer = treeNodePointer.getRight();
                    }
                }
            }
            if (treeNodePointer.isLeaf()) {
                if (askYesNo("Do you want to play again? ")) {
                    treeNodePointer = treeRoot;
                }
                else {
                    if (askYesNo("Do you want to save the current tree? ")) {
                        try {
                            PrintStream BufferWriter = new PrintStream(new FileOutputStream("suggestions.txt"));
                            treeNodePointer = treeRoot;
                            traverse(treeNodePointer , BufferWriter);
                            BufferWriter.close();
                        } catch (IOException e) {
                            System.err.println("File not found exception");
                        } finally {
                            System.exit(0);
                        }
                    }
                    else {
                        System.exit(0);
                    }
                }
            }
        }
    }

    public static boolean askYesNo (String question) {
        System.out.println(question + " [y/n]");
        String userInput = kbd.next();
        if (userInput.equals("y")) {
            return true;
        }
        return false;
    }

    public static void traverse (TreeNode cursor , PrintStream BufferWriter) {
        if (cursor.isLeaf()) {
            BufferWriter.println("0" + cursor.value + ",");
        }
        else {
            BufferWriter.println("1" + cursor.value + ",");
        }
        if (cursor.getLeft() != null) {
            traverse(cursor.getLeft() , BufferWriter);
        }
        if (cursor.getRight() != null) {
            traverse(cursor.getRight() , BufferWriter);
        }
    }
//    Line 37 FIXME code
//    public static void readPreOrder (TreeNode treeRoot , int iterator) throws IOException {
//        String next;
//        next = fileReader.readLine();
//        if (iterator == 0) {
//            treeRoot.setCurrent(next);
//            iterator=iterator+1;
//            readPreOrder(treeRoot , iterator);
//
//        }
//        if (! checkLeaf(next)) {
//            String nextNext;
//            nextNext = fileReader.readLine();
//            if (checkLeaf(nextNext)) {
//                String nextNextNext;
//                nextNextNext = fileReader.readLine();
//                if (checkLeaf(nextNextNext)) {
//                    treeRoot.setLeft(new TreeNode(next));
//                    treeRoot.getRight().setLeft(new TreeNode(nextNext));
//                    treeRoot.getLeft().setRight(new TreeNode(nextNextNext));
//                    iterator=iterator+1;
//                    String nextNextNext = fileReader.readLine();
//                    // nextNextNext = fileReader.readLine();
//                    treeRoot.setRight(new TreeNode(nextNextNext));
//                    readPreOrder(treeRoot.getRight(),iterator);
//                }
//            }else {
//                String nextNext = fileReader.readLine();
//                if (checkLeaf(nextNext)){
//                    treeRoot.setLeft(new TreeNode(next));
//                    treeRoot.setRight(new TreeNode(nextNext));
//                    iterator=iterator+1;
//                    readPreOrder(treeRoot.getRight(), iterator);
//                }
//            }
//        }
//
//    }
}
