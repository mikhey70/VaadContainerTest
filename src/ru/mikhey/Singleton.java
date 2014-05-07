package ru.mikhey;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import ru.NewButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Singleton {

    private static volatile Singleton instance;
    private static final Object LAZY_INIT_LOCK = new Object();

    private static int count = 0;
    private boolean state = false;
    private Container container = new IndexedContainer();

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (LAZY_INIT_LOCK) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private Singleton() {
        count++;
        container.addContainerProperty("Category",String.class,"");
        container.addContainerProperty("Total",Integer.class,0);
//        container.addContainerProperty("name",Label.class,null);
//        container.addContainerProperty("action",Button.class,null);

//        Button.ClickListener cl = new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                Integer ind = ((NewButton)clickEvent.getButton()).getIdBut();
//                Notification.show("Clicked "+ind, Notification.Type.WARNING_MESSAGE);
//            }
//        };

        for  (int i=0; i<5;i++) {

            Item item = container.addItem(i*2);
            item.getItemProperty("Category").setValue(String.valueOf(i*2));
            item.getItemProperty("Total").setValue(i);
//            item.getItemProperty("name").setValue(new Label("<font color=\"red\">Красненький</font>", ContentMode.HTML));
//            item.getItemProperty("action").setValue(new NewButton(cl, i*2));
            item = container.addItem(i*2+1);
            item.getItemProperty("Category").setValue(String.valueOf(i*2+1));
            item.getItemProperty("Total").setValue(i*2);
//            item.getItemProperty("name").setValue(new Label("<font color=\"green\"><b>Green</b></font>", ContentMode.HTML));
//            item.getItemProperty("action").setValue(new NewButton(cl, i*2+1));
        }
        state=true;
    }

    public static int getCount() {
        return count++;
    }

    public boolean isState() {
        return state;
    }

    public Container getContainer() {
        return container;
    }
}