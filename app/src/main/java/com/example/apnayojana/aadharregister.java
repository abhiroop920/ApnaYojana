package com.example.apnayojana;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class aadharregister extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref4,myref89;
    EditText e1;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadharregister);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.number);
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
    }

    public void verifyclicked(View v)
    {
        final String str1=e1.getText().toString();
        myref4=database.getReference().child("scheme1");
        myref89=database.getReference().child("people");
        if(str1.length()==12 && !str1.isEmpty()){
            myref89.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.child(str1).exists()){
                count=1;
            }
            else
            {
                count=0;
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


            myref4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(str1).exists() && count==1) {
                            Toast.makeText(aadharregister.this, "Already registered with scheme", Toast.LENGTH_LONG).show();
                            e1.setText("");
                        } else if(count==1){
                            Intent i = new Intent(aadharregister.this, verification.class);
                            i.putExtra("aa number", str1);
                            startActivity(i);
                            e1.setText("");
                        }
                        else
                        {
                            Toast.makeText(aadharregister.this,"Enter Valid aadhar number",Toast.LENGTH_LONG).show();
                        }

                    }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(aadharregister.this,"Enter Valid Aadhaar number",Toast.LENGTH_LONG).show();
        }
    }

}

