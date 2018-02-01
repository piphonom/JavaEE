package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.otus.rik.web.gwt.client.RikGWTApp;
import ru.otus.rik.web.gwt.client.service.RikGWTAppService;
import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;

public class MainView extends Composite {
    @UiTemplate("MainPart.ui.xml")
    interface MainViewUiBinder extends UiBinder<VerticalPanel, MainView> {
    }

    @UiField
    TextBox loginTextField;

    @UiField
    PasswordTextBox passwordTextField;

    @UiField
    Button submit;

    MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

    public MainView(RikGWTAppServiceAsync asyncService) {
        initWidget(uiBinder.createAndBindUi(this));
        submit.addClickHandler(event -> {
            LoginFormDTO form = new LoginFormDTO(loginTextField.getValue(), passwordTextField.getValue());
            asyncService.authorize(form, new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                    caught.getMessage();
                }

                @Override
                public void onSuccess(Void result) {
                    Window.alert("You are good!");
                }
            });
        });
    }
}
