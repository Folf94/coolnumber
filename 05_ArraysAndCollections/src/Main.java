public class Main {
    public static void main(String[] args) {
         Lesson5_1.enumirationColors();
         System.out.println("+++++++++++++++++++++++++++++");
         Lesson5_1.countOfTemperature();
        System.out.println("+++++++++++++++++++++++++++++");
         Lesson5_1.drowingX();
    }


    public static class Lesson5_1 {

        public static void enumirationColors() {
            String[] colors = {"Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый"};
            for (String color : colors) {
                System.out.println(color);
            }
            System.out.println("+++++++++++++++++++++++++++++");
            for (int j = colors.length - 1; j >= 0; j--) {
                System.out.println(colors[j]);
            }
        }

        public static void countOfTemperature() {
            int countOfIlness = 0;
            double[] temperature = new double[30];
            for (int i = 0; i < temperature.length; i++) {
                temperature[i] = (Math.random() * ((39.0 - 32.0) + 1)) + 32.0;
                if (36.2 <= temperature[i] && temperature[i] <= 36.9) {
                    countOfIlness++;
                }
            }
            System.out.println("Здоровых пациентов: " + countOfIlness);
            double amount;
            double sum = 0;
            for (double v : temperature) {
                sum += v;
            }
            amount = sum / temperature.length;
            System.out.println("Средняя температура: " + amount);
        }

        public static void drowingX() {
            String[][] x = {
                    {"X   " + "  X"},
                    {" X  " + " X"},
                    {"  X " + "X"},
                    {"   X  "},
                    {"  X " + "X"},
                    {" X  " + " X"},
                    {"X   " + "  X"}
            };
            for (String[] strings : x) {
                for (String string : strings) System.out.println(string);
            }


        }
    }
}