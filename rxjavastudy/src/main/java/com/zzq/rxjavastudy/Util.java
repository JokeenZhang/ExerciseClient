package com.zzq.rxjavastudy;

import com.zzq.rxjavastudy.bean.Course;
import com.zzq.rxjavastudy.bean.Student;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Util {

    private void createAndtraverseStudentsFromRxJava() {
        final StringBuilder builder = new StringBuilder();
        Observable<Student> observable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> e) throws Exception {

            }
        });

        Observable<String> map = observable.subscribeOn(Schedulers.io())

                .doOnNext(new Consumer<Student>() {
                    @Override
                    public void accept(Student student) throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Student, ObservableSource<Course>>() {
                    @Override
                    public ObservableSource<Course> apply(Student student) throws Exception {
                        return Observable.fromIterable(student.mCourses);
                    }
                })
                .map(new Function<Course, String>() {
                    @Override
                    public String apply(Course course) throws Exception {
                        return builder.append(course.name + course.score).toString();
                    }
                });
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

}
