package features.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Algorithm {
    public static String compute(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            return "Loi thuat toan: " + e.getMessage();
        }
    }

    private static String convertToHex(byte[] data) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : data) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
