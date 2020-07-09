package my.task.test;

import my.task.test.repository.KeyValueRepoImpl;
import my.task.test.service.DataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static junit.framework.TestCase.*;

@ActiveProfiles("dev")
public class ServiceTest {

    @Mock
    private KeyValueRepoImpl repoImpl;

    @InjectMocks
    private DataServiceImpl servceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getExistData() {

        Mockito.when(repoImpl.getData("T")).thenReturn("Test");

        assertEquals("Test", servceImpl.getData("T"));
    }

    @Test
    public void getNotExistData() {

        Mockito.when(repoImpl.getData("T")).thenReturn(null);

        assertNull(servceImpl.getData("T"));
    }

//    @Test
//    public void saveData() {
//
//        Mockito.verify(repoImpl.saveData("T", "Thomson"), times(1));
//
//        verify(mock, only()).someMethod("no other method has been called on the mock");
//
//
//    }
//
//    @Test
//    public void updateData() {
//
//        Mockito.when(repoImpl.getData("T")).thenReturn("Test");
//
//        assertEquals("Test", servceImpl.getData("T"));
//
//    }
//
//    @Test
//    public void deleteData() {
//
//        Mockito.when(repoImpl.deleteData("T")).thenReturn("Test");
//
//        assertEquals("Test", servceImpl.getData("T"));
//
//    }

}
