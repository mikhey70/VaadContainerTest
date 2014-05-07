package ru;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import ru.mikhey.Singleton;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by mikhey70 on 01.05.2014.
 */
@SuppressWarnings("unchecked")
public class TestContainer extends UI {
    @Override
    public void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();
        setContent(layout);
        layout.setSizeFull();
        layout.setMargin(true);

        if (!Singleton.getInstance().isState()) {
            Notification.show("Not ready", Notification.Type.WARNING_MESSAGE);
            return;
        }

        final Container container = Singleton.getInstance().getContainer();// new IndexedContainer();

//        container.addContainerProperty("Category",String.class,"");
//        container.addContainerProperty("name",Label.class,null);
//        container.addContainerProperty("action",Button.class,null);
//
//        Button.ClickListener cl = new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                Integer ind = ((NewButton)clickEvent.getButton()).getIdBut();
//                Notification.show("Clicked "+ind, Notification.Type.WARNING_MESSAGE);
//            }
//        };
//        for  (int i=0; i<10;i++) {
//
//            Item item = container.addItem(i*2);
//            item.getItemProperty("Category").setValue(String.valueOf(i*2));
//            item.getItemProperty("name").setValue(new Label("<font color=\"red\">Красненький</font>", ContentMode.HTML));
//            item.getItemProperty("action").setValue(new NewButton(cl, i*2));
//            item = container.addItem(i*2+1);
//            item.getItemProperty("Category").setValue(String.valueOf(i*2+1));
//            item.getItemProperty("name").setValue(new Label("<font color=\"green\"><b>Green</b></font>", ContentMode.HTML));
//            item.getItemProperty("action").setValue(new NewButton(cl, i*2+1));
//        }

        final Table table = new Table();
        table.setContainerDataSource(container);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setHeight("100%");
        layout.addComponent(table);
        Button change = new Button("change");
        change.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
//                container.getItem(1).getItemProperty("Category").setValue("Bu-ga-ga");

                Object sel = table.getValue();
                if (sel != null)
                    container.getItem(sel).getItemProperty("Total").setValue(666);
            }
        });
        Button deleteBut = new Button("Delete");
        deleteBut.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Object sel = table.getValue();
                if (sel != null)
                    container.removeItem(sel);


            }
        });

        layout.addComponent(change);
        layout.addComponent(deleteBut);
        layout.setExpandRatio(table,1.0F);
        Worker worker = new Worker(container);
        worker.start();
        UI.getCurrent().setPollInterval(1000);

    }

    @Override
    public void detach() {
//        Singleton.getInstance().getContainer().getItem(3).getItemProperty("Category").setValue("Closed");
        Item item = Singleton.getInstance().getContainer().addItem(Singleton.getCount() + 500);
        item.getItemProperty("Category").setValue("Кто-то свалил");
        item.getItemProperty("Total").setValue(0);
        super.detach();
    }

    class Worker extends Thread {
        private Container container;

        Worker(Container container) {
            this.container = container;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            container.getItem(2).getItemProperty("Category").setValue("hocus-pocus");
//            UI.getCurrent().setPollInterval(-1);

        }
    }
}
