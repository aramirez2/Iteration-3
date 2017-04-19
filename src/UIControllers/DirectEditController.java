package UIControllers;

import Definitions.Physician;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.Language;
import org.Point;

/**
 * Created by Leon Zhang on 4/4/2017.
 */
public class DirectEditController extends CentralUIController implements Initializable {
  private int selectedHPIndex;
  private Physician selectedHP = null;
  private ChoiceBox<String> selectedCB = null;
  private ArrayList<Physician> docs;
  private ArrayList<Point> rooms;
  private ArrayList<String> roomNames;
  private ObservableList<Physician> docDisplay = FXCollections.observableArrayList();
  private ArrayList<ChoiceBox> locations;
  private String searchString = "";

  @FXML
  private Pane DirectEdit;
  @FXML
  private Pane DirectSearchPane;
  @FXML
  private TextField FirstName;
  @FXML
  private TextField LastName;
  @FXML
  private TextField Title;
  @FXML
  private TableView<Physician> Directory;
  @FXML
  private TableColumn firstNameField;
  @FXML
  private TableColumn lastNameField;
  @FXML
  private TableColumn titleField;
  @FXML
  private TableColumn locationsField;
  @FXML
  private ListView<ChoiceBox> Locations;
  @FXML
  private Label AddLocation;
  @FXML
  private Label RemoveLocation;
  @FXML
  private AnchorPane anchorPane;


  @FXML
  private Label DirectBack;
  @FXML
  private Label DirectFirstName;
  @FXML
  private Label DirectLastName;
  @FXML
  private Label DirectTitle;
  @FXML
  private Label DirectLocations;
  @FXML
  private Label DirectCancel;
  @FXML
  private Label DirectSave;
  @FXML
  private Label DirectLogoff;
  @FXML
  private Label DirectCreate;
  @FXML
  private Label DirectDelete;
  @FXML
  private TextField DirectSearch;

