package fr.saman.demostyles;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Saman on 14/11/2017.
 */

public class InstaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stocky.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
