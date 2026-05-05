import java.util.Scanner;
import java.security.KeyPair;
import features.asymmetric.RSAHandler;
import features.hashing.HashHandler;
import features.symmetric.AESHandler.AESHandler;
import features.symmetric.DESHandler.DES;
import features.symmetric.TripleDESHandler.TripleDES;

public class Main {   
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("\n==============================");
        System.out.println("===  CRYPTOGRAPHY TOOLKIT  ===");
        System.out.println("==============================");
        System.out.println("1. Symmetric: DES");
        System.out.println("2. Symmetric: 3DES (Triple DES)");
        System.out.println("3. Symmetric: AES");
        System.out.println("4. Asymmetric: RSA");
        System.out.println("5. Hash: MD5 OR SHA-256 Check");
        System.out.println("0. Exit");
        System.out.print("Select feature: ");

        // Kiểm tra đầu vào có phải số không để tránh crash
        if (!sc.hasNextInt()) {
            System.out.println("Loi: Vui long nhap mot con so!");
            sc.next(); // Đọc bỏ giá trị sai
            continue;
        }

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("\n--> Ban chon DES");
                DES.runDES(sc);
                break;
            case 2:
                System.out.println("\n--> Ban chon 3DES (Triple DES)");
                TripleDES.run3DES(sc);
                break;
            case 3:
                System.out.println("\n--> Ban chon AES");
                AESHandler aesHandler = new AESHandler();
                aesHandler.execute(sc);
                break;
            case 4:
                System.out.println("\n--> Ban chon RSA");
                try {
                    // Tạo key
                    KeyPair keyPair = RSAHandler.generateKeyPair();

                    // Hiển thị khóa
                    System.out.println("===== RSA Key Pair =====");
                    System.out.println("Public Key: " + keyPair.getPublic());
                    System.out.println("Private Key: " + keyPair.getPrivate());
                    System.out.println("========================");

                    // Nhập dữ liệu
                    System.out.print("Nhập plaintext: ");
                    String plainText = sc.nextLine();

                    // Mã hóa
                    String encrypted = RSAHandler.encrypt(plainText, keyPair.getPublic());
                    System.out.println("Encrypted: " + encrypted);

                    // Giải mã
                    String decrypted = RSAHandler.decrypt(encrypted, keyPair.getPrivate());
                    System.out.println("Decrypted: " + decrypted);
                } catch (Exception e) {
                    System.out.println("Lỗi khi thực hiện RSA: " + e.getMessage());
                    // e.printStackTrace(); // Uncomment this line if you need to debug specific errors
                }
                break;
            case 5:
                System.out.println("\n--> Ban chon Feature 3: Hash Functions");
                HashHandler.runHashFeatures(sc);
                break;
            case 0:
                System.out.println("Exiting program...");
                sc.close();
                System.exit(0);
            default:
                System.out.println("Lua chon khong ton tai!");
            }
        }
    }
}