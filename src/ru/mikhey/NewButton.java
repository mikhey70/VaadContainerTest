package ru.mikhey;

import com.vaadin.ui.Button;

/**
 * Created by mikhey70 on 02.05.2014.
 */
public class NewButton extends Button {
    private Integer idBut;

    public NewButton(ClickListener listener, Integer idBut) {
        super("click me", listener);
        this.idBut = idBut;
    }

    public Integer getIdBut() {
        return idBut;
    }
}
