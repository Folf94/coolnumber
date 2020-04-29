
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        System.out.println(text);

        System.out.println(Integer.parseInt(text.substring(15,19)) + Integer.parseInt(text.substring(35,39)) + Integer.parseInt(text.substring(56,61)));
    }
}