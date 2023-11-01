public class Dog extends Animal{

    public static int counter;
    public static int getCounter() {
        return counter;
    }

    public Dog(String name, int maxRun, int maxSwim) {
        super(name, maxRun, maxSwim);
        counter++;
    }
}
