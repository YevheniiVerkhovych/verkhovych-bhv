package my.task.test;

import my.task.test.config.DataAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataAppConfig.class })
@WebAppConfiguration
@ActiveProfiles("dev")
public class ExceptionHandlerTest {

    private final static String KEY_FIRST = "T";
    private final static String VALUE_FIRST = "Tom Sawyer";
    private final static String KEY_SECOND = "J";
    private final static String VALUE_SECOND = "James Gosling";
    private final static String NSTRING = null;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(post("/api/data")
                .param("key", KEY_FIRST)
                .param("value", VALUE_FIRST))
                .andDo(print());
    }

    @Test
    public void getRequestWithWrongParameter_PassedIfStatusIsNotFound() throws Exception {
        this.mockMvc.perform(get("/api/data")
                .param("key", KEY_SECOND))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void postRequestWithExistingParameter_PassedIfStatusIsConflict() throws Exception {
        this.mockMvc.perform(post("/api/data")
                .param("key", KEY_FIRST)
                .param("value", VALUE_FIRST))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void putRequestWithWrongParameter_PassedIfStatusIsNotFound() throws Exception {
        this.mockMvc.perform(put("/api/data")
                .param("key", KEY_SECOND)
                .param("value", VALUE_SECOND))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void deleteRequestWithWrongParameter_PassedIfStatusIsNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/data")
                .param("key", KEY_SECOND))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void deleteRequestWithNullParameter_PassedIfStatusIsBadRequest() throws Exception {
        String nString = null;
        this.mockMvc.perform(delete("/api/data")
                .param("key", NSTRING))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}


