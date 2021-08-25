package Model;

public class CameraVO {

    private int camera_numbering;
    private String camera_address;

    public CameraVO(int camera_numbering, String camera_address) {
        this.camera_numbering = camera_numbering;
        this.camera_address = camera_address;
    }

    public int getCamera_numbering() {
        return camera_numbering;
    }

    public void setCamera_numbering(int camera_numbering) {
        this.camera_numbering = camera_numbering;
    }

    public String getCamera_address() {
        return camera_address;
    }

    public void setCamera_address(String camera_address) {
        this.camera_address = camera_address;
    }
}
