package ps.social.tadabbur;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Abdulrahman on 09-12-15.
 */
public class ParseInitialize extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Register any ParseObject subclass. Must be done before calling Parse.initialize()

        Parse.initialize(this, "4Zq7ugmsgzwIWgyczr6oEQpd3mdTerqwI0j9OrbX", "ZPf6nqSWHXVdxZnPyAkyaUapse3C2iLtdXtQWpwS");
    }

}

