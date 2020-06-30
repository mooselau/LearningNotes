package javaself;

import lombok.Data;


public class OtherTester {
    public static void main(String[] args) {
        OtherTester tester = new OtherTester();
        tester.entrypoint();
    }
    public void entrypoint() {
        Condition conditionA = null;

        if (conditionA != null && conditionA.getValue() != null) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    @Data
    private class Condition {
        private String value = "";
    }
}
