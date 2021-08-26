package Model;

public class OutPut_VO {

    private String date;
    private int prod;


    public OutPut_VO( String date, int prod) {
        this.date = date;
        this.prod = prod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProd() {
        return prod;
    }

    public void setProd(int prod) {
        this.prod = prod;
    }
}
