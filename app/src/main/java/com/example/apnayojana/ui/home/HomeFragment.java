package com.example.apnayojana.ui.home;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.apnayojana.R;
import com.example.apnayojana.Schemepage;

import com.example.apnayojana.Signin;
import com.example.apnayojana.aadharregister;
import com.example.apnayojana.myprofile;
import com.example.apnayojana.schemesmic;
import com.example.apnayojana.servicecentres;
import com.example.apnayojana.ui.gallery.GalleryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
   // private TextToSpeech myTTS;
     private SpeechRecognizer mySpeechRecognizer;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imv=(ImageView) view.findViewById(R.id.slide_show1);
        AnimationDrawable animationDrawable=(AnimationDrawable)((ImageView)getView().findViewById(R.id.slide_show1)).getDrawable() ;
        animationDrawable.start();
        FloatingActionButton fab =(FloatingActionButton) view.findViewById(R.id.floatingActionButton);
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
        if(SpeechRecognizer.isRecognitionAvailable(getContext())){
            mySpeechRecognizer =SpeechRecognizer.createSpeechRecognizer(getContext());
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

        if(command.contains("open") || command.contains("go") || command.contains("want") || command.contains("see")){
            if(command.contains("my") && command.contains("schemes"))
            {
                GalleryFragment galleryFragment = new GalleryFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homepage, galleryFragment);
                fragmentTransaction.commit();

            }
            else if(command.contains("schemes")) {
                Intent intent = new Intent(getActivity(), schemesmic.class);
                startActivity(intent);
            }
            else if(command.contains("my") && command.contains("profile"))
            {
                Intent intent =new Intent(getActivity(), myprofile.class);
                startActivity(intent);
            }

            else if(command.contains("nearby") || command.contains("service"))
            {
                Intent intent =new Intent(getActivity(), servicecentres.class);
                startActivity(intent);
            }
            else if(command.contains("sign out"))
            {
                Intent intent =new Intent(getActivity(), Signin.class);
                startActivity(intent);
            }
            else if(command.contains("register") &&(command.contains("one family one job") || command.contains("scheme one")||command.contains("first scheme")))
            {
                Intent intent =new Intent(getActivity(), aadharregister.class);
                startActivity(intent);
            }
            else if(command.contains("one family one job") || command.contains("scheme one"))
            {
                Intent intent =new Intent(getActivity(), Schemepage.class);
                startActivity(intent);
            }
            else if(command.contains("sign in"))
            {
                Intent intent =new Intent(getActivity(), Signin.class);
                startActivity(intent);
            }

        }

    }

  /*  private void speak(String message){
        if(Build.VERSION.SDK_INT>=21){
            myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else{
            myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null);
        }
    } */




}