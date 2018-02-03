package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

public class HeaderMenu extends Composite {

    public interface Listener {
        void onItemSelected(HeaderMenuItem item);
    }

    @UiTemplate("HeaderMenu.ui.xml")
    interface Binder extends UiBinder<Widget, HeaderMenu> {}
    interface SelectionStyle extends CssResource {
        String selectedRow();
    }

    @UiField
    FlexTable table;

    @UiField
    SelectionStyle selectionStyle;

    private Listener listener;
    private int selectedRow = -1;
    private HeaderMenuItemsList itemsList;

    private static final Binder binder = GWT.create(Binder.class);

    public HeaderMenu(HeaderMenuItemsList itemsList) {
        initWidget(binder.createAndBindUi(this));
        initTable();
        setItemsList(itemsList);
    }

    public void setItemsList(HeaderMenuItemsList itemsList) {
        this.itemsList = itemsList;
        update();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected void onLoad() {
        if (selectedRow == -1) {
            selectRow(0);
        }
    }

    @UiHandler("table")
    void onTableClicked(ClickEvent event) {
        HTMLTable.Cell cell = table.getCellForEvent(event);
        if (cell != null) {
            int row = cell.getRowIndex();
            selectRow(row);
        }
    }

    private void initTable() {
        table.getColumnFormatter().setWidth(0, "128px");
    }

    private void styleRow(int row, boolean selected) {
        if (row != -1) {
            String style = selectionStyle.selectedRow();

            if (selected) {
                table.getRowFormatter().addStyleName(row, style);
            } else {
                table.getRowFormatter().removeStyleName(row, style);
            }
        }
    }

    private void selectRow(int row) {
        HeaderMenuItem item = itemsList.getItem(row);
        if (item == null) {
            return;
        }

        styleRow(selectedRow, false);
        styleRow(row, true);

        selectedRow = row;

        if (listener != null) {
            listener.onItemSelected(item);
        }
    }

    private void update() {
        int count = itemsList.getItemsCount();

        int i = 0;
        for (; i < count; ++i) {
            HeaderMenuItem item = itemsList.getItem(i);

            table.setText(i, 0, item.name);
        }
    }
}
