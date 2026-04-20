//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("=== CRYPTOGRAPHY TOOLKIT ===");
        System.out.println("1. Symmetric Encryption (DES, 3DES, AES)");
        System.out.println("2. Asymmetric Encryption (RSA)");
        System.out.println("3. Hash Functions (MD5, SHA-256)");
        System.out.println("0. Exit");
        System.out.print("Select feature group: ");

        // Dùng Scanner để lấy lựa chọn từ người dùng [cite: 34]
        int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("=== SYMMETRIC ENCRYPTION ===");
                        features.symmetric.AESHandler aesHandler = new features.symmetric.AESHandler();
                        aesHandler.execute(sc);
                        break;
                    case 2:
                        System.out.println("2");
                        break;
                    case 3:
                        System.out.println("3");
                        break;
                    case 0:
                        System.exit(0);
                }
    }
}
