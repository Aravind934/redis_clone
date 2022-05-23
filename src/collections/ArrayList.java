package collections;

import entity.Data;

public class ArrayList {
    private Data[] arr;
    private int idx = 0;
    private Data[] backup;
    private final int defaultSize = 5;

    public ArrayList() {
        arr = new Data[defaultSize];
    }

    public Boolean add(Data data) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].key.equals(data.key)) {
                arr[i].value = data.value;
                return true;
            }
        }
        if (idx == arr.length) {
            this.expandArraySize();
        }
        arr[idx++] = data;
        return true;
    }

    public Boolean unset(String key) {
        boolean modified = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].key.equals(key)) {
                arr[i].value = null;
                modified = true;
                break;
            }
        }
        return modified;
    }

    public Object get(String key) {
        Object res = null;
        for (Data item : arr) {
            if (item != null && item.key.equals(key)) {
                res = item.value;
            }
        }
        return res;
    }

    public int count(Object value) {
        int count = 0;
        for (Data item : arr) {
            if (item != null && item.value.equals(value)) ++count;
        }
        return count;
    }

    public Boolean update(String key, Object value) {
        Data data = null;
        for (Data item : arr) {
            if (item != null && item.key.equals(key)) data = item;
        }
        if (data == null) return false;
        data.value = value;
        return true;
    }

    public void backupOn() {
        this.backup = new Data[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Data data = arr[i];
            if (data != null) {
                this.backup[i] = new Data(data.key, data.value);
            }
        }
    }

    public void backupOff() {
        this.backup = null;
    }

    public void rollback() {
        if (backup != null && backup.length > 0) {
            this.arr = this.backup.clone();
        }
        this.backupOff();
    }

    private void expandArraySize() {
        Data[] tempArr = new Data[arr.length + defaultSize];
        for (int i = 0; i < arr.length; i++) {
            tempArr[i] = arr[i];
        }
        arr = tempArr;
    }
}
