public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        char ch = '9';


        int n = Character.getNumericValue(ch);
        System.out.println(n);



    }

    public static Integer sumDigits(Integer number){
        //@TODO: write code here
        String integerString = number.toString();
        Integer n = Integer.parseInt(integerString);
        Integer sum = n;

        return n;
    }
}
