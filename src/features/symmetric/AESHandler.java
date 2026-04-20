package features.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class AESHandler {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    // Hàm chính để Main.java gọi
    public void execute(Scanner scanner) {
        System.out.println("\n--- AES ENCRYPTION/DECRYPTION ---");
        System.out.println("1. Encrypt text");
        System.out.println("2. Decrypt text");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            if (choice == 1) {
                System.out.print("Enter plaintext to encrypt: ");
                String plaintext = scanner.nextLine();

                System.out.print("Enter a secret key (or press Enter to auto-generate): ");
                String keyStr = scanner.nextLine();

                SecretKey key;
                if (keyStr.isEmpty()) {
                    key = generateRandomKey();
                    System.out.println("[!] Auto-generated Key (Base64): " + Base64.getEncoder().encodeToString(key.getEncoded()));
                    System.out.println("[!] KEEP THIS KEY SAFE FOR DECRYPTION!");
                } else {
                    key = getKeyFromString(keyStr);
                }

                String ciphertext = encrypt(plaintext, key);
                System.out.println("\n[SUCCESS] Encrypted Ciphertext (Base64):");
                System.out.println(ciphertext);

            } else if (choice == 2) {
                System.out.print("Enter ciphertext (Base64) to decrypt: ");
                String ciphertext = scanner.nextLine();

                System.out.print("Enter your secret key: ");
                String keyStr = scanner.nextLine();

                SecretKey key = keyStr.length() > 30 ? getRawKeyFromBase64(keyStr) : getKeyFromString(keyStr);

                String decryptedText = decrypt(ciphertext, key);
                System.out.println("\n[SUCCESS] Decrypted Plaintext:");
                System.out.println(decryptedText);
            } else {
                System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            System.out.println("\n[ERROR] Operation failed! " + e.getMessage());
            System.out.println("Please check your input or key and try again.");
        }
    }

    private SecretKey generateRandomKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, new SecureRandom()); // Sinh key 256-bit
        return keyGen.generateKey();
    }

    private SecretKey getKeyFromString(String keyStr) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(keyStr.getBytes("UTF-8"));
        return new SecretKeySpec(keyBytes, "AES");
    }

    private SecretKey getRawKeyFromBase64(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    private String encrypt(String plainText, SecretKey key) throws Exception {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));

        byte[] combined = new byte[iv.length + encryptedText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedText, 0, combined, iv.length, encryptedText.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    private String decrypt(String encryptedBase64, SecretKey key) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encryptedBase64);

        byte[] iv = new byte[16];
        System.arraycopy(combined, 0, iv, 0, iv.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] encryptedText = new byte[combined.length - 16];
        System.arraycopy(combined, 16, encryptedText, 0, encryptedText.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] plainText = cipher.doFinal(encryptedText);
        return new String(plainText, "UTF-8");
    }
}