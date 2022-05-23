package entity;

public class Data {
    public String key;
    public Object value;
    public Data next;

    public Data(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return this.key + " = " + this.value;
        //" next-> "+this.next;
    }
}
