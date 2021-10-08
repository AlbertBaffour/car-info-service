package project.apt.carinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.apt.carinfoservice.model.CarInfo;
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
            CarInfo car1 = new CarInfo("Volkswagen", "Golf 5", "1VQW871", "Euro 4", CarInfo.portierOptie.VIERDEURS);
            carInfoRepository.save(car1);

            CarInfo car2 = new CarInfo("Audi", "R8", "1VQW871", "Euro 1", CarInfo.portierOptie.VIERDEURS);
            carInfoRepository.save(car2);
        }

        //DB TEST
        System.out.println("Cars: " + carInfoRepository.findAll().size());
    }

    @GetMapping("/cars")
    public List<CarInfo> getAllCars() {
        return carInfoRepository.findAll();
    }

    @GetMapping("/car/{licensePlate}")
    public CarInfo findCarByLicensePlate(@PathVariable String licensePlate) {
        return carInfoRepository.findCarInfoByLicensePlate(licensePlate);
    }

    @GetMapping("/cars/{euroNorm}")
    public List<CarInfo> getCarsByEuroNorm(@PathVariable String euroNorm) {
        return carInfoRepository.findCarInfosByEuroNorm(euroNorm);
    }

    @GetMapping("/cars/{merk}")
    public List<CarInfo> getCarsByMerk(@PathVariable String merk) {
        return carInfoRepository.findCarInfosByMerk(merk);
    }

    @GetMapping("/cars/{type}")
    public List<CarInfo> getCarsByType(@PathVariable String type) {
        return carInfoRepository.findCarInfosByType(type);
    }

    @GetMapping("/cars/{portier}")
    public List<CarInfo> getCarsByPortier(@PathVariable CarInfo.portierOptie portier) {
        return carInfoRepository.findCarInfosByPortier(portier);
    }

    @GetMapping("/cars/{merk}/{type}")
    public List<CarInfo> getCarsByMerkAndType(@PathVariable String merk, @PathVariable String type) {
        return carInfoRepository.findCarInfosByMerkAndType(merk, type);
    }

    @PostMapping("/car")
    public CarInfo addCar(@RequestBody CarInfo carInfo) {
        carInfoRepository.save(carInfo);
        return carInfo;
    }

    @PutMapping("/car/{licensePlate}")
    public CarInfo updateCarInfo(@PathVariable String licensePlate, @RequestBody CarInfo carInfo) {
        CarInfo retrievedCarInfo = carInfoRepository.findCarInfoByLicensePlate(licensePlate);
        retrievedCarInfo.setLicensePlate(carInfo.getLicensePlate());
        retrievedCarInfo.setEuroNorm(carInfo.getEuroNorm());
        retrievedCarInfo.setMerk(carInfo.getMerk());
        retrievedCarInfo.setType(carInfo.getType());
        retrievedCarInfo.setPortier(carInfo.getPortier());
        carInfoRepository.save(retrievedCarInfo);

        return retrievedCarInfo;
    }

    @DeleteMapping("/car/{licensePlate}")
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