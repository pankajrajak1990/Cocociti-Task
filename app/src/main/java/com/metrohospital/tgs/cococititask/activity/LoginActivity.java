package com.metrohospital.tgs.cococititask.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.metrohospital.tgs.cococititask.R;
import com.metrohospital.tgs.cococititask.adapter.User;
import com.metrohospital.tgs.cococititask.datamodal.LoginModel;
import com.metrohospital.tgs.cococititask.retrofit.API;
import com.metrohospital.tgs.cococititask.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.metrohospital.tgs.cococititask.R.string.please_check_internet_connection;
public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private ProgressDialog dialog;
    private TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(LoginActivity.this);
        mEmailView = (EditText) findViewById(R.id.email);
        forgotPassword = (TextView) findViewById(R.id.forgot);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, R.string.forget_pwd, Toast.LENGTH_SHORT).show();
            }
        });
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.btn_login);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.isConnected(LoginActivity.this)) {
                    attemptLogin();
                }else {
                   Toast.makeText(LoginActivity.this,R.string.please_check_internet_connection,Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button SignUpButton = (Button) findViewById(R.id.sign_up_btn);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, R.string.Sign_up, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void attemptLogin() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean isFormValid = true;
        if (TextUtils.isEmpty(email)) {
            showError(mEmailView, getString(R.string.error_field_required), isFormValid);
            isFormValid = false;
        } else if (!isEmailValid(email)) {
            showError(mEmailView, getString(R.string.error_invalid_email), isFormValid);
            isFormValid = false;
        }
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            showError(mPasswordView, getString(R.string.error_invalid_password), isFormValid);
            isFormValid = false;
        }
        if (isFormValid) {
            startLoginProcess(new User(email, password));
        }
    }
    private void showError(EditText editText, String error, boolean requestFocus) {
        editText.setError(error);
        if (requestFocus) editText.requestFocus();
    }
    private void startLoginProcess(User user) {
        showProgress(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<LoginModel> call = api.checkLogin(user);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                int status = response.code();
                if (status == 200) {
                    showProgress(false);
                    LoginModel loginmodal = response.body();
                    if (loginmodal.getStatus().equals(Constant.SUCCESS)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(Constant.EMAIL, loginmodal.getData().getUser().getEmail());
                        intent.putExtra(Constant.ACCESS_TOKEN, loginmodal.getData().getUser().getAccess_token());
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.invalid_email_pwd, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
            }
        });
        // startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 1;
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        if (show) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }
}
