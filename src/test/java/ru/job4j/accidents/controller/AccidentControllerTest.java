package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService accidentService;

    @MockBean
    private AccidentTypeService accidentTypeService;

    @MockBean
    private RuleService ruleService;

    @Test
    @WithMockUser
    void checkAccidents() throws Exception {
        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents"));
    }

    @Test
    @WithMockUser
    void checkViewCreateAccident() throws Exception {
        this.mockMvc.perform(get("/addAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    void checkUpdateAccidentGet() throws Exception {
        given(this.accidentService.findById(1)).willReturn(Accident.of("name", "text",
                "address", AccidentType.of("typeName")));
        this.mockMvc.perform(get("/formUpdateAccident?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editAccident"));
    }

    @Test
    @WithMockUser
    void checkSave() throws Exception {
        given(this.accidentTypeService.findById(1)).willReturn(AccidentType.of("typeName"));
        given(this.ruleService.findById(1)).willReturn(Rule.of("ruleName"));
        this.mockMvc.perform(post("/saveAccident")
                        .param("name", "nameAccident")
                        .param("text", "textAccident")
                        .param("address", "addressAccident")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).add(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("nameAccident");
        assertThat(argument.getValue().getText()).isEqualTo("textAccident");
        assertThat(argument.getValue().getAddress()).isEqualTo("addressAccident");
        assertThat(argument.getValue().getType().getName()).isEqualTo("typeName");
        assertThat(argument.getValue().getRules()).hasSize(1);
        assertThat(argument.getValue().getRules())
                .first().matches(rule -> rule.getName().equals("ruleName"));

    }

    @Test
    @WithMockUser
    void checkUpdate() throws Exception {
        given(this.accidentTypeService.findById(1)).willReturn(AccidentType.of("typeName"));
        given(this.ruleService.findById(1)).willReturn(Rule.of("ruleName1"));
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "1")
                        .param("name", "nameAccident")
                        .param("text", "textAccident")
                        .param("address", "addressAccident")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).update(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("nameAccident");
        assertThat(argument.getValue().getText()).isEqualTo("textAccident");
        assertThat(argument.getValue().getAddress()).isEqualTo("addressAccident");
        assertThat(argument.getValue().getType().getName()).isEqualTo("typeName");
        assertThat(argument.getValue().getRules()).hasSize(1);
        assertThat(argument.getValue().getRules())
                .first().matches(rule -> rule.getName().equals("ruleName1"));
    }
}