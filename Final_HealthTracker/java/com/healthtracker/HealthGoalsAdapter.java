
package com.healthtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HealthGoalsAdapter extends RecyclerView.Adapter<HealthGoalsAdapter.ViewHolder> {
    private ArrayList<HealthGoal> healthGoals;

    public HealthGoalsAdapter(ArrayList<HealthGoal> healthGoals) {
        this.healthGoals = healthGoals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthGoal goal = healthGoals.get(position);
        holder.goalName.setText(goal.getGoalName());
        holder.goalProgress.setText(goal.getProgress() + "/" + goal.getTarget());
    }

    @Override
    public int getItemCount() {
        return healthGoals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView goalName, goalProgress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goalName = itemView.findViewById(R.id.goalName);
            goalProgress = itemView.findViewById(R.id.goalProgress);
        }
    }
}
