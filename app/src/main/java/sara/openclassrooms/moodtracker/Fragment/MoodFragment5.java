package sara.openclassrooms.moodtracker.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sara.openclassrooms.moodtracker.R;

public class MoodFragment5 extends Fragment {
    @Nullable



    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState){

        ViewGroup rootView = (ViewGroup) inflater.
                inflate(R.layout.mood5_super, container, false);

        return rootView;

    }

}
