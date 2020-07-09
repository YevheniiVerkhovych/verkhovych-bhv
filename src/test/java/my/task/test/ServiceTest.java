package my.task.test;

import my.task.test.exceptions.DataNotFoundException;
import my.task.test.repository.KeyValueRepo;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ActiveProfiles("dev")
public class ServiceTest {

    private final static String KEY = "T";

    @Mock
    private KeyValueRepo repoImpl;

    @InjectMocks
    private DataServiceImpl servceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getExistDataCorrectValueExpected() {

        when(repoImpl.getData(KEY)).thenReturn("Test");

        assertEquals("Test", servceImpl.getData(KEY));

        when(servceImpl.getData(null))
                .thenThrow(new DataNotFoundException());

//        servceImpl.getData("T");
//
//        verify(repoImpl).getData("T");
    }

//    @Test
//    public void getNotExistData_NullReturnExpected() {
//
//        when(repoImpl.getData("T")).thenReturn(null);
//
//
//        //verify(repoImpl).getData("T");
//    }

//    @Test
//    public void saveData() {
//
//       when(repoImpl.saveData("T")).thenReturn("Test");
//
//       verify(repoImpl).getData(KEY);
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
