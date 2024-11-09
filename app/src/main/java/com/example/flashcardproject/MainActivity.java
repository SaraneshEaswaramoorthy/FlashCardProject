package com.example.flashcardproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
        // Create a dialog to add a new flashcard
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New FlashCard");

        // Set up the input fields
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_flashcard, null);
        EditText inputQuestion = dialogView.findViewById(R.id.inputQuestion);
        EditText inputAnswer = dialogView.findViewById(R.id.inputAnswer);

        builder.setView(dialogView);
        builder.setPositiveButton("Add", (dialog, which) -> {
            // Add flashcard to the list and update adapter
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
        // Show a dialog to edit the flashcard
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit FlashCard");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_flashcard, null);
        EditText inputQuestion = dialogView.findViewById(R.id.inputQuestion);
        EditText inputAnswer = dialogView.findViewById(R.id.inputAnswer);

        // Set existing values
        inputQuestion.setText(flashCardList.get(position).getQuestion());
        inputAnswer.setText(flashCardList.get(position).getAnswer());

        builder.setView(dialogView);
        builder.setPositiveButton("Update", (dialog, which) -> {
            String question = inputQuestion.getText().toString();
            String answer = inputAnswer.getText().toString();
            if (!question.isEmpty() && !answer.isEmpty()) {
                flashCardList.get(position).setQuestion(question);
                flashCardList.get(position).setAnswer(answer);
                flashCardAdapter.notifyItemChanged(position);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void onDelete(int position) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete this flash card?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    flashCardList.remove(position);
                    flashCardAdapter.notifyItemRemoved(position);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
