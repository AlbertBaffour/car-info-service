package project.apt.carinfoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apt.carinfoservice.model.CarInfo;

import java.util.List;

@Repository
public interface CarInfoRepository extends JpaRepository<CarInfo, Integer> {

    List<CarInfo> findCarInfosByMerk(String merk);
    List<CarInfo> findCarInfosByEuroNorm(String euroNorm);
    List<CarInfo> findCarInfosByPortier(CarInfo.portierOptie portier);
    List<CarInfo> findCarInfosByType(String type);

    CarInfo findCarInfoByLicensePlate(String licensePlate);


}
