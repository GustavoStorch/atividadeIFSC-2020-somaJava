package com.gustavo.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class SomarController implements Initializable {

	@FXML
	private GridPane pnlGrid;

	@FXML
	private Label lblNumeroUm;

	@FXML
	private TextField txtNumero1;

	@FXML
	private Label lblNumeroDois;

	@FXML
	private TextField txtNumero2;

	@FXML
	private Button btnSomar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Label lblResultado;

	@FXML
	private Label lblResultadoSoma;

	@FXML
	void btnLimpar(ActionEvent event) {
		this.txtNumero1.setText(new String());
		this.txtNumero2.setText(new String());
		this.lblResultadoSoma.setText(new String("0"));
		this.txtNumero1.requestFocus();
	}

	@FXML
	void btnSomar(ActionEvent event) {
		try {
			double numero1 = Double.parseDouble(this.txtNumero1.getText());
			double numero2 = Double.parseDouble(this.txtNumero2.getText());

			numero1 = numero1 + numero2;
			lblResultadoSoma.setText(new Double(numero1).toString());
		} catch (NumberFormatException e) {
			// Create expandable Exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String textoErro = sw.toString();

			// Mostrando os erros.
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Aconteceu um erro de conversão numérica.");

			Label label = new Label("Segue a pilha de exceção:");

			TextArea textArea = new TextArea(textoErro);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			// Adicionando o text area para um conteúdo expansível no alerta.
			alerta.getDialogPane().setExpandableContent(expContent);
			alerta.showAndWait();
		}
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do sistema?");
		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;
		alerta.getButtonTypes().setAll(botaoSim, botaoNao);
		Optional<ButtonType> opcaoClicada = alerta.showAndWait();
		return opcaoClicada.get() == botaoSim ? true : false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
