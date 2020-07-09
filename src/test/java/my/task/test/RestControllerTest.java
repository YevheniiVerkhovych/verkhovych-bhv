package my.task.test;
import my.task.test.config.DataAppConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.ServletContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataAppConfig.class })
@WebAppConfiguration
@ActiveProfiles("dev")
public class RestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Before
    public void addDataKeyValueForTesting() throws Exception {
        this.mockMvc.perform(post("/api/data")
                .param("key", "J")
                .param("value", "John Doe"))
                .andDo(print());
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesDataRestController() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("dataRestController"));
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


