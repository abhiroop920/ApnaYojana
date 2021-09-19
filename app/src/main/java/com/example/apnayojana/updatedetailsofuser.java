package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class updatedetailsofuser extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref;
    EditText fname,name,passwd1,passwd2,phone,security;
    private SpeechRecognizer mySpeechRecognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetailsofuser);

        database=FirebaseDatabase.getInstance();
        fname=(EditText)findViewById(R.id.username);
        name=(EditText)findViewById(R.id.name1);
        passwd1=(EditText)findViewById(R.id.passwd1);
        passwd2=(EditText)findViewById(R.id.passwd2);
        phone=(EditText)findViewById(R.id.contact);
        security=(EditText)findViewById(R.id.security);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);

                } else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        fname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);

                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        passwd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);

                }  else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        passwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);

                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);

                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        security.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);

                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.floatingActionButton4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
                mySpeechRecognizer.startListening(intent);
            }
        });
        initializeSpeechRecognizer();

    }


    private void initializeSpeechRecognizer(){
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            mySpeechRecognizer =SpeechRecognizer.createSpeechRecognizer(this);
            mySpeechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    List<String> results=bundle.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                    );
                    processResult(results.get(0));
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }

    private void processResult(String command) {
        command = command.toLowerCase();
        int k=command.length();
        String y =command.substring(command.indexOf("is")+3,k);
        if(command.contains("clear") || command.contains("remove") || command.contains("delete"))
        {
            if (command.contains("username")) {
                EditText ed = (EditText) findViewById(R.id.username);
                ed.setText("");
            } else if (command.contains("name")) {
                EditText ed = (EditText) findViewById(R.id.name1);
                ed.setText("");
            } else if (command.contains("retype")) {
                EditText ed = (EditText) findViewById(R.id.passwd2);
                ed.setText("");
            } else if (command.contains("password")) {
                EditText ed = (EditText) findViewById(R.id.passwd1);
                ed.setText("");
            } else if (command.contains("mobile") || command.contains("number")) {
                EditText ed = (EditText) findViewById(R.id.contact);
                ed.setText("");
            } else if (command.contains("answer")) {
                EditText ed = (EditText) findViewById(R.id.security);
                ed.setText("");
            }
        }
        else {
            if (command.contains("username")) {
                EditText ed = (EditText) findViewById(R.id.username);
                ed.setText(y.replaceAll("\\s+", "").trim());
            } else if (command.contains("name")) {
                EditText ed = (EditText) findViewById(R.id.name1);
                ed.setText(y.trim());
            } else if (command.contains("retype")) {
                EditText ed = (EditText) findViewById(R.id.passwd2);
                ed.setText(y.trim());
            } else if (command.contains("password")) {
                EditText ed = (EditText) findViewById(R.id.passwd1);
                ed.setText(y.trim());
            } else if (command.contains("mobile") || command.contains("number")) {
                EditText ed = (EditText) findViewById(R.id.contact);

                ed.setText(y.replaceAll("\\s+", "").trim());
            } else if (command.contains("answer")) {
                EditText ed = (EditText) findViewById(R.id.security);
                ed.setText(y.trim());
            }
        }
    }




    public void change(View v){
        final String user=fname.getText().toString().trim();
        final String p1=passwd1.getText().toString().trim();
        final String n=name.getText().toString().trim();
        final String p2=passwd2.getText().toString().trim();
        final String po=phone.getText().toString().trim();
        final String se=security.getText().toString().trim();
        myref=database.getReference().child("users");
        if (!user.isEmpty() && !p1.isEmpty() && !p2.isEmpty() && !n.isEmpty()){
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(user).exists()){
                        if(p1.equals(p2)) {

                            myref.child(user).child("Name").setValue(n);
                            myref.child(user).child("password").setValue(p1);
                            myref.child(user).child("password2").setValue(p2);
                            myref.child(user).child("mobile").setValue(po);
                            myref.child(user).child("answer").setValue(se);
                            Toast.makeText(updatedetailsofuser.this, "Updated successfully", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(updatedetailsofuser.this,Signin.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(updatedetailsofuser.this,"Passwords Don't match",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(updatedetailsofuser.this,"User do not exists",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(updatedetailsofuser.this,"Enter Complete details",Toast.LENGTH_LONG).show();
        }

    }
}