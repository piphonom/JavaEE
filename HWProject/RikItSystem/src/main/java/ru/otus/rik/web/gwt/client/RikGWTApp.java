package ru.otus.rik.web.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;
import ru.otus.rik.web.gwt.client.service.RikGWTAppService;
import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.client.text.WebConstants;
import ru.otus.rik.web.gwt.client.widget.MainView;

public class RikGWTApp implements EntryPoint {

    private static RikGWTAppServiceAsync clientService = GWT.create(RikGWTAppService.class);
    private static WebConstants constants = GWT.create(WebConstants.class);

    @Override
    public void onModuleLoad() {
        initHeaderAndTitle();
        RootPanel.get("slot").add(new MainView(clientService));
    }


    private void initHeaderAndTitle(){
        Document.get().getElementById("header").setInnerText(constants.form_header());
        Document.get().getElementById("title").setInnerText(constants.title());
    }
}
