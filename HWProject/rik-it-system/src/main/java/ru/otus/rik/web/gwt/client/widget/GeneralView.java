package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import ru.otus.rik.web.gwt.client.service.RikGWTAppServiceAsync;
import ru.otus.rik.web.gwt.client.text.WebConstants;

public class GeneralView extends Composite {

    public interface DynamicContentUpdater {
        void update(Widget newWidget);
    }

    @UiTemplate("GeneralView.ui.xml")
    interface Binder extends UiBinder<DockLayoutPanel, GeneralView> {}

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    VerticalPanel shortcutPanel;

    @UiField
    VerticalPanel menuPanel;

    @UiField
    VerticalPanel dynamicContentPanel;

    public GeneralView(RikGWTAppServiceAsync asyncService, WebConstants webConstants) {
        DockLayoutPanel outer = binder.createAndBindUi(this);

        DynamicContentUpdater contentUpdater = (widget -> {
            try {
                Widget current;
                current = dynamicContentPanel.getWidget(0);
                dynamicContentPanel.remove(current);
            } catch (IndexOutOfBoundsException e) {}
            dynamicContentPanel.add(widget);
        });

        HeaderMenuItemsList menuItemsList = new HeaderMenuItemsList(asyncService, webConstants, contentUpdater);
        HeaderMenu headerMenu = new HeaderMenu(menuItemsList);
        headerMenu.setListener(item ->
            contentUpdater.update(item.view)
        );
        menuPanel.add(headerMenu);

        RootLayoutPanel root = RootLayoutPanel.get();
        root.add(outer);
    }
}
