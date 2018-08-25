package com.zzq.rxjavastudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zzq.rxjavastudy.bean.Course;
import com.zzq.rxjavastudy.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private List<Student> mStudents;
    private TextView tvShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShowData = findViewById(R.id.tv_show_data);
        creatAndShow();
    }

    public void createData(View view) {
        createData();
        tvShowData.setText(traverseStudents());
    }

    private void createData() {
        String[] strings = new String[]{"语文", "数学", "英语", "体育", "生物", "化学", "政治", "地理", "历史", "物理"};
        String[] nameStrings = new String[]{"li", "zhang", "peng","lai","yu","lei","zhou","hu","mi"};
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        mStudents = new ArrayList<>();
        List<Course> courses = null;
        for (int i = 0; i < nameStrings.length; i++) {
            courses = new ArrayList<>();
            integers.clear();
            int courseNumber = random.nextInt(strings.length - 1) + 1;
            for (int m = 0; m < courseNumber; m++) {
                int k = random.nextInt(strings.length);
                //如果已经记录该课程，就跳过
                if (!integers.contains(k)) {
                    integers.add(k);
                    courses.add(new Course(10, strings[k], random.nextInt(100 - 30) + 30));
                } else {
                    m--;
                }
            }
            mStudents.add(new Student(nameStrings[i], 1, courses));
        }
    }

    private String traverseStudents() {
        final StringBuilder builder = new StringBuilder();
        for (Student student : mStudents) {
            for (Course course : student.mCourses) {
                builder.append(student.name + " " + course.name + " " + course.score + "\n");
                Log.e("zzq", student.name + " " + course.name + " " + course.score);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    int index = 0;

    private void creatAndShow() {
        index = 0;
        createData();
        /*Observable
                .fromIterable(mStudents)
                .flatMap(new Function<Student, ObservableSource<Course>>() {
                    @Override
                    public ObservableSource<Course> apply(Student student) throws Exception {
                        Log.e("zzq", student.name+ "  " + student.mCourses.size());
                        for (Course course : student.mCourses) {
                            Log.e("zzq", "       ******course        "+course.name+ "  " + course.score);
                        }
                        return Observable.fromIterable(student.mCourses);
                    }
                })
                .subscribe(new Consumer<Course>() {
                    @Override
                    public void accept(Course course) throws Exception {
                        Log.e("zzq", (index++) + "  " + course.name+ "  " + course.score);

                    }
                });*/

        //探究RxJava这几个操作符发射事件的顺序
        Observable.fromIterable(mStudents)
                .subscribe(new Consumer<Student>() {
                    @Override
                    public void accept(Student student) throws Exception {
                        for (Course course : student.mCourses) {
                            Log.e("zzq", student.name+"  "+course.name+ "  " + course.score);
                        }
                    }
                });

        ////首先一旦订阅，通过flatmap根据from将所有传递过来的student包装到另一个observable中，
        /// 然后一一发送事件（student）执行return Observable.from(student.getCoursesList());
        //一旦事件发送完毕调用call，一一执行
        //

    }
}
