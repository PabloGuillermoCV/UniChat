package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.app.Activity;
import android.app.Application;

/**
 * Created by Lark Digital on 8/3/2018.
 */

public class Study extends Application {

    private Activity currentActivity = null;

    public void onCreate(){
        super.onCreate();
    }

    public Activity getCurrentActivity(){
        return currentActivity;
    }

    public void setCurrentActivity(Activity mCurrent){
        currentActivity = mCurrent;
    }
}
