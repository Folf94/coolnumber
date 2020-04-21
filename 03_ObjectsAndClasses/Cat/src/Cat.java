
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double eat;
    private double peePee;

    static int count ;
    private boolean isAlive;

    public static final int EYES_COUNT = 2;
    public static final int MAX_WEIGHT = 9000;
    public static final int MIN_WEIGHT = 1000;

    private String color;

    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }


    public Cat clone() throws CloneNotSupportedException{

        Cat cat = (Cat) super.clone();

        return cat;
    }


    public Cat()
    {
        count++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        isAlive = true;

    }
    public double eated()
    {
        eat = weight - originWeight;
        return eat;

    }

    public Cat(int weight){

        this.weight = weight;
    }
    public void getKitten(int weight){
        this.weight = weight;
    }


    public boolean isAlive() {
        if (weight > minWeight && weight < maxWeight)
            isAlive = true;
        else {
            System.out.println("Кошка мертва");
            isAlive = false;
        }

        return false;
    }

    public int getCount() {

        return count;
    }

    public void pee(){
        if (isAlive ) {
            peePee = originWeight - 100;
            System.out.println("Пора убирать лоток!!!");
        }
        else isAlive();

    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        if (isAlive)
            weight = weight + amount;
        else isAlive();

    }

    public void drink(Double amount)
    {
        if (isAlive)
            weight = weight + amount;
        else isAlive();

    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus(String dead)
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}