package Model;

public class Z_List_VO {

    private int numbering = 0; // 염전번호
    private int z_salinity = 0; // 염도
    private int z_indoor_temp = 0; // 실내온도
    private int z_indoor_humid = 0; // 실내습도
    private int z_water_temp = 0; // 소금물 온도
    private int z_wire_temp = 0; // 바닥열선온도
    private int z_water_high = 0; // 수위정보
    private int z_place_size = 0; // 염전크기
    private int z_pump_move = 0; // 펌프기동

    public Z_List_VO(int numbering, int z_salinity, int z_indoor_temp, int z_indoor_humid, int z_water_temp, int z_wire_temp, int z_water_high, int z_place_size, int z_pump_move) {
        this.numbering = numbering;
        this.z_salinity = z_salinity;
        this.z_indoor_temp = z_indoor_temp;
        this.z_indoor_humid = z_indoor_humid;
        this.z_water_temp = z_water_temp;
        this.z_wire_temp = z_wire_temp;
        this.z_water_high = z_water_high;
        this.z_place_size = z_place_size;
        this.z_pump_move = z_pump_move;
    }

    public int getNumbering() {
        return numbering;
    }

    public void setNumbering(int numbering) {
        this.numbering = numbering;
    }

    public int getZ_salinity() {
        return z_salinity;
    }

    public void setZ_salinity(int z_salinity) {
        this.z_salinity = z_salinity;
    }

    public int getZ_indoor_temp() {
        return z_indoor_temp;
    }

    public void setZ_indoor_temp(int z_indoor_temp) {
        this.z_indoor_temp = z_indoor_temp;
    }

    public int getZ_indoor_humid() {
        return z_indoor_humid;
    }

    public void setZ_indoor_humid(int z_indoor_humid) {
        this.z_indoor_humid = z_indoor_humid;
    }

    public int getZ_water_temp() {
        return z_water_temp;
    }

    public void setZ_water_temp(int z_water_temp) {
        this.z_water_temp = z_water_temp;
    }

    public int getZ_wire_temp() {
        return z_wire_temp;
    }

    public void setZ_wire_temp(int z_wire_temp) {
        this.z_wire_temp = z_wire_temp;
    }

    public int getZ_water_high() {
        return z_water_high;
    }

    public void setZ_water_high(int z_water_high) {
        this.z_water_high = z_water_high;
    }

    public int getZ_place_size() {
        return z_place_size;
    }

    public void setZ_place_size(int z_place_size) {
        this.z_place_size = z_place_size;
    }

    public int getZ_pump_move() {
        return z_pump_move;
    }

    public void setZ_pump_move(int z_pump_move) {
        this.z_pump_move = z_pump_move;
    }
}