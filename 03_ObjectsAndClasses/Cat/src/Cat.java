
public class Cat
{
    private static int count;
    private double originWeight;
    private double weight;

    public double getOriginWeight() {
        return originWeight;
    }

    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAmountFood(double amountFood) {
        this.amountFood = amountFood;
    }

    private Color color;
    private boolean isLife;

    private final double MIN_WEIGHT = 1000.0;
    private final double MAX_WEIGHT = 9000.0;
    private final int NUMBER_OF_EYES = 2;
    private double amountFood;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int getCount() {
        return count;
    }


    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        isLife = true;
        color = Color.GRAY;
        count++;
    }

    public Cat(double weight){
        this();
        this.weight=weight;
        originWeight = weight;
    }

    public double getAmountFood() {
        return amountFood;
    }

    public void pee()
    {
        if (isLife) {
            weight -= Math.random() * (int) (weight / 10);
            System.out.println("Meow Meow Meow!");

        }
        checkIsLife();
    }

    public void meow()
    {
        if (isLife) {
            weight = weight - 1;
            System.out.println("Meow");
        }
        checkIsLife();
    }

    public void feed(Double amount)
    {
        if (isLife) {
            amountFood += amount;
            weight = weight + amount;

        }
        checkIsLife();
    }

    public void drink(Double amount)
    {
        if (isLife) {
            amountFood += amount;
            weight = weight + amount;

        }
        checkIsLife();
    }

    private void checkIsLife()
    {
        if (isLife) {
            if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                count--;
                isLife = false;
            }
        }
    }
    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }


    protected Cat copy() {
        Cat cloneCat = new Cat();
        cloneCat.setColor(getColor());
        cloneCat.setAmountFood(getAmountFood());
        cloneCat.setWeight(getWeight());
        cloneCat.setOriginWeight(getOriginWeight());
        return cloneCat;
    }
}