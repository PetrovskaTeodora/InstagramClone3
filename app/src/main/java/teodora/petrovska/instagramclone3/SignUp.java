package teodora.petrovska.instagramclone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

   private  EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
   private  Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        edtName=findViewById(R.id.edtName);
        edtPunchSpeed=findViewById(R.id.edtPunchSpeed);
        edtPunchPower=findViewById(R.id.edtPunchPower);
        edtKickSpeed=findViewById(R.id.edtKickSpeed);
        edtKickPower=findViewById(R.id.edtKickPower);
        btnSave=findViewById(R.id.btnSave);

        btnSave.setOnClickListener(SignUp.this);

    }

    @Override
    public void onClick(View buttonView) {
try {
    final ParseObject kickBoxer = new ParseObject("KickBoxer");

    kickBoxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
    kickBoxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
    kickBoxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
    kickBoxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));
    kickBoxer.put("name", edtName.getText().toString());

    kickBoxer.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if (e == null) {
                FancyToast.makeText(SignUp.this, kickBoxer.get("name") + "is saved to server!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
            } else {
                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
            }
        }
    });
}
catch(Exception e){
    
        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


}

    }

    //    public void HelloWorldTapped(View view){
//        ParseObject boxer=new ParseObject("Boxer");
//
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    Toast.makeText(SignUp.this, "Boxer object is saved successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//
//    public void MakeKickBoxerTapped(View view){
//
//        final ParseObject kickBoxer= new ParseObject("KickBoxer");
//
//        kickBoxer.put("punch_speed", edtPunchSpeed.getText().toString());
//        kickBoxer.put("punch_power", edtPunchPower.getText().toString());
//        kickBoxer.put("kick_speed", edtKickSpeed.getText().toString());
//        kickBoxer.put("kick_power", edtKickPower.getText().toString());
//        kickBoxer.put("name", edtName.getText().toString());
//
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    Toast.makeText(SignUp.this,kickBoxer.get("name")+ " created successfully",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }



}
