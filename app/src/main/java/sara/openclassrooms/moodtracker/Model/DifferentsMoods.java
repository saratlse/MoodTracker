package sara.openclassrooms.moodtracker.Model;

import sara.openclassrooms.moodtracker.R;

public enum DifferentsMoods  {
    SAD,
    DISAPPOINTED,
    NORMAL,
    HAPPY,
    SUPER_HAPPY,

}

    /*SAD(0, R.layout.mood1_sad, R.color.sad_red),
    DISAPPOINTED(1, R.layout.mood2_disappointed, R.color.disappointed_grey),
    NORMAL(2, R.layout.mood3_normal, R.color.normal_blue),
    HAPPY(3, R.layout.mood4_happy, R.color.happy_green),
    SUPER_HAPPY(4, R.layout.mood5_super, R.color.super_happy_yellow);


    private final int mId;
    private final int mLayoutId;
    private final int mColorId;


    DifferentsMoods(int id, int layoutId, int colorId) {
        mId = id;
        mLayoutId = layoutId;
        mColorId = colorId;

    }

    public int getId() {
        return mId;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    public int getColorId() {
        return mColorId;
    }

    public DifferentsMoods valueOf(int id) {
        for (DifferentsMoods differentsMoods : DifferentsMoods.values()) {
            if (differentsMoods.getId() == id) {
                return differentsMoods;
            }
        }
        throw new RuntimeException("No mood for this id");
    }*/

