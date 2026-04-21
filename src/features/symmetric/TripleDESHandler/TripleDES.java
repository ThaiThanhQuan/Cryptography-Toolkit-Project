package features.symmetric.TripleDESHandler;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class TripleDES {
    public static void run3DES(Scanner sc) {
        try {
            System.out.println("=== 3DES ENCRYPTION TOOL ===");
            System.out.print("Nhap van ban can ma hoa: ");
            String plainText = sc.nextLine();

            KeyGenerator keygen = KeyGenerator.getInstance("DESede");
            SecretKey key = keygen.generateKey();
            System.out.println("\n[+] Da tao khoa 3DES thanh cong.");

            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            String encodedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("[+] Ban ma (Base64): " + encodedText);

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encodedText));
            System.out.println("[+] Giai ma thanh cong: " + new String(decryptedBytes));

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}
