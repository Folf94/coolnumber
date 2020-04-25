
public class Loader {
    private static Cat getKitten() {

        Cat kitten = new Cat(1100);
        return kitten;

    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat(cat);
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();
        System.out.println("------------------------------------------------");
        System.out.println(cat.getWeight());
        System.out.println(cat2.getWeight());
        System.out.println("------------------------------------------------");

        System.out.println(cat.getWeight());
        System.out.println(cat1.getWeight());
        System.out.println(cat2.getWeight());
        System.out.println(cat3.getWeight());
        System.out.println(cat4.getWeight());
        System.out.println(cat5.getWeight());

        System.out.println("------------------------------------------------");

        cat.feed(150.0);
        cat1.feed(50.0);
        System.out.println(cat.getWeight());
        System.out.println(cat1.getWeight());


        System.out.println("------------------------------------------------");


        while (cat2.getWeight() < Cat.MAX_WEIGHT) {
            cat2.feed(165.0);
        }
        System.out.println(cat2.getStatus(""));


        while (cat3.getWeight() > Cat.MIN_WEIGHT) {
            cat3.meow();
        }

        System.out.println(cat3.getStatus(""));

        System.out.println("------------------------------------------------");









       // System.out.println("Кошка съела " + cat.getAmountOfEatenFood() " грамм");



       // System.out.println(cat.getWeight());

       // cat.isAlive();
        // System.out.println( cat.getWeight());
      // cat.feed(150.0);
        //cat.feed(10.0);
       // cat.pee();

        Cat kitten = new Cat();
        kitten.getKitten(1100);
        System.out.println(kitten.getWeight());

        cat.feed(100.0);
        cat.feed(100.0);
        cat.feed(100.0);

        cat2.feed(10.0);
        cat.feed(100.0);
        cat2.feed(100.0);
        System.out.println("------------------------------------------------");
        cat.isAlive();
        cat2.isAlive();
        System.out.println("------------------------------------------------");
        System.out.println(Cat.getCount());













    }
}