package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.user.client.ui.Composite;

public final class HeaderMenuItem {
    public final String name;
    public final Composite view;

    public HeaderMenuItem(String name, Composite view) {
        this.name = name;
        this.view = view;
    }
}
