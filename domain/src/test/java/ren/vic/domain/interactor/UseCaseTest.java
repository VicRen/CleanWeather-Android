package ren.vic.domain.interactor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest {

    private UseCaseTestClass useCase;

    private TestDisposableObserver<Object> testObserver;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        useCase = new UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
        testObserver = new TestDisposableObserver<>();
        given(mockPostExecutionThread.getScheduler()).willReturn(new TestScheduler());
    }

    private static class UseCaseTestClass extends UseCase<Object, Params> {

        UseCaseTestClass(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override
        Observable<Object> buildUseCaseObservable(Params params) {
            return Observable.empty();
        }
    }

    @Test
    public void testBuildUseCaseObservableReturnCorrectResult() {
        useCase.execute(testObserver, Params.EMPTY);
        assertThat(testObserver.valuesCount).isZero();
    }

    @Test
    public void testSubscriptionWhenExecutingUseCase() {
        useCase.execute(testObserver, Params.EMPTY);
        useCase.dispose();

        assertThat(testObserver.isDisposed()).isTrue();
    }

    @Test
    public void testShouldFailWhenExecuteWithNullObserver() {
        expectedException.expect(NullPointerException.class);
        useCase.execute(null, Params.EMPTY);
    }

    private static class TestDisposableObserver<T> extends DisposableObserver<T> {

        private int valuesCount = 0;

        @Override
        public void onNext(T t) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

    private static class Params {
        private static Params EMPTY = new Params();

        private Params() {
        }
    }
}
