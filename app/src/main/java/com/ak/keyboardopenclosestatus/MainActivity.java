package com.ak.keyboardopenclosestatus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ak961.keyboardstatuslistener.KeyboardEventListener;
import com.ak961.keyboardstatuslistener.KeyboardListener;

public class MainActivity extends AppCompatActivity implements KeyboardListener {
    EditText textView;
    KeyboardEventListener keyboardEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.editText);
//        attachKeyboardListeners();
        View rootView = findViewById(android.R.id.content);
        keyboardEventListener = new KeyboardEventListener(this, rootView, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (keyboardEventListener != null) {
            keyboardEventListener.unregisterKeyboardListener();
        }
    }

    @Override
    public void onKeyboardStatusChange(Boolean isOpen) {
        if (isOpen) {
            Log.e("AK961 >> ", "keyboard show");
            Toast.makeText(this, "keyboard show", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("Ak961 >> ", "keyboard hide");
            Toast.makeText(this, "keyboard hide", Toast.LENGTH_SHORT).show();
        }
    }
}
