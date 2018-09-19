package water.android.io.main;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * just for test
 */
public class MyClass {

    static MyClass target = new MyClass();

    public static void main(String[] args) {
        target.test1();
    }

    public void test1() {
        Observable.range(1, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(integer + "");
                    }
                });
    }

}
