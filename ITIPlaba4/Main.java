import java.io.IOException;
import java.util.logging.*;

class CustomEmptyStackException extends Exception {
    public CustomEmptyStackException(String message) {
        super(message);
    }
}

class CustomStack<T> {
    private int maxSize;
    private T[] stackArray;
    private int top;
    private static final Logger logger = Logger.getLogger(CustomStack.class.getName());     // Логгер

    @SuppressWarnings("unchecked")
    public CustomStack(int size) {
        this.maxSize = size;
        this.stackArray = (T[]) new Object[maxSize];
        this.top = -1;

        try {
            setupLogger();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to set up logger", e);
        }
    }

    // Метод для настройки логгера
    private void setupLogger() throws IOException {
        // Создаем обработчик для записи логов в файл
        FileHandler fileHandler = new FileHandler("stack_exceptions.log", true);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());

        // Добавляем обработчик в логгер
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }

    public void push(T value) {
        if (top == maxSize - 1) {
            throw new StackOverflowError("Стек полон.");
        }
        stackArray[++top] = value;
    }

    public T pop() throws CustomEmptyStackException {
        if (isEmpty()) {
            CustomEmptyStackException ex = new CustomEmptyStackException("Нельзя извлечь элемент: стек пуст.");
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        return stackArray[top--];
    }

    public T peek() throws CustomEmptyStackException {
        if (isEmpty()) {
            CustomEmptyStackException ex = new CustomEmptyStackException("Нельзя проверить элемент: стек пуст.");
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class Main {
    public static void main(String[] args) {
        CustomStack<Integer> stack = new CustomStack<>(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        try {
            System.out.println("Элемент извлечён из стека: " + stack.pop());
            System.out.println("Элемент извлечён из стека: " + stack.pop());
            System.out.println("Элемент извлечён из стека: " + stack.pop());

            stack.pop();

        } catch (CustomEmptyStackException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}

