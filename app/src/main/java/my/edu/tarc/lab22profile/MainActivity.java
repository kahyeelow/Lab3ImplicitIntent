package my.edu.tarc.lab22profile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_UPDATE_REQUEST = 2;
    public static final String PROFILE_NAME = "my.edu.tarc.lab22profile.name";
    public static final String PROFILE_EMAIL = "my.edu.tarc.lab22profile.email";
    private TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//save
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking UI to program
        textViewName=(TextView)findViewById(R.id.textViewName);
        textViewEmail=(TextView)findViewById(R.id.textViewEmail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main Activity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main Activity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main Activity", "onPause");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode = the unique code to identify the intent call
        //resultCode = the result of an intent call; either OK or Cancel
        //data = the actual data returned by an intent call

        if(requestCode == PROFILE_UPDATE_REQUEST && resultCode == RESULT_OK){
            String name, email;
            name = data.getStringExtra(PROFILE_NAME);
            email = data.getStringExtra(PROFILE_EMAIL);

            //display
            textViewName.setText(getString(R.string.name) + " : " + name);//get string from Recources
            textViewEmail.setText(getString(R.string.email) + " : " + email);
        }

    }


    public void updateProfile(View view){
        //call another activity - ProfileActivity.class
        Intent intent = new Intent(this, ProfileActivity.class);
        //call activity that will return result
        startActivityForResult(intent, PROFILE_UPDATE_REQUEST);
    }

    public void visitBAIT2073(View v){
        String uri = "http://bait2073.000webhostapp.com/welcome.html";
        Intent intent = new Intent(Intent.ACTION_VIEW);//Implicit Intent, we do not know where and what's the activity
        intent.setData(Uri.parse(uri));
        startActivity(intent);

    }
    public void showMain(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }

    public void showDial(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "0123456789"));
        startActivity(intent);
    }

    public void showSendTo(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto: " + "seekt@tarc.edu.my"));

        //verify if resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities =
                packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(intent);
        }
    }
}
