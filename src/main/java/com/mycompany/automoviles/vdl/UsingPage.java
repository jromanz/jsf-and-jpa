package com.mycompany.automoviles.vdl;

import java.io.IOException;

import javavdl.view.Page;

import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlBody;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class UsingPage implements Page {

	HtmlOutputText info;

	@Override
	public void buildView(FacesContext context, UIViewRoot viewRoot)
			throws IOException {
		UIOutput htmlBegin = new UIOutput();
		htmlBegin.setValue("<html>");
		viewRoot.getChildren().add(htmlBegin);

		HtmlBody htmlBody = new HtmlBody();
		viewRoot.getChildren().add(htmlBody);

		HtmlForm form = new HtmlForm();
		htmlBody.getChildren().add(form);

		info = new HtmlOutputText();
		info.setValue("Primera Ejecución en creación de pantalla.");
		form.getChildren().add(info);

		HtmlCommandButton boton = new HtmlCommandButton();
		boton.setValue("Pulsame");

		boton.addActionListener(new ActionListener() {
			@Override
			public void processAction(ActionEvent event)
					throws AbortProcessingException {
				info.setValue("Oh!!! Genial! ¿cómo lo has conseguido?");
			}
		});

		UIOutput htmlEnd = new UIOutput();
		htmlEnd.setValue("</html>");
		viewRoot.getChildren().add(htmlEnd);
	}

}
