package com.example.iyengara18.leagueofstatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends AppCompatActivity {
    private CourseAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
    }

    private void updateUI(){
        List<MatchHistoryInfo> matches = new ArrayList<>();
        matches.add(new MatchHistoryInfo("Victory", "Fiddlesticks", 10));
        mAdapter = new CourseAdapter((List)matches);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CourseHolder extends RecyclerView.ViewHolder{
        private TextView mCourseTitleTextView;
        private TextView mCourseInstructorTextView;
        private ImageView mCourseImageView;
        private Course mCourse;

        public CourseHolder(View itemView){
            super(itemView);
            mCourseTitleTextView = (TextView) itemView.findViewById(R.id.textViewCourseTitle);
            mCourseInstructorTextView = (TextView) itemView.findViewById(R.id.textViewCourseInstructor);
            mCourseImageView = (ImageView) itemView.findViewById(R.id.imageViewCourseImage);
        }

        public void bindCourse(Course course){
            mCourse = course;
            mCourseTitleTextView.setText(mCourse.getCourseTitle());
            mCourseInstructorTextView.setText(mCourse.getCourseInstructor());
            if(course.getCourseImageResourceId() > 0){
                mCourseImageView.setImageResource(mCourse.getCourseImageResourceId());
            }
        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{
        private List<Course> mCourses;

        public CourseAdapter(List<Course> courses){
            mCourses = courses;
        }

        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int ViewType){
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.list_item_course, parent, false);
            return new CourseHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position){
            Course course = mCourses.get(position);
            holder.bindCourse(course);
        }

        @Override
        public int getItemCount(){
            return mCourses.size();
        }
    }
}
