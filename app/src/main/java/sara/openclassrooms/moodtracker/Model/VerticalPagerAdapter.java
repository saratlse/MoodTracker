package sara.openclassrooms.moodtracker.Model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class VerticalPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public VerticalPagerAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
    }




    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    //la methode getCount renvoie le nombre de pages que l'adaptateur creera
    @Override
    public int getCount() {
        return fragmentList.size();
    }

}

