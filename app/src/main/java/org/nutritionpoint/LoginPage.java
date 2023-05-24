package org.nutritionpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.tensorflow.lite.examples.detection.R;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        final Button login_btn = findViewById(R.id.login_btn);
        final EditText emailView = findViewById(R.id.email);
        final EditText passwordView = findViewById(R.id.password);
        final Button forgotpass = findViewById(R.id.forgot_pass);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Objects.requireNonNull(emailView.getText()).toString().trim();
                final String password = Objects.requireNonNull(passwordView.getText()).toString().trim();

                if (isValidEmail(email)) {
                    if (isValidPassword(password)) {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(LoginPage.this, MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        passwordView.setError(getString(R.string.password_val));
                    }
                } else {
                    emailView.setError(getString(R.string.email_val));
                }
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, SignUpPage.class));
            }
        });
    }

    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidPassword(CharSequence password) {
        return (!TextUtils.isEmpty(password) && password.length() > 8);
    }
}