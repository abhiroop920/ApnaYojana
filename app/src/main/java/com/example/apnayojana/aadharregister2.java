

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

public class aadharregister2 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref4;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadharregister2);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.number2);
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        Intent intent = getIntent();
        String id_value = intent.getStringExtra("ID");
        e1.setText(id_value);
    }

    public void verifyclicked(View v)
    {
        final String str1=e1.getText().toString();
        myref4=database.getReference().child("scheme1");
        if(str1.length()==12 && !str1.isEmpty()){
            myref4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(str1).exists()){
                        Toast.makeText(aadharregister2.this,"Already registered with scheme",Toast.LENGTH_LONG).show();
                        e1.setText("");
                    }
                    else
                    {
                        Intent i=new Intent(aadharregister2.this,verification.class);
                        i.putExtra("aa number",str1);
                        startActivity(i);
                        e1.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(aadharregister2.this,"Enter Valid Aadhaar number",Toast.LENGTH_LONG).show();
        }
    }

}


