package project.apt.carinfoservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarInfo {

    public enum portierOptie {
        TWEEDEURS,
        VIERDEURS;
    } 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String merk;
    private String type;
    private String licensePlate;
    private String euroNorm;
    private portierOptie portier;

    public CarInfo() {}

    public CarInfo(String merk, String type, String licensePlate, String euroNorm, portierOptie portier) {
        this.merk = merk;
        this.type = type;
        this.licensePlate = licensePlate;
        this.euroNorm = euroNorm;
        this.portier = portier;
    }

    public Integer getId() {
        return id;
    }

    //public void setId(Integer id) {
    //    this.id = id;
    //}

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

    public portierOptie getPortier() {
        return portier;
    }

    public void setPortier(portierOptie portier) {
        this.portier = portier;
    }
}
