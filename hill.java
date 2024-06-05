import java.util.Scanner;

public class hill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plaintext (3 characters): ");
        String plaintext = scanner.nextLine().toUpperCase();

        // Ensure plaintext length is 3
        if (plaintext.length() != 3) {
            System.out.println("Plaintext must be exactly 3 characters long.");
            return;
        }

        int[][] keyMatrix = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
        int[] plaintextVector = {plaintext.charAt(0) - 'A', plaintext.charAt(1) - 'A', plaintext.charAt(2) - 'A'};

        // Encryption
        int[] ciphertextVector = multiplyMatrix(keyMatrix, plaintextVector);
        String ciphertext = vectorToString(ciphertextVector);
        System.out.println("Cipher text: " + ciphertext);

        // Decryption
        // Inverse of the key matrix
        int[][] inverseKeyMatrix = {{8, 5, 10}, {21, 8, 21}, {21, 12, 8}};
        int[] decipheredVector = multiplyMatrix(inverseKeyMatrix, ciphertextVector);
        String decipheredText = vectorToString(decipheredVector);
        System.out.println("Deciphered text: " + decipheredText);
    }

    // Matrix-vector multiplication
    private static int[] multiplyMatrix(int[][] matrix, int[] vector) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26; // Modulo 26
        }
        return result;
    }

    // Convert integer vector to string
    private static String vectorToString(int[] vector) {
        StringBuilder result = new StringBuilder();
        for (int value : vector) {
            result.append((char) (value + 'A'));
        }
        return result.toString();
    }
}
