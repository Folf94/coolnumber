public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;
        char ch = '9';

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        int n = Character.getNumericValue(ch);
        System.out.println(n);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(sumDigits(7843));

    }

    public static Integer sumDigits(Integer number) {
        //@TODO: write code here
        int sum = 0;
        String integerString = number.toString();
        int n = Integer.parseInt(integerString);

        for (; n > 0; n /= 10)
            sum += n % 10;
        return sum;
    }
}
