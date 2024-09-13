package by.clevertec.controller;

import by.clevertec.common.CakeType;
import by.clevertec.domain.Cake;
import by.clevertec.service.CakeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CakeController.class)
class CakeControllerTest {
    @MockBean
    private CakeService cakeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {

        //given
        when(cakeService.getCake())
                .thenReturn(List.of(new Cake(), new Cake()));

        //when then
        mockMvc.perform(get("/api/getCake"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void findById() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        when(cakeService.getCakeById(id)).thenReturn(new Cake(id, "Cake 4", CakeType.BIG, OffsetDateTime.now()));

        //when then
        mockMvc.perform(get("/api/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void update() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        Cake updatedCake = new Cake(id, "Simple Cake", CakeType.BIG, OffsetDateTime.now());

        when(cakeService.update(Mockito.eq(id), Mockito.any(Cake.class))).thenReturn(updatedCake);

        // when  then
        mockMvc.perform(put("/api/up/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));

    }


    @Test
    void deleteCake() throws Exception {
        //given
        UUID id = UUID.randomUUID();

        doNothing().when(cakeService).delete(id);

        // when & then
        mockMvc.perform(delete("/api/del/{id}", id))
                .andExpect(status().isNoContent());
    }

}
