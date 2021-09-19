package com.example.apnayojana;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.TextView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class Listofchild extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref123;
    TextView t1;
    int count=0;
int i=0;
int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofchild);
        database=FirebaseDatabase.getInstance();
        t1=(TextView)findViewById(R.id.detailsofusers);
        final StringBuilder s1=new StringBuilder();


        myref123=database.getReference().child("scheme2");
        myref123.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                 s1.append(dataSnapshot1.getKey().toString());
                 s1.append("\n");
                }
                t1.setText(s1.toString());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}