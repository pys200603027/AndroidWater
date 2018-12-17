package function.rx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class RxTest {

    /**
     * 测试 contact
     */
    @Test
    public void test1() {
        Observable.concat(
                Observable.just(1, 2, 3),
                Observable.just(3, 4, 5),
                Observable.just(6, 7, 8)
        ).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    /**
     * 测试 contact & just
     */
    @Test
    public void test2() {
        Single.concat(
                Single.just(1),
                Single.just(2),
                Single.just(3)
        ).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    /**
     * 测试Zip
     */
    @Test
    public void test3() {
        Single<String> s1 = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("hello");
            }
        });

        Single<Integer> s2 = Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(99);
            }
        });

        Single<Integer> s3 = Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(99);
            }
        });

        Single.zip(s1, s2, s3, new Function3<String, Integer, Integer, Object>() {
            @Override
            public Object apply(String s, Integer integer, Integer integer2) throws Exception {
                return new Object();
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });

        Single.zip(s1, s2, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).flatMap(new Function<String, SingleSource<String>>() {
            @Override
            public SingleSource<String> apply(String s) throws Exception {
                return Single.just("final" + s);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    @Test
    public void testFrom() {
        List<String> files = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            files.add(String.valueOf(i));
        }
        Observable.fromIterable(files)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        Random random = new Random();
                        int i = Integer.parseInt(s);
                        i = i + random.nextInt(100);
                        return i;
                    }
                })
                .collect(new Callable<List<Integer>>() {
                    @Override
                    public List<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<List<Integer>, Integer>() {
                    @Override
                    public void accept(List<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                })
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println(integers);
                    }
                });
    }
}
