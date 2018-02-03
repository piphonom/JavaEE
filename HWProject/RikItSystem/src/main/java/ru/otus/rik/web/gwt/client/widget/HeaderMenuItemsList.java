package ru.otus.rik.web.gwt.client.widget;

import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.client.text.WebConstants;

import java.util.ArrayList;
import java.util.List;

public final class HeaderMenuItemsList {

    private final List<HeaderMenuItem> items = new ArrayList<>();

    public HeaderMenuItemsList(RikGWTAppServiceAsync asyncService, WebConstants webConstants) {
        addItem(new HeaderMenuItem(webConstants.login_page(), new LoginView(asyncService)));
        addItem(new HeaderMenuItem(webConstants.main_page(), new MainView()));
    }

    private void addItem(HeaderMenuItem item) {
        items.add(item);
    }

    public int getItemsCount() {
        return items.size();
    }

    public HeaderMenuItem getItem(int index) {
        if (index >= items.size())
            return null;
        return items.get(index);
    }
}
