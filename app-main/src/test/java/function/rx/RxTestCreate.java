package function.rx;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxTestCreate {


    /**
     * 类型：创建
     * 关于fromIterable （遍历数组）& collect（用一个新的数组接）
     * 文档：http://reactivex.io/documentation/operators/from.html
     */
    @Test
    public void testFromIterableAndCollect() {
        List<String> files = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            files.add(String.valueOf(i));
        }
        /*
        接收List<String>
         */
        Observable.fromIterable(files)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        Random random = new Random();
                        int i = Integer.parseInt(s);
                        i = i + random.nextInt(100);
                        //将每个数字随机成Int
                        System.out.println(i);
                        return i;
                    }
                })
                /**
                 * 用一个新的数组去接
                 */
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

    /**
     * 类型：创建
     * 简单遍历数组
     * 文档：http://reactivex.io/documentation/operators/from.html
     */
    @Test
    public void testFromIterable() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Observable.fromIterable(list)
                .map(s -> s + ":")
                .subscribe(s -> System.out.print(s));
        Flowable.fromIterable(list)
                .subscribe(s -> System.out.println(s));
    }


    /**
     * 类型：创建
     * 数组
     */
    @Test
    public void testFromArray() {
        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        Observable.fromArray(array).subscribe(i -> System.out.println(i));
    }

    /**
     * 类型：创建
     */
    @Test
    public void testCallbale() {
        Callable<String> callable = () -> {
            return "Hello World!";
        };

        Observable.fromCallable(callable)
                .subscribe(s -> {
                    System.out.println(s);
                });

        Completable.fromCallable(callable)
                .subscribe(() -> System.out.println("Action!"));
    }

    /**
     * 类型：创建
     */
    @Test
    public void testAction() {
        Action action = () -> System.out.println("Hello World!");

        //Case 1
        Completable.fromAction(action)
                .subscribe(() -> System.out.println("Done"));

        //Case 2
        Maybe.fromAction(action).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("End.");
            }
        });
    }

    /**
     * 类型：创建
     */
    @Test
    public void testRunnable() {
        //Case 1
        Completable.fromRunnable(() -> {
            System.out.println("Hello World!");
        }).subscribe(() -> {
            System.out.println("Done");
        });
        //Case 2
        Maybe.fromRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("End");
            }
        });
    }

    /**
     * 类型：创建
     * 测试Just创建发射体
     * 文档：http://reactivex.io/documentation/operators/just.html
     */
    @Test
    public void testJust() {

        /**
         * Observable
         */
        Observable.just("Hello World.").subscribe(s -> System.out.print(s));

        /**
         * Flowable
         */
        Flowable.just("Hello World.").subscribe(s -> System.out.print(s));

        /**
         * Single
         */
        Single.just("Hello World.").subscribe(s -> System.out.print(s));

        /**
         *
         */
        Maybe.just("Hello World.").subscribe(s -> System.out.println(s));

        /**
         * 直接抛出空指针
         */
        Observable.just(null)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(o);
                    }
                });

    }

    /**
     * 类型：创建
     */
    @Test
    public void testFuture() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<String> future = executor.schedule(() -> "Hello world!", 1, TimeUnit.SECONDS);

        Observable.fromFuture(future)
                .subscribe(item -> System.out.println(item),
                        error -> error.printStackTrace(),
                        () -> System.out.println("Done")
                );
        executor.shutdown();
    }

    /**
     * 类型：创建
     */
    @Test
    public void testFrom() {
//        Flowable.fromPublisher()

//        Observable.fromPublisher()

        Single.fromObservable(Observable.just(1))
                .subscribe(integer -> System.out.println(integer));
    }

    /**
     * 类型：创建
     */
    @Test
    public void testGenerate() {
        int startValue = 1;
        int inCrementValue = 1;


        Flowable.generate(() -> startValue, (BiConsumer<Integer, Emitter<Integer>>) (integer, integerEmitter) -> {
            int nextValue = integer + inCrementValue;
            integerEmitter.onNext(nextValue);
            integerEmitter.onComplete();
        }).subscribe(integer -> System.out.println(integer));
    }

    /**
     * 类型：创建
     * ReactiveX documentation: http://reactivex.io/documentation/operators/create.html
     */
    @Test
    public void testCreate() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            ScheduledFuture<?> future = executor.schedule(() -> {
                emitter.onNext("Hello");
                emitter.onNext("World");
                emitter.onComplete();
            }, 1, TimeUnit.SECONDS);

            emitter.setCancellable(() -> future.cancel(false));
        })
                /**
                 * onNext
                 * onError
                 * onComplete
                 */
                .subscribe(item -> System.out.println(item), error -> error.printStackTrace(), () -> System.out.println("Done"));


        Thread.sleep(2000);
        executor.shutdown();
    }


    /**
     * 类型：创建 (只有订阅后才生效)
     * ReactiveX documentation: http://reactivex.io/documentation/operators/defer.html
     */
    @Test
    public void testDefer() throws InterruptedException {
        //仅仅当订阅的时候，才会执行代码
        Observable<Object> observable = Observable.defer(() -> Observable.just(System.currentTimeMillis()));

        observable.subscribe(time -> System.out.println(time));

        Thread.sleep(1000);

        observable.subscribe(time -> System.out.println(time));
    }

    /**
     * 类型：创建 (在订阅发生之前，不希望执行任何代码)
     * https://www.jianshu.com/p/c83996149f5b
     */
    @Test
    public void testDefer1() {
        /**
         * 测试value数据
         */
        class SomeType {
            private String value;

            public void setValue(String value) {
                this.value = value;
            }

            public Observable<String> valueObservable() {
                //Case 1
                /**
                 * 当调用Observable.just(value)的时候，value的值已经传入，所以null point
                 */
//                return Observable.just(value);

                //Case 2
                /**
                 * defer方式调用，仅仅在订阅的时候才会执行
                 */
                return Observable.defer(() -> Observable.just(value));
            }
        }

        SomeType instance = new SomeType();
        Observable<String> valueObservable = instance.valueObservable();
        instance.setValue("Hello world");
        valueObservable.subscribe(s -> {
            System.out.println(s);
        });

    }

    /**
     * 类型：创建 (Observable & Flowable)
     * ReactiveX documentation: http://reactivex.io/documentation/operators/range.html
     */
    @Test
    public void testRange() {
        String greeting = "Hello World!";
        Observable<Integer> indexes = Observable.range(0, greeting.length());

        Observable<Character> characterObservable = indexes.map(index -> greeting.charAt(index));
        characterObservable.subscribe(character -> System.out.println(character), error -> error.printStackTrace(), () -> System.out.println("Done"));
    }

    /**
     * 类型：创建 (Observable & Flowable)
     * ReactiveX documentation: http://reactivex.io/documentation/operators/interval.html
     */
    @Test
    public void testInterval() throws InterruptedException {
        Observable<Long> clock = Observable.interval(1, TimeUnit.SECONDS);

//        clock.blockingSubscribe(time -> {
//            /**
//             * 启动了一个新的线程进行无限计时器
//             */
//            System.out.println(Thread.currentThread().getName());
//            if (time % 2 == 0) {
//                System.out.println("Tick");
//            } else {
//                System.out.println("Tock");
//            }
//        });

        /**
         * subscribe 使用
         */
        clock.subscribe(time -> {
            /**
             * 启动了一个新的线程进行无限计时器
             */
            System.out.println(Thread.currentThread().getName());
            if (time % 2 == 0) {
                System.out.println("Tick");
            } else {
                System.out.println("Tock");
            }
        });
        Thread.sleep(10 * 1000);
    }

    /**
     * 类型：创建
     * ReactiveX documentation: http://reactivex.io/documentation/operators/timer.html
     */
    @Test
    public void testTimer() throws InterruptedException {
        Observable<Long> eggTimer = Observable.timer(5, TimeUnit.SECONDS);

        eggTimer.blockingSubscribe(v -> System.out.println("Egg is ready!"));

//        eggTimer.subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                System.out.println("Egg is ready!");
//            }
//        });
//        Thread.sleep(6*1000);
    }


    /**
     * 类型：创建
     * ReactiveX documentation: http://reactivex.io/documentation/operators/empty-never-throw.html
     */
    @Test
    public void testNever() {
        Observable<String> never = Observable.never();

        never.subscribe(
                v -> System.out.println("This should never be printed!"),
                error -> System.out.println("Or this!"),
                () -> System.out.println("This neither!"));
    }

    /**
     * 类型：创建
     * ReactiveX documentation: http://reactivex.io/documentation/operators/empty-never-throw.html
     */
    @Test
    public void testEmpty() {
        Observable<String> empty = Observable.empty();

        empty.subscribe(
                v -> System.out.println("This should never be printed!"),
                error -> System.out.println("Or this!"),
                () -> System.out.println("Done will be printed."));
    }

    /**
     * 类型：创建
     * ReactiveX documentation: http://reactivex.io/documentation/operators/empty-never-throw.html
     */
    @Test
    public void testError() {
        Observable<String> error = Observable.error(new IOException());
        error.subscribe(
                v -> System.out.println("This should never be printed!"),
                e -> e.printStackTrace(),
                () -> System.out.println("This neither!"));
    }

    /**
     * 类型：创建
     * ReactiveX documentation: http://reactivex.io/documentation/operators/empty-never-throw.html
     */
    @Test
    public void testError2() {
        Observable<String> observable = Observable.fromCallable(() -> {
            if (Math.random() < 0.5) {
                throw new IOException();
            }
            throw new IllegalArgumentException();
        });

        Observable<String> result = observable.onErrorResumeNext(error -> {
            if (error instanceof IllegalArgumentException) {
                /**
                 * 立即返回onComplete
                 */
                return Observable.empty();
            }
            /**
             * 错误抛出
             */
            return Observable.error(error);
        });

        for (int i = 0; i < 10; i++) {
            System.out.println("index:" + i);
            result.subscribe(
                    (String v) -> {
                        System.out.println("This should never be printed!");
                    },
                    (Throwable error) -> {
                        System.out.println("error Throw :" + error);
//                        error.printStackTrace();
                    },
                    () -> {
                        System.out.println("Done");
                    });
        }
    }

    /**
     * 没有subscribe的话，是不会执行emitter中的打印的
     */
    @Test
    public void testCreate2() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            System.out.println("Hello RxJava");
            emitter.onComplete();
        });
    }


}
