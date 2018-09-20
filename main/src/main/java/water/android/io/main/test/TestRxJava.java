package water.android.io.main.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class TestRxJava {

    public void test1() {
        Observable.range(1, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(integer + "");
                    }
                });
    }

    public void test2() {
        Observable o1 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d("o1 finish");
                emitter.onNext("");
            }
        });

        Observable o2 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d("o2 finish");
                emitter.onNext("");
            }
        });

        Observable.zip(o1, o2, new BiFunction<Object, Object, Object>() {
            @Override
            public Object apply(Object o, Object o2) throws Exception {

                Log.d("zip apply");
                return new Object();
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("accept");
            }
        });
    }
}
