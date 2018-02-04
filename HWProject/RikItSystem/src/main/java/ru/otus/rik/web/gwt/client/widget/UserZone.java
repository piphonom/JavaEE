package ru.otus.rik.web.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import ru.otus.rik.web.gwt.shared.model.UsersListDTO;

public class UserZone extends Composite {

    interface Binder extends UiBinder<Widget, UserZone> { }

    @UiField
    FlexTable header;

    @UiField
    FlexTable table;

    private static final Binder binder = GWT.create(Binder.class);
    public UserZone() {
        initWidget(binder.createAndBindUi(this));
        initTable();
    }

    private void initTable() {

        header.getColumnFormatter().setWidth(0, "128px");
        header.getColumnFormatter().setWidth(1, "128px");
        header.getColumnFormatter().setWidth(2, "128px");

        header.setText(0, 0, "Name");
        header.setText(0, 1, "Email");
        header.setText(0, 2, "Department");

        table.getColumnFormatter().setWidth(0, "128px");
        table.getColumnFormatter().setWidth(1, "128px");
        table.getColumnFormatter().setWidth(2, "128px");

        getUsers(table);
    }

    private void getUsers(FlexTable table) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, "/allUsers");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(Request request, Response response) {
                    JSONValue jsonValue = JSONParser.parseStrict(response.getText());

                    JSONObject customerObject = jsonValue.isObject();
                    JSONArray jsonArray = customerObject.get("users").isArray();

                    int size = jsonArray.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject user = jsonArray.get(i).isObject();
                        String value = user.get("name").isString().stringValue();
                        table.setText(i, 0, value);
                        value = user.get("email").isString().stringValue();
                        table.setText(i, 1, value);
                        value = user.get("department").isString().stringValue();
                        table.setText(i, 2, value);
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    Window.alert("Users loading error: " + exception.getMessage());
                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
}
