public class ArrayAverage {
    public static void main(String[] args) {
        Object[] arr = {1, 2, 3, 4, 5, "shest", 7};
        int sum = 0;
        int count = 0;
        double avg = 0.0;
        try {
            for (int i = 0; i <= arr.length; i++) {
                sum += Integer.parseInt(arr[i].toString());
                count++;
            }
            if (count > 0) {
                avg = (double) sum / count;
                System.out.println("Среднее арифметическое: " + avg);
            } else {
                System.out.println("Массив не содержит числовых элементов.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за границы массива. " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: элемент массива не является числом - " + e.getMessage());
        }
    }
}
