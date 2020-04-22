package core;

public class Car
{
    // объявление переменных: number, height, weight, hasVehicle, isSpecial
    public String number;
    public int height;
    public double weight;
    public boolean hasVehicle;
    public boolean isSpecial;

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber(String number){
        return number;
    }

    public void setHeight (int height){
        this.height = height;
    }

    public int getHeight (int height){
        return height;
    }

    public void setWeight (double weight){
        this.weight = weight;
    }
    public double getWeight (double weight){
        return weight;
    }

    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean isHasVehicle() {
        return hasVehicle;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public String toString()
    {
        //объявление переменной special
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
                special + "Автомобиль с номером " + number +
                ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}