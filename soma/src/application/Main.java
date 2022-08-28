package application;

import com.gustavo.controller.SomarController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Configurando alguns dados da tela
			primaryStage.setTitle("Somar números");
			primaryStage.resizableProperty().setValue(Boolean.FALSE);

			// Carregando o arquivo de tela
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/peregrinoti/view/Somar.fxml"));
			Parent arquivoXML = loader.load();

			// Carregando a estrutura do XML para uma classe de cena e atribuindo a janela
			Scene somarCenario = new Scene(arquivoXML);
			primaryStage.setScene(somarCenario);

			// Carregando a classe de controle da cena
			SomarController somarController = loader.getController();

			// Atribuindo evento para fechar a tela na classe de controller
			primaryStage.setOnCloseRequest(e -> {
				if (somarController.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}
			});
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
