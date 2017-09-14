package stats.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColorChooserController {
	@FXML
	ColorPicker colorP1;
	@FXML
	ColorPicker colorP2;
	@FXML
	ColorPicker colorP3;
	@FXML
	ColorPicker colorP4;

	@FXML
	Rectangle rectangle1;
	@FXML
	Rectangle rectangle2;
	@FXML
	Rectangle rectangle3;
	@FXML
	Rectangle rectangle4;
	@FXML
	Button closeButton;

	public ColorChooserController() {
	}

	@FXML
	private void initialize() {
		rectangle1.setFill(Color.rgb(168, 54, 54));
		rectangle2.setFill(Color.rgb(168, 151, 54));
		rectangle3.setFill(Color.rgb(66, 45, 115));
		rectangle4.setFill(Color.rgb(43, 134, 43));
		if (MainPanelController.currentColor == 1) {
			rectangle2.setStroke(Color.BLACK);
			rectangle2.setStrokeWidth(2);
		} else if (MainPanelController.currentColor == 2) {
			rectangle3.setStroke(Color.BLACK);
			rectangle3.setStrokeWidth(2);
		} else if (MainPanelController.currentColor == 3) {
			rectangle4.setStroke(Color.BLACK);
			rectangle4.setStrokeWidth(2);
		} else {
			rectangle1.setStroke(Color.BLACK);
			rectangle1.setStrokeWidth(2);
		}
	}

	@FXML
	private void handleColor1() {
		rectangle1.setStroke(Color.BLACK);
		rectangle1.setStrokeWidth(2);
		rectangle2.setStroke(Color.WHITE);
		rectangle3.setStroke(Color.WHITE);
		rectangle4.setStroke(Color.WHITE);
		MainPanelController.color1 = Color.rgb(252, 165, 165);
		MainPanelController.color2 = Color.rgb(210, 102, 102);
		MainPanelController.color3 = Color.rgb(126, 20, 20);
		MainPanelController.color4 = Color.rgb(83, 0, 0);
		MainPanelController.currentColor = 0;
	}

	@FXML
	private void handleColor2() {
		rectangle1.setStroke(Color.WHITE);
		rectangle2.setStroke(Color.BLACK);
		rectangle2.setStrokeWidth(2);
		rectangle3.setStroke(Color.WHITE);
		rectangle4.setStroke(Color.WHITE);
		MainPanelController.color1 = Color.rgb(252, 240, 165);
		MainPanelController.color2 = Color.rgb(210, 194, 102);
		MainPanelController.color3 = Color.rgb(126, 110, 20);
		MainPanelController.color4 = Color.rgb(83, 71, 0);
		MainPanelController.currentColor = 1;
	}

	@FXML
	private void handleColor3() {
		rectangle1.setStroke(Color.WHITE);
		rectangle2.setStroke(Color.WHITE);
		rectangle3.setStroke(Color.BLACK);
		rectangle3.setStrokeWidth(2);
		rectangle4.setStroke(Color.WHITE);
		MainPanelController.color1 = Color.rgb(136, 119, 173);
		MainPanelController.color2 = Color.rgb(97, 77, 144);
		MainPanelController.color3 = Color.rgb(41, 21, 86);
		MainPanelController.color4 = Color.rgb(21, 6, 57);
		MainPanelController.currentColor = 2;
	}

	@FXML
	private void handleColor4() {
		rectangle1.setStroke(Color.WHITE);
		rectangle2.setStroke(Color.WHITE);
		rectangle3.setStroke(Color.WHITE);
		rectangle4.setStroke(Color.BLACK);
		rectangle4.setStrokeWidth(2);
		MainPanelController.color1 = Color.rgb(132, 202, 132);
		MainPanelController.color2 = Color.rgb(81, 168, 81);
		MainPanelController.color3 = Color.rgb(16, 100, 16);
		MainPanelController.color4 = Color.rgb(0, 67, 0);
		MainPanelController.currentColor = 3;
	}

	@FXML
	private void handleCustomColor1() {
		MainPanelController.color4 = colorP1.getValue();
	}

	@FXML
	private void handleCustomColor2() {
		MainPanelController.color3 = colorP2.getValue();
	}

	@FXML
	private void handleCustomColor3() {
		MainPanelController.color2 = colorP3.getValue();
	}

	@FXML
	private void handleCustomColor4() {
		MainPanelController.color1 = colorP4.getValue();
	}

	@FXML
	private void handleSaveColor() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}
