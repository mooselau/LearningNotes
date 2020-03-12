package powermock;

public class Employee {
    private String fName;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    private static int threshold = 10;
    public static int getThreshold() {
        System.out.println("do something..");
        return threshold;
    }
}
