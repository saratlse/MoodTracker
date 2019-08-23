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



    public String getComment() {
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public String getMood(){
        return mood;
    }

    public void setMood(String mood){
        this.mood = mood;
    }



    public int getMoodBackgroundColor() {
        return moodBackgroundColor;
    }

    public void setMoodBackgroundColor(int moodBackgroundColor){
        this.moodBackgroundColor= moodBackgroundColor;
    }


    public DifferentsMoods getDifferentsMoods(){
        return differentsMoods;
    }

    public void setDifferentsMoods(DifferentsMoods differentsMoods){
       this.differentsMoods = differentsMoods;
    }





}


