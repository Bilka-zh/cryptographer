import java.util.Scanner;

public class CaesarCipher {

    // Метод для шифрования сообщения
    public static String encrypt(String message, int shiftKey) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c + shiftKey);
                if ((Character.isUpperCase(c) && shifted > 'Z') || (Character.isLowerCase(c) && shifted > 'z')) {
                    shifted = (char) (c - (26 - shiftKey)); // для обертки алфавита
                }
                encryptedMessage.append(shifted);
            } else {
                encryptedMessage.append(c); // оставляем символы без изменений
            }
        }
        return encryptedMessage.toString();
    }

    // Метод для дешифрования сообщения
    public static String decrypt(String encryptedMessage, int shiftKey) {
        return encrypt(encryptedMessage, -shiftKey); // дешифрование - это просто шифрование с отрицательным сдвигом
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сообщение для шифрования: ");
        String message = scanner.nextLine();

        System.out.print("Введите ключ (целое число для сдвига): ");
        int shiftKey = scanner.nextInt();

        // Шифрование сообщения
        String encryptedMessage = encrypt(message, shiftKey);
        System.out.println("Зашифрованное сообщение: " + encryptedMessage);

        // Дешифрование сообщения
        String decryptedMessage = decrypt(encryptedMessage, shiftKey);
        System.out.println("Дешифрованное сообщение: " + decryptedMessage);

        scanner.close();
    }
}
