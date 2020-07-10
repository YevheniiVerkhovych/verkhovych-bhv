package my.task.test;
import my.task.test.controller.DataRestController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class RestControllerTest {


    @Mock
    private DataService dataService;

    @InjectMocks
    private DataRestController dataRestController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dataRestController).build();
    }


    @Test
    public void getRequestWithParameter_PassedIfValueExists() throws Exception {
        this.mockMvc.perform(get("/api/data")
                .param("key", "J"))
                .andExpect(content().string("John Doe"))
                .andDo(print());
    }

    @Test
    public void postRequestWithParameters_PassedIfStatusOKANDReturnedValueCorresponds() throws Exception {
        this.mockMvc.perform(post("/api/data")
                .param("key", "M")
                .param("value", "Mark Twain"))
                .andExpect(content().string("Mark Twain"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void putRequestWithParameter_PassedIfValueReturnedAndStausIsOK() throws Exception {
        this.mockMvc.perform(put("/api/data")
                .param("key", "J")
                .param("value", "John Doe 2"))
                .andExpect(content().string("John Doe 2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteRequestWithParameter_PassedIfStatusIsOK() throws Exception {
        //MvcResult result =
        this.mockMvc.perform(delete("/api/data")
                .param("key", "J"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}


