package features.symmetric.DESHandler;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DES {

    public static void runDES(Scanner sc) {
        try {
            System.out.println("=== DES ENCRYPTION TOOL ===");

            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Nhap key (8 ky tu): ");
            String keyStr = sc.nextLine();

            if (keyStr.length() != 8) {
                System.out.println("Key phai dung 8 ky tu!");
                return;
            }

            SecretKey key = new SecretKeySpec(keyStr.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");

            if (choice == 1) {
                System.out.print("Nhap plaintext: ");
                String plainText = sc.nextLine();

                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] encrypted = cipher.doFinal(plainText.getBytes());
                String encoded = Base64.getEncoder().encodeToString(encrypted);

                System.out.println("[+] Ciphertext: " + encoded);

            } else if (choice == 2) {
                System.out.print("Nhap ciphertext: ");
                String cipherText = sc.nextLine();

                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] decoded = Base64.getDecoder().decode(cipherText);
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