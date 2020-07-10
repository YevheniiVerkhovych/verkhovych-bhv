package my.task.test;
import my.task.test.controller.Controller;
import my.task.test.service.DataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    private final static String KEY = "M";
    private final static String VALUE = "Mark Twain";

    @Mock
    private DataService dataService;

    @InjectMocks
    private Controller controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getRequestWithParameter_PassedIfValueExists() throws Exception {
        when(dataService.getData(KEY)).thenReturn(VALUE);
        this.mockMvc.perform(get("/api/data")
                .param("key", KEY))
                .andExpect(content().string(VALUE))
                .andDo(print());
        verify(dataService).getData(KEY);
    }


    @Test
    public void postRequestWithParameters_PassedIfStatusOKANDReturnedValueCorresponds() throws Exception {
        when(dataService.saveData(KEY, VALUE)).thenReturn(VALUE);
        this.mockMvc.perform(post("/api/data")
                .param("key", KEY)
                .param("value", VALUE))
                .andExpect(content().string(VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void putRequestWithParameter_PassedIfValueReturnedAndStatusIsOK() throws Exception {
        when(dataService.updateData(KEY, VALUE)).thenReturn(VALUE);
        this.mockMvc.perform(put("/api/data")
                .param("key", KEY)
                .param("value", VALUE))
                .andExpect(content().string(VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteRequestWithParameter_PassedIfStatusIsOK() throws Exception {
        this.mockMvc.perform(delete("/api/data")
                .param("key", KEY))
                .andExpect(status().isOk())
                .andDo(print());
    }
}


