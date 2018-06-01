package ren.vic.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.CityRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CityGetAllTest {

    private CityGetAll cityGetAll;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private CityRepository mockCityRepository;

    @Before
    public void setUp() {
        cityGetAll = new CityGetAll(mockThreadExecutor, mockPostExecutionThread, mockCityRepository);
    }

    @Test
    public void testBuildCityGetAllUseCaseHappyCase() {
        cityGetAll.buildUseCaseObservable(null);

        verify(mockCityRepository).cityList();
        verifyNoMoreInteractions(mockCityRepository);
        verifyZeroInteractions(mockThreadExecutor, mockPostExecutionThread);
    }
}