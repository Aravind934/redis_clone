package collections;

import entity.*;

import java.util.Arrays;

public class HashTable {
    private Data[] arr = new Data[50];

    private Data[] backup;

    public int hashCode(String key) {
        int temp = 0;
        for (int i = 0; i < key.length(); i++) {
            temp = (temp + (int) (key.charAt(i)) + 31) % 50;
        }
        return temp;
    }

    public Boolean add(Data d) {
        int idx = this.hashCode(d.key);
        Data current = arr[idx];
        if (current == null) {
            arr[idx] = d;
            return true;
        }
        while (current.next != null) {
            if (current.key.equals(d.key)) {
                current.value = d.value;
                return true;
            }
            current = current.next;
        }
        if (current.key.equals(d.key)) {
            current.value = d.value;
            return true;
        }
        current.next = d;
        return true;
    }

    public Object get(String key) {
        int idx = this.hashCode(key);
        Data res = null;
        Data current = arr[idx];
        while (true) {
            if (current == null) break;
            if (current.key.equals(key)) {
                res = current;
                break;
            }
        }
        if (res == null) return null;
        return res.value;
    }

    public Boolean unset(String key) {
        boolean res = false;
        int idx = this.hashCode(key);
        Data current = arr[idx];
        while (true) {
            if (current == null) break;
            if (current.key.equals(key)) {
                current.value = null;
                res = true;
                break;
            }
        }
        return res;
    }

    public Boolean update(String key, Object val) {
        boolean res = false;
        Data current = arr[this.hashCode(key)];
        while (true) {
            if (current == null) break;
            if (current.key.equals(key)) {
                current.value = val;
                res = true;
                break;
            }
        }
        return res;
    }

    public int count(Object val) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].value != null && arr[i].value.equals(val)) ++res;
        }
        return res;
    }

    public Data[] backupOn() {
        this.backup = new Data[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Data current = arr[i];
            Data newCurr = null;
            while (true) {
                if (current == null) break;
                if (newCurr == null) newCurr = new Data(current.key, current.value);
                else newCurr.next = new Data(current.key, current.value);
                current = current.next;
            }
            this.backup[i] = newCurr;
        }
        return this.backup;
    }

    @Deprecated
    public void print(Data[] d) {
        if (d == null) d = this.arr;
        for (Data item : d) {
            Data current = item;
            while (current != null) {
                System.out.println(current);
                current = current.next;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public void backupOff() {
        this.backup = null;
    }

    public void rollback() {
        if (this.backup != null && this.backup.length > 0) {
            this.arr = this.backup.clone();
        }
        this.backupOff();
    }
}
