package com.example.flashcardproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FlashCardDetailActivity extends AppCompatActivity {

    private TextView questionTextView, answerTextView;
    private boolean isAnswerVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_detail);

        questionTextView = findViewById(R.id.tvFlashCardDetailQuestion);
        answerTextView = findViewById(R.id.tvFlashCardDetailAnswer);

        // Get the flashcard question and answer from the intent
        String question = getIntent().getStringExtra("QUESTION");
        String answer = getIntent().getStringExtra("ANSWER");

        questionTextView.setText(question);
        answerTextView.setText(answer);

        // Set up click listener to flip between question and answer
        questionTextView.setOnClickListener(v -> flipFlashCard());
        answerTextView.setOnClickListener(v -> flipFlashCard());
    }

    private void flipFlashCard() {
        if (isAnswerVisible) {
            questionTextView.setVisibility(View.VISIBLE);
            answerTextView.setVisibility(View.GONE);
        } else {
            questionTextView.setVisibility(View.GONE);
            answerTextView.setVisibility(View.VISIBLE);
        }
        isAnswerVisible = !isAnswerVisible;
    }
}
