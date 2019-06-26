package sara.openclassrooms.moodtracker.Model;

public class Mood {

    private String comment;
    private int position;
    private int moodBackgroundColor;





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



    public int getMoodBackgroundColor() {
        return moodBackgroundColor;
    }
    public int setMoodBackgroundColor(){
        return moodBackgroundColor;
    }







}