  @Override
  public void customListenerX () {
    DirectLogoff.setLayoutX(x_res - DirectLogoff.getPrefWidth() - 12);
    double LabelX = 0.055;
    DirectFirstName.setLayoutX(x_res * LabelX - DirectFirstName.getPrefWidth()/2);
    DirectLastName.setLayoutX(x_res * LabelX - DirectFirstName.getPrefWidth()/2);
    DirectTitle.setLayoutX(x_res * LabelX - DirectFirstName.getPrefWidth()/2);
    DirectLocations.setLayoutX(x_res * LabelX - DirectFirstName.getPrefWidth()/2);
    double FieldX = 0.2176923076923077;
    FirstName.setLayoutX(x_res * FieldX - FirstName.getPrefWidth()/2);
    LastName.setLayoutX(x_res * FieldX - FirstName.getPrefWidth()/2);
    Title.setLayoutX(x_res * FieldX - FirstName.getPrefWidth()/2);
    Locations.setLayoutX(x_res * FieldX - FirstName.getPrefWidth()/2);
    double LocationButtonX = 0.065;
    AddLocation.setLayoutX(x_res * LocationButtonX - AddLocation.getPrefWidth()/2);
    RemoveLocation.setLayoutX(x_res * LocationButtonX - RemoveLocation.getPrefWidth()/2);
    // directory zooming
    Directory.setPrefWidth(670 * x_res / 1300);
    firstNameField.setPrefWidth(150 * x_res / 1300);
    lastNameField.setPrefWidth(150 * x_res / 1300);
    titleField.setPrefWidth(150 * x_res / 1300);
    locationsField.setPrefWidth(200 * x_res / 1300);
    Directory.setLayoutX(x_res * 0.6080769230769231 - Directory.getPrefWidth()/2);
    DirectSearchPane.setLayoutX(Directory.getLayoutX());
    double DataManipX = 0.9288461538461538;
    DirectCreate.setLayoutX(x_res * DataManipX - DirectCreate.getPrefWidth()/2);
    DirectDelete.setLayoutX(x_res * DataManipX - DirectCreate.getPrefWidth()/2);
    DirectCancel.setLayoutX(x_res * DataManipX - DirectCreate.getPrefWidth()/2);
    DirectSave.setLayoutX(x_res * DataManipX - DirectCreate.getPrefWidth()/2);
  }
  @Override
  public void customListenerY () {
    LastName.setLayoutY(y_res * 0.23 - LastName.getPrefHeight()/2);
    DirectSearchPane.setLayoutY(y_res * 0.23 - DirectSearchPane.getPrefHeight()/2);
    DirectLastName.setLayoutY(y_res * 0.23 - DirectLastName.getPrefHeight()/2);
    FirstName.setLayoutY(y_res * 0.34 - FirstName.getPrefHeight()/2);
    DirectFirstName.setLayoutY(y_res * 0.34 - DirectFirstName.getPrefHeight()/2);
    Title.setLayoutY(y_res * 0.45 - Title.getPrefHeight()/2);
    DirectTitle.setLayoutY(y_res * 0.45 - DirectTitle.getPrefHeight()/2);
    DirectLocations.setLayoutY(y_res * 0.56 - DirectLocations.getPrefHeight()/2);
    Locations.setLayoutY(y_res * 0.56 - 20);
    Locations.setPrefHeight(y_res * 9/10 - Locations.getLayoutY());
    if ((y_res)/750 > (x_res)/1300) {
      double newWid = 45 *  (1+(x_res - 1300)/1300);
      AddLocation.setPrefHeight(newWid);
      AddLocation.setPrefWidth(newWid);
      RemoveLocation.setPrefHeight(newWid);
      RemoveLocation.setPrefWidth(newWid);
    } else {
      double newWid = 45 *  (1+(y_res - 750)/750);
      AddLocation.setPrefHeight(newWid);
      AddLocation.setPrefWidth(newWid);
      RemoveLocation.setPrefHeight(newWid);
      RemoveLocation.setPrefWidth(newWid);
    }
    AddLocation.setLayoutY(y_res * 0.7 - AddLocation.getPrefHeight()*1.133333333333333);
    RemoveLocation.setLayoutY(y_res * 0.7 + RemoveLocation.getPrefHeight()/7.5);
    Directory.setLayoutY(FirstName.getLayoutY() - (FirstName.getLayoutY() - LastName.getLayoutY() - LastName.getPrefHeight()) / 2);
    Directory.setPrefHeight(y_res * 9/10 - Directory.getLayoutY());
    DirectCreate.setLayoutY(Directory.getLayoutY() + Directory.getPrefHeight() - 228);
    DirectDelete.setLayoutY(Directory.getLayoutY() + Directory.getPrefHeight() - 168);
    DirectSave.setLayoutY(Directory.getLayoutY() + Directory.getPrefHeight() - 108);
    DirectCancel.setLayoutY(Directory.getLayoutY() + Directory.getPrefHeight() - 48);
  }

  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    if (currSession.getLanguage() == Language.SPANISH) {
      DirectLogoff.setPrefWidth(200);
    } else if (currSession.getLanguage() == Language.PORTUGESE) {
      DirectLogoff.setPrefWidth(240);
    } else {
      DirectLogoff.setPrefWidth(150);
    }
    addResolutionListener(anchorPane);
    setBackground(anchorPane);

    /* apply language configs */
    DirectBack.setText(dictionary.getString("Back", currSession.getLanguage()));
    DirectFirstName.setText(dictionary.getString("First Name", currSession.getLanguage()));
    DirectLastName.setText(dictionary.getString("Last Name", currSession.getLanguage()));
    DirectTitle.setText(dictionary.getString("Title", currSession.getLanguage()));
    DirectCancel.setText(dictionary.getString("Cancel", currSession.getLanguage()));
    DirectCreate.setText(dictionary.getString("Create", currSession.getLanguage()));
    DirectDelete.setText(dictionary.getString("Delete", currSession.getLanguage()));
    DirectLocations.setText(dictionary.getString("Locations", currSession.getLanguage()));
    DirectSave.setText(dictionary.getString("Save", currSession.getLanguage()));
    DirectLogoff.setText(dictionary.getString("Log off", currSession.getLanguage()));
    //DirectEditMap.setText(dictionary.getString("Edit Map", currSession.getLanguage()));

