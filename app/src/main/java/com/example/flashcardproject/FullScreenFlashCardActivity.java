package com.example.flashcardproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
public class FullScreenFlashCardActivity extends AppCompatActivity {

    private TextView fullScreenQuestion;
    private TextView fullScreenAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_flash_card);

        fullScreenQuestion = findViewById(R.id.tvFullScreenQuestion);
        fullScreenAnswer = findViewById(R.id.tvFullScreenAnswer);

        // Get the data passed from the MainActivity
        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");

        // Set the data to the TextViews
        fullScreenQuestion.setText(question);
        fullScreenAnswer.setText(answer);
    }
}
