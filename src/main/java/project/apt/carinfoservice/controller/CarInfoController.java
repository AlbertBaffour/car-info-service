package project.apt.carinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.apt.carinfoservice.model.CarInfo;
import project.apt.carinfoservice.model.CarInfoDTO;
import project.apt.carinfoservice.repository.CarInfoRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CarInfoController {

    @Autowired
    private CarInfoRepository carInfoRepository;

    @PostConstruct
    public void fillDB() {
        if (carInfoRepository.count() == 0) {
            CarInfo car1 = new CarInfo("Volkswagen", "Golf 5", "1VQW871", "4", CarInfo.portierOptie.VIERDEURS);
            carInfoRepository.save(car1);

            CarInfo car2 = new CarInfo("Audi", "R8", "1VCJ543", "5", CarInfo.portierOptie.VIERDEURS);
            carInfoRepository.save(car2);
        }

        //DB TEST
        //System.out.println("Cars: " + carInfoRepository.findAll().size());
    }

    @GetMapping("/cars")
    public List<CarInfo> getAllCars() {
        return carInfoRepository.findAll();
    }

    @GetMapping("/cars/license_plate/{licensePlate}")
    public CarInfo findCarByLicensePlate(@PathVariable String licensePlate) {
        return carInfoRepository.findCarInfoByLicensePlate(licensePlate);
    }

    @GetMapping("/cars/merk/{merk}")
    public List<CarInfo> getCarsByMerk(@PathVariable String merk) {
        return carInfoRepository.findCarInfosByMerk(merk);
    }

    @GetMapping("/cars/portier/{portier}")
    public List<CarInfo> getCarsByPortier(@PathVariable CarInfo.portierOptie portier) {
        return carInfoRepository.findCarInfosByPortier(portier);
    }

    @PostMapping("/cars")
    public CarInfo addCar(@RequestBody CarInfoDTO carInfoDTO) {
        CarInfo carInfo = new CarInfo(carInfoDTO.getMerk(),carInfoDTO.getType(),carInfoDTO.getLicensePlate(),carInfoDTO.getEuroNorm(),carInfoDTO.getPortier());
        carInfoRepository.save(carInfo);
        return carInfo;
    }

    @PutMapping("/cars")
    public CarInfo updateCarInfo(@RequestBody CarInfoDTO carInfo) {
            CarInfo retrievedCarInfo = carInfoRepository.findCarInfoByLicensePlate(carInfo.getLicensePlate());
            retrievedCarInfo.setLicensePlate(carInfo.getLicensePlate());
            retrievedCarInfo.setEuroNorm(carInfo.getEuroNorm());
            retrievedCarInfo.setMerk(carInfo.getMerk());
            retrievedCarInfo.setType(carInfo.getType());
            retrievedCarInfo.setPortier(carInfo.getPortier());
            carInfoRepository.save(retrievedCarInfo);

            return retrievedCarInfo;
    }

    @DeleteMapping("/cars/license_plate/{licensePlate}")
    public ResponseEntity deleteCarInfo(@PathVariable String licensePlate) {
        CarInfo carInfo = carInfoRepository.findCarInfoByLicensePlate(licensePlate);
        if (carInfo != null) {
            carInfoRepository.delete(carInfo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
