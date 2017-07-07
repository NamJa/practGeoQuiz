package com.example.ddori.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.ddori.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.ddori.geoquiz.answer_shown";


    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private Button mBacktoQuestion;
    private TextView buildVersion;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
                                                                        //false 요놈은 만약 extra_answer_is_true
                                                                        //저 키값이 없을때 어떻게 표시할지를 결정하는 인수다.
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);


        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        buildVersion = (TextView)findViewById(R.id.build_version_textview);
        mShowAnswer = (Button)findViewById(R.id.show_answer_button);
        mBacktoQuestion = (Button)findViewById(R.id.back_to_question_button);

        buildVersion.setText("API 레벨 " + Build.VERSION.SDK_INT);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                setAnswerShownResult(true);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mShowAnswer.getWidth() / 2;
                    int cy = mShowAnswer.getHeight() / 2;
                    float radius = mShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswer.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else { mShowAnswer.setVisibility(View.INVISIBLE); }
            }
        });
        //뒤로 가기 버튼을 눌러 ui상에서 직접 activity 스택을 없앤다.
        mBacktoQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
