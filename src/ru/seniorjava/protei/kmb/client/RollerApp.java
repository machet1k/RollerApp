package ru.seniorjava.protei.kmb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import ru.seniorjava.protei.kmb.client.objects.*;

public class RollerApp implements EntryPoint {

	StringServiceAsync stringService = GWT.create(StringService.class);
	
	// Комплекты роликовых коньков
	private RollerSkates romanRollerSkates = new RollerSkates("Ролики Романа",
		new Frame("Seba Deluxe black", "Aluminium", "4x90mm", "165mm",  "273mm"), 
		new Wheels("Matter SUPER Juice", "90mm", "F1(84a)"), 
		new Bearings("Wicked Hybrid Ceramic", "Speed", "Steel", "7", "Ceramic"), 
		new Boot("Seba FR1", "Synthetic", "165mm", "High"));
	
	private RollerSkates bartRollerSkates = new RollerSkates("Ролики Свингса",
		new Frame("Powerslide Triple-X", "Aluminium", "3x125mm", "195mm",  "12.6''"), 
		new Wheels("Matter one-20-five", "125mm", "F0(88a)"), 
		new Bearings("Movemax Full Ceramic Speedbearings", "Speed", "Ceramic", "6", "Ceramic"), 
		new Boot("Powerslide Custom Boots", "Carbon", "195mm", "Low"));
	
	private RollerSkates bogrecRollerSkates = new RollerSkates("Ролики Богреца",
		new Frame("Powerslide Pleasure Tool 2.0", "Aluminium 6061", "3x110mm", "165mm",  "9.7''/246mm"), 
		new Wheels("Powerslide Fothon LED", "110mm", "82a"), 
		new Bearings("Twincam ILQ 7", "Speed", "Steel", "6", "Steel"), 
		new Boot("Seba Deluxe", "Synthetic", "165mm", "Middle"));

	// Лейблы роллеров
	private Label roman = new Label(romanRollerSkates.getName());
	private Label bart  = new Label(bartRollerSkates.getName());
	private Label bogrec = new Label(bogrecRollerSkates.getName());
	
	// ссылка на выбранного роллера
	private RollerSkates selectedRS = null;
	
	private Button saveButton = new Button("Сохранить изменения");
	private Button estimateButton = new Button("Произвести оценку");
	
	private Label estimatedPrice = new Label();
	
	private VerticalPanel skatersList = new VerticalPanel();

	private Label titleOfDetails = new Label("Выберите ролики из списка");
	
	private final DialogBox dialogBox = new DialogBox();
	
	// bearings
	private TextBox brName = new TextBox();
	private TextBox brClassification = new TextBox(); 
	private TextBox brMaterial = new TextBox(); 
	private TextBox brBalls = new TextBox(); 
	private TextBox brMaterialBalls = new TextBox(); 
	private Grid bearingsGrid = new Grid(5, 2);
	private VerticalPanel bearingsPanel = new VerticalPanel();
	// boot
	private TextBox btName = new TextBox();
	private TextBox btMaterial = new TextBox(); 
	private TextBox btMounting = new TextBox(); 
	private TextBox btCuff = new TextBox(); 
	private Grid bootGrid = new Grid(4, 2);
	private VerticalPanel bootPanel = new VerticalPanel();
	// frame
	private TextBox frName = new TextBox();
	private TextBox frMaterial = new TextBox(); 
	private TextBox frMounting = new TextBox(); 
	private TextBox frSetup = new TextBox(); 
	private TextBox frLength = new TextBox(); 
	private Grid frameGrid = new Grid(5, 2);
	private VerticalPanel framePanel = new VerticalPanel();
	// wheels
	private TextBox whName = new TextBox();
	private TextBox whSize = new TextBox(); 
	private TextBox whHardness = new TextBox();
	private Grid wheelsGrid = new Grid(3, 2);
	private VerticalPanel wheelsPanel = new VerticalPanel();

	private HorizontalPanel detailsContent = new HorizontalPanel();
	private VerticalPanel detailsAndTitle = new VerticalPanel();
	private HorizontalPanel horizontPanel = new HorizontalPanel();
	
	private final Button closeButton = new Button("Понятно");
	private final HTML serverResponse = new HTML();
	
