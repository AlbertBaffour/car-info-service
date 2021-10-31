package project.apt.carinfoservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.apt.carinfoservice.model.CarInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static project.apt.carinfoservice.model.CarInfo.portierOptie.TWEEDEURS;

@SpringBootTest
class CarInfoServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getCarInfo_happy(){
        final CarInfo carInfo= new CarInfo("audi","a3","1VQW871","5", TWEEDEURS);
        assertThat(carInfo).isNotNull();
        assertThat(carInfo.getMerk()).isEqualTo("audi");
        assertThat(carInfo.getType()).isEqualTo("a3");
        assertThat(carInfo.getEuroNorm()).isEqualTo("5");
    }
    @Test
    void getSetCarInfo_happy(){
        final CarInfo carInfo= new CarInfo();
        carInfo.setMerk("audi");
        carInfo.setEuroNorm("5");
        carInfo.setPortier(TWEEDEURS);
        assertThat(carInfo).isNotNull();
        assertThat(carInfo.getMerk()).isEqualTo("audi");
        assertThat(carInfo.getEuroNorm()).isEqualTo("5");
        assertThat(carInfo.getPortier()).isEqualTo(TWEEDEURS);
    }
}
