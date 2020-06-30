package networking;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class IPTypeDemo {
    public static void main(String[] args) {
        IPTypeDemo demo = new IPTypeDemo();
        demo.entrypoint();
    }

    private void entrypoint() {
        // 2001:db8:a0b:12f0::1, 10.244.4.60, ::ffff:10.244.4.60
        String ip = "::ffff:10.244.4.60";
        System.out.println(getIpType(ip));
    }

    private String getIpType(String ip) {
        return isIPv4(ip) ? "IPv4" : (isIPv6(ip) ? "IPv6" : "Unknown");
    }

    public static boolean isIPv4(String ipAddress) {
        boolean isIPv4 = false;

        if (ipAddress != null) {
            try {
                InetAddress inetAddress = InetAddress.getByName(ipAddress);
                isIPv4 = (inetAddress instanceof Inet4Address) && inetAddress.getHostAddress().equals(ipAddress);
            } catch (UnknownHostException ex) {
            }
        }

        return isIPv4;
    }

    public static boolean isIPv6(String ipAddress) {
        boolean isIPv6 = false;

        if (ipAddress != null) {
            try {
                InetAddress inetAddress = InetAddress.getByName(ipAddress);
                isIPv6 = (inetAddress instanceof Inet6Address);
            } catch (UnknownHostException ex) {
            }
        }

        return isIPv6;
    }
}
