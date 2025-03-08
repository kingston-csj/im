import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypPwd {

    public void testCost() {
        String password = "$apr1$1001$RY0r9WJwPR8hE/ylZsg6l.";
        // 成本因子为10
        BCryptPasswordEncoder encoder10 = new BCryptPasswordEncoder(10);
        long startTime10 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            encoder10.encode(password);
        }
        long endTime10 = System.currentTimeMillis();
        System.out.println("Cost factor 10, Time taken: " + (endTime10 - startTime10) + " ms");

        BCryptPasswordEncoder encoder12 = new BCryptPasswordEncoder(12);
        long startTime12 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            encoder12.encode(password);
        }
        long endTime12 = System.currentTimeMillis();
        System.out.println("Cost factor 12, Time taken: " + (endTime12 - startTime12) + " ms");
    }

}
