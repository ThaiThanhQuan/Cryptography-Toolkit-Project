import java.security.MessageDigest;
import java.util.Scanner;

public class Crypto_MD5_Collision {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("=== KHOI TAO KIEM TRA VA CHAM MD5 ===");
            
            System.out.print("Nhap chuoi (van ban) thu 1: ");
            String str1 = sc.nextLine();
            
            System.out.print("Nhap chuoi (van ban) thu 2: ");
            String str2 = sc.nextLine();
            
            // Tinh toan ma bam MD5 cho chuoi 1
            String hash1 = getMD5Hash(str1);
            System.out.println("\n[+] Ma bam MD5 cua chuoi 1: " + hash1);
            
            // Tinh toan ma bam MD5 cho chuoi 2
            String hash2 = getMD5Hash(str2);
            System.out.println("[+] Ma bam MD5 cua chuoi 2: " + hash2);
            
            // Kiem tra va cham
            System.out.println("\n=== KET QUA SO SANH ===");
            if (!str1.equals(str2) && hash1.equals(hash2)) {
                System.out.println("[!] NGUY HIEM - PHAT HIEN VA CHAM: 2 chuoi khac nhau nhung Hash MD5 giong nhau!");
            } else if (str1.equals(str2)) {
                System.out.println("[-] 2 chuoi ban nhap giong het nhau nen Hash giong nhau (Day khong phai la va cham).");
            } else {
                System.out.println("[+] AN TOAN: Khong co va cham. 2 chuoi khac nhau tao ra Hash khac nhau.");
            }
            
        } catch (Exception e) {
            System.out.println("Loi xay ra: " + e.getMessage());
        }
    }

    // Ham ho tro: Chuyen van ban thanh ma MD5 (dinh dang Hex)
    public static String getMD5Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        
        // Chuyen byte sang chuoi Hex de de doc
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}