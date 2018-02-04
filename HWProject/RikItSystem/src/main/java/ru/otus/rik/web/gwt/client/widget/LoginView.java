package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;
import ru.otus.rik.web.gwt.shared.model.UsersListDTO;

public class LoginView extends Composite {

    public interface LoginCallback {
        void execute();
    }

    @UiTemplate("LoginView.ui.xml")
    interface Binder extends UiBinder<VerticalPanel, LoginView> {
    }

    @UiField
    TextBox loginTextField;

    @UiField
    PasswordTextBox passwordTextField;

    @UiField
    Button submit;

    Binder binder = GWT.create(Binder.class);

    private LoginCallback callback;

    public LoginView(RikGWTAppServiceAsync asyncService) {
        initWidget(binder.createAndBindUi(this));
        submit.addClickHandler(event -> {
            LoginFormDTO form = new LoginFormDTO(loginTextField.getValue(), passwordTextField.getValue());
            asyncService.authorize(form, new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(caught.getMessage());
                }

                @Override
                public void onSuccess(Void result) {
                    if(callback != null) {
                        callback.execute();
                    }

                }
            });
        });
    }

    public void setCallback(LoginCallback callback) {
        this.callback = callback;
    }
}
