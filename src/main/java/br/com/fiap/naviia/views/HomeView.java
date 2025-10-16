package br.com.fiap.naviia.views;


import br.com.fiap.naviia.NaviService;
import br.com.fiap.naviia.views.components.NaviTextArea;
import br.com.fiap.naviia.views.components.StyleSelector;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

    @Route("")
    public class HomeView extends VerticalLayout {

        private final NaviService naviService;
        private TextArea originalTextArea = new NaviTextArea("Texto Original", VaadinIcon.PENCIL.create());
        private TextArea translatedTextArea = new NaviTextArea("Texto Traduzido", VaadinIcon.OPEN_BOOK.create());
        private Select<String> selectStyle = new StyleSelector();

        public HomeView(NaviService naviService) {
            this.naviService = naviService;

            translatedTextArea.setReadOnly(true);

            var split = new SplitLayout(originalTextArea, translatedTextArea);
            split.setWidthFull();
            split.setHeight("100%");

            var button = new Button("Traduzir", VaadinIcon.ARROW_RIGHT.create());
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            button.addClickListener(event -> {
                var original = originalTextArea.getValue();
                var estilo = selectStyle.getValue();

                if (original == null || original.trim().isEmpty()) {
                    translatedTextArea.setValue("Por favor, insira o texto original.");
                    return;
                }

                if (estilo == null) {
                    translatedTextArea.setValue("Por favor, selecione um estilo de tradução.");
                    return;
                }

                var traducao = naviService.translate(original, estilo);
                translatedTextArea.setValue(traducao);
            });

            add(new H1("Navi"));
            add(new Paragraph("Tradutor de textos universais"));
            add(split);
            add(new HorizontalLayout(selectStyle, button));
        }
    }

