import java.util.Scanner;

public class Loader {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);
        System.out.println("++++++++++++++++++++++++++++++++++");
        Text.sumOfDigits(text);
        System.out.println("++++++++++++++++++++++++++++++++++");
        Text.extractFullName();
        System.out.println("++++++++++++++++++++++++++++++++++");
        Text.collatingTelephoneNumber();
        System.out.println("++++++++++++++++++++++++++++++++++");
        Text.splittingText(largeText);
    }

    private static String largeText = "Meet my family. There are five of us – my parents, my elder brother, my baby sister and me.  " +
            "First, meet my mum and dad, Jane and Michael. My mum enjoys reading and my dad enjoys playing " +
            "chess with my brother Ken. My mum is slim and rather tall. She has long red hair and big brown eyes. " +
            "She has a very pleasant smile and a soft voice. My mother is very kind and understanding. We are real friends." +
            " She is a housewife. As she has three children, she is always busy around the house. She takes care of my baby " +
            "sister Meg, who is only three months old. My sister is very small and funny. She sleeps, eats and sometimes cries. " +
            "We all help our mother and let her have a rest in the evening. Then she usually reads a book or just watches TV. " +
            "My father is a doctor. He is tall and handsome. He has short dark hair and gray eyes. He is a very hardworking man. " +
            "He is rather strict with us, but always fair. My elder brother Ken is thirteen, and he is very clever. He is good " +
            "at Maths and always helps me with it, because I can hardly understand all these sums and problems. Ken has red hair" +
            " and brown eyes. My name is Jessica. I am eleven. I have long dark hair and brown eyes. I am not as clever as my brother," +
            " though I try to do my best at school too. I am fond of dancing. Our dancing studio won The Best Dancing Studio 2015 competition " +
            "last month. I am very proud of it. I also like to help my mother with my little sister very much. Our family is very united." +
            " We love each other and always try to spend more time together.";

    public static class Text {

        public static void splittingText(String largeText) {
            String a = largeText.replaceAll("[^а-яА-Я a-zA-Z]", "");
            String b = a.trim();
            String[] c = b.split("[,;:.!?\\s]+");
            for (int i = 0; i <= largeText.length(); i++) {
                System.out.println(c[i]);
            }

        }

        public static void sumOfDigits(String text) {
            String a = text.replaceAll("[^0-9]", " ");
            String[] strArr = a.split("\\s+", 4);
            int b = Integer.parseInt(strArr[1].trim());
            int c = Integer.parseInt(strArr[2].trim());
            int d = Integer.parseInt(strArr[3].trim());
            System.out.println(b + c + d);
        }

        public static void extractFullName() {
            System.out.println("Введите ваше ФИО");
            Scanner scanner = new Scanner(System.in);
            String fio = scanner.nextLine();
            String newFio = fio.trim();
            String space = " ";
            String[] subStr = newFio.split(space, 3);
            System.out.println("Фамилия: " + subStr[0]);
            System.out.println("Имя: " + subStr[1]);
            System.out.println("Отчество: " + subStr[2]);
        }

        public static void collatingTelephoneNumber() {
            System.out.println("Введите ваш номер телефона");
            Scanner scanner = new Scanner(System.in);
            String phone = scanner.nextLine();
            System.out.println(phone.replaceAll("[^0-9]", ""));

        }
    }


}