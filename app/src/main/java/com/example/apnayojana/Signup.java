
        package com.example.apnayojana;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;

        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.speech.RecognitionListener;
        import android.speech.RecognizerIntent;
        import android.speech.SpeechRecognizer;
        import android.speech.tts.TextToSpeech;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.Toast;

        import com.example.apnayojana.ui.gallery.GalleryFragment;
        import com.example.apnayojana.ui.slideshow.SlideshowFragment;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.snackbar.Snackbar;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.List;
        import java.util.Locale;

        public class Signup extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref,myref45;
    int count;
    EditText name,username,passwd1,passwd2,phone,security,aadhaarnumber;
            private TextToSpeech myTTS;
            private SpeechRecognizer mySpeechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        database=FirebaseDatabase.getInstance();
        name=(EditText)findViewById(R.id.name1);
        username=(EditText)findViewById(R.id.email);
        passwd1=(EditText)findViewById(R.id.passwd1);
        passwd2=(EditText)findViewById(R.id.passwd2);
        phone=(EditText)findViewById(R.id.contact);
        security=(EditText)findViewById(R.id.security);
        aadhaarnumber=(EditText)findViewById(R.id.aanumber000);
        initializeisempty();
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    initializeTextToSpeech();
                }
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        passwd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        passwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundResource(R.drawable.focus_border_style);
                    retypepassword();
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
                    entermobilenumber();
                }
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        security.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.floatingActionButton2);
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
                    Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
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
                        EditText ed = (EditText) findViewById(R.id.email);
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
                        EditText ed = (EditText) findViewById(R.id.email);
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
            private void initializeTextToSpeech(){

                myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(myTTS.getEngines().size() ==0)
                            Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
                        else
                        {
                            myTTS.setLanguage(Locale.ENGLISH);
                            speak("Enter your mobile number or speak your mobilenumber starting with My mobilenumber is");
                        }
                    }

                });
            }

            private void answer(){

                myTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(myTTS.getEngines().size() ==0)
                            Toast.makeText(Signup.this,"voice not possible",Toast.LENGTH_LONG).show();
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


            public void signupclicked(View v)
       {

        final String user=username.getText().toString().trim();
        final String p1=passwd1.getText().toString().trim();
        final String aanumber=aadhaarnumber.getText().toString().trim();
        myref45=database.getReference().child("people");
        myref45.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(aanumber).exists()){
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
        final String n=name.getText().toString();
        final String p2=passwd2.getText().toString().trim();
        final String po=phone.getText().toString().trim();
        final String se=security.getText().toString().trim();
        myref=database.getReference().child("users");
        if (!user.isEmpty() && !p1.isEmpty() && !p2.isEmpty() && !n.isEmpty()){
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(user).exists()){
                        Toast.makeText(Signup.this,"User already exits",Toast.LENGTH_LONG).show();
                        name.setText("");
                        username.setText("");
                        passwd1.setText("");
                        passwd2.setText("");
                        phone.setText("");
                        security.setText("");
                        aadhaarnumber.setText("");

                    }
                    else
                    {
                        if(p1.equals(p2)){
                            if(count==1) {

                                myref.child(user).child("Name").setValue(name.getText().toString());
                                myref.child(user).child("username").setValue(user);
                                myref.child(user).child("password").setValue(p1);
                                myref.child(user).child("password2").setValue(p2);
                                myref.child(user).child("mobile").setValue(po);
                                myref.child(user).child("answer").setValue(se);
                                myref.child(user).child("Aadhaar Number").setValue(aanumber);
                                Toast.makeText(Signup.this, "Registration successfull", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Signup.this, Signin.class);
                                startActivity(intent);
                            }
                            else if(count==0)
                            {
                                Toast.makeText(Signup.this,"Does not belong to sikkim",Toast.LENGTH_LONG).show();
                            }
                            else
                            {

                            }
                        }
                        else
                        {
                            Toast.makeText(Signup.this,"Passwords not matched",Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(Signup.this, "Fill the complete details ", Toast.LENGTH_LONG).show();

        }




    }
}
