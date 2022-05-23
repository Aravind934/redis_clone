package DB;

import collections.HashTable;
import entity.Data;

public class Db {
    private HashTable table = new HashTable();

    public Boolean set(String[] arr) {
        if (arr.length < 3) {
            System.out.println("Parameter wanted!");
            return false;
        }
        table.add(new Data(arr[1], arr[2]));
        return true;
    }

    public Boolean get(String[] arr) {
        if (arr.length < 2) {
            System.out.println("Parameter wanted!");
            return false;
        }
        System.out.println(table.get(arr[1]));
        return true;
    }

    public Boolean update(String[] arr) {
        if (arr.length < 3) {
            System.out.println("Parameter wanted!");
            return false;
        }
        if (!table.update(arr[1], arr[2])) {
            System.out.println("No variable named " + arr[1]);
            return false;
        }
        return true;
    }

    public void count(String[] arr) {
        if (arr.length < 2) {
            System.out.println("Parameter wanted!");
        }
        System.out.println(table.count(arr[1]));
    }

    public void backupOn() {
        table.backupOn();
    }

    public void backupOff() {
        table.backupOff();
    }

    public void rollback() {
        table.rollback();
    }

    public Boolean unset(String[] arr) {
        if (arr.length < 2) {
            System.out.println("Parameter wanted!");
            return false;
        }
        if (!table.unset(arr[1])) {
            System.out.println("No variable named " + arr[1]);
            return false;
        }
        return true;
    }
}