    firstNameField.setCellValueFactory(
        new PropertyValueFactory<Physician, String>("firstName")
    );
    lastNameField.setCellValueFactory(
        new PropertyValueFactory<Physician, String>("lastName")
    );
    titleField.setCellValueFactory(
        new PropertyValueFactory<Physician, String>("title")
    );
    locationsField.setCellValueFactory(new Callback<CellDataFeatures<Physician, String>, ObservableValue<String>>() {
      public ObservableValue<String> call(CellDataFeatures<Physician, String> p) {
        String locations = "";
        for (int i = 0; i < p.getValue().getLocations().size(); i++) {
          locations += p.getValue().getLocations().get(i).getName();
          locations += "\n";
        }
        return new ReadOnlyStringWrapper(locations);
      }
    });
    rooms = database.getNamedPoints();
    docs = database.getPhysicians();
    roomNames = new ArrayList<>();

    // load all displayDocs
    refreshDir();
    Directory.setItems(docDisplay);

    for (Point n : rooms) {
      roomNames.add(n.getName());
    }

    /////////////////////////////
    ///////// Listeners /////////
    /////////////////////////////
    // when select any doc
    Directory.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<Physician>() {
          public void changed(ObservableValue<? extends Physician> ov,
              Physician old_val, Physician new_val) {
            int clicked = Directory.getSelectionModel().getSelectedIndex();
            if (clicked >= 0) {
              selectedHPIndex = clicked;
              selectedHP = docDisplay.get(selectedHPIndex);
            }
            refreshLoc();
            // set text field
            refreshInfo();
          }
        });
    Locations.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<ChoiceBox>() {
          @Override
          public void changed(ObservableValue<? extends ChoiceBox> observable, ChoiceBox oldValue,
              ChoiceBox newValue) {
            selectedCB = newValue;
          }
        });
    DirectSearch.textProperty().addListener((observable, oldValue, newValue) -> {
      searchString = newValue.toString();
      refreshDir();
    });
  }

  /////////////////////////////
  ///// Refresh functions /////
  /////////////////////////////

  public void refreshDir () {
    docDisplay.clear();
    if (searchString != "") {
      for (Physician doc : docs) {
        if ((doc.getFirstName() + " " + doc.getLastName()).contains(searchString)) {
          docDisplay.add(doc);
        }
      }
    } else {
      docDisplay.addAll(docs);
    }
  }

  private void refreshLoc () {
    int i = 0;
    locations = new ArrayList<>();
    for (Point p : selectedHP.getLocations()) {
      ChoiceBox<String> cb = new ChoiceBox<>();
      cb.setItems(FXCollections.observableList(roomNames));
      Point temp = selectedHP.getLocations().get(i);
      cb.setValue(temp.getName());
      locations.add(cb);
      i++;
    }
    Locations.setItems(FXCollections.observableList(locations));
  }

  private void refreshInfo () {
    LastName.setText(selectedHP.getLastName());
    FirstName.setText(selectedHP.getFirstName());
    Title.setText(selectedHP.getTitle());
  }


  /////////////////////////////
  ////// Clear functions //////
  /////////////////////////////
  public void clearSearch () {
    DirectSearch.setText("");
  }

  private void clearLoc () {
    Locations.setItems(FXCollections.observableList(new ArrayList<>()));
  }

  private void clearInfo () {
    LastName.setText("");
    FirstName.setText("");
    Title.setText("");
  }

  /////////////////////////////
  /// location manipulation ///
  /////////////////////////////
  public void addLocation () {
    if (selectedHP != null) {
      ChoiceBox<String> cb = new ChoiceBox<>();
      cb.setItems(FXCollections.observableList(roomNames));
      locations.add(cb);
      Locations.setItems(FXCollections.observableList(locations));
    }
  }

  public void removeLocation () {
    if (selectedHP != null) {
      locations.remove(selectedCB);
      Locations.setItems(FXCollections.observableList(locations));
    }
  }

  private ArrayList<Point> finalLocs () {
    ArrayList<Point> ret = new ArrayList<>();
    for (ChoiceBox cb : locations) {
      addtoFinalLocs(ret, cb);
    }
    return ret;
  }

  private void addtoFinalLocs(ArrayList<Point> ret, ChoiceBox L) {
    for (Point n : rooms) {
      try {
        if (n.getName().equals(L.getValue().toString())) {
          ret.add(n);
        }
      } catch (NullPointerException e) {
        continue;
      }
    }
  }

  /////////////////////////////
  ///// data manipulation /////
  /////////////////////////////
  public void save () {
    try {
      selectedHP.setFirstName(FirstName.getText());
      selectedHP.setLastName(LastName.getText());
      selectedHP.setTitle(Title.getText());
      selectedHP.setLocations(finalLocs());
      // check if it's a new Physician
      boolean isNewPhysician = true;
      for (Physician doc : docs) {
        if (doc.getID() == selectedHP.getID()){
          doc = selectedHP;
          docDisplay.set(selectedHPIndex, selectedHP);
          isNewPhysician = false;
          break;
        }
      }
      if (isNewPhysician) {
        docs.add(selectedHP);
        docDisplay.add(selectedHP);
      }
      // refresh the page
      refreshInfo();
      refreshDir();
      // save to database
      Directory.getSelectionModel().select(selectedHP);
      database.setPhysicians(docs);
    } catch (NullPointerException e) {
      System.out.println("Nothing is selected");
    }
  }

  public void cancel () {
    try {
      refreshInfo();
      refreshLoc();
    } catch (NullPointerException e) {
      System.out.println("Nothing is selected");
    }
  }

  public void create () {
    Directory.getSelectionModel().select(-1);
    long newPID;
    try {
      newPID = docs.get(docs.size() - 1).getID() + 1;
    } catch (ArrayIndexOutOfBoundsException e) {
      newPID = 1;
    }
    selectedHP = new Physician("", "", "", newPID, new ArrayList<>());
    selectedHPIndex = docDisplay.size();
    refreshInfo();
    refreshLoc();
  }

  public void delete () {
    docs.remove(selectedHP);
    refreshDir();
    clearLoc();
    clearInfo();
    database.setPhysicians(docs);
  }

  /////////////////////////////
  //////// scene travel ///////
  /////////////////////////////
  public void back () {
    Stage primaryStage = (Stage) DirectEdit.getScene().getWindow();
    try {
      loadScene(primaryStage, "/AdminMenu.fxml");
    } catch (Exception e) {
      System.out.println("Cannot load admin login menu");
      e.printStackTrace();
    }
  }

  public void logoff () {
    Stage primaryStage = (Stage) DirectEdit.getScene().getWindow();
    try {
      loadScene(primaryStage, "/MainMenu.fxml");
    } catch (Exception e) {
      System.out.println("Cannot load main menu");
      e.printStackTrace();
    }
  }

  public void editMap(){
    mapViewFlag = 3;
    Stage primaryStage = (Stage) DirectEdit.getScene().getWindow();
    try {
      loadScene(primaryStage, "/MapScene.fxml");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> enterAlias(){
    TextInputDialog aliasEntry = new TextInputDialog();
    aliasEntry.setResizable(true);
    aliasEntry.setTitle("Alias Entry");
    aliasEntry.setHeaderText("Enter alias for point");
    aliasEntry.setContentText("Please enter an alias");
    Optional<String> res = aliasEntry.showAndWait();
    String s = res.toString().substring(9, res.toString().length()-1);
    ArrayList<String> aliases = new ArrayList<String>(Arrays.asList(s.split(", ")));
    return aliases;
    //ArrayList<String>(Arrays.asList(String.split))
  }
}
