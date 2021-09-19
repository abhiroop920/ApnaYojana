package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import com.example.apnayojana.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;

public class Signin extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref;
    EditText name,pass;
    CheckBox ch;
    TextView t1;
    int count=3;


      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        database=FirebaseDatabase.getInstance();

        ch=(CheckBox)findViewById(R.id.checkBox);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        t1=(TextView)findViewById(R.id.errormessage);
        t1.setText("No of Attempts remaining : "+count);
        name=(EditText)findViewById(R.id.editText2);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });

        pass=(EditText)findViewById(R.id.editText3);
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
    }

    public void buttonclicked(View v)
    {
        final String namu= name.getText().toString();
        final String passw = pass.getText().toString();
        myref = database.getReference().child("users");
        if(!namu.isEmpty() && !passw.isEmpty()){
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(namu).exists()){
                        String password=dataSnapshot.child(namu).child("password").getValue().toString();
                        if(passw.equals(password)){
                            Intent i2=new Intent(Signin.this,navigation.class);
                            i2.putExtra("user identity",namu);
                            startActivity(i2);
                            name.setText("");
                            pass.setText("");
                        }
                        else
                        {
                            Toast.makeText(Signin.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                            name.setText("");
                            pass.setText("");
                            count--;
                            if(count==0) {
                               Toast.makeText(Signin.this,"Attempts Completed",Toast.LENGTH_LONG).show();
                            }

                            t1.setText("No of Attempts remaining : "+count);


                        }

                    }
                    else
                    {
                        Toast.makeText(Signin.this,"User don't exist",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(Signin.this,"Enter complete details",Toast.LENGTH_LONG).show();
        }

    }

    public void registerclick(View v)
    {
        Intent reg =new Intent(Signin.this,Signup.class);
        startActivity(reg);
    }
    public void adminclicked(View v){
        Intent admin=new Intent(Signin.this,Admin.class);
        startActivity(admin);
    }
    public void forgotclick(View v){
        Intent forgot=new Intent(Signin.this,Forgot.class);
        startActivity(forgot);
    }

    public void adminclick(View v)
    {
        Intent newadmin=new Intent(Signin.this,AdminAcc.class);
        startActivity(newadmin);
    }
}