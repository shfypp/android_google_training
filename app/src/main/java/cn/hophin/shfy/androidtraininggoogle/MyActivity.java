package cn.hophin.shfy.androidtraininggoogle;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MyActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "cn.hophin.shfy.androidtraininggoogle.MESSAGE";
    private Camera mCamera;
    private int mCurrentScore;
    private int mCurrentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            //Restore value of members from saved state
            mCurrentScore=savedInstanceState.getInt(STATE_SCORE);
            mCurrentLevel=savedInstanceState.getInt(STATE_LEVEL);
        }else{
            //Initialize members with default values for a new instance
            mCurrentLevel=0;
            mCurrentScore=0;
        }
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when hte user clicks the Send button
     */
    public void sendMessage(View view){
        Intent intent=new Intent(this,DisplayMessageActivity.class);
        EditText editText= (EditText) findViewById(R.id.edit_message);
        String message=editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    /**
     * the response method for button clicked
     * @param view
     */
    public void anotherActivity(View view){
        Intent intent=new Intent(this,ForShowThemeActivity.class);
        startActivity(intent);
    }


    public void learnFragment(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**check system version at runtime**/
    private void setUpActionBar(){
        //Make sure we're running on Honeycomb or higher to ActionBar APIs
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            ActionBar actionBar=getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     *         Release system resources,such as broadcast receiver,handles to sensors(传感器),
     *         or any resources that may affect battery life while your activity is paused and
     *         the user dose not need them.
     */
    @Override
    protected void onPause() {
        super.onPause();    //Always call the superclass method first

        //Release the Camera because we don't need it when paused
        //and other activities might need to use it
        if (mCamera != null) {
            mCamera.release();
            mCamera=null;
        }
    }

    /**
     *        You should implement onResume() to initialize components that you release during onPause() and
     *        perform any other initialization that must occur each time the activity enters the Resumed state(
     *        such as begin animations and initialize components only used while the activity has user focus)
     */
    @Override
    protected void onResume() {
        super.onResume();   //Always call the superclass method at first
        //Get the Camera instance as the activity achieves full user focus
        if (mCamera == null) {
            initializeCamera(); //Local method to handle camera init
        }
    }

    /**
     * Local method to handle camera init
     */
    private void initializeCamera() {
        //TODO camera init
    }

    @Override
    protected void onStart() {

        super.onStart();    //Always call the superclass method first
        //The activity is either being restarted or started for the first time
        //so this is where we should make sure that GPS is enable
        LocationManager locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            //Create a dialog here that requests the user to enable GPS,and use an intent
            //with the android.provider. Settings.ACTION_LOCATION_SOURCE_SETTINGS action
            //to take the user to the Settings screen to enable GPS when they click "ok"
        }
    }

    static final String STATE_SCORE="playerScore";
    static final String STATE_LEVEL="playerLevel";

    /**
     *     1.  To save additional state information for your activity,you must
     *     implement onSaveInstanceState() and add key-value pairs to the Bundle object.
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Save the user's current game state
        outState.putInt(STATE_LEVEL,mCurrentLevel);
        outState.putInt(STATE_SCORE,mCurrentScore);
        //Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }
}
