package com.example.a20sep;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_VOICE_INPUT = 123;
    private Button voiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voiceButton = findViewById(R.id.voiceButton);

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognition();
            }
        });
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak the website name");
        startActivityForResult(intent, REQUEST_CODE_VOICE_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_VOICE_INPUT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && result.size() > 0) {
                String spokenText = result.get(0);
                openWebsite(spokenText);
            }
        }
    }

    private void openWebsite(String websiteName) {
        String url = determineWebsiteURL(websiteName);
        if (url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

    private String determineWebsiteURL(String websiteName) {
        switch (websiteName.toLowerCase()) {
            case "open google" :
                return "https://www.google.com";

            case "open youtube":
                return "https://www.youtube.com";

            case "open linkedin":
                return "https://www.linkedin.com/";

            case "open github":
                return "https://github.com/";

            case "open bhadrak autonomous college website":
                return "https://bhadrakcollege.nic.in/";

            case "open instagram":
                return "https://www.instagram.com/";

            case "open twitter":
                return "https://twitter.com/";

            case "open facebook":
                return "https://www.facebook.com/";

            case "open whatsapp":
                return "https://web.whatsapp.com/";

            case "open chatgpt":
                return "https://chat.openai.com/";

            case "open gmail":
                return "https://mail.google.com/mail/u/0/#inbox";

            default:
                return "error";
        }
    }
}