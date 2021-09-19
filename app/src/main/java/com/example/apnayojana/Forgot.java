package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class Forgot extends AppCompatActivity {
    EditText name,phone,passo,passn;
    FirebaseDatabase database;
    DatabaseReference myref;
    private TextToSpeech myTTS;
    private SpeechRecognizer mySpeechRecognizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        name=(EditText)findViewById(R.id.usernames);
        phone=(EditText)findViewById(R.id.answer);
        passo=(EditText)findViewById(R.id.oldpass);
        passn=(EditText)findViewById(R.id.newpass);
        initializeisempty();
        database=FirebaseDatabase.getInstance();
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                      enterusername();
                }
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                        answer();
                }
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        passo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                       enterpassword();
                }
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        passn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                         retypepassword();
                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });

        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.floatingActionButton3);
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
    private void processResult(String command) {
        command = command.toLowerCase();
        int k=command.length();
        String y =command.substring(command.indexOf("is")+3,k);
        if(command.contains("clear") || command.contains("remove") || command.contains("delete"))
        {
            if (command.contains("username")) {
                EditText ed = (EditText) findViewById(R.id.usernames);
                ed.setText("");
            }
            else if (command.contains("retype") || command.contains("confirm")) {
                EditText ed = (EditText) findViewById(R.id.newpass);
                ed.setText("");
            } else if (command.contains("password")) {
                EditText ed = (EditText) findViewById(R.id.oldpass);
                ed.setText("");
            } else if (command.contains("answer")) {
                EditText ed = (EditText) findViewById(R.id.answer);
                ed.setText("");
            }
        }
        else if (command.contains("re") || command.contains("type")) {
            EditText ed = (EditText) findViewById(R.id.newpass);
            ed.setText(y.trim());
        }
        else {
            if (command.contains("username")) {
                EditText ed = (EditText) findViewById(R.id.usernames);
                ed.setText(y.replaceAll("\\s+", "").trim());
            } else if (command.contains("password")) {
                EditText ed = (EditText) findViewById(R.id.oldpass);
                ed.setText(y.trim());
            } else if (command.contains("answer")) {
                EditText ed = (EditText) findViewById(R.id.answer);
                ed.setText(y.trim());
            }
        }
    }
    public void initializeisempty(){
        myTTS=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size()==0){
                    Toast.makeText(Forgot.this,"voice not possible",Toast.LENGTH_LONG).show();
                }
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak(" ");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
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
    private void answer(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(Forgot.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter answer to question or speak answer starting with My answer is");
                }
            }

        });
    }
    private void speak(String message){
        if(Build.VERSION.SDK_INT>=21){
            myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else{
            myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null);
        }
    }


    private void enterusername(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(Forgot.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter your username or speak your username starting with My username is");
                }
            }

        });
    }

    private void enterpassword(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(Forgot.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter your password or speak your password starting with My new password is");
                }
            }

        });
    }

    private void retypepassword(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(Forgot.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Retype your password or speak your password starting with My retyped password is");
                }
            }

        });
    }

    public void verify(View v){
        final String names=name.getText().toString();
        final String hint=phone.getText().toString();
        final String oldpass=passo.getText().toString();
        final String newpass=passn.getText().toString();
        myref=database.getReference().child("users");
        if(!names.isEmpty() && !hint.isEmpty() && !oldpass.isEmpty() && !newpass.isEmpty()){
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(names).exists()){
                        String str=dataSnapshot.child(names).child("answer").getValue().toString();
                        if(str.equals(hint)) {
                            if (oldpass.equals(newpass)) {
                                myref.child(names).child("password").setValue(newpass);
                                myref.child(names).child("password2").setValue(newpass);

                                Toast.makeText(Forgot.this, "Password updated successfully", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(Forgot.this,Signin.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Forgot.this, "passwords do not match", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Forgot.this,"Details do not match",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(Forgot.this,"user do not exists",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(Forgot.this,"Enter full details",Toast.LENGTH_LONG).show();
        }



    }
}
