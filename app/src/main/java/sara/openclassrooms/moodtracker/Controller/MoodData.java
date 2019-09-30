package sara.openclassrooms.moodtracker.Controller;

public class MoodData {

    private int MOOD;
    private String COMMENT;
    private String WHEN_;
    private int differentsMoods;


    //CONSTRUCTOR
    public MoodData(int MOOD, String COMMENT, String WHEN_){
        this.MOOD =MOOD;
        this.COMMENT = COMMENT;
        this.WHEN_ = WHEN_;



    }



    public void setMOOD(int mood){
    this.MOOD = mood;
    }
    public int getMOOD() {
        return MOOD;
    }

    public void setCOMMENT(String comment) {
        this.COMMENT = comment;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setWHEN_(String when_) {
        this.WHEN_ = when_;
    }

    public String getWHEN_() {
        return WHEN_;
    }
    public int getDifferentsMoods(){
        return differentsMoods;
    }

    public void setDifferentsMoods(int differentsMoods){
        this.differentsMoods = differentsMoods;
    }

    @Override
    public String toString() {

        return MOOD + " : " + COMMENT + " : " + WHEN_;


    }
}
