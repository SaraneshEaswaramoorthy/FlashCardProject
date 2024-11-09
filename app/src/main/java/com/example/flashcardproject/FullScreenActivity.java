package com.example.flashcardproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FullScreenActivity extends AppCompatActivity {

    private TextView questionTextView;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        questionTextView = findViewById(R.id.tvFullScreenQuestion);
        answerTextView = findViewById(R.id.tvFullScreenAnswer);

        // Get the question and answer from the intent
        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");

        questionTextView.setText(question);
        answerTextView.setText(answer);
    }
}
