
public class Loader
{
    private static Cat getKitten() {
        Cat kitten = new Cat(1100);
        return kitten;

    }
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        System.out.println(cat.getWeight());
        System.out.println(cat1.getWeight());
        System.out.println(cat2.getWeight());
        System.out.println(cat3.getWeight());
        System.out.println(cat4.getWeight());
        System.out.println(cat5.getWeight());

        cat.feed(150.0);
        cat1.feed(50.0);
        System.out.println(cat.getWeight());
        System.out.println(cat1.getWeight());






        for ( int i = 0; i <100 ; i++) {
            cat2.feed(165.0);
        }
        System.out.println(cat2.getStatus(""));


        for ( int i = 0; i <100000 ; i++) {
            cat2.meow();
        }

        System.out.println(cat2.getStatus(""));



        cat.feed(150.0);
        //cat.feed(10.0);
        cat.eated();
        cat.pee();
        cat.pee();
        cat.pee();
        cat.pee();
        System.out.println("Кошка съела "+ cat.eated() + " грамм");

        // System.out.println(cat1.getCount());

        System.out.println( cat.getWeight());
        cat.feed(10000.0);
        cat.isAlive();
        // System.out.println( cat.getWeight());
        cat.feed(150.0);
        cat.feed(10.0);
        cat.pee();

        Cat kitten = new Cat();
        kitten.getKitten(1100);
        System.out.println(kitten.getWeight());

    }
}