
package com.example.apnayojana;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.method.HideReturnsTransformationMethod;
        import android.text.method.PasswordTransformationMethod;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref;
    EditText e1,e2;
    CheckBox ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        database=FirebaseDatabase.getInstance();

        ch=(CheckBox)findViewById(R.id.checkBox2);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.password);
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        e2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });

    }
    public void adminclick(View v){
        final String name=e1.getText().toString();
        final String pass=e2.getText().toString();
        myref=database.getReference().child("admins");
        if(!name.isEmpty() && !pass.isEmpty()){
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(name).exists()){
                        String passwords=dataSnapshot.child(name).child("password").getValue().toString();
                        if(passwords.equals(pass)){
                            Intent i2=new Intent(Admin.this,choice.class);
                            startActivity(i2) ;
                            e1.setText("");
                            e2.setText("");
                        }
                        else
                        {
                            Toast.makeText(Admin.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                            e1.setText("");
                            e2.setText("");
                        }
                    }
                    else
                    {
                        Toast.makeText(Admin.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                        e1.setText("");
                        e2.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(Admin.this,"Enter complete details",Toast.LENGTH_LONG).show();
        }




    }
}

