package com.vaadin.touchkitsampler.ui;

import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.addon.touchkit.ui.UrlField;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;

public class FieldView extends NavigationView {
    private static final long serialVersionUID = -310203104553728231L;
    
    public FieldView() {
        super("Text Input Fields");
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        class MyForm extends CssLayout
              implements Button.ClickListener {
            private static final long serialVersionUID = 8512115834680760052L;

            EmailField  email  = new EmailField("Email");
            NumberField number = new NumberField("Number");
            UrlField    url    = new UrlField("URL");
            FieldGroup binder;

            public MyForm(Item item) {
                VerticalComponentGroup group =
                        new VerticalComponentGroup();
                FormLayout formLayout = new FormLayout();

                email.addValidator(new EmailValidator(
                    "Not a proper email address"));
                formLayout.addComponent(email);
                
                number.addValidator(new IntegerRangeValidator(
                    "Outside range", 1, 1000));
                formLayout.addComponent(number);

                formLayout.addComponent(url);
                
                group.addComponent(formLayout);
                addComponent(group);

                // Bind the form
                binder = new FieldGroup(item);
                binder.bindMemberFields(this);
                
                // Handle the form
                Button commit = new Button("OK", this);
                addComponent(commit);
            }

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    binder.commit();
                } catch (CommitException e) {
                    Notification.show("Invalid value");
                }
            }
        }
        
        Item item = new PropertysetItem();
        item.addItemProperty("email",
            new ObjectProperty<String>(""));
        item.addItemProperty("number",
            new ObjectProperty<Integer>(0));
        item.addItemProperty("url",
            new ObjectProperty<String>(""));
        
        MyForm form = new MyForm(item);
        setContent(form);
    }
}