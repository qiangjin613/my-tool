package org.qiangjin.tool;

/**
 * 功能: ipv4 与 int 的相互转换
 * 背景:
 *      在数据库中，常使用 varchar(15) 来存储 ip 地址，然而实际上它们只是 32 位无符号整数。
 *      使用 int unsigned 存储 ip 比用 varchar 要好。
 *      MySQL 也提供了 INET_ATON(expr) 和 INET_NTOA(expr) 函数在 int 和 ip 之间进行转换。
 *
 * @author qiang.jin
 * @create 2021/4/2
 */
public class IpConvert {
    /**
     * ip -> int
     * @param ip 合法的ip地址
     * @return ip对应的整数
     */
    public static int convert(String ip) {
        String[] numbers = ip.split("\\.");
        int result = 0;
        for (String number : numbers) {
            result = (result << 8) | Integer.parseInt(number);
        }
        return result;
    }

    /**
     * int -> ip
     * @param ipNum 地址对应的整数
     * @return ip地址
     */
    public static String convert(int ipNum) {
        String[] strNums = new String[4];
        for (int i = 0; i < 4; i++) {
            strNums[3 - i] = (ipNum & 0xFF) + "";
            ipNum >>>= 8; // ">>" 有同样的效果
        }
        return String.join(".", strNums);
    }

    public static void main(String[] args) {
        System.out.println(convert("0.0.0.0")); // output: 0
        System.out.println(convert(0));
        System.out.println("------------------------------"); // output: -1
        System.out.println(convert("255.255.255.255"));
        System.out.println(convert(-1));
        System.out.println("------------------------------");
        System.out.println(convert("192.168.1.10"));
        System.out.println(convert(-1062731510));
    }

}
