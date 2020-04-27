
public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double eat;
    private static int count;
    private boolean isAlive;

    public static final int EYES_COUNT = 2;
    public static final int MAX_WEIGHT = 9000;
    public static final int MIN_WEIGHT = 1000;

    private ColorType colorType;

    public ColorType getColorType() {
        return colorType;
    }

    public void setColorType(ColorType colorType) {
        this.colorType = colorType;
    }

    public Cat(Cat other) {
        this.weight = other.weight;
        this.originWeight = other.originWeight;
        this.colorType = other.colorType;
    }

    public Cat copy() {
        return new Cat(this);
    }

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
        isAlive();
    }

    public Double getAmountOfEatenFood() {
        return eat;
    }


    public Cat(int weight) {

        this.weight = weight;
    }

    public void getKitten(int weight) {
        this.weight = weight;
    }

    public void isAlive() {
        if (weight > minWeight && weight < maxWeight)
            isAlive = true;
        if (weight < minWeight || weight > maxWeight) {
            System.out.println("Кошка мертва");
            isAlive = false;
            count--;
        }
    }

    public static int getCount() {
        return Cat.count;
    }

    public void pee() {
        if (isAlive) {
            weight = weight - 100.0;
            System.out.println("Пора убирать лоток!!!");
        } else isAlive();

    }

    public void meow() {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount) {
        if (isAlive)
            weight = weight + amount;
        else isAlive();
        eat += amount;
    }

    public void drink(Double amount) {
        if (isAlive)
            weight = weight + amount;
        else isAlive();

    }

    public Double getWeight() {

        return weight;
    }

    public String getStatus() {
        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

}