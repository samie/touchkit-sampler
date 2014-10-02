package com.vaadin.touchkitsampler.ui;

import java.util.Collection;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

class PopoverView extends NavigationView {

    private static final long serialVersionUID = 3750679255269899661L;

    Table table = new Table("Planets", DemoHelper.planetData());

    public PopoverView() {
        super("Popover");

        CssLayout content = new CssLayout();

        content.addComponent(new Label("This view demonstrates the Popover component"));
        table.setWidth("100%");
        table.setPageLength(table.size());
        content.addComponent(table);
        
        table.setSelectable(true);
        table.setImmediate(true);
        table.addItemClickListener(new ItemClickListener() {
            private static final long serialVersionUID = -6439972483179810841L;

            @SuppressWarnings("unchecked")
            @Override
            public void itemClick(ItemClickEvent event) {
                final Object itemId = event.getItemId();
                
                class DetailsPopover extends Popover
                      implements MouseEvents.ClickListener {
                    private static final long serialVersionUID = 3742410675843716898L;

                    public DetailsPopover() {
                        setWidth("350px");
                        setHeight("65%");

                        // Have some details to display
                        FormLayout layout = new FormLayout();
                        for (String pid: (Collection<String>) table.getContainerPropertyIds()) {
                            Label label = new Label(table.getContainerProperty(itemId, pid));
                            label.setCaption(table.getColumnHeader(pid));
                            layout.addComponent(label);
                        }
                        layout.setMargin(true);

                        NavigationView content = new NavigationView(layout);
                        content.setCaption("Details");
                        setContent(content);

                        addClickListener(this);
                    }

                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        close();
                    }
                }

                DetailsPopover popover = new DetailsPopover();
                popover.showRelativeTo(getNavigationBar());
            }
        });
        
        setContent(content);
    }

    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        table.select(null);
    }
}