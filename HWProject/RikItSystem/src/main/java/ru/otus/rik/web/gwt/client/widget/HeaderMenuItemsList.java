package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.user.client.ui.Composite;
import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.client.text.WebConstants;

import java.util.*;

public final class HeaderMenuItemsList {

    private final LinkedHashMap<String, HeaderMenuItem> itemsMap = new LinkedHashMap<>();

    public HeaderMenuItemsList(RikGWTAppServiceAsync asyncService, WebConstants webConstants, GeneralView.DynamicContentUpdater contentUpdater) {
        String loginMenuName = webConstants.login_page();
        LoginView loginView = new LoginView(asyncService);
        loginView.setCallback(() -> {
            Composite userZone = new UserZone();
            itemsMap.put(loginMenuName, new HeaderMenuItem(loginMenuName, userZone));
            contentUpdater.update(userZone);
        });

        addItem(new HeaderMenuItem(webConstants.main_page(), new MainView()));
        addItem(new HeaderMenuItem(loginMenuName, loginView));
    }

    private void addItem(HeaderMenuItem item) {
        itemsMap.put(item.name, item);
    }

    public int getItemsCount() {
        return itemsMap.keySet().size();
    }

    public HeaderMenuItem getItem(int index) {
        if (index >= getItemsCount())
            return null;
        return new ArrayList<>(itemsMap.entrySet()).get(index).getValue();
    }
}
