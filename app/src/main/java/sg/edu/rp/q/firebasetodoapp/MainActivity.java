package sg.edu.rp.q.firebasetodoapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvDate, tvTitle, tvNum;
    private EditText etDate, etTitle, etNum;
    private CheckBox cbCompleted;
    private Button btnAdd;
    private boolean completed;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messagePOJOReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.textViewTitle);
        tvNum = findViewById(R.id.tvDays);
        etDate = findViewById(R.id.editTextDate);
        etTitle = findViewById(R.id.editTextTitle);
        etNum = findViewById(R.id.editTextDays);
        cbCompleted = findViewById(R.id.cbCompleted);
        btnAdd = findViewById(R.id.buttonAdd);

        firebaseDatabase = FirebaseDatabase.getInstance();
        messagePOJOReference = firebaseDatabase.getReference("toDoItem");
        messagePOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Item item = dataSnapshot.getValue(Item.class);

                if(item != null){
                    tvTitle.setText("Title: " + item.getTitle() + "\nDate: " + item.getDate() + "\nNumOfDays: " + item.getNumOfDays() + "\nCompleted?" + completed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completed = cbCompleted.isChecked();
                Item item  = new Item(etTitle.getText().toString(), etDate.getText().toString(), Integer.parseInt(etNum.getText().toString()), completed);
                messagePOJOReference.setValue(item);
                etTitle.setText("");
                etDate.setText("");
                etNum.setText("");
                cbCompleted.setChecked(false);
            }
        });
    }
}
