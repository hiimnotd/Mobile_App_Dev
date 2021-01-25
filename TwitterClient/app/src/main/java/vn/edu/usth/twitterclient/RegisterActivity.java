package vn.edu.usth.twitterclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText PasswordReg, EmailReg;
    Button RegisterBtn;
    TextView HadAccount;

    private FirebaseAuth Auth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1DA1F2")));

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        PasswordReg = findViewById(R.id.passwordReg);
        EmailReg = findViewById(R.id.emailReg);
        RegisterBtn = findViewById(R.id.register);

        Auth = FirebaseAuth.getInstance();

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailReg.getText().toString().trim();
                String password = PasswordReg.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    EmailReg.setError("Invalid Email");
                    EmailReg.setFocusable(true);
                }
                else if (password.length() < 8){
                    PasswordReg.setError("Password length at least 8 characters!");
                    PasswordReg.setFocusable(true);
                }else{
                    regUser(email, password);
                }
            }
        });

        HadAccount = findViewById(R.id.hadAccount);

        HadAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        pd = new ProgressDialog(this);
        pd.setMessage("Signing Up...");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void regUser(String email, String password){
        pd.show();
        Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if (task.isSuccessful()){
                            FirebaseUser user = Auth.getCurrentUser();

                            pd.dismiss();

                            String email = user.getEmail();
                            String uid = user.getUid();
                            //Stroe data user in firebase realtime database by using HashMap
                            HashMap<Object, String> hashMap = new HashMap<>();
                            //Store infor
                            hashMap.put("email", email);
                            hashMap.put("uid", uid);
                            hashMap.put("name", "");
                            hashMap.put("description", "");
                            hashMap.put("location", "");
                            hashMap.put("image", "");
                            hashMap.put("url", "");
                            hashMap.put("username", "");

                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            DatabaseReference reference = database.getReference("Users");

                            reference.child(uid).setValue(hashMap);

                            Toast.makeText(RegisterActivity.this,"Registering...\n"+email, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            pd.dismiss();

                            Toast.makeText(RegisterActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
