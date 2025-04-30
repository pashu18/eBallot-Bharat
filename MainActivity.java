package com.example.votingproject; 
 
import android.content.Intent; 
import androidx.appcompat.app.AppCompatActivity; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.Toast; 
 
public class MainActivity extends AppCompatActivity { 
 
    private EditText editTextUsername; 
    private EditText editTextPassword; 
    private Button buttonLogin; 
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
 
        editTextUsername = findViewById(R.id.editTextUsername); 
        editTextPassword = findViewById(R.id.editTextPassword); 
        buttonLogin = findViewById(R.id.buttonLogin); 
 
        buttonLogin.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                String username = editTextUsername.getText().toString().trim(); 
                String password = editTextPassword.getText().toString().trim(); 
 
                // Check if username and password are correct (for demonstration purposes, it's 
hardcoded) 
                if (username.equals("abc") && password.equals("123")) { 
                    // Login successful, start VotingActivity 
                    Intent intent = new Intent(MainActivity.this, VotingActivity.class); 
                    startActivity(intent); 
 
                    // Finish current activity to prevent going back to login screen 
                    finish(); 
                } else { 
                    // Login failed, show an error message 
                    Toast.makeText(MainActivity.this, "Invalid username or password", 
Toast.LENGTH_SHORT).show(); 
                } 
            } 
        }); 
    } 
}