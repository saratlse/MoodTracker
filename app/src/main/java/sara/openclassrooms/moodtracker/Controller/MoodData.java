package sara.openclassrooms.moodtracker.Controller;

public class MoodData {

    private int MOOD;
    private String COMMENT;
    private String WHEN_;


    //CONSTRUCTOR
    public MoodData(int MOOD, String COMMENT, String WHEN_) {
        this.MOOD = MOOD;
        this.COMMENT = COMMENT;
        this.WHEN_ = WHEN_;

    }

    public int getMOOD() {
        return MOOD;
    }

    public void setMOOD(int mood) {
        this.MOOD = mood;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String comment) {
        this.COMMENT = comment;
    }

    public void setWHEN_(String when_) {
        this.WHEN_ = when_;
    }

    @Override
    public String toString() {
        return MOOD + " : " + COMMENT + " : " + WHEN_;


    }
}
