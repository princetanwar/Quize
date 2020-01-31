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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private TextView question, noIndicater;
    private FloatingActionButton bookmarkbt;
    private LinearLayout optionsContainer;
    private Button share, next;
    private int count = 0;
    private List<QusetionModel> list;
    private int position;
    private int score = 0;
    private String category;
    private int setNo;


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

        category = getIntent().getStringExtra("category");
        setNo = getIntent().getIntExtra("setsNo ", 1);


        list = new ArrayList<>();

        myRef.child("SETS").child(category).child("questions").orderByChild("setNO").equalTo(setNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    list.add(snapshot.getValue(QusetionModel.class));
                }

                if (list.size() > 0) {
                    for (int i = 0; i < 4; i++) {
                        optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkAnswer((Button) view);
                            }
                        });
                    }
                    noIndicater.setText(position + "/" + list.size());

                    playanim(question, 0, list.get(position).getQus());
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            next.setEnabled(false);
                            next.setAlpha(0.7f);
                            enableOption(true);
                            position++;
                            if (position == list.size()) {
                                // score activity
                                return;
                            }
                            count = 0;
                            playanim(question, 0, list.get(position).getQus());

                        }
                    });
                }
                else {

                    Toast.makeText(QuestionsActivity.this, "no qusetions now", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuestionsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
                        options = list.get(position).getA();
                    } else if (count == 1) {
                        options = list.get(position).getB();
                    } else if (count == 2) {
                        options = list.get(position).getC();
                    } else if (count == 3) {
                        options = list.get(position).getD();
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
                        noIndicater.setText(position + 1 + "/" + list.size());
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
        if (selectOption.getText().toString().equals(list.get(position).getAns())) {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            score++;
        } else {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption =  optionsContainer.findViewWithTag(list.get(position).getAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }

    }

    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            optionsContainer.getChildAt(i).setEnabled(enable);
            if (enable) {
                optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }
}
