package my.task.test.service;
import my.task.test.exceptions.DataAlreadyExistException;
import my.task.test.exceptions.DataNotFoundException;
import my.task.test.repository.KeyValueRepo;
import my.task.test.service.KeyValueServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ActiveProfiles("dev")
public class KeyValueServiceTest {

    private final static String KEY = "T";
    private final static String VALUE = "Test";
    private final static String NEWVALUE = "TestUpdated";

    @Mock
    private KeyValueRepo repoImpl;

    @InjectMocks
    private KeyValueServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getExistsDataCorrectValueExpected() {
        when(repoImpl.getData(KEY)).thenReturn(VALUE);
        assertNotNull(serviceImpl.getData(KEY));
        assertEquals(VALUE, serviceImpl.getData(KEY));
        verify(repoImpl, times(4)).getData(KEY);
    }

    @Test(expected = DataNotFoundException.class)
    public void getNotExistsDataExceptionThrown() {
        when(repoImpl.getData(anyString())).thenReturn(null);
        serviceImpl.getData(KEY);
    }
    @Test
    public void saveExistsDataCorrectValueExpected() {
        when(repoImpl.getData(KEY)).thenReturn(null);
        when(repoImpl.saveData(KEY, VALUE)).thenReturn(VALUE);
        assertEquals(VALUE, serviceImpl.saveData(KEY, VALUE));
        verify(repoImpl, times(1)).getData(KEY);
        verify(repoImpl, times(1)).saveData(KEY, VALUE);
    }

    @Test(expected = DataAlreadyExistException.class)
    public void saveDataExistsExceptionThrown() {
        when(repoImpl.getData(anyString())).thenReturn(VALUE);
        serviceImpl.saveData(KEY, VALUE);
    }

    @Test
    public void updateExistsDataCorrectValueExpected() {
        when(repoImpl.getData(KEY)).thenReturn(VALUE);
        when(repoImpl.updateData(KEY, NEWVALUE)).thenReturn(NEWVALUE);
        assertEquals(NEWVALUE, serviceImpl.updateData(KEY, NEWVALUE));
        verify(repoImpl, times(1)).getData(KEY);
        verify(repoImpl, times(1)).updateData(anyString(), anyString());
    }

    @Test(expected = DataNotFoundException.class)
    public void updateDataNotExistsExceptionThrown() {
        when(repoImpl.getData(anyString())).thenReturn(null);
        serviceImpl.updateData(KEY, VALUE);
    }

    @Test
    public void deleteExistsDataCorrectValueExpected() {
        when(repoImpl.getData(KEY)).thenReturn(VALUE);
        serviceImpl.deleteData(KEY);
        verify(repoImpl, times(1)).getData(KEY);
        verify(repoImpl, times(1)).deleteData(anyString());
    }

    @Test(expected = DataNotFoundException.class)
    public void deleteDataNotExistsExceptionThrown() {
        when(repoImpl.getData(anyString())).thenReturn(null);
        serviceImpl.deleteData(KEY);
    }

}
