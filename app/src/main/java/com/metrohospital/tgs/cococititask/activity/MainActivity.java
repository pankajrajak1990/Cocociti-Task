package com.metrohospital.tgs.cococititask.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.metrohospital.tgs.cococititask.R;
import com.metrohospital.tgs.cococititask.fragment.MainFragment;
import com.wang.avi.AVLoadingIndicatorView;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public AVLoadingIndicatorView progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Fragment fragmentToReplace = null;
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            fragmentToReplace = new MainFragment();
            Bundle arguments = new Bundle();
            arguments.putString(Constant.EMAIL, getIntent().getStringExtra(Constant.EMAIL));
            arguments.putString(Constant.TOKEN, getIntent().getStringExtra(Constant.ACCESS_TOKEN));
            fragmentToReplace.setArguments(arguments);
            transaction.replace(R.id.frag_container, fragmentToReplace, TAG);
            transaction.commit();
        }
        progressDialog = (AVLoadingIndicatorView) findViewById(R.id.loading_bar);
    }
    public AVLoadingIndicatorView getProgressDialog() {
        return progressDialog;
    }
}
