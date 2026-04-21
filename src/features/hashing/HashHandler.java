package features.hashing;

import java.util.Scanner;

public class HashHandler {
    public static void runHashFeatures(Scanner sc) {
        System.out.println("\n--- FEATURE 3: HASH FUNCTIONS ---");
        System.out.println("1. MD5");
        System.out.println("2. SHA-256");
        System.out.print("Chon thuat toan: ");

        int choice = sc.nextInt();
        sc.nextLine(); // Dọn dẹp bộ đệm sau khi nhập số [cite: 34]

        System.out.print("Nhap chuoi van ban can Bam (Hash): ");
        String input = sc.nextLine();

        switch (choice) {
            case 1:
                // Gọi file MD5 xử lý
                String md5Result = MD5Algorithm.compute(input);
                System.out.println("[+] Ket qua MD5 (Digest): " + md5Result);
                break;
            case 2:
                // Gọi file SHA-256 xử lý
                String shaResult = SHA256Algorithm.compute(input);
                System.out.println("[+] Ket qua SHA-256 (Digest): " + shaResult);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }
}
