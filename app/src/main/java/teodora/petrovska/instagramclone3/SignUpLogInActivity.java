package teodora.petrovska.instagramclone3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogInActivity extends AppCompatActivity {
    private EditText edtUserNameSignUp, edtPasswordSignUp, edtUserNameLogIn, edtPasswordLogIn;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUserNameSignUp=findViewById(R.id.edtUserNameSignUp);
        edtUserNameLogIn=findViewById(R.id.edtUserNameLogIn);
        edtPasswordSignUp=findViewById(R.id.edtPasswordSignUp);
        edtPasswordLogIn=findViewById(R.id.edtPasswordLogIn);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogIn=findViewById(R.id.btnLogIn);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser= new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLogInActivity.this,appUser.get("username")+"is signed up successfully!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else
                        {
                            FancyToast.makeText(SignUpLogInActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true);
                        }
                    }
                });
            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ParseUser.logInInBackground(edtUserNameLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if(user!=null && e==null){
                                FancyToast.makeText(SignUpLogInActivity.this,user.get("username")+"is logged in successfully!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                            else
                            {
                                FancyToast.makeText(SignUpLogInActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true);
                            }
                        }
                    });
            }
        });

    }
}
