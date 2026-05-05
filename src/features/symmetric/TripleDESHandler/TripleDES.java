package features.symmetric.TripleDESHandler;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class TripleDES {

    public static void run3DES(Scanner sc) {
        try {
            System.out.println("=== 3DES TOOL ===");

            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Nhap key (24 ky tu): ");
            String keyStr = sc.nextLine();

            if (keyStr.length() != 24) {
                System.out.println("Key phai 24 ky tu!");
                return;
            }

            SecretKey key = new SecretKeySpec(keyStr.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");

            if (choice == 1) {
                System.out.print("Nhap plaintext: ");
                String text = sc.nextLine();

                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] encrypted = cipher.doFinal(text.getBytes());
                String encoded = Base64.getEncoder().encodeToString(encrypted);

                System.out.println("[+] Ciphertext: " + encoded);

            } else if (choice == 2) {
                System.out.print("Nhap ciphertext: ");
                String text = sc.nextLine();

                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] decoded = Base64.getDecoder().decode(text);
                byte[] decrypted = cipher.doFinal(decoded);

                System.out.println("[+] Plaintext: " + new String(decrypted));

            } else {
                System.out.println("Lua chon khong hop le!");
            }

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}