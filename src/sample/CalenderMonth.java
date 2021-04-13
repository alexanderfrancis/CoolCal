package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

import java.awt.ScrollPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class CalenderMonth implements Initializable {
    public static Integer eventId=-1;

    public GridPane grid;
    public Label DateMonth;
    public Label DateYear;
    public Label DateTime;
    public Button backButton;

    public Date nextDate;
    public Node getNodeByRowColumnIndex(final int row,final int column,GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    public void arrangeEvents(){

        TableView tb= new TableView();
        Calendar cell= Calendar.getInstance();
        String DYear= DateYear.getText();
        String DMonth= DateMonth.getText();

        Timestamp cal=CalData.setEtime(Integer.parseInt(DYear),Integer.parseInt(DMonth),1,0,0,0);
        cell.setTime(cal);


//        ObservableList<Event> events  = FXCollections.observableArrayList();
        ObservableList<Event> events  = FXCollections.observableArrayList();
        ObservableList<MultiEvent> multiEvents  = FXCollections.observableArrayList();




        for (Node Child: grid.getChildren()){
            Integer column = GridPane.getColumnIndex(Child);
            Integer row = GridPane.getRowIndex(Child);

            Node c=getNodeByRowColumnIndex(row,column,grid);
            Boolean found=false;
            if (c instanceof VBox) {
                VBox stackC=(VBox)c;

                for (Node b: stackC.getChildren()){

                    if (b instanceof Label){
                        System.out.println("instance of label");
                        Integer dayOfMonth= Integer.parseInt(((Label) b).getText());
                        Calendar calDay=Calendar.getInstance();

                        Integer ID=CalData.current_user_id;
//                        ArrayList<Event> eventList=CalData.getEvents(ID);
                        ArrayList<MultiEvent> multiList=CalData.userjoin(ID);
                        found=false;
//
//                        for(Event name : eventList)
//                        {
//                            System.out.println("in events loop");
//                            Timestamp d= name.getDate();
//                            calDay.setTime(d);
//                            Integer yearCell=cell.get(Calendar.YEAR);
//                            Integer monthCell=cell.get(MONTH);
//                            Integer monthEvent=calDay.get(Calendar.MONTH)+1;
//                            Integer yearEvent=calDay.get(Calendar.YEAR);
//                            Integer dayEvent=calDay.get(Calendar.DAY_OF_MONTH);
//
////                            System.out.println("yearCell: "+yearCell+"yeartype "+ yearCell.getClass());
////                            System.out.println("monthCell: "+monthCell);
////
////                            System.out.println("dayofMonthCell: "+ dayOfMonth);
////                            System.out.println(" ");
////
////                            System.out.println("yearEvent: "+ yearEvent+"yeartype "+ yearEvent.getClass());
////
////                            System.out.println("monthEvent: "+monthEvent);
////
////                            System.out.println("dayEvent: "+ dayEvent);
////                            System.out.println("Comparing years: (yearCell==yearEvent) :"+(yearCell.equals(yearEvent)));
////                            System.out.println("-------------------------");
//
//
//                            if (dayEvent==(dayOfMonth) && monthCell==monthEvent && yearCell.equals(yearEvent) ){
//                                found=true;
//
//                                System.out.println("has found date in common");
//                                events.add(name);
//
//                            }
//
//
//
//
//                        }
                        for(MultiEvent name : multiList)
                        {
                            System.out.println("in events loop");
                            Timestamp d= name.getScheduledAt();
                            calDay.setTime(d);
                            Integer yearCell=cell.get(Calendar.YEAR);
                            Integer monthCell=cell.get(MONTH);
                            Integer monthEvent=calDay.get(Calendar.MONTH)+1;
                            Integer yearEvent=calDay.get(Calendar.YEAR);
                            Integer dayEvent=calDay.get(Calendar.DAY_OF_MONTH);

//                            System.out.println("yearCell: "+yearCell+"yeartype "+ yearCell.getClass());
//                            System.out.println("monthCell: "+monthCell);
//
//                            System.out.println("dayofMonthCell: "+ dayOfMonth);
//                            System.out.println(" ");
//
//                            System.out.println("yearEvent: "+ yearEvent+"yeartype "+ yearEvent.getClass());
//
//                            System.out.println("monthEvent: "+monthEvent);
//
//                            System.out.println("dayEvent: "+ dayEvent);
//                            System.out.println("Comparing years: (yearCell==yearEvent) :"+(yearCell.equals(yearEvent)));
//                            System.out.println("-------------------------");


                            if (dayEvent==(dayOfMonth) && monthCell==monthEvent && yearCell.equals(yearEvent) ){
                                found=true;

                                System.out.println("has found date in common");
                                multiEvents.add(name);

                            }




                        }



                    }
                    if (b instanceof TableView && found){
//                        TableView tbCell=null;
//                        Integer ID=CalData.current_user_id;
//                        ArrayList<Event> eventList=CalData.getEvents(ID);
//                        for(Event name : eventList)
//                        {
//
//                            events.add(name);
//
//
//
//
//                        }

//                        ((TableView) b).setItems(events);
                        ((TableView) b).setItems(multiEvents);
                        System.out.println("tableview");
//                        events  = FXCollections.observableArrayList();
                        multiEvents=FXCollections.observableArrayList();


                    }


                }

            }



        }
//        tb.setItems(events);




    }

    public void nextMonth(){

        Calendar c = Calendar.getInstance();

        int getCurrentMonth= Integer.parseInt(DateMonth.getText());
        Date next=new Date();
        c.setTime(nextDate);




        c.set(MONTH, getCurrentMonth-1);

        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(MONTH, 1);
        if (c.get(MONTH)==0){
            c.set(YEAR,c.get(YEAR));

        }
        nextDate=c.getTime();
        System.out.println(c.getTime());
        createNewCal(c);

        arrangeEvents();

    }
    public void lastMonth(){

        Calendar c = Calendar.getInstance();

        int getCurrentMonth= Integer.parseInt(DateMonth.getText());
        Date next=new Date();
        c.setTime(nextDate);




        c.set(MONTH, getCurrentMonth-1);

        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(MONTH, -1);

        if (c.get(MONTH)==0){
            c.set(YEAR,c.get(YEAR));

        }
        nextDate=c.getTime();
        System.out.println(c.getTime());
        createNewCal(c);
//        arrangeEvents(c.get(MONTH),c.get(YEAR));

        arrangeEvents();

    }

    public void createNewCal(Calendar cal){

        grid.getChildren().clear();

//         Date today = new Date();
//         Calendar cal= Calendar.getInstance();
//         cal.setTime(today);
//         System.out.print(today);
        int year= cal.get(Calendar.YEAR);
        int month= cal.get(MONTH)+1;

        System.out.print("year: "+year+" month: "+ month);

        DateMonth.setText(String.valueOf(month));
        DateYear.setText(String.valueOf(year));

//         createNewCal(Calendar cal);


        YearMonth ym = YearMonth.of(year, month);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        int counter = 1;
        Integer currentRow=0;

        // Get day of week of 1st date of the month and print space for as many days as
        // distant from SUN
        for (int i = 0; i < 7; i++, counter++) {
            System.out.printf("%-4s", "");
            String dayOfWeek="";
            switch(i) {
                case 0:
                    // code block
                    dayOfWeek="Sun";
                    break;
                case 1:
                    // code block
                    dayOfWeek="Mon";
                    break;
                case 2:
                    dayOfWeek="Tue";
                    break;
                case 3:
                    // code block
                    dayOfWeek="Wed";
                    break;
                case 4:
                    // code block
                    dayOfWeek="Thu";

                    break;
                case 5:
                    // code block
                    dayOfWeek="Fri";
                    break;
                case 6:
                    // code block
                    dayOfWeek="Sat";
                    break;
                default:
                    // code block
            }
            Label day=new Label(dayOfWeek);
            grid.addRow(currentRow,day);


        }
        currentRow++;


        int dayValue = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
        System.out.print("dayValue: "+dayValue);
        if (dayValue==7){
            dayValue=0;
        }
        for (int i = 0; i < dayValue; i++, counter++) {
            System.out.printf("%-4s", "");
            Label day=new Label("");
            grid.addRow(currentRow,day);


        }


        ColumnConstraints colConstraint = new ColumnConstraints(120);
        colConstraint.setHalignment(HPos.CENTER);

        RowConstraints rowConstraints = new RowConstraints(130);

        rowConstraints.setValignment(VPos.CENTER);
        grid.getColumnConstraints().addAll(colConstraint, colConstraint, colConstraint,colConstraint,colConstraint,colConstraint,colConstraint);

        for (int i = 1; i <= ym.getMonth().length(ym.isLeapYear()); i++, counter++) {

            System.out.printf("%-4d", i);
            Label day=new Label();
            day.setText(String.valueOf(i));
//            grid.getColumnIndex();


            VBox pane=new VBox();
//            StackPane pane= new StackPane();
            pane.setStyle("-fx-background-color:#f9f3c5;");
            pane.setMinWidth(120);
            pane.setMinHeight(120);
//            pane.setAlignment(day, Pos.TOP_LEFT);
            pane.getChildren().add(day);

            TableView tb= new TableView();
            Label EventName=new Label();
            EventName.setText("hi");
            pane.getChildren().add(tb);
            tb.prefHeight(30.0);

            EventName.setStyle("-fx-border-color: black");
            EventName.setStyle("-fx-background-color:#e6e6ff");
            grid.setVgrow(pane, Priority.ALWAYS);

            TableColumn newCol= new TableColumn();
            newCol.setCellValueFactory(new PropertyValueFactory<>("title"));

            tb.getColumns().add(newCol);

            tb.setRowFactory( tv -> {
                TableRow<MultiEvent> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        MultiEvent rowData = row.getItem();
                        eventId=rowData.getEventID();
                        System.out.println(rowData);
                        try {

                            Parent root = FXMLLoader.load(getClass().getResource("viewEvent.fxml"));
                            System.out.println("this is register button");

//                             Stage stage = new Stage();
//                             stage.setTitle("My New Stage Title");
//                             stage.setScene(new Scene(root, 800, 600));
//                             stage.setAlwaysOnTop(true);
//                             stage.show();

                            Stage window = (Stage) row.getScene().getWindow();
                            window.setScene(new Scene(root, 800, 800));

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                });
                return row ;
            });




            pane.setStyle("-fx-border-color: black");
            day.setStyle("-fx-background-color:#f9f3c5;");
            day.setStyle("-fx-border-color: black");
            grid.addRow(currentRow,pane);

            // Break the line if the value of the counter is multiple of 7
            if (counter % 7 == 0) {
                System.out.println();
                currentRow++;
                grid.getRowConstraints().add(rowConstraints);
            }
        }

    }

    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Calendar_Main.fxml"));

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date today = new Date();
        Calendar cal= Calendar.getInstance();
        cal.setTime(today);
        System.out.print(today);
        int year= cal.get(Calendar.YEAR);
        int month= cal.get(MONTH)+1;
        System.out.print("year: "+year+" month: "+ month);
        DateMonth.setText(String.valueOf(month));
        DateYear.setText(String.valueOf(year));

        nextDate=today;

        createNewCal(cal);
        arrangeEvents();








    }

}
