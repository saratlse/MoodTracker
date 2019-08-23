package sara.openclassrooms.moodtracker.Controller;

public class MoodData {
    private int ID_;
    private int MOOD;
    private String COMMENT;
    private String WHEN_;


    //CONSTRUCTOR
    public MoodData (int ID_, int MOOD, String COMMENT,String WHEN_){
        this.ID_=ID_;
        this.MOOD =MOOD;
        this.COMMENT = COMMENT;
        this.WHEN_ = WHEN_;

    }

    public void setID_(int id_){
        this.ID_ = id_;
    }

    public int getID_() {
        return ID_;
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

    @Override
    public String toString(){
        return ID_ + ":"+MOOD+":"+COMMENT+":"+WHEN_;
    }

}
