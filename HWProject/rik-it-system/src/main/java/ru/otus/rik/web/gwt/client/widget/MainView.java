package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {
    @UiTemplate("MainView.ui.xml")
    interface Binder extends UiBinder<VerticalPanel, MainView> {
    }

    private Binder binder = GWT.create(Binder.class);

    public MainView() {
        initWidget(binder.createAndBindUi(this));
    }
}
