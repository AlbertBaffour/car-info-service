package project.apt.carinfoservice.model;

public class CarInfoDTO {


    private Integer id;
    private String merk;
    private String type;
    private String licensePlate;
    private String euroNorm;
    private CarInfo.portierOptie portier;

    public CarInfoDTO() {}

    public CarInfoDTO(String merk, String type, String licensePlate, String euroNorm, CarInfo.portierOptie portier) {
        this.merk = merk;
        this.type = type;
        this.licensePlate = licensePlate;
        this.euroNorm = euroNorm;
        this.portier = portier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getEuroNorm() {
        return euroNorm;
    }

    public void setEuroNorm(String euroNorm) {
        this.euroNorm = euroNorm;
    }

    public CarInfo.portierOptie getPortier() {
        return portier;
    }

    public void setPortier(CarInfo.portierOptie portier) {
        this.portier = portier;
    }
}
