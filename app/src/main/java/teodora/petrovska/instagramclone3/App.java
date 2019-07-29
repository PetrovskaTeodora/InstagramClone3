package teodora.petrovska.instagramclone3;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("oEoPIdypwZxyFirWipHWk5zP5RGxEqKMPRMK53fm")
                // if defined
                .clientKey("NH4KjE24pvye95ol6UeEeGnyiwZVQUo6idDz0zIT")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
