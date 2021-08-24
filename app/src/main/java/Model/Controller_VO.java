package Model;

public class Controller_VO {

    private String c_numbering;
    private String c_fan;
    private String c_pump;
    private String c_wire;
    private String c_pusher;
    private String c_conveyer;
    private String c_light;
    private String c_camera;

    public Controller_VO(String c_numbering, String c_fan, String c_pump, String c_wire, String c_pusher, String c_conveyer, String c_light, String c_camera) {
        this.c_numbering = c_numbering;
        this.c_fan = c_fan;
        this.c_pump = c_pump;
        this.c_wire = c_wire;
        this.c_pusher = c_pusher;
        this.c_conveyer = c_conveyer;
        this.c_light = c_light;
        this.c_camera = c_camera;
    }


    public String getC_numbering() {
        return c_numbering;
    }

    public void setC_numbering(String c_numbering) {
        this.c_numbering = c_numbering;
    }

    public String getC_fan() {
        return c_fan;
    }

    public void setC_fan(String c_fan) {
        this.c_fan = c_fan;
    }

    public String getC_pump() {
        return c_pump;
    }

    public void setC_pump(String c_pump) {
        this.c_pump = c_pump;
    }

    public String getC_wire() {
        return c_wire;
    }

    public void setC_wire(String c_wire) {
        this.c_wire = c_wire;
    }

    public String getC_pusher() {
        return c_pusher;
    }

    public void setC_pusher(String c_pusher) {
        this.c_pusher = c_pusher;
    }

    public String getC_conveyer() {
        return c_conveyer;
    }

    public void setC_conveyer(String c_conveyer) {
        this.c_conveyer = c_conveyer;
    }

    public String getC_light() {
        return c_light;
    }

    public void setC_light(String c_light) {
        this.c_light = c_light;
    }

    public String getC_camera() {
        return c_camera;
    }

    public void setC_camera(String c_camera) {
        this.c_camera = c_camera;
    }
}