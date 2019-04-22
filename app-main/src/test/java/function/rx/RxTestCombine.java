package function.rx;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


/**
 * 摘自
 * https://github.com/ReactiveX/RxJava/wiki/Combining-Observables
 */
public class RxTestCombine {

    /**
     * 加入一个新的数据在开头
     */
    @Test
    public void testStart() {
        Observable.just("Kirk", "Spock", "McCoy")
                .startWith("Kirk")
                .subscribe(s -> {
                    System.out.println(s);
                });
    }

    /**
     * 测试mergeWith
     */
    @Test
    public void testMergeWith() {
        Observable.just(1, 2, 3)
                .mergeWith(Observable.just(4, 5, 6))
                .subscribe(integer -> {
                    System.out.println(integer);
                });
    }

    /**
     * 测试mergeWith
     */
    @Test
    public void testMergeWith2() {
        Observable.fromCallable(() -> "Hello World").mergeWith(Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World 2";
            }
        }))
                .subscribe(s -> {
                    /**
                     * 这里会打印两次
                     */
                    System.out.println(s);
                });
    }

    /**
     * 测试MergeDelay
     */
    @Test
    public void testMergeDelay() {
        Observable<String> error = Observable.error(new IllegalArgumentException());
        Observable<String> just = Observable.just("Four", "Five", "Six");
        Observable.mergeDelayError(error, just)
                .subscribe(s -> {
                    System.out.println(s);
                });

    }


    /**
     * 测试Zip 合并两个字符串
     */
    @Test
    public void testZip() {
        Observable<String> firstName = Observable.just("James", "Jean-Luc", "Benjamin");
        Observable<String> lastName = Observable.just("Kirc", "Picard", "Sisko");
        firstName.zipWith(lastName, (s, s2) -> s + " " + s2).subscribe(s -> System.out.println(s));

    }

    /**
     * 测试zip 合并数字 和字符串
     */
    @Test
    public void testZip2() {
        Observable<String> firstName = Observable.just("James", "Jean-Luc", "Benjamin");
        Observable<Integer> number = Observable.just(1, 2, 3);
        number.zipWith(firstName, (integer, s) -> integer + " " + s).subscribe(s -> System.out.println(s));
    }

    /**
     * 测试zip 数组和单个数据zip的效果
     */
    @Test
    public void testZip3() {
        Observable<String> firstName = Observable.just("James", "Jean-Luc", "Benjamin");
        Observable<String> pass = Observable.fromCallable(() -> "Pass!");

        firstName.zipWith(pass, (s, s2) -> s + " " + s2).subscribe(s -> System.out.println(s));

        //反过来会如何呢？
        pass.zipWith(firstName, (s, s2) -> s + " " + s2).subscribe(s -> System.out.println(s));

        //zip会如何 ( firstName.zipWith效果一样)
        Observable.zip(firstName, pass, (s, s2) -> s + " " + s2).subscribe(s -> System.out.println(s));
    }

    /**
     * 测试Zip异步 会等待最后的数据一起返回
     */
    @Test
    public void testZip4() throws InterruptedException {
        Observable<String> one = Observable.create(emitter -> {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emitter.onNext("result 1");
                emitter.onComplete();
            }).start();
        });

        Observable<String> two = Observable.create(emitter -> {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emitter.onNext("result 2");
                emitter.onComplete();
            }).start();
        });

        Observable.zip(one, two, (s, s2) -> "For:" + s + " For:" + s2).subscribe(s -> System.out.println(s));
        Thread.sleep(2000);
    }


    /**
     * 测试CombileLastest
     */
    @Test
    public void combineLatest() {
        Observable<Long> newRefreshes = Observable.interval(100, TimeUnit.MICROSECONDS);
        Observable<Long> weatherRefreshes = Observable.interval(50, TimeUnit.MICROSECONDS);
        Observable.combineLatest(newRefreshes, weatherRefreshes, (l1, l2) -> "Refreshed news " + l1 + " times and weather " + l2).subscribe(s -> System.out.println(s));
    }


    /**
     * 测试异步效果
     *
     * @throws InterruptedException
     */
    @Test
    public void combineLatest2() throws InterruptedException {
        Observable<String> one = Observable.create(emitter -> {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emitter.onNext("result 1");
                emitter.onComplete();
            }).start();
        });

        Observable<String> two = Observable.create(emitter -> {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emitter.onNext("result 2");
                emitter.onComplete();
            }).start();
        });

        Observable.combineLatest(one, two, (s, s2) -> "For:" + s + " For:" + s2).subscribe(s -> System.out.println(s));

        Thread.sleep(2000);
    }
}


