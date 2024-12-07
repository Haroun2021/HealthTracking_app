
package com.healthtracker;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private RecyclerView healthGoalsRecyclerView;
    private HealthGoalsAdapter adapter;
    private ArrayList<HealthGoal> healthGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        db = FirebaseFirestore.getInstance();
        healthGoalsRecyclerView = findViewById(R.id.healthGoalsRecyclerView);
        healthGoalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        healthGoals = new ArrayList<>();
        adapter = new HealthGoalsAdapter(healthGoals);
        healthGoalsRecyclerView.setAdapter(adapter);

        loadHealthGoals();
    }

    private void loadHealthGoals() {
        db.collection("healthGoals")
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        int progress = document.getLong("progress").intValue();
                        int target = document.getLong("target").intValue();
                        healthGoals.add(new HealthGoal(name, progress, target));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DashboardActivity.this, "Failed to load health goals.", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
