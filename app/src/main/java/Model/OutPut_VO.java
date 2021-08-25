package Model;

public class OutPut_VO {
    private int numbering;
    private String date;
    private int prod;


    public OutPut_VO(int numbering, String date, int prod) {
        this.numbering = numbering;
        this.date = date;
        this.prod = prod;
    }

    public int getNumbering() {
        return numbering;
    }

    public void setNumbering(int numbering) {
        this.numbering = numbering;
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
