package net.ukr.tigor;

public class Cat {

    @Save private String name;
    @Save private int age;
    @Save private String color;
    @Save private boolean isStriped;

    public Cat() {

    }

    public Cat(String name, int age, String color, boolean isStriped) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.isStriped = isStriped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isStriped() {
        return isStriped;
    }

    public void setStriped(boolean striped) {
        isStriped = striped;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color=" + color +
                ", isStriped=" + isStriped +
                '}';
    }
}
