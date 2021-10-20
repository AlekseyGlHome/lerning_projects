
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        cat3.feed(100.);
        cat3.drink(150.);
        System.out.println(cat3.getStatus());
        System.out.println(cat3.getWeight());

        System.out.println("Вес кошки № 1 "+cat.getWeight());
        System.out.println("Вес кошки № 2 "+cat1.getWeight());
        System.out.println("Вес кошки № 3 "+cat2.getWeight());
        System.out.println("Вес кошки № 4 "+cat3.getWeight());
        System.out.println("Вес кошки № 5 "+cat4.getWeight());
        System.out.println("Вес кошки № 6 "+cat5.getWeight());

        cat.feed(100.);
        cat1.feed(200.);
        System.out.println("Вес кошки № 1 "+cat.getWeight());
        System.out.println("Вес кошки № 2 "+cat1.getWeight());

        System.out.println("Количество кошек "+Cat.getCount());

        while (!cat.getStatus().equals("Exploded")){
            cat.feed(100.);
        }

        System.out.println("Статус кошки № 1 "+cat.getStatus());
        System.out.println("Количество кошек "+Cat.getCount());

        while (!cat1.getStatus().equals("Dead")){
            cat1.meow();
        }
        System.out.println("Статус кошки № 2 "+cat1.getStatus());

        System.out.println("Количество кошек "+Cat.getCount());

        System.out.println("============================");
        System.out.println("Вес кошки № 5 "+cat5.getWeight());
        cat5.feed(100.);
        cat5.feed(150.);
        System.out.println("Сумма съеденой еды: "+cat5.getAmountFood()+" гр.");
        System.out.println("Вес кошки № 5 "+cat5.getWeight());
        cat5.pee();
        cat5.pee();
        cat5.pee();
        cat5.pee();
        System.out.println("Вес кошки № 5 "+cat5.getWeight());

        Cat tom = getKitten();
        Cat jery = getKitten();
        Cat murka = getKitten();

        Cat newCat = tom.copy();
    }

    private static Cat getKitten(){
        return new Cat(1100.);
    }
}