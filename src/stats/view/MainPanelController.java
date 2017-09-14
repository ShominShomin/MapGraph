package stats.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import stats.model.ContentData;
import stats.model.ContentDataWrapper;

import javax.imageio.ImageIO;
import javax.xml.bind.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPanelController {
	@FXML
	TextField arkhangaiText;
	@FXML
	TextField bayanKnongorText;
	@FXML
	TextField BayanOlgiiText;
	@FXML
	TextField BulganText;
	@FXML
	TextField darkhanUulText;
	@FXML
	TextField dornodText;
	@FXML
	TextField dornoGoviText;
	@FXML

	TextField dundGoviText;
	@FXML
	TextField goviAltaiText;
	@FXML
	TextField goviSumberText;
	@FXML
	TextField hovdText;
	@FXML
	TextField khentiiText;
	@FXML
	TextField khovsgolText;
	@FXML
	TextField omnoGoviText;
	@FXML
	TextField orkhonText;
	@FXML
	TextField ovorKhangaiText;
	@FXML
	TextField selengeText;
	@FXML
	TextField sukhBaatarText;
	@FXML
	TextField tovText;
	@FXML
	TextField ulaanbaatarText;
	@FXML
	TextField uvsText;
	@FXML
	TextField zavhanText;
	@FXML
	ImageView arkhangai;
	@FXML
	ImageView bayanKnongor;
	@FXML
	ImageView BayanOlgii;
	@FXML
	ImageView Bulgan;
	@FXML
	ImageView darkhanUul;
	@FXML
	ImageView dornod;
	@FXML
	ImageView dornoGovi;
	@FXML
	ImageView dundGovi;
	@FXML
	ImageView goviAltai;
	@FXML
	ImageView goviSumber;
	@FXML
	ImageView hovd;
	@FXML
	ImageView khentii;
	@FXML
	ImageView khovsgol;
	@FXML
	ImageView omnoGovi;
	@FXML
	ImageView orkhon;
	@FXML
	ImageView ovorKhangai;
	@FXML
	ImageView selenge;
	@FXML
	ImageView sukhBaatar;
	@FXML
	ImageView tov;
	@FXML
	ImageView ulaanbaatar;
	@FXML
	ImageView uvs;
	@FXML
	ImageView zavhan;
	@FXML
	Slider slider;
	@FXML
	ListView<String> listView;
	@FXML
	TextField pathText;
	@FXML
	Rectangle rectangle1;
	@FXML
	Rectangle rectangle2;
	@FXML
	Rectangle rectangle3;
	@FXML
	Rectangle rectangle4;
	@FXML
	Label labelValue1;
	@FXML
	Label labelValue2;
	@FXML
	Label labelValue3;
	@FXML
	Label labelValue4;
	@FXML
	AnchorPane anchorPane;
	boolean darkGrayUsed;
	boolean blackUsed;
	boolean dragHolder;
	ContentDataWrapper years;
	List<ContentDataWrapper> contentList = new ArrayList<>();
	String filePath = "D:/workspace/Stats/src/data.xml";
	final FileChooser fileChooser = new FileChooser();
	public static Color color1 = Color.rgb(252, 165, 165);
	public static Color color2 = Color.rgb(210, 102, 102);
	public static Color color3 = Color.rgb(126, 20, 20);
	public static Color color4 = Color.rgb(83, 0, 0);
	public static int currentColor = 0;

	public MainPanelController() {
	}

	@FXML
	private void initialize() {
		pathText.setStyle("-fx-text-fill: black");
		clearAllProvince();
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				if (new_val.intValue() != old_val.intValue()) {
					drawColorsOnMap(new_val.intValue() - 1);
				}
			}
		});
		try {
			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ContentDataWrapper.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			years = (ContentDataWrapper) jaxbUnmarshaller.unmarshal(file);
			ObservableList<String> yearsList = FXCollections.observableArrayList();
			for (int i = 0; i < years.getYears().size(); i++) {
				yearsList.add(years.getYears().get(i).getYearNum());
				listView.setItems(yearsList);
			}
			listView.setItems(yearsList);
		} catch (JAXBException e) {
			e.printStackTrace();
			pathText.setText("Invalid file");
			pathText.setStyle("-fx-text-fill: red");
		}
		setupListView();
	}

	private void setupListView() {
		listView.setEditable(true);
		listView.setCellFactory(TextFieldListCell.forListView());
		listView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> t) {
				listView.getItems().set(t.getIndex(), t.getNewValue());
				years.getYears().get(t.getIndex()).setYearNum(t.getNewValue());
				try {
					File file = new File(filePath);
					JAXBContext jaxbContext = JAXBContext.newInstance(ContentDataWrapper.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					jaxbMarshaller.marshal(years, file);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				initialize();
			}
		});
	}

	private void drawColorsOnMap(int currentMonth) {
		if (!listView.getSelectionModel().isEmpty()) {
			Integer current = listView.getSelectionModel().getSelectedIndex();
			String dataString = years.getYears().get(current).getMonths().get(currentMonth);
			String[] dataArray = dataString.split(",");
			int[] intArray = new int[dataArray.length];
			for (int i = 0; i < dataArray.length; i++) {
				try {
					intArray[i] = Integer.parseInt(dataArray[i]);
				} catch (NumberFormatException nfe) {
				}
			}
			int maxHolder = 0, minHolder = 0;
			for (int i = 1; i < intArray.length; i++) {
				if (intArray[maxHolder] < intArray[i]) {
					maxHolder = i;
				}
				if (intArray[minHolder] > intArray[i]) {
					minHolder = i;
				}
			}
			maxHolder = intArray[maxHolder];
			minHolder = intArray[minHolder];
			int diff = maxHolder - minHolder;
			diff = diff / 4;
			labelValue1.setText(Integer.toString(minHolder) + " - " + Integer.toString(minHolder + diff));
			labelValue2.setText(Integer.toString(minHolder + diff) + " - " + Integer.toString(diff * 2 + minHolder));
			labelValue3
					.setText(Integer.toString(diff * 2 + minHolder) + " - " + Integer.toString(diff * 3 + minHolder));
			labelValue4.setText(Integer.toString(diff * 3 + minHolder) + " - " + Integer.toString(maxHolder));
			rectangle1.setFill(color1);
			rectangle2.setFill(color2);
			rectangle3.setFill(color3);
			rectangle4.setFill(color4);
			colorByDiff(arkhangai, arkhangaiText, intArray[0], diff, minHolder);
			colorByDiff(bayanKnongor, bayanKnongorText, intArray[1], diff, minHolder);
			colorByDiff(BayanOlgii, BayanOlgiiText, intArray[2], diff, minHolder);
			colorByDiff(Bulgan, BulganText, intArray[3], diff, minHolder);
			colorByDiff(darkhanUul, darkhanUulText, intArray[4], diff, minHolder);
			colorByDiff(dornod, dornodText, intArray[5], diff, minHolder);
			colorByDiff(dornoGovi, dornoGoviText, intArray[6], diff, minHolder);
			colorByDiff(dundGovi, dundGoviText, intArray[7], diff, minHolder);
			colorByDiff(goviAltai, goviAltaiText, intArray[8], diff, minHolder);
			colorByDiff(goviSumber, goviSumberText, intArray[9], diff, minHolder);
			colorByDiff(hovd, hovdText, intArray[10], diff, minHolder);
			colorByDiff(khentii, khentiiText, intArray[11], diff, minHolder);
			colorByDiff(khovsgol, khovsgolText, intArray[12], diff, minHolder);
			colorByDiff(omnoGovi, omnoGoviText, intArray[13], diff, minHolder);
			colorByDiff(orkhon, orkhonText, intArray[14], diff, minHolder);
			colorByDiff(ovorKhangai, ovorKhangaiText, intArray[15], diff, minHolder);
			colorByDiff(selenge, selengeText, intArray[16], diff, minHolder);
			colorByDiff(sukhBaatar, sukhBaatarText, intArray[17], diff, minHolder);
			colorByDiff(tov, tovText, intArray[18], diff, minHolder);
			colorByDiff(ulaanbaatar, ulaanbaatarText, intArray[19], diff, minHolder);
			colorByDiff(uvs, uvsText, intArray[20], diff, minHolder);
			colorByDiff(zavhan, zavhanText, intArray[21], diff, minHolder);
		}
	}

	private void colorByDiff(ImageView image, TextField text, int currentValue, int diff, int min) {
		Color color;
		DropShadow ds = new DropShadow();
		ds.setRadius(2);
		ds.setSpread(1);
		ds.setColor(Color.GRAY);

		if (currentValue <= min)
			color = color1;
		else if (currentValue <= min + diff && currentValue > min)
			color = color2;
		else if (currentValue <= diff * 2 + min && currentValue > min + diff)
			color = color3;
		else
			color = color4;

		ds.setInput(callColorInnerShadow(color));
		image.setEffect(ds);
		text.setText(Integer.toString(currentValue));
		text.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					text.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	@FXML
	private void handleLoad() {
		configureFileChooser(fileChooser);
		Window stage = null;
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			filePath = file.getAbsolutePath();
			pathText.setText(filePath);
			initialize();
		}
	}

	private void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Choose XML file");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
	}

	@FXML
	private void handleChanges() {
		StringBuilder builder = new StringBuilder();
		builder.append(arkhangaiText.getText());
		builder.append(",");
		builder.append(bayanKnongorText.getText());
		builder.append(",");
		builder.append(BayanOlgiiText.getText());
		builder.append(",");
		builder.append(BulganText.getText());
		builder.append(",");
		builder.append(darkhanUulText.getText());
		builder.append(",");
		builder.append(dornodText.getText());
		builder.append(",");
		builder.append(dornoGoviText.getText());
		builder.append(",");
		builder.append(dundGoviText.getText());
		builder.append(",");
		builder.append(goviAltaiText.getText());
		builder.append(",");
		builder.append(goviSumberText.getText());
		builder.append(",");
		builder.append(hovdText.getText());
		builder.append(",");
		builder.append(khentiiText.getText());
		builder.append(",");
		builder.append(khovsgolText.getText());
		builder.append(",");
		builder.append(omnoGoviText.getText());
		builder.append(",");
		builder.append(orkhonText.getText());
		builder.append(",");
		builder.append(ovorKhangaiText.getText());
		builder.append(",");
		builder.append(selengeText.getText());
		builder.append(",");
		builder.append(sukhBaatarText.getText());
		builder.append(",");
		builder.append(tovText.getText());
		builder.append(",");
		builder.append(ulaanbaatarText.getText());
		builder.append(",");
		builder.append(uvsText.getText());
		builder.append(",");
		builder.append(zavhanText.getText());
		Integer current = listView.getSelectionModel().getSelectedIndex();
		Double sliderCurrent = slider.getValue();
		Integer currentSlider = sliderCurrent.intValue() - 1;
		years.getYears().get(current).getMonths().set(currentSlider, builder.toString());
		try {
			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ContentDataWrapper.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(years, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		handleListChange();
	}

	@FXML
	private void handleListChange() {
		if (!listView.getSelectionModel().isEmpty()) {
			Double d = new Double(slider.getValue());
			int i = d.intValue();
			drawColorsOnMap(i - 1);
		}
	}

	private void clearAllProvince() {
		colorAtStart(BayanOlgii);
		colorAtStart(hovd);
		colorAtStart(uvs);
		colorAtStart(zavhan);
		colorAtStart(arkhangai);
		colorAtStart(dornod);
		colorAtStart(Bulgan);
		colorAtStart(dundGovi);
		colorAtStart(selenge);
		colorAtStart(darkhanUul);
		colorAtStart(orkhon);
		colorAtStart(khovsgol);
		colorAtStart(omnoGovi);
		colorAtStart(tov);
		colorAtStart(goviAltai);
		colorAtStart(goviSumber);
		colorAtStart(khentii);
		colorAtStart(dornoGovi);
		colorAtStart(sukhBaatar);
		colorAtStart(bayanKnongor);
		colorAtStart(ovorKhangai);
		colorAtStart(ulaanbaatar);
	}

	private void colorAtStart(ImageView image) {
		DropShadow ds = new DropShadow();
		ds.setRadius(2);
		ds.setSpread(1);
		ds.setColor(Color.GREY);
		if (darkGrayUsed & blackUsed) {
			ds.setInput(callColorInnerShadow(Color.gray(0.60)));
			image.setEffect(ds);
			darkGrayUsed = false;
			blackUsed = false;
			return;
		}
		if (blackUsed) {
			ds.setInput(callColorInnerShadow(Color.gray(0.75)));
			image.setEffect(ds);
			darkGrayUsed = true;
			return;
		}
		ds.setInput(callColorInnerShadow(Color.gray(0.90)));
		image.setEffect(ds);
		blackUsed = true;
		return;
	}

	private InnerShadow callColorInnerShadow(Color color) {
		InnerShadow is = new InnerShadow();
		is.setColor(color);
		is.setRadius(150);
		is.setChoke(1);
		return is;
	}

	@FXML
	private void handleNewYear() {
		ContentData newContent = new ContentData();
		newContent.setYearNum(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
		years.getYears().add(newContent);
		for (int i = 0; i < 12; i++) {
			newContent.getMonths().add("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
		}
		try {
			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ContentDataWrapper.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(years, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		initialize();
	}

	@FXML
	private void handleDeleteYear() {
		years.getYears().remove(listView.getSelectionModel().getSelectedIndex());
		try {
			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ContentDataWrapper.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(years, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		initialize();
	}

	@FXML
	private void handleNewFile() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save file");
		fileChooser.setInitialFileName("NewMap");
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
		Window stage = null;
		File savedFile = fileChooser.showSaveDialog(stage);
		if (savedFile != null) {
			years.getYears().clear();
			ContentData newContent = new ContentData();
			newContent.setYearNum(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
			years.getYears().add(newContent);
			for (int i = 0; i < 12; i++) {
				newContent.getMonths().add("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
			}
			filePath = savedFile.getAbsolutePath();
			try {
				File file = new File(filePath);
				JAXBContext jaxbContext = JAXBContext.newInstance(ContentDataWrapper.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(years, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			initialize();
		}
	}

	@FXML
	private void handleColorChooser() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ColorChooser.fxml"));
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Color Chooser");
		stage.setScene(new Scene(root));
		stage.showAndWait();
		handleListChange();
	}

	@FXML
	private void handleImageOutput() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save file");
		fileChooser.setInitialFileName("MapImage");
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
		Window stage = null;
		File savedFile = fileChooser.showSaveDialog(stage);
		if (savedFile != null) {
			WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
			File file = new File(savedFile.getAbsolutePath());
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			} catch (IOException e) {
			}
		}
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Made with JavaFX by B.Battuvshin.");
		alert.setContentText("Contact me at Battuvshin.neet@gmail.com");
		alert.showAndWait();
	}
}