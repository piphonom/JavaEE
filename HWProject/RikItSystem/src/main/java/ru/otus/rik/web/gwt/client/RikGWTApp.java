package ru.otus.rik.web.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.RootPanel;
import ru.otus.rik.web.gwt.client.service.RikGWTAppService;
import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.client.text.WebConstants;
import ru.otus.rik.web.gwt.client.widget.GeneralView;

public class RikGWTApp implements EntryPoint {

    private static final RikGWTAppServiceAsync clientService = GWT.create(RikGWTAppService.class);
    private static final WebConstants webConstants = GWT.create(WebConstants.class);

    @Override
    public void onModuleLoad() {
        //GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
        //initHeaderAndTitle();
        //RootPanel rootPanel = RootPanel.get("slot");
        //rootPanel.add(new GeneralView(clientService, webConstants));
        new GeneralView(clientService, webConstants);
    }


    private void initHeaderAndTitle(){
        Document.get().getElementById("header").setInnerText(webConstants.form_header());
        Document.get().getElementById("title").setInnerText(webConstants.title());
    }
}
