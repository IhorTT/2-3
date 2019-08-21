package net.ukr.tigor;

public class Main {
    public static void main(String[] args) {

        Cat cat1 = new Cat("Vasya",4,"white",true);
        Service.serialize(cat1,"data.txt");

        Cat cat2 = new Cat();
        Service.deserialization(cat2,"data.txt");
        System.out.println(cat2);
    }

}
