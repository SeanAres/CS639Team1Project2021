package pace.cs639.healthyshopper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText editTextEmail, editTextPassword;
    private TextView loginClick;
    private Button button_register;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        button_register = findViewById(R.id.button_register);
        loginClick = findViewById(R.id.loginClick);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createAccount();
            }
        });

        loginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void createAccount() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            editTextEmail.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            editTextPassword.setError("Enter your password");
            return;
        }
        else if(password.length()<4){
            editTextPassword.setError("Password must be more than 4 characters");
            return;
        }
/*//        else if(isVallidEmail(email)){
//            editTextEmail.setError("Enter a valid email");
//            return;
//        }*/
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

//    private void updateUI(FirebaseUser user) {
//        if(user.equals(null)){
//            editTextEmail.setText("");
//            editTextPassword.setText("");
//        }
//        else {
//            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//        }
//    }

    private Boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}