import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGenerator {

    private static final int BATCH_SIZE = 1000;

    public static List<String> generateCombinations(char[] characters, int length) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsRecursive(characters, length, "", combinations);
        return combinations;
    }

    private static void generateCombinationsRecursive(char[] characters, int length, String prefix, List<String> combinations) {
        if (length == 0) {
            combinations.add(prefix);
            return;
        }
        for (char c : characters) {
            String newPrefix = prefix + c;
            generateCombinationsRecursive(characters, length - 1, newPrefix, combinations);
        }
    }

    public static Map<String, Integer> generatePasswordDictionary(boolean includeNumbers, boolean includeSpecialChars, int minLength, int maxLength, char[] userChars) {
        Map<String, Integer> passwordDictionary = new HashMap<>();

        List<Character> characterList = new ArrayList<>();
        if (includeNumbers) {
            for (char c = '0'; c <= '9'; c++) {
                characterList.add(c);
            }
        }
        for (char c = 'a'; c <= 'z'; c++) {
            characterList.add(c);
        }
        if (includeSpecialChars) {
            characterList.add('@');
            characterList.add('#');
            characterList.add('$');
            characterList.add('%');
            characterList.add('&');
            characterList.add('!');
        }

        char[] characters = new char[characterList.size()];
        for (int i = 0; i < characterList.size(); i++) {
            characters[i] = characterList.get(i);
        }

        try (FileWriter writer = new FileWriter("passwords.txt")) {
            generatePasswordsRecursive(characters, minLength, maxLength, userChars, writer);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        return passwordDictionary;
    }

    private static void generatePasswordsRecursive(char[] characters, int minLength, int maxLength, char[] userChars, FileWriter writer) throws IOException {
        generatePasswordsRecursive(characters, minLength, maxLength, userChars, "", writer);
    }

    private static void generatePasswordsRecursive(char[] characters, int minLength, int maxLength, char[] userChars, String prefix, FileWriter writer) throws IOException {
        if (minLength > maxLength) {
            return;
        }

        if (minLength == 0) {
            writer.write(prefix + System.lineSeparator());
            writer.flush();  // Guardar la contraseña generada en el archivo
            return;  // Detener la recursión para esta rama
        }

        for (char c : characters) {
            String newPrefix = prefix + c;
            generatePasswordsRecursive(characters, minLength - 1, maxLength, userChars, newPrefix, writer);
        }

        if (userChars != null && userChars.length > 0) {
            for (char c : userChars) {
                String newPrefix = prefix + c;
                generatePasswordsRecursive(characters, minLength - 1, maxLength, userChars, newPrefix, writer);
            }
        }

        if (prefix.isEmpty()) {
            writer.flush();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Incluir números? (s/n): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("¿Incluir caracteres especiales? (s/n): ");
        boolean includeSpecialChars = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("Longitud mínima de la contraseña: ");
        int minLength = scanner.nextInt();

        System.out.print("Longitud máxima de la contraseña: ");
        int maxLength = scanner.nextInt();

        System.out.print("¿Desea agregar caracteres personalizados? (s/n): ");
        boolean customChars = scanner.next().equalsIgnoreCase("s");

        char[] userChars = null;
        if (customChars) {
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese los caracteres personalizados: ");
            String customCharString = scanner.nextLine();
            userChars = customCharString.toCharArray();
        }

        List<String> passwordList = generateCombinations(new char[] {'a', 'b', 'c'}, maxLength);
        int passwordCount = passwordList.size();
        System.out.println("Se generaron " + passwordCount + " contraseñas.");

        scanner.close();
    }
}
