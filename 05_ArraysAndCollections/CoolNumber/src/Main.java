import java.util.*;

public class Main {
    public static void main(String[] args) {
        Numbers.generationOfNumbers();
    }

    public static class Numbers {

        public static void generationOfNumbers() {
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
                        System.out.printf("%s%03d%s%02d\n", s, j, s + s, regions);
                    }
                    for (int j = 111; j <= 999; j += 111) {
                        System.out.printf("%s%03d%s%02d\n", s, j, s + s, regions);
                    }
                }
                for (int i = 111; i <= 999; i += 111) {
                    for (String s : alphobet) {
                        for (String value : alphobet) {
                            for (String item : alphobet) {
                                String autoNumbers = String.format("%s%03d%s%02d\n", s, i, value + item, regions);
                                numbers.add(autoNumbers);
                                System.out.printf("%s%03d%s%02d\n", s, i, value + item, regions);
                            }
                        }
                    }
                }
            }
            for (String s : numbers) {
                System.out.print(s + " ");
            }
            for (; ; ) {
                TreeSet<String> treeSet = new TreeSet<>();
                treeSet.addAll(numbers);
                HashSet<String> hashSet = new HashSet<>();
                hashSet.addAll(numbers);
                System.out.println("Введите номер автомобиля");
                Scanner scanner = new Scanner(System.in);
                String autoNumber = scanner.nextLine();

                long start = System.nanoTime();
                boolean contains = numbers.contains(autoNumber);
                long finish = System.nanoTime();
                if (contains)
                    System.out.println("Поиск с перебором: " + "номер не найден, " + "поиск занял " + (finish - start) + "нс");
                else
                    System.out.println("Поиск с перебором: " + "номер  найден,  " + "поиск занял " + (finish - start) + "нс");
                long start1 = System.nanoTime();
                int contans2 = Collections.binarySearch(numbers, autoNumber);
                long finish1 = System.nanoTime();
                if (contans2 % 0 != 1)
                    System.out.println("Поиск с перебором: " + "номер не найден, " + "поиск занял " + (finish1 - start1) + "нс");
                if (contans2 % 0 == 1)
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
}