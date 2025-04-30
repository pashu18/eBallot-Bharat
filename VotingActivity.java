package com.example.votingproject; 
 
import android.content.Intent; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.Toast; 
import androidx.appcompat.app.AppCompatActivity; 
 
public class VotingActivity extends AppCompatActivity { 
 
    private int bjpVotes = 0; 
    private int congressVotes = 0; 
    private int aapVotes = 0; 
    private int othersVotes = 0; 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_voting); 
 
        Button buttonBJP = findViewById(R.id.buttonBJP); 
        Button buttonCongress = findViewById(R.id.buttonCongress); 
        Button buttonAAP = findViewById(R.id.buttonAAP); 
        Button buttonOthers = findViewById(R.id.buttonOthers); 
        Button buttonResults = findViewById(R.id.buttonResults); 
 
        buttonBJP.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                bjpVotes++; 
                showToast("You voted for BJP"); 
            } 
        }); 
 
        buttonCongress.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                congressVotes++; 
                showToast("You voted for CONGRESS"); 
            } 
        }); 
 
        buttonAAP.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                aapVotes++; 
                showToast("You voted for AAP"); 
            } 
        }); 
 
        buttonOthers.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                othersVotes++; 
                showToast("You voted for OTHERS"); 
            } 
        }); 
        buttonResults.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                Intent intent = new Intent(VotingActivity.this, ResultActivity.class); 
                intent.putExtra("BJP_VOTES", bjpVotes); 
                intent.putExtra("CONGRESS_VOTES", congressVotes); 
                intent.putExtra("AAP_VOTES", aapVotes); 
                intent.putExtra("OTHERS_VOTES", othersVotes); 
                startActivity(intent); 
            } 
        }); 
    } 
 
    private void showToast(String message) { 
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); 
    } 
}