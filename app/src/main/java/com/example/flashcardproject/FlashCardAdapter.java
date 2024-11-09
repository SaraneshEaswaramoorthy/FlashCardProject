package com.example.flashcardproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.FlashCardViewHolder> {

    private final ArrayList<FlashCard> flashCardList;
    private final OnFlashCardInteractionListener listener;

    public interface OnFlashCardInteractionListener {
        void onEdit(int position);
        void onDelete(int position);
    }

    public FlashCardAdapter(ArrayList<FlashCard> flashCardList, OnFlashCardInteractionListener listener) {
        this.flashCardList = flashCardList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FlashCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flashcard, parent, false);
        return new FlashCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashCardViewHolder holder, int position) {
        FlashCard flashCard = flashCardList.get(position);
        holder.questionTextView.setText(flashCard.getQuestion());
        holder.answerTextView.setText(flashCard.getAnswer());

        // Set the click listener for the flip animation
        holder.flashCardFrame.setOnClickListener(v -> {
            if (holder.questionTextView.getVisibility() == View.VISIBLE) {
                // Flip to the answer
                holder.questionTextView.setVisibility(View.GONE);
                holder.answerTextView.setVisibility(View.VISIBLE);
                // Start flip-in animation
                Animation flipIn = AnimationUtils.loadAnimation(v.getContext(), R.anim.flip_in);
                holder.flashCardFrame.startAnimation(flipIn);
            } else {
                // Flip back to the question
                holder.answerTextView.setVisibility(View.GONE);
                holder.questionTextView.setVisibility(View.VISIBLE);
                // Start flip-out animation
                Animation flipOut = AnimationUtils.loadAnimation(v.getContext(), R.anim.flip_out);
                holder.flashCardFrame.startAnimation(flipOut);
            }
        });

        // Edit and Delete Button listeners
        holder.editButton.setOnClickListener(v -> listener.onEdit(position));
        holder.deleteButton.setOnClickListener(v -> listener.onDelete(position));
    }

    @Override
    public int getItemCount() {
        return flashCardList.size();
    }

    public static class FlashCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionTextView;
        private final TextView answerTextView;
        private final Button editButton;
        private final Button deleteButton;
        private final FrameLayout flashCardFrame;

        public FlashCardViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.tvFlashCardQuestion);
            answerTextView = itemView.findViewById(R.id.tvFlashCardAnswer);
            editButton = itemView.findViewById(R.id.btnEdit);
            deleteButton = itemView.findViewById(R.id.btnDelete);
            flashCardFrame = itemView.findViewById(R.id.flashCardFrame); // Correct ID for FrameLayout
        }
    }
}
