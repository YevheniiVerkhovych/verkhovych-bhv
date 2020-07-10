package my.task.test.controller;
import my.task.test.controller.KeyValueController;
import my.task.test.service.KeyValueService;
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
public class KeyValueControllerTest {
    private final static String KEY = "key";
    private final static String VALUE = "value";
    private final static String KEY_STRING = "M";
    private final static String VALUE_STRING = "Mark Twain";
    private final static String PATH = "/api/data";

    @Mock
    private KeyValueService keyValueService;

    @InjectMocks
    private KeyValueController keyValueController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(keyValueController).build();
    }

    @Test
    public void getRequestWithParameter_PassedIfValueExists() throws Exception {
        when(keyValueService.getData(KEY_STRING)).thenReturn(VALUE_STRING);
        this.mockMvc.perform(get(PATH)
                .param(KEY, KEY_STRING))
                .andExpect(content().string(VALUE_STRING))
                .andDo(print());
        verify(keyValueService).getData(KEY_STRING);
    }


    @Test
    public void postRequestWithParameters_PassedIfStatusOKANDReturnedValueCorresponds() throws Exception {
        when(keyValueService.saveData(KEY_STRING, VALUE_STRING)).thenReturn(VALUE_STRING);
        this.mockMvc.perform(post(PATH)
                .param(KEY, KEY_STRING)
                .param(VALUE, VALUE_STRING))
                .andExpect(content().string(VALUE_STRING))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void putRequestWithParameter_PassedIfValueReturnedAndStatusIsOK() throws Exception {
        when(keyValueService.updateData(KEY_STRING, VALUE_STRING)).thenReturn(VALUE_STRING);
        this.mockMvc.perform(put(PATH)
                .param(KEY, KEY_STRING)
                .param(VALUE, VALUE_STRING))
                .andExpect(content().string(VALUE_STRING))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteRequestWithParameter_PassedIfStatusIsOK() throws Exception {
        this.mockMvc.perform(delete(PATH)
                .param(KEY, KEY_STRING))
                .andExpect(status().isOk())
                .andDo(print());
    }
}


