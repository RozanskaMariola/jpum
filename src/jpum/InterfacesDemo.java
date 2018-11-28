package jpum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Mobile {
    default void goTo(int x, int y) { System.out.println("Mobile::goTo"); }
}

interface Flyable extends Mobile {
    default void flyTo(int x, int y) {
        System.out.println("Flyable::flyTo");
        //goTo(x, y);
    }
}

interface Sailable extends Mobile {
    default void sailTo(int x, int y) {
        System.out.println("Sailable::sailTo");
        //goTo(x, y);
    }
}

class Person implements Mobile {
    private long id;
    private int age;
    private String givenName;
    private String surname;

    public Person(long id, String givenName, String surname, int age) {
        this.id = id;
        this.age = age;
        this.givenName = givenName;
        this.surname = surname;
    }

//    @Override
//    public void goTo(int x, int y) { System.out.println("Person::goTo"); }
}

class PeterPan extends Person implements Flyable {
    private static final PeterPan INSTANCE = new PeterPan();

    private PeterPan() { super(0, "Peter", "Pan", 13); }

    public static PeterPan getInstance() { return INSTANCE; }

//    @Override
//    public void goTo(int x, int y) { System.out.println("PeterPan::goTo"); }
//
//    @Override
//    public void flyTo(int x, int y) { System.out.println("PeterPan::flyTo"); }
}

abstract class Vehicle implements Mobile {}

class Car extends Vehicle {
//    @Override
//    public void goTo(int x, int y) { System.out.println("Car::goTo"); }
}

class Ship extends Vehicle implements Sailable {
//    @Override
//    public void sailTo(int x, int y) { System.out.println("Ship::sailTo"); }
//
//    @Override
//    public void goTo(int x, int y) { System.out.println("Ship::goTo"); }
}

class Spacecraft extends Vehicle implements Flyable {
//    @Override
//    public void flyTo(int x, int y) { System.out.println("Spacecraft::flyTo"); }
//
//    @Override
//    public void goTo(int x, int y) { System.out.println("Spacecraft::goTo"); }
}

class Seaplane extends Vehicle implements Flyable, Sailable {

//    @Override
//    public void sailTo(int x, int y) { System.out.println("Seaplane::sailTo"); }
//
//    @Override
//    public void flyTo(int x, int y) { System.out.println("Seaplane::flyTo"); }
//
//    @Override
//    public void goTo(int x, int y) { System.out.println("Seaplane::goTo"); }
}

public class InterfacesDemo {
    private static void moveAllTo(int x, int y, List<Mobile> mobiles) {
        for (Mobile m : mobiles) { m.goTo(x, y); }
    }

    private static void flyAllTo(int x, int y, List<Flyable> flyables) {
        for (Flyable f: flyables) { f.flyTo(x, y); }
    }

    private static void sailAllTo(int x, int y, List<Sailable> sailables) {
        for (Sailable s: sailables) { s.sailTo(x, y); }
    }

    private static void moveSubMobilesTo(int x, int y, List<? extends Mobile> subMobiles)  {
        for (Mobile m : subMobiles) { m.goTo(x, y); }
    }

    private static void addFlyable(List<? super Flyable> flyables, Flyable f) { flyables.add(f); }

    public static void main(String[] args) {
        Car car = new Car();
        Person johnKowalsky = new Person(12345678, "John", "Kowalsky", 31);

        Ship ship = new Ship();
        Spacecraft spacecraft = new Spacecraft();

        Seaplane seaPlane = new Seaplane();
        PeterPan peterPan = PeterPan.getInstance();

        List<Mobile> mobiles = new ArrayList<>(Arrays.asList(
                car,
                johnKowalsky,
                ship,
                spacecraft,
                seaPlane,
                peterPan));

        System.out.println("1) moveAllTo(10, 10, mobiles):");
        moveAllTo(10, 10, mobiles);

        List<Flyable> flyables = new ArrayList<>(Arrays.asList(
                spacecraft,
                seaPlane,
                peterPan));

        System.out.println("\n2) flyAllTo(10, 10, flyables):");
        flyAllTo(10, 10, flyables);

        List<Sailable> sailables = new ArrayList<>(Arrays.asList(
                ship,
                seaPlane));

        System.out.println("\n3) sailAllTo(10, 10, sailables):");
        sailAllTo(10, 10, sailables);

//        moveAllTo(1, 1, sailables);
//        moveAllTo(1, 1, flyables);

        System.out.println("\n4) moveSubMobilesTo(30,40, sailables):");
        moveSubMobilesTo(30,40, sailables);

        System.out.println("\n5) moveSubMobilesTo(30,40, flyables):");
        moveSubMobilesTo(30,40, flyables);

        // or

        System.out.println("\n6) List<? extends Mobile> subMobiles = sailables, for (...) { m.goTo(1, 2); }:");
        List<? extends Mobile> subMobiles = sailables;
        for (Mobile m : sailables) { m.goTo(1, 2); }

        addFlyable(mobiles, new Seaplane());
        addFlyable(mobiles, new Spacecraft());
        addFlyable(mobiles, PeterPan.getInstance());

        System.out.println("\n7) moveAllTo(10,10, mobiles):");
        moveAllTo(10,10, mobiles);

        // or

        List<? super Flyable> supFlyables = mobiles;
        supFlyables.add(new Seaplane());
        supFlyables.add(new Spacecraft());
        supFlyables.add(PeterPan.getInstance());
        
        System.out.println("\n8) moveAllTo(10,10, (List<Mobile>)supFlyables):");
        moveAllTo(10,10, (List<Mobile>)supFlyables);
    }
}