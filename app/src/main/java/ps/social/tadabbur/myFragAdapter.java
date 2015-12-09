package ps.social.tadabbur;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Kanaan on 11/28/2015.
 */
public class myFragAdapter extends FragmentStatePagerAdapter {
    public myFragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       /*if (position<2){
            FirstPage fp=FirstPage.newInstance(position,"");
            return fp;
        }
        else
        {*/
            RemainderFragment rf=RemainderFragment.newInstance(position,"");
            return rf;
        //}
    }


    @Override
    public int getCount() {
        return 604;
    }
}
