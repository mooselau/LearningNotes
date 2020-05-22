package javaself;

public class DateAndTime {
    public static void main(String[] args) {
        DateAndTime demo = new DateAndTime();
        demo.entrypoint();
    }

    private void entrypoint() {
        testZero();
    }

    private void testZero() {
        long subject = -11L;
        long target = 10L;
        int gap = (int) (subject / target);
        //        double gap = (double) (subject / target);
        //        long gap = Math.round((double)(subject / target));
        gap = subject > 0 ? ++gap : --gap;
        System.out.println("Gap: " + gap);
        System.out.println("Sign: " + (gap > 0));
    }

    private void previousTest() {
        long expiryMillis = 1000L * 3600 * 24 * 7;
        long updatedTime = 1589012677000L;
        long expireDateMillis = expiryMillis + updatedTime;
        long currentTime = System.currentTimeMillis() + 1000L * 3600 * 24 * 8;
        long gap = expireDateMillis - currentTime;
        long oneDayMillis = 1000L * 3600 * 24;
        int days = (int) (gap / oneDayMillis);
        System.out.println(days + " days left");
    }
}
