package function.rx;

import org.junit.Test;
import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class RxTestTransform {

    /**
     * 类型：变换
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
     * 类型：变换
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
     * 类型：变换
     * 测试Zip
     */
    @Test
    public void test3() {
        //字符串
        Single<String> s1 = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("hello");
            }
        });

        //Int
        Single<Integer> s2 = Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(99);
            }
        });

        //Int
        Single<Integer> s3 = Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(99);
            }
        });

        //Case 1
        //String ,Int ,Int -> Object
        Single.zip(s1, s2, s3, new Function3<String, Integer, Integer, Object>() {
            @Override
            public Object apply(String s, Integer integer, Integer integer2) throws Exception {
                System.out.println(s + integer + integer2);
                return new Object();
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });

        //Case2
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

        //Case3
        Single.concat(s2, s3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }


    /**
     * 类型：变换
     * 测试循环效果
     */
    @Test
    public void testLoop() {
        Observable.just("")
                .map(s -> {
                    for (int i = 0; i < 10; i++) {
                        Observable.just(i)
                                .map(i1 -> {
                                    String s1 = "Loop" + i1;
                                    return s1;
                                })
                                .subscribe(s12 -> System.out.println(s12));
                    }

                    return new Object();
                })
                .subscribe(o -> System.out.println("Consumer"));
    }

    /**
     * 类型：变换
     */
    @Test
    public void testFlowableFlatMap() {

        Flowable.just("1")
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(String s) throws Exception {
                        Flowable<String> just = Flowable.just("3");
                        return Flowable.just("2");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
