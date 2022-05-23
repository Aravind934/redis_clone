import java.util.Scanner;

import DB.Db;

public class Main {
    Db db = new Db();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Main main = new Main();
        while (true) {
            System.out.print(">> ");
            String str = input.nextLine();
            if (str.equals("exit")) {
                break;
            }
            String[] arr = str.split(" ");
            String cmd = arr[0].toLowerCase();
            main.execute(cmd, arr);
        }

    }

    public void execute(String cmd, String[] arr) {
        Scanner input = new Scanner(System.in);
        switch (cmd) {
            case "set":
                db.set(arr);
                break;
            case "get":
                db.get(arr);
                break;
            case "update":
                db.update(arr);
                break;
            case "count":
                db.count(arr);
                break;
            case "unset":
                db.unset(arr);
                break;
            case "begin":
                db.backupOn();
                while (true) {
                    String temp = input.nextLine();
                    if (temp.equalsIgnoreCase("rollback") || temp.equalsIgnoreCase("commit")) {
                        if (temp.equals("rollback")) db.rollback();
                        if (temp.equals("commit")) db.backupOff();
                        break;
                    }
                    String[] tempArr = temp.split(" ");
                    if (!tempArr[0].isEmpty()) this.execute(tempArr[0], tempArr);
                }
                break;
            default:
                System.out.println(arr[0] + " is not a right command!");
        }

    }
}