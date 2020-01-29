package com.loveinshayari.quize;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private TextView question, noIndicater;
    private FloatingActionButton bookmarkbt;
    private LinearLayout optionsContainer;
    private Button share, next;
    private int count = 0;
    private List<QusetionModel> qusetionModels;
    private int position;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        question = findViewById(R.id.question);
        noIndicater = findViewById(R.id.noIndicaiton);
        bookmarkbt = findViewById(R.id.bookmarkfb);
        optionsContainer = findViewById(R.id.optionsContainer);
        share = findViewById(R.id.share);
        next = findViewById(R.id.next);

        qusetionModels = new ArrayList<>();
        qusetionModels.add(new QusetionModel("Qusetion 1", "A", "B", "C", "D", "B"));
        qusetionModels.add(new QusetionModel("Qusetion 2", "A", "B", "C", "D", "B"));
        qusetionModels.add(new QusetionModel("Qusetion 3", "A", "B", "C", "D", "C"));
        qusetionModels.add(new QusetionModel("Qusetion 4", "A", "B", "C", "D", "D"));
        qusetionModels.add(new QusetionModel("Qusetion 5", "A", "B", "C", "D", "A"));
        qusetionModels.add(new QusetionModel("Qusetion 6", "A", "B", "C", "D", "C"));
        qusetionModels.add(new QusetionModel("Qusetion 7", "A", "B", "C", "D", "D"));

        for (int i = 0; i < 4; i++) {
            optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((Button) view);
                }
            });
        }

        noIndicater.setText(position+"/"+qusetionModels.size());

        playanim(question,0,qusetionModels.get(position).getQusetion());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setEnabled(false);
                next.setAlpha(0.7f);
                enableOption(true);
                position++;
                if (position == qusetionModels.size()){
                    // score activity
                   return;
                }
                count = 0;
                playanim(question, 0, qusetionModels.get(position).getQusetion());

            }
        });

    }

    private void playanim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0 && count < 4) {
                    String options = "";
                    if (count == 0) {
                        options = qusetionModels.get(position).getOptionA();
                    } else if (count == 1) {
                        options = qusetionModels.get(position).getOptionB();
                    } else if (count == 2) {
                        options = qusetionModels.get(position).getOptionC();
                    } else if (count == 3) {
                        options = qusetionModels.get(position).getOptionD();
                    }
                    playanim(optionsContainer.getChildAt(count), 0, options);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        noIndicater.setText(position+1+"/"+qusetionModels.size());
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                }
                view.setTag(data);
                playanim(view, 1, data);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void checkAnswer(Button selectOption) {
        enableOption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if (selectOption.getText().toString().equals(qusetionModels.get(position).getCorrectAns())) {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            score++;
        } else {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption = (Button) optionsContainer.findViewWithTag(qusetionModels.get(position).getCorrectAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }

    }

    private void enableOption(boolean enabled) {
        for (int i = 0; i < 4; i++) {
            optionsContainer.getChildAt(i).setEnabled(enabled);
            if (enabled){
                optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }
}
