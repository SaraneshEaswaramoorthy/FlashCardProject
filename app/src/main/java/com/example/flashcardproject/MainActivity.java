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
public class MainActivity extends AppCompatActivity implements FlashCardAdapter.OnFlashCardInteractionListener {

    private ArrayList<FlashCard> flashCardList;
    private FlashCardAdapter flashCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and Adapter
        RecyclerView recyclerView = findViewById(R.id.flashCardRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        flashCardList = new ArrayList<>();
        flashCardAdapter = new FlashCardAdapter(flashCardList, this);
        recyclerView.setAdapter(flashCardAdapter);

        // Add Flash Card Button
        FloatingActionButton fabAdd = findViewById(R.id.fabAddFlashCard);
        fabAdd.setOnClickListener(v -> addNewFlashCard());
    }

    private void addNewFlashCard() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New FlashCard");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_flashcard, null);
        EditText inputQuestion = dialogView.findViewById(R.id.inputQuestion);
        EditText inputAnswer = dialogView.findViewById(R.id.inputAnswer);

        builder.setView(dialogView);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String question = inputQuestion.getText().toString();
            String answer = inputAnswer.getText().toString();
            if (!question.isEmpty() && !answer.isEmpty()) {
                FlashCard newFlashCard = new FlashCard(question, answer);
                flashCardList.add(newFlashCard);
                flashCardAdapter.notifyItemInserted(flashCardList.size() - 1);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void onEdit(int position) {
        // Handle editing the flashcard
    }

    @Override
    public void onDelete(int position) {
        // Handle deleting the flashcard
    }

    @Override
    public void onFlashCardClick(int position) {
        // Handle flashcard click (e.g., flip animation)
    }

    @Override
    public void onFullScreen(int position) {
        // Open a full-screen view of the flashcard
        FlashCard flashCard = flashCardList.get(position);
        Intent intent = new Intent(MainActivity.this, FullScreenActivity.class);
        intent.putExtra("question", flashCard.getQuestion());
        intent.putExtra("answer", flashCard.getAnswer());
        startActivity(intent);
    }
}
