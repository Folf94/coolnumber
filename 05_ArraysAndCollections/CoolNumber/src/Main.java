import java.util.*;

public class Main {
    public static void main(String[] args) {
        Numbers.countingTime();
    }

    public static class Numbers {
        public ArrayList<String> generateNumbers() {
            ArrayList<String> numbers = new ArrayList<>();
            int[] region = new int[200];
            for (int i = 1; i < 200; i++) {
                region[i] = i;
            }
            String[] alphobet = {"C", "M", "T", "B", "A", "P", "O", "H", "E", "Y", "X", "K"};
            Arrays.sort(alphobet);
            for (int regions : region) {
                for (String s : alphobet) {
                    for (int j = 1; j <= 10; j++) {
                        numbers.add(String.format("%s%03d%s%03d\n", s, j, s + s, regions));
                    }
                    for (int j = 111; j <= 999; j += 111) {
                        numbers.add(String.format("%s%03d%s%03d\n", s, j, s + s, regions));
                    }
                }
                for (int i = 111; i <= 999; i += 111) {
                    for (String s : alphobet) {
                        for (String value : alphobet) {
                            for (String item : alphobet) {
                                numbers.add(String.format("%s%03d%s%03d\n", s, i, value + item, regions));
                            }
                        }
                    }
                }
            }
            return numbers;
        }

        public static void countingTime() {
            Numbers numbers = new Numbers();
            ArrayList<String> myNumbers = numbers.generateNumbers();
            TreeSet<String> treeSet = new TreeSet<>(myNumbers);
            HashSet<String> hashSet = new HashSet<>(myNumbers);
            System.out.println("Введите номер автомобиля");
            Scanner scanner = new Scanner(System.in);
            String autoNumber = scanner.nextLine().trim().toUpperCase();
            long start = System.nanoTime();
            boolean contains = myNumbers.contains(autoNumber);
            long finish = System.nanoTime();
            if (contains)
                System.out.println("Поиск с перебором: " + "номер не найден, " + "поиск занял " + (finish - start) + "нс");
            else
                System.out.println("Поиск с перебором: " + "номер  найден,  " + "поиск занял " + (finish - start) + "нс");
            long start1 = System.nanoTime();
            int contans2 = Collections.binarySearch(myNumbers, autoNumber);
            long finish1 = System.nanoTime();
            if (contans2 < 0)
                System.out.println("Поиск с перебором: " + "номер не найден, " + "поиск занял " + (finish1 - start1) + "нс");
            if (contans2 >= 0)
                System.out.println("Поиск с перебором: " + "номер  найден,  " + "поиск занял " + (finish1 - start1) + "нс");
            long start2 = System.nanoTime();
            boolean hashSetContains = hashSet.contains(autoNumber);
            long finish2 = System.nanoTime();
            if (hashSetContains)
                System.out.println("Поиск с перебором: " + "номер не найден, " + "поиск занял " + (finish2 - start2) + "нс");
            else
                System.out.println("Поиск с перебором: " + "номер найден, " + "поиск занял " + (finish2 - start2) + "нс");
            long start3 = System.nanoTime();
            boolean treesetContains = treeSet.contains(autoNumber);
            long finish3 = System.nanoTime();
            if (treesetContains)
                System.out.println("Поиск с перебором: " + "номер не найден, " + "поиск занял " + (finish3 - start3) + "нс");
            else
                System.out.println("Поиск с перебором: " + "номер найден, " + "поиск занял " + (finish2 - start2) + "нс");
        }
    }
}