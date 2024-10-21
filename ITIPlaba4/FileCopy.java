import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        Path sourceFile = Paths.get("original.txt");  // исходный файл
        Path destinationFile = Paths.get("parodiya.txt");  // файл назначения
        try {
            Files.copy(sourceFile, destinationFile);
            System.out.println("Файл успешно скопирован!");
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
