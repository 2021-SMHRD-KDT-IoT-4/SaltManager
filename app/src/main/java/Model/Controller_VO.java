package Model;

public class Controller_VO {

    private int c_numbering;
    private int c_fan;
    private int c_pump;
    private int c_wire;
    private int c_pusher;
    private int c_conveyer;
    private int c_light;
    private int c_camera;
    private int c_part;

    public Controller_VO(int c_numbering, int c_fan, int c_pump, int c_wire, int c_pusher, int c_conveyer, int c_light, int c_camera, int c_part) {
        this.c_numbering = c_numbering;
        this.c_fan = c_fan;
        this.c_pump = c_pump;
        this.c_wire = c_wire;
        this.c_pusher = c_pusher;
        this.c_conveyer = c_conveyer;
        this.c_light = c_light;
        this.c_camera = c_camera;
        this.c_part = c_part;
    }

    public int getC_numbering() {
        return c_numbering;
    }

    public void setC_numbering(int c_numbering) {
        this.c_numbering = c_numbering;
    }

    public int getC_fan() {
        return c_fan;
    }

    public void setC_fan(int c_fan) {
        this.c_fan = c_fan;
    }

    public int getC_pump() {
        return c_pump;
    }

    public void setC_pump(int c_pump) {
        this.c_pump = c_pump;
    }

    public int getC_wire() {
        return c_wire;
    }

    public void setC_wire(int c_wire) {
        this.c_wire = c_wire;
    }

    public int getC_pusher() {
        return c_pusher;
    }

    public void setC_pusher(int c_pusher) {
        this.c_pusher = c_pusher;
    }

    public int getC_conveyer() {
        return c_conveyer;
    }

    public void setC_conveyer(int c_conveyer) {
        this.c_conveyer = c_conveyer;
    }

    public int getC_light() {
        return c_light;
    }

    public void setC_light(int c_light) {
        this.c_light = c_light;
    }

    public int getC_camera() {
        return c_camera;
    }

    public void setC_camera(int c_camera) {
        this.c_camera = c_camera;
    }

    public int getC_part() {
        return c_part;
    }

    public void setC_part(int c_part) {
        this.c_part = c_part;
    }
}