package com.example.votingproject; 
 
import android.app.Notification; 
import android.app.NotificationChannel; 
import android.app.NotificationManager; 
import android.content.Context; 
import android.graphics.BitmapFactory; 
import android.os.Build; 
import android.os.Bundle; 
import android.widget.TextView; 
import androidx.appcompat.app.AppCompatActivity; 
import android.widget.ImageView; 
import android.view.View; 
 
public class ResultActivity extends AppCompatActivity { 
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_result); 
        // Get votes count from intent 
        int bjpVotes = getIntent().getIntExtra("BJP_VOTES", 0); 
        int congressVotes = getIntent().getIntExtra("CONGRESS_VOTES", 0); 
        int aapVotes = getIntent().getIntExtra("AAP_VOTES", 0); 
        int othersVotes = getIntent().getIntExtra("OTHERS_VOTES", 0); 
 
        // Calculate total votes for each party 
        int totalBJPVotes = bjpVotes; 
        int totalCongressVotes = congressVotes; 
        int totalAAPVotes = aapVotes; 
        int totalOthersVotes = othersVotes; 
 
        // Displaying Number of Votes 
        TextView textViewResult = findViewById(R.id.textViewScore); 
        textViewResult.setText("BJP Votes: " + totalBJPVotes + "\n" + 
                "CONGRESS Votes: " + totalCongressVotes + "\n" + 
                "AAP Votes: " + totalAAPVotes + "\n" + 
                "OTHERS Votes: " + totalOthersVotes + "\n\n"); 
 
        // Determine the winner 
        int maxVotes = Math.max(totalBJPVotes, Math.max(totalCongressVotes, 
Math.max(totalAAPVotes, totalOthersVotes))); 
 
        StringBuilder tiedParties = new StringBuilder(); 
        int tiedCount = 0; 
 
        // Check for tied parties 
        if (totalBJPVotes == maxVotes) { 
            tiedParties.append("BJP"); 
            tiedCount++; 
        } 
        if (totalCongressVotes == maxVotes) { 
            if (tiedCount > 0) { 
                tiedParties.append(" and Congress"); 
            } else { 
                tiedParties.append("Congress"); 
            } 
            tiedCount++; 
        } 
        if (totalAAPVotes == maxVotes) { 
            if (tiedCount > 0) { 
                tiedParties.append(" and AAP"); 
            } else { 
                tiedParties.append("AAP"); 
            } 
            tiedCount++; 
        } 
        if (totalOthersVotes == maxVotes) { 
            if (tiedCount > 0) { 
                tiedParties.append(" and Others"); 
            } else { 
                tiedParties.append("Others"); 
            } 
            tiedCount++; 
        } 
 
        // Display the result 
        TextView resultTextView = findViewById(R.id.textViewResult); 
        ImageView winningPartyLogo = findViewById(R.id.winningPartyLogo); 
        if (tiedCount > 1) { 
            resultTextView.setText("It's a tie between " + tiedParties.toString() + "!"); 
            winningPartyLogo.setVisibility(View.GONE); // Hide the logo in case of tie 
            showNotification("Tie", "It's a tie between " + tiedParties.toString() + "!"); 
        } else { 
            String winner; 
            int winningLogoResId; 
            if (totalBJPVotes == maxVotes) { 
                winner = "BJP"; 
                winningLogoResId = R.drawable.bjp; // Set the resource ID of BJP logo 
            } else if (totalCongressVotes == maxVotes) { 
                winner = "Congress"; 
                winningLogoResId = R.drawable.congress; // Set the resource ID of Congress logo 
            } else if (totalAAPVotes == maxVotes) { 
                winner = "AAP"; 
                winningLogoResId = R.drawable.aap; // Set the resource ID of AAP logo 
            } else { 
                winner = "Others"; 
                winningLogoResId = R.drawable.others; // Set the resource ID of Others logo 
            } 
            resultTextView.setText("The winner is " + winner + "!"); 
            winningPartyLogo.setImageResource(winningLogoResId); // Set the winning party 
logo 
            winningPartyLogo.setVisibility(View.VISIBLE); // Show the logo  
            if (tiedCount == 1) { 
                showNotification(winner, "The winner is " + winner + "!"); 
            } 
        } 
    } 
 
    // Method to show notification 
    private void showNotification(String winner, String message) { 
        NotificationManager notificationManager = 
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
 
        // Create a unique notification channel ID 
        String channelId = "election_results_channel"; 
        String channelName = "Election Results"; 
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { 
            NotificationChannel channel = new NotificationChannel(channelId, channelName, 
                    NotificationManager.IMPORTANCE_DEFAULT); 
            notificationManager.createNotificationChannel(channel); 
        } 
 
        // Create a notification 
        Notification.Builder builder = new Notification.Builder(this, channelId) 
                .setContentTitle(winner + " Wins!") 
                .setContentText(message) 
                .setSmallIcon(R.drawable.notification_image)  
                .setAutoCancel(true); 
 
        // Show the notification 
        notificationManager.notify(1, builder.build()); 
    } 
} 
 
