package br.com.fiap.naviia.views.components;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;

public class StyleSelector extends Select<String> {

    public StyleSelector() {
        super();
        this.setLabel("Estilo de Tradução");
        this.setHelperText("Escolha um estilo de escrita para a tradução");
        this.setPrefixComponent(VaadinIcon.CHAT.create());
        this.setMinWidth("300px");
        this.setItems(
                "Gíria das ruas",
                "Criança de 2 anos",
                "Juridiquês",
                "Caipira",
                "Fausto Silva"
        );
        this.setPlaceholder("Selecione um estilo...");
        this.setEmptySelectionAllowed(false);
    }
}
