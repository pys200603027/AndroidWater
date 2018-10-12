package water.android.io.main.ab;

public class ABHandler implements IAbHandler {

    @ABTesting(pro = "uid", op = "left")
    public void runA() {
        System.out.println("open Dialog A");
    }

    @ABTesting(pro = "gentle", op = "out")
    public void runB() {
        System.out.println("start Activity B");
    }

    @ABTesting(pro = "city", op = "in")
    public void runC() {
        System.out.println("run Logic C");
    }

}
