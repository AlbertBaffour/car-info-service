package project.apt.carinfoservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.apt.carinfoservice.model.CarInfo;
import project.apt.carinfoservice.repository.CarInfoRepository;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static project.apt.carinfoservice.model.CarInfo.portierOptie.TWEEDEURS;
import static project.apt.carinfoservice.model.CarInfo.portierOptie.VIERDEURS;


@SpringBootTest
@AutoConfigureMockMvc
class CarInfoControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarInfoRepository carInfoRepository;


    private ObjectMapper mapper = new ObjectMapper();

    private CarInfo carInfo1 = new CarInfo("audi","a3","1VQW871","5", TWEEDEURS);
    private CarInfo carInfo2 = new CarInfo("vw","golf5","1VCJ854","5",VIERDEURS);
    private CarInfo carInfo3 = new CarInfo("audi","r8","1ABC123","4",TWEEDEURS);

    @BeforeEach
     void beforeAllTests() {
        carInfoRepository.deleteAll();
        carInfoRepository.save(carInfo1);
        carInfoRepository.save(carInfo2);
        carInfoRepository.save(carInfo3);
    }
    @AfterEach
     void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        carInfoRepository.deleteAll();
    }
    @Test
     void givenCarInfo_whenGetCarInfos_thenReturnJsonCarInfos() throws Exception {

        mockMvc.perform(get("/cars"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].type",is("a3")))
                .andExpect(jsonPath("$[0].licensePlate",is("1VQW871")))
                .andExpect(jsonPath("$[0].merk",is("audi")))
                .andExpect(jsonPath("$[1].type",is("golf5")))
                .andExpect(jsonPath("$[1].licensePlate",is("1VCJ854")))
                .andExpect(jsonPath("$[1].merk",is("vw")))
                .andExpect(jsonPath("$[2].type",is("r8")));
    }
    @Test
     void givenCarInfo_whenGetCarInfoByLicensePlate_thenReturnJsonCarInfo() throws Exception {

        mockMvc.perform(get("/cars/licensePlate/{licensePlate}","1VQW871"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merk",is("audi")))
                .andExpect(jsonPath("$.licensePlate",is("1VQW871")))
                .andExpect(jsonPath("$.type",is("a3")));
    }
    @Test
     void givenCarInfo_whenGetCarInfoByEuroNorm_thenReturnJsonCarInfo() throws Exception {

        mockMvc.perform(get("/cars/euroNorm/{euroNorm}", "5"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].type",is("a3")))
                .andExpect(jsonPath("$[0].licensePlate",is("1VQW871")))
                .andExpect(jsonPath("$[0].merk",is("audi")))
                .andExpect(jsonPath("$[1].type",is("golf5")))
                .andExpect(jsonPath("$[1].licensePlate",is("1VCJ854")))
                .andExpect(jsonPath("$[1].merk",is("vw")));
    }
    @Test
     void givenCarInfo_whenGetCarInfoByMerk_thenReturnJsonCarInfo() throws Exception {

        mockMvc.perform(get("/cars/merk/{merk}", "audi"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].type",is("r8")))
                .andExpect(jsonPath("$[0].type",is("a3")))
                .andExpect(jsonPath("$[0].licensePlate",is("1VQW871")))
                .andExpect(jsonPath("$[0].merk",is("audi")));
    }

    @Test
     void givenCarInfo_whenGetCarInfoByType_thenReturnJsonCarInfo() throws Exception {

        mockMvc.perform(get("/cars/type/{type}", "a3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type",is("a3")))
                .andExpect(jsonPath("$[0].licensePlate",is("1VQW871")))
                .andExpect(jsonPath("$[0].merk",is("audi")));
    }

    @Test
     void givenCarInfo_whenGetCarInfoByPortier_thenReturnJsonCarInfo() throws Exception {

        mockMvc.perform(get("/cars/portier/{portier}", "TWEEDEURS"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].type",is("r8")))
                .andExpect(jsonPath("$[0].type",is("a3")))
                .andExpect(jsonPath("$[0].licensePlate",is("1VQW871")))
                .andExpect(jsonPath("$[0].portier",is("TWEEDEURS")));
    }

    @Test
     void givenCarInfo_whenGetCarInfoByMerkEnType_thenReturnJsonCarInfo() throws Exception {

        mockMvc.perform(get("/cars/merk/{merk}/type/{type}", "audi","a3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].merk",is("audi")))
                .andExpect(jsonPath("$[0].type",is("a3")))
                .andExpect(jsonPath("$[0].licensePlate",is("1VQW871")));
    }
    @Test
     void whenPostCarInfo_thenReturnJsonCarInfo() throws Exception{
        CarInfo carInfo = new CarInfo("kia","optima","1ABC321","5",VIERDEURS);

        mockMvc.perform(post("/cars")
                .content(mapper.writeValueAsString(carInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merk",is("kia")))
                .andExpect(jsonPath("$.licensePlate",is("1ABC321")))
                .andExpect(jsonPath("$.type",is("optima")))
                .andExpect(jsonPath("$.portier",is("VIERDEURS")));
    }

    @Test
     void givenCarInfo_whenPutCarInfo_thenReturnJsonCarInfo() throws Exception{

        CarInfo updatedCarInfo =  new CarInfo("audi","a5","1VQW871","6", TWEEDEURS);

        mockMvc.perform(put("/cars")
                .content(mapper.writeValueAsString(updatedCarInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type",is("a5")))
                .andExpect(jsonPath("$.euroNorm",is("6")))
                .andExpect(jsonPath("$.merk",is("audi")));
    }
    @Test
     void givenCarInfo_whenDeleteCarInfo_thenStatusOk() throws Exception{

        mockMvc.perform(delete("/cars/licensePlate/{licensePlate}","1VQW871")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void givenNoCarInfo_whenDeleteCarInfo_thenStatusNotFound() throws Exception{

        mockMvc.perform(delete("/cars/licensePlate/{licensePlate}","1KBC871")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
