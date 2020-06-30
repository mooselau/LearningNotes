package javaself;

public class EnumTester {
    public static void main(String[] args) {
        EnumTester tester = new EnumTester();
        tester.entrypoint(null);
    }

    private void entrypoint(EType eType) {
        System.out.println(EType.TYPEA.name());
    }

    private enum EType {
        TYPEA(1),
        TYPEB(2),
        ;
        private int value;

        EType(int value) {
            this.value = value;
        }
    }
}
