package itkido.me.androidfirebasefirestorejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<String> mTitle = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        buildRecyclerView();
    }

    private void buildRecyclerView(){
        //Get all documents for collection cities
        db.collection("cities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String title = String.valueOf(document.getData().get("title"));
                                Log.d(TAG, "onComplete: title " + title);

                                mTitle.add(title);
                                initRecyclerView();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        VerticalViewAdapter featuredRecyclerViewAdapter = new VerticalViewAdapter(getApplicationContext(), mTitle);
        recyclerView.setAdapter(featuredRecyclerViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}