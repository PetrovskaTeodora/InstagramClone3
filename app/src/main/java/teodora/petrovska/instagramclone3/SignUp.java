package teodora.petrovska.instagramclone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

   private  EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
   private  Button btnSave;
   private TextView txtGetData;
   private TextView txtGetData2;
   private Button btnGetAllData;
   private String allKickBoxers;
   private Button btnGetAllData2;
   private String site="";
   private Button btnNextActivity;


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
        txtGetData=findViewById(R.id.txtGetData);
        txtGetData2=findViewById(R.id.txtGetData2);
        btnGetAllData=findViewById(R.id.btnGetAllData);
        btnGetAllData2=findViewById(R.id.btnGetAllData2);
        btnNextActivity=findViewById(R.id.btnNextActivity);

        btnSave.setOnClickListener(SignUp.this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery= ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("dM39D7YrwT", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e==null){
                            txtGetData.setText(object.get("name") + "-" + "punch power: "+object.get("punch_power"));
                        }
                    }
                });
            }
        });


        txtGetData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery1=ParseQuery.getQuery("KickBoxer");
                parseQuery1.getInBackground("jKXgWgGMPo", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null){
                            txtGetData2.setText("Name: "+object.get("name")+", Kick Power: " + object.get("kick_power")+ ", Kick Speed: "+object.get("kick_speed")+", Punch Power: "+object.get("punch_power"));
                        }
                    }
                });



            }
        });

            btnGetAllData.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    allKickBoxers="";
                    ParseQuery<ParseObject> queryAll= ParseQuery.getQuery("KickBoxer");

                    queryAll.whereGreaterThan("punch_power", 200);

                    queryAll.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null){
                                if(objects.size()>0){

                                    for(ParseObject kickBoxer : objects) {
                                        allKickBoxers = allKickBoxers + kickBoxer.get("name")+"\n";
                                    }

                                    FancyToast.makeText(SignUp.this,allKickBoxers ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                }
                                else
                                {
                                    FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                                }
                            }

                        }
                    });
                }
            });

            btnGetAllData2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    site="";
                    final ParseQuery<ParseObject> siteKikBokseri=ParseQuery.getQuery("KickBoxer");
                    siteKikBokseri.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null){
                                if(objects.size()>0){
                                    for(ParseObject kikbokser: objects){
                                        site=site+kikbokser.get("name")+" "+kikbokser.get("punch_speed")+"\n";
                                    }

                                    FancyToast.makeText(SignUp.this,site,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                }
                                else
                                {
                                    FancyToast.makeText(SignUp.this,site,FancyToast.LENGTH_LONG,FancyToast.ERROR,true);
                                }
                            }
                        }
                    });
                }
            });

            btnNextActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

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
