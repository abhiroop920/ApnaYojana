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

public class AdminAcc extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref;
    EditText names,sec,sec1,unames,mobil,idadmin;
    private TextToSpeech myTTS;
    private SpeechRecognizer mySpeechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_acc);
        names=(EditText)findViewById(R.id.name0);
        sec=(EditText)findViewById(R.id.passwd10);
        sec1=(EditText)findViewById(R.id.passwd20);
        unames=(EditText)findViewById(R.id.email0);
        mobil=(EditText)findViewById(R.id.contact0);
        idadmin=(EditText)findViewById(R.id.adminid0);
        database=FirebaseDatabase.getInstance();
        initializeisempty();
        names.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    initializeTextToSpeech();
                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        sec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    enterpassword();
                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        sec1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    retypepassword();
                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        unames.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    enterusername();
                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        mobil.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    entermobilenumber();
                }else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        idadmin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    adminid();
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

    public void initializeisempty(){
        myTTS=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size()==0){
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
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
    private void processResult(String command) {
        command = command.toLowerCase();
        int k=command.length();
        String y =command.substring(command.indexOf("is")+3,k);
        if(command.contains("clear") || command.contains("remove") || command.contains("delete"))
        {
            if (command.contains("username")) {
                EditText ed = (EditText) findViewById(R.id.email0);
                ed.setText("");
            } else if (command.contains("name")) {
                EditText ed = (EditText) findViewById(R.id.name0);
                ed.setText("");
            } else if (command.contains("retype")) {
                EditText ed = (EditText) findViewById(R.id.passwd20);
                ed.setText("");
            } else if (command.contains("password")) {
                EditText ed = (EditText) findViewById(R.id.passwd10);
                ed.setText("");
            } else if (command.contains("mobile") || command.contains("number")) {
                EditText ed = (EditText) findViewById(R.id.contact0);
                ed.setText("");
            } else if (command.contains("id") || command.contains("admin")) {
                EditText ed = (EditText) findViewById(R.id.adminid0);
                ed.setText("");
            }
        }
        else {
            if (command.contains("username")) {
                EditText ed = (EditText) findViewById(R.id.email0);
                ed.setText(y.replaceAll("\\s+", "").trim());
            } else if (command.contains("name")) {
                EditText ed = (EditText) findViewById(R.id.name0);
                ed.setText(y.trim());
            } else if (command.contains("retype")) {
                EditText ed = (EditText) findViewById(R.id.passwd20);
                ed.setText(y.trim());
            } else if (command.contains("password")) {
                EditText ed = (EditText) findViewById(R.id.passwd10);
                ed.setText(y.trim());
            } else if (command.contains("mobile") || command.contains("number")) {
                EditText ed = (EditText) findViewById(R.id.contact0);

                ed.setText(y.replaceAll("\\s+", "").trim());
            } else if (command.contains("id") || command.contains("admin")) {
                EditText ed = (EditText) findViewById(R.id.adminid0);
                ed.setText(y.trim());
            }
        }
    }
    private void initializeTextToSpeech(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter your name or speak your name starting with My name is");
                }
            }

        });
    }

    private void enterusername(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter your password or speak your password starting with My password is");
                }
            }

        });
    }

    private void retypepassword(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Retype your password or speak your password starting with My retyped password is");
                }
            }

        });
    }

    private void entermobilenumber(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter your mobile number or speak your mobilenumber starting with My mobilenumber is");
                }
            }

        });
    }

    private void adminid(){

        myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() ==0)
                    Toast.makeText(AdminAcc.this,"voice not possible",Toast.LENGTH_LONG).show();
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Enter your admin id or speak admin id starting with My admin id is");
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

    public void signupadminclicked(View v) {
        final String name = names.getText().toString();
        final String password = sec.getText().toString();
        final String password2 = sec1.getText().toString();
        final String username = unames.getText().toString();
        final String mobile = mobil.getText().toString();
        final String adminid = idadmin.getText().toString();
        myref = database.getReference().child("admins");
        if (!name.isEmpty() && !password.isEmpty() && !password2.isEmpty() && !username.isEmpty() && !mobile.isEmpty() && !adminid.isEmpty()) {
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username).exists()) {
                        Toast.makeText(AdminAcc.this, "User already exits ", Toast.LENGTH_LONG).show();
                        names.setText("");
                        unames.setText("");
                        sec.setText("");
                        sec1.setText("");
                        mobil.setText("");
                        idadmin.setText("");

                    } else {
                        if (password.equals(password2)) {
                            if (adminid.equals("000")) {
                                myref.child(username).child("Name").setValue(names.getText().toString());
                                myref.child(username).child("username").setValue(username);
                                myref.child(username).child("password").setValue(password);
                                myref.child(username).child("password2").setValue(password2);
                                myref.child(username).child("mobile").setValue(mobile);
                                myref.child(username).child("admin id").setValue(adminid);
                                Toast.makeText(AdminAcc.this, "Registration successfull", Toast.LENGTH_LONG).show();
                                Intent i1 = new Intent(AdminAcc.this, Signin.class);
                                startActivity(i1);
                            } else {
                                Toast.makeText(AdminAcc.this, "Admin Id invalid", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(AdminAcc.this, "Passwords Do not match", Toast.LENGTH_LONG).show();
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
            Toast.makeText(AdminAcc.this,"Enter complete details",Toast.LENGTH_LONG).show();

        }



    }
}