	public void onModuleLoad() {
		roman.setStyleName("elementOfSkaterlist");
		roman.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { showDetails(romanRollerSkates); }
		});	
		
		bart.setStyleName("elementOfSkaterlist");
		bart.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { showDetails(bartRollerSkates); }
		});
		
		bogrec.setStyleName("elementOfSkaterlist");
		bogrec.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { showDetails(bogrecRollerSkates); }
		});	
		
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(serverResponse);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		saveButton.addStyleName("button");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (selectedRS != null) {
					
					String rewrite = "";
					boolean isChanged = false;
					
					Boot boot = selectedRS.getBoot();
					if (!btName.getText().equals(boot.getName())) { boot.setName(btName.getText()); rewrite += "<li>Название ботинка</li>"; isChanged = true; }
					if (!btMaterial.getText().equals(boot.getMaterial())) { boot.setMaterial(btMaterial.getText()); rewrite += "<li>Материал ботинка</li>"; isChanged = true; }
					if (!btMounting.getText().equals(boot.getMounting())) { boot.setMounting(btMounting.getText()); rewrite += "<li>Разбивка ботинка</li>"; isChanged = true; }
					if (!btCuff.getText().equals(boot.getCuff())) { boot.setCuff(btCuff.getText()); rewrite += "<li>Кафф ботинка</li>"; isChanged = true; }
					
					Frame frame = selectedRS.getFrame();
					if (!frName.getText().equals(frame.getName())) { frame.setName(frName.getText()); rewrite += "<li>Название рамы</li>"; isChanged = true; }
					if (!frMounting.getText().equals(frame.getMounting())) { frame.setMounting(frMounting.getText()); rewrite += "<li>Разбивка рамы</li>"; isChanged = true; }
					if (!frMaterial.getText().equals(frame.getMaterial())) { frame.setMaterial(frMaterial.getText()); rewrite += "<li>Материал рамы</li>"; isChanged = true; }
					if (!frSetup.getText().equals(frame.getSetup())) { frame.setSetup(frSetup.getText()); rewrite += "<li>Формат рамы</li>"; isChanged = true; }
					if (!frLength.getText().equals(frame.getLength())) { frame.setLength(frLength.getText()); rewrite += "<li>Длина рамы</li>"; isChanged = true; }
					
					Wheels wheels = selectedRS.getWheels();
					if (!whName.getText().equals(wheels.getName())) { wheels.setName(whName.getText()); rewrite += "<li>Название колёс</li>"; isChanged = true; }
					if (!whSize.getText().equals(wheels.getSize())) { wheels.setSize(whSize.getText()); rewrite += "<li>Диаметр колёс</li>"; isChanged = true; }
					if (!whHardness.getText().equals(wheels.getHardness())) { wheels.setHardness(whHardness.getText()); rewrite += "<li>Твёрдость колёс</li>"; isChanged = true; }
					
					Bearings bearings = selectedRS.getBearings();
					if (!brName.getText().equals(bearings.getName())) { bearings.setName(brName.getText()); rewrite += "<li>Название подшипников</li>"; isChanged = true; }
					if (!brClassification.getText().equals(bearings.getClassification())) { bearings.setClassification(brClassification.getText()); rewrite += "<li>Классификация подшипников</li>"; isChanged = true; }
					if (!brMaterial.getText().equals(bearings.getMaterial())) { bearings.setMaterial(brMaterial.getText()); rewrite += "<li>Материал самих подшипников</li>"; isChanged = true; }
					if (!brBalls.getText().equals(bearings.getBalls())) { bearings.setBalls(brBalls.getText()); rewrite += "<li>Число подшипников</li>"; isChanged = true; }
					if (!brMaterialBalls.getText().equals(bearings.getMaterialBalls())) { bearings.setMaterialBalls(brMaterialBalls.getText()); rewrite += "<li>Материал шаров подшипников</li>"; isChanged = true; }
					
					if (isChanged) {
						dialogBox.setText("Уведомление об изменении");
						serverResponse.setHTML("Были изменены следующие элементы:<ol>" + rewrite + "</ol>");
						dialogBox.center();
					} else Window.alert("Ни одно из полей не было изменено.");
				} else Window.alert("Не выбран ни один роллер из списка.");
			}
		});
		
		estimateButton.addStyleName("button");
		estimateButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (selectedRS != null) {
					stringService.checkString(selectedRS, new AsyncCallback<Integer>() {			
						@Override
						public void onSuccess(Integer result) {
							estimatedPrice.setStyleName("estimatedPrice");
							estimatedPrice.setText("Ролики оцениваются в $" + result);
						}			
						@Override
						public void onFailure(Throwable caught) {
							estimatedPrice.setText("Произошла ошибка: " + caught.getMessage());
						}
					});
				} else {
					Window.alert("Не выбран ни один роллер из списка.");
				}
			}
		});
		
		skatersList.add(roman);
		skatersList.add(bart);
		skatersList.add(bogrec);
		skatersList.add(saveButton);
		skatersList.add(estimateButton);
		
		skatersList.addStyleName("skaterList");
		
		bootGrid.setStyleName("gridSection");
		bootGrid.setWidget(0, 0, new Label("Название"));
		bootGrid.setWidget(0, 1, btName);
		bootGrid.setWidget(1, 0, new Label("Материал"));
		bootGrid.setWidget(1, 1, btMaterial);
		bootGrid.setWidget(2, 0, new Label("Разбивка"));
		bootGrid.setWidget(2, 1, btMounting);
		bootGrid.setWidget(3, 0, new Label("Кафф"));
		bootGrid.setWidget(3, 1, btCuff);
		Label titleBoot = new Label("Ботинок");
		titleBoot.setStyleName("titleOfCategoryDetails");
		bootPanel.add(titleBoot);
		bootPanel.add(bootGrid);
		
		frameGrid.setStyleName("gridSection");
		frameGrid.setWidget(0, 0, new Label("Название"));
		frameGrid.setWidget(0, 1, frName);
		frameGrid.setWidget(1, 0, new Label("Материал"));
		frameGrid.setWidget(1, 1, frMaterial);
		frameGrid.setWidget(2, 0, new Label("Разбивка"));
		frameGrid.setWidget(2, 1, frMounting);
		frameGrid.setWidget(3, 0, new Label("Формат"));
		frameGrid.setWidget(3, 1, frSetup);
		frameGrid.setWidget(4, 0, new Label("Длина"));
		frameGrid.setWidget(4, 1, frLength);
		Label titleFrame = new Label("Рама");
		titleFrame.setStyleName("titleOfCategoryDetails");
		framePanel.add(titleFrame);
		framePanel.add(frameGrid);
		
		wheelsGrid.setStyleName("gridSection");
		wheelsGrid.setWidget(0, 0, new Label("Название"));
		wheelsGrid.setWidget(0, 1, whName);
		wheelsGrid.setWidget(1, 0, new Label("Диаметр"));
		wheelsGrid.setWidget(1, 1, whSize);
		wheelsGrid.setWidget(2, 0, new Label("Твёрдость"));
		wheelsGrid.setWidget(2, 1, whHardness);
		Label titleWheels = new Label("Колёса");
		titleWheels.setStyleName("titleOfCategoryDetails");
		wheelsPanel.add(titleWheels);
		wheelsPanel.add(wheelsGrid);
		
		bearingsGrid.setStyleName("gridSection");
		bearingsGrid.setWidget(0, 0, new Label("Название"));
		bearingsGrid.setWidget(0, 1, brName);
		bearingsGrid.setWidget(1, 0, new Label("Классификация"));
		bearingsGrid.setWidget(1, 1, brClassification);
		bearingsGrid.setWidget(2, 0, new Label("Материал"));
		bearingsGrid.setWidget(2, 1, brMaterial);
		bearingsGrid.setWidget(3, 0, new Label("Шары"));
		bearingsGrid.setWidget(3, 1, brBalls);
		bearingsGrid.setWidget(4, 0, new Label("Материал шаров"));
		bearingsGrid.setWidget(4, 1, brMaterialBalls);
		Label titleBearings = new Label("Подшипники");
		titleBearings.setStyleName("titleOfCategoryDetails");
		bearingsPanel.add(titleBearings);
		bearingsPanel.add(bearingsGrid);
		
		detailsContent.add(bootPanel);
		detailsContent.add(framePanel);
		detailsContent.add(wheelsPanel);
		detailsContent.add(bearingsPanel);
		
		titleOfDetails.setStyleName("titleOfDetails");
		detailsAndTitle.add(titleOfDetails);
		detailsAndTitle.add(detailsContent);
		
		horizontPanel.add(skatersList);
		horizontPanel.add(detailsAndTitle);
		
		RootPanel.get("main").add(horizontPanel);
		RootPanel.get("main").add(estimatedPrice);
	}
	
	private void showDetails(RollerSkates rollerSkates) {

		titleOfDetails.setText(rollerSkates.getName());
		selectedRS = rollerSkates;
		
		btName.setText(rollerSkates.getBoot().getName());
		btMaterial.setText(rollerSkates.getBoot().getMaterial());
		btMounting.setText(rollerSkates.getBoot().getMounting());
		btCuff.setText(rollerSkates.getBoot().getCuff());

		frName.setText(rollerSkates.getFrame().getName());
		frMaterial.setText(rollerSkates.getFrame().getMaterial());
		frMounting.setText(rollerSkates.getFrame().getMounting());
		frSetup.setText(rollerSkates.getFrame().getSetup());
		frLength.setText(rollerSkates.getFrame().getLength());
			
		whName.setText(rollerSkates.getWheels().getName());
		whSize.setText(rollerSkates.getWheels().getSize());
		whHardness.setText(rollerSkates.getWheels().getHardness());

		brName.setText(rollerSkates.getBearings().getName());
		brClassification.setText(rollerSkates.getBearings().getClassification());
		brMaterial.setText(rollerSkates.getBearings().getMaterial());
		brBalls.setText(rollerSkates.getBearings().getBalls());
		brMaterialBalls.setText(rollerSkates.getBearings().getMaterialBalls());
	}
	
}
