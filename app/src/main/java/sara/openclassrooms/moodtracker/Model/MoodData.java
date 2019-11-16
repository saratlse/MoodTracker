package sara.openclassrooms.moodtracker.Model;

public class MoodData {

    private int mood;
    private String comment;
    private String when;


    //CONSTRUCTOR
    public MoodData(int mood, String comment, String when) {
        this.mood = mood;
        this.comment = comment;
        this.when = when;

    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return mood + " : " + comment + " : " + when;
        
    }
}
