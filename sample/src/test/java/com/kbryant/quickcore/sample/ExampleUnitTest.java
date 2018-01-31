package com.kbryant.quickcore.sample;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void rxJavaTestObservableSimple() throws Exception {
        Observable.fromArray("a","b","c","d","e","f").subscribe(getSimpleSubscribe());
    }

    @Test
    public void rxJavaTestObservableCreate() throws Exception{
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for(int i=0;i<30;i+=5){
                    if(i>20){
                        e.onError(new Exception());
                    }
                    e.onNext(i);
                }
                e.onComplete();
            }
        }).subscribe(getSimpleSubscribe());
    }

    private <T> Observer<T> getSimpleSubscribe(){
        return new Observer<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull T t) {
                System.out.println("onNext?" + String.valueOf(t));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
    }
}