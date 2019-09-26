package sara.openclassrooms.moodtracker.Controller;

import sara.openclassrooms.moodtracker.Model.DifferentsMoods;

public class MoodData {
    private int Id_;
    private int MOOD;
    private String COMMENT;
    private String WHEN_;
    private int differentsMoods;


    //CONSTRUCTOR
    public MoodData ( int MOOD, String COMMENT,String WHEN_,int ID_){
        this.MOOD =MOOD;
        this.COMMENT = COMMENT;
        this.WHEN_ = WHEN_;
        this.Id_ = ID_;


    }

    public int getId_() {
        return Id_;
    }
    public void setId_(int id_){
        this.Id_= id_;
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

        return Id_ + " : " + MOOD + " : " + COMMENT + " : " + WHEN_;


    }
}
