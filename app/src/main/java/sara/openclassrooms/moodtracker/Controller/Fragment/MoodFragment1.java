package sara.openclassrooms.moodtracker.Controller.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sara.openclassrooms.moodtracker.R;

public class MoodFragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable
                                     Bundle savedInstanceState) {
        
        ViewGroup rootView = (ViewGroup) inflater.
                inflate(R.layout.mood1_sad,
                        container, false);
        return rootView;

    }

}



