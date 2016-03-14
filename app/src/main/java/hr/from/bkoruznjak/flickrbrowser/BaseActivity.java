package hr.from.bkoruznjak.flickrbrowser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Borna on 14.3.16.
 */
public class BaseActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    public static final String FLICKR_QUERY = "FLICKR QUERY";
    public static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";

    protected Toolbar activateToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            if(mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    protected Toolbar activateToolbarWithHomeEnabled(){
        activateToolbar();
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return mToolbar;
    }
}
