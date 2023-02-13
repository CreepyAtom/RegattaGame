package fr.ensicaen.genielogiciel.model;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LoginPresenterTest {

    @Mock
    private LoginView _view;
    private AutoCloseable closeable;

    @BeforeEach
    public void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void should_activate_view_error_display() {
        // TODO this interest of this test holds on the use of Mockito

        // given
        LoginPresenter presenter = new LoginPresenter();
        presenter.setLoginView(_view);

        // when
        presenter.launchGame("");

        // then
        verify(_view, times(1)).displayError(Main.getMessageBundle(0).getString("error.nickname"));
    }
}