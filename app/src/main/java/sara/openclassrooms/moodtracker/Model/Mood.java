package sara.openclassrooms.moodtracker.Model;

public class Mood {

    private String comment;
    private int position;
    private int moodBackgroundColor;

    private String mood;
    private DifferentsMoods differentsMoods;


    //constructor
    public Mood(String comment, int position, int moodBackgroundColor){
        this.comment = comment;
        this.position = position;
        this.moodBackgroundColor = moodBackgroundColor;

    }

    //constructor
     public Mood(){
        position = 1;
        comment = "";
    }


    public void setComment(String comment){
        this.comment = comment;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMood(String mood){
        this.mood = mood;
    }

    public void setMoodBackgroundColor(int moodBackgroundColor){
        this.moodBackgroundColor= moodBackgroundColor;
    }

    public void setDifferentsMoods(DifferentsMoods differentsMoods){
       this.differentsMoods = differentsMoods;
    }





}


