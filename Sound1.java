/*------------SoundBoard----------------*/
/* Producted by Takeda Ryota
 * version : 1.1.0
 */

package SoundBoard;

import java.io.*;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Sound1 extends Application{
	
	int i;
	
	Slider slider = new Slider();//スライダーの生成
	Slider volumeslider = new Slider(0, 100, 100);//ボリュームスライダー
	Label musicname = new Label();//曲名の表示
	Label playtimer = new Label();//再生時間を表示する
	String[] buttonname = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
	Label keydisplay = new Label();//再生されているボタンを表示する
	Label setdisplay = new Label();//設定のキーを表示する
	Label[] label = new Label[9];
	TextField textfield = new TextField();//音声ファイルの入力
	CheckBox repeat = new CheckBox("リピート");//リピート
	CheckBox feedin = new CheckBox("フェードイン");//フェードイン
	CheckBox feedout = new CheckBox("フェードアウト");//フェードアウト
	ChoiceBox<String> baisoku = new ChoiceBox<>();//速度変更
	TextField playtime0 = new TextField();//再生時間の分数
	TextField playtime1 = new TextField();//開始時間の秒数
	TextField playtime2 = new TextField();//終了時間の分数
	TextField playtime3 = new TextField();//終了時間の秒数
	CheckBox timeset = new CheckBox("再生時間設定");//再生時間を設定するかどうか
	String[][] st = new String[4][36];//再生時間の保存
	BorderPane borderPane = new BorderPane();//ボーダーペイン
	GridPane gridPane = new GridPane();//グリッドペイン
	FlowPane root = new FlowPane();//フローペイン
	FlowPane stroot = new FlowPane();//開始時間の入力部
	FlowPane fnroot = new FlowPane();//終了時間の入力部
	String[] musicfile = new String[36];//設定からの曲を取得
	boolean[] timest = new boolean[36];//再生時間の設定可否を記憶する
	boolean[] repeatst = new boolean[36];//リピートの可否を記憶する
	
	Button update = new Button("更新");
			
	public void start(Stage stage) throws Exception {
		//ステージのタイトルとサイズ
		stage.setTitle("SoundPlayer");
		stage.setWidth(900);
		stage.setHeight(600);
		
		musicname.setPadding(new Insets(5, 0, 0, 0));//曲名表示の上を少し開ける
		
		//コントロールの初期化
		Button[] button = new Button[36];
		Label[] spacelabel = new Label[9];
		Button start = new Button("再生→[,]");
		Button stop = new Button("一時停止→[.]");
		Button[] setbutton = new Button[36];
		
		//キーボードの設定
		for(i = 0; i < 36; i++) {
			button[i] = new Button(buttonname[i]);//アクションボタン生成
			setbutton[i] = new Button("▼");//セットアップボタン生成
			button[i].setPrefSize(stage.getWidth()/10, 75);
			setbutton[i].setPrefSize(stage.getWidth()/10, 15);
			setbutton[i].setFont(Font.font(8));
		} 
		//              0   1   2   3   4   5   6    7    8    
		int[] width1 = {200, 30, 60, 30, 60, 60, 60, 105, 105};
		int[] height1 = {20, 75, 75, 15, 15, 75, 15, 15,   15};
		//spaceとなるキーのサイズ
		for(i = 0; i <= 8; i++) {
			spacelabel[i] = new Label();
			spacelabel[i].setPrefSize(width1[i], height1[i]);
			label[i] = new Label();
		}
		start.setPrefSize(105, 75);
		stop.setPrefSize(105, 75);
		
		button[0].setOnAction(event -> buttonPressed(0));
		button[1].setOnAction(event -> buttonPressed(1));
		button[2].setOnAction(event -> buttonPressed(2));
		button[3].setOnAction(event -> buttonPressed(3));
		button[4].setOnAction(event -> buttonPressed(4));
		button[5].setOnAction(event -> buttonPressed(5));
		button[6].setOnAction(event -> buttonPressed(6));
		button[7].setOnAction(event -> buttonPressed(7));
		button[8].setOnAction(event -> buttonPressed(8));
		button[9].setOnAction(event -> buttonPressed(9));
		button[10].setOnAction(event -> buttonPressed(10));
		button[11].setOnAction(event -> buttonPressed(11));
		button[12].setOnAction(event -> buttonPressed(12));
		button[13].setOnAction(event -> buttonPressed(13));
		button[14].setOnAction(event -> buttonPressed(14));
		button[15].setOnAction(event -> buttonPressed(15));
		button[16].setOnAction(event -> buttonPressed(16));
		button[17].setOnAction(event -> buttonPressed(17));
		button[18].setOnAction(event -> buttonPressed(18));
		button[19].setOnAction(event -> buttonPressed(19));
		button[20].setOnAction(event -> buttonPressed(20));
		button[21].setOnAction(event -> buttonPressed(21));
		button[22].setOnAction(event -> buttonPressed(22));
		button[23].setOnAction(event -> buttonPressed(23));
		button[24].setOnAction(event -> buttonPressed(24));
		button[25].setOnAction(event -> buttonPressed(25));
		button[26].setOnAction(event -> buttonPressed(26));
		button[27].setOnAction(event -> buttonPressed(27));
		button[28].setOnAction(event -> buttonPressed(28));
		button[29].setOnAction(event -> buttonPressed(29));
		button[30].setOnAction(event -> buttonPressed(30));
		button[31].setOnAction(event -> buttonPressed(31));
		button[32].setOnAction(event -> buttonPressed(32));
		button[33].setOnAction(event -> buttonPressed(33));
		button[34].setOnAction(event -> buttonPressed(34));
		button[35].setOnAction(event -> buttonPressed(35));
		start.setOnAction(event -> nowplaying.play());
		stop.setOnAction(event -> nowplaying.pause());
		
		setbutton[0].setOnAction(event -> setbuttonPressed(0));
		setbutton[1].setOnAction(event -> setbuttonPressed(1));
		setbutton[2].setOnAction(event -> setbuttonPressed(2));
		setbutton[3].setOnAction(event -> setbuttonPressed(3));
		setbutton[4].setOnAction(event -> setbuttonPressed(4));
		setbutton[5].setOnAction(event -> setbuttonPressed(5));
		setbutton[6].setOnAction(event -> setbuttonPressed(6));
		setbutton[7].setOnAction(event -> setbuttonPressed(7));
		setbutton[8].setOnAction(event -> setbuttonPressed(8));
		setbutton[9].setOnAction(event -> setbuttonPressed(9));
		setbutton[10].setOnAction(event -> setbuttonPressed(10));
		setbutton[11].setOnAction(event -> setbuttonPressed(11));
		setbutton[12].setOnAction(event -> setbuttonPressed(12));
		setbutton[13].setOnAction(event -> setbuttonPressed(13));
		setbutton[14].setOnAction(event -> setbuttonPressed(14));
		setbutton[15].setOnAction(event -> setbuttonPressed(15));
		setbutton[16].setOnAction(event -> setbuttonPressed(16));
		setbutton[17].setOnAction(event -> setbuttonPressed(17));
		setbutton[18].setOnAction(event -> setbuttonPressed(18));
		setbutton[19].setOnAction(event -> setbuttonPressed(19));
		setbutton[20].setOnAction(event -> setbuttonPressed(20));
		setbutton[21].setOnAction(event -> setbuttonPressed(21));
		setbutton[22].setOnAction(event -> setbuttonPressed(22));
		setbutton[23].setOnAction(event -> setbuttonPressed(23));
		setbutton[24].setOnAction(event -> setbuttonPressed(24));
		setbutton[25].setOnAction(event -> setbuttonPressed(25));
		setbutton[26].setOnAction(event -> setbuttonPressed(26));
		setbutton[27].setOnAction(event -> setbuttonPressed(27));
		setbutton[28].setOnAction(event -> setbuttonPressed(28));
		setbutton[29].setOnAction(event -> setbuttonPressed(29));
		setbutton[30].setOnAction(event -> setbuttonPressed(30));
		setbutton[31].setOnAction(event -> setbuttonPressed(31));
		setbutton[32].setOnAction(event -> setbuttonPressed(32));
		setbutton[33].setOnAction(event -> setbuttonPressed(33));
		setbutton[34].setOnAction(event -> setbuttonPressed(34));
		setbutton[35].setOnAction(event -> setbuttonPressed(35));
		
		slider.setPrefSize(500, 20);
		//Volumeの表示
		label[0].setPrefSize(50, 20);
		label[0].setText("Volume");
		volumeslider.setPrefSize(100, 20);
		volumeslider.setOnMouseClicked(event -> volumeslide(volumeslider.getValue()));
		//再生中の曲名を表示する
		musicname.setPrefSize(900, 20);
		musicname.setAlignment(Pos.TOP_CENTER);//中央に配置
		//再生中の曲の時間を表示する
		playtimer.setPrefSize(900, 20);
		playtimer.setAlignment(Pos.TOP_RIGHT);
		//再生されているボタンを表示する
		keydisplay.setPrefSize(70, 160);
		keydisplay.setBackground(new Background(new BackgroundFill( Color.BLACK, null, null)));
		keydisplay.setAlignment(Pos.CENTER);
		keydisplay.setTextFill(Color.WHITE);
		keydisplay.setFont(new Font(20));
		
		/*****ボタンの設定画面//BorderPaneにて作製****/
		textfield.setPrefSize(380, 10);
		textfield.setFont(Font.font(9));
		borderPane.setPrefSize(400, 50);
		borderPane.setPadding(new Insets(2, 5, 100, 10));
		
		/*********曲の設定//GridPaneにて作製****/
		
		label[2].setText("曲の設定");
		repeat.setFont(Font.font(10));//リピート
		feedin.setFont(Font.font(10));//フェードイン
		feedout.setFont(Font.font(10));//フェードアウト
		label[4].setText("速度：");
		baisoku.getItems().addAll("×2", "×1.5", "×1", "×0.8", "×0.5");//速度調整のチョイスボックス
		baisoku.setValue("×1");
		baisoku.setPrefSize(60, 10);
		label[3].setText(" : ");
		label[3].setFont(Font.font(10));
		label[7].setText(" : ");
		label[7].setFont(Font.font(10));
		label[5].setText("開始時間：");
		label[6].setText("終了時間：");
		stroot.setPrefSize(90, 10);
		fnroot.setPrefSize(90, 10);
		playtime0.setFont(Font.font(10));
		playtime0.setPrefSize(30, 10);
		playtime0.setText("00");
		playtime1.setFont(Font.font(10));
		playtime1.setPrefSize(30, 10);
		playtime1.setText("00");
		playtime2.setFont(Font.font(10));
		playtime2.setPrefSize(30, 10);
		playtime3.setFont(Font.font(10));
		playtime3.setPrefSize(30, 10);
		timeset.setFont(Font.font(10));
		
		stroot.getChildren().add(playtime0);
		stroot.getChildren().add(label[3]);
		stroot.getChildren().add(playtime1);
		fnroot.getChildren().add(playtime2);
		fnroot.getChildren().add(label[7]);
		fnroot.getChildren().add(playtime3);
		
		gridPane.setPrefSize(430, 160);
		gridPane.setPadding(new Insets(2, 10, 5, 20));//(top, right, bottom, left)
		gridPane.add(label[2], 0, 0);
		gridPane.add(repeat, 0, 2);
		gridPane.add(feedin, 0, 3);
		gridPane.add(feedout, 0, 4);
		gridPane.add(label[4], 0, 5);
		gridPane.add(baisoku, 1, 5);
		gridPane.add(label[5], 0, 6);
		gridPane.add(stroot, 1, 6);
		gridPane.add(timeset, 2, 6);
		gridPane.add(label[6], 0, 7);
		gridPane.add(fnroot, 1, 7);
		
		//ルートペインを生成し，コントロールを配置
		root.getChildren().add(musicname);
		root.getChildren().add(spacelabel[0]);
		root.getChildren().add(slider);
		root.getChildren().add(label[0]);
		root.getChildren().add(volumeslider);
		root.getChildren().add(playtimer);
		for(i = 0;i <= 9; i++) {
			root.getChildren().add(button[i]);
		}
		for(i = 0;i <= 9; i++) {
			root.getChildren().add(setbutton[i]);
		}
		for(i = 10;i <= 19; i++) {
			root.getChildren().add(button[i]);
		}
		for(i = 10;i <= 19; i++) {
			root.getChildren().add(setbutton[i]);
		}
		root.getChildren().add(spacelabel[1]);
		for(i = 20; i <= 28; i++) {
			root.getChildren().add(button[i]);
		}
		root.getChildren().add(spacelabel[2]);
		root.getChildren().add(spacelabel[3]);
		for(i = 20; i <= 28; i++) {
			root.getChildren().add(setbutton[i]);
		}
		root.getChildren().add(spacelabel[4]);
		root.getChildren().add(spacelabel[5]);
		for(i = 29; i <= 35; i++) {
			root.getChildren().add(button[i]);
		}
		root.getChildren().add(start);
		root.getChildren().add(stop);
		root.getChildren().add(spacelabel[6]);
		for(i = 29; i <= 35; i++) {
			root.getChildren().add(setbutton[i]);
		}
		root.getChildren().add(spacelabel[7]);
		root.getChildren().add(spacelabel[8]);
		root.getChildren().add(keydisplay);
		root.getChildren().add(borderPane);
		root.getChildren().add(gridPane);
		
		//ルートペインをもとにシーンを生成
		Scene scene = new Scene(root);
		
		//ハードウェアのキーボードのアクション
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();
		    	switch (code) {
		        	case DIGIT1:
		        		buttonPressed(0);break;
		            case DIGIT2:
		            	buttonPressed(1);break;
		            case DIGIT3:
		            	buttonPressed(2);break;
		            case DIGIT4:
		            	buttonPressed(3);break;
		            case DIGIT5:
		            	buttonPressed(4);break;
		            case DIGIT6:
		            	buttonPressed(5);break;
		            case DIGIT7:
		            	buttonPressed(6);break;
		            case DIGIT8:
		            	buttonPressed(7);break;
		            case DIGIT9:
		            	buttonPressed(8);break;
		            case DIGIT0:
		            	buttonPressed(9);break;
		            case Q:
		            	buttonPressed(10);break;
		            case W:
		            	buttonPressed(11);break;
		            case E:
		            	buttonPressed(12);break;
		            case R:
		            	buttonPressed(13);break;
		            case T:
		            	buttonPressed(14);break;
		            case Y:
		            	buttonPressed(15);break;
		            case U:
		            	buttonPressed(16);break;
		            case I:
		            	buttonPressed(17);break;
		            case O:
		            	buttonPressed(18);break;
		            case P:
		            	buttonPressed(19);break;
		            case A:
		            	buttonPressed(20);break;
		            case S:
		            	buttonPressed(21);break;
		            case D:
		            	buttonPressed(22);break;
		            case F:
		            	buttonPressed(23);break;
		            case G:
		            	buttonPressed(24);break;
		            case H:
		            	buttonPressed(25);break;
		            case J:
		            	buttonPressed(26);break;
		            case K:
		            	buttonPressed(27);break;
		            case L:
		            	buttonPressed(28);break;
		            case Z:
		            	buttonPressed(29);break;
		            case X:
		            	buttonPressed(30);break;
		            case C:
		            	buttonPressed(31);break;
		            case V:
		            	buttonPressed(32);break;
		            case B:
		            	buttonPressed(33);break;
		            case N:
		            	buttonPressed(34);break;
		            case M:
		            	buttonPressed(35);break;
		            case COMMA://カンマ・キー
		            	nowplaying.play();break;
		            case PERIOD://ピリオド・キー
		            	nowplaying.pause();break;
		            default:
		                break;
		    	}
			}
		});
	
		//ステージにシーンを配置
		stage.setScene(scene);
		//アプリケーションウィンドウを表示
		stage.show();
	}

	//プレイリストの初期化
	MediaPlayer nowplaying = null;//再生中
	MediaPlayer newplaying = null;//次の再生
	
	//ボタンアクションの定義
	public void buttonPressed(int a) {
		try {
			File music = new File(musicfile[a]);//ファイルの取得
			String filename = music.getName();//ファイルの名前拡張子あり
			newplaying = new MediaPlayer(new Media((music.toURI().toString())));	
			keydisplay.setText(buttonname[a]);

		//現在再生されていない時，そのまま再生
		if(nowplaying == null) {
			newplaying.play();
			nowplaying = newplaying;
			
			//ボリュームの設定
			nowplaying.setVolume(volumeslider.getValue()/100);
			
			if(timest[a]) {
				//再生時間の設定
				Timechange startime = new Timechange(Integer.parseInt(st[0][a]), Integer.parseInt(st[1][a]));
				nowplaying.setStartTime(Duration.seconds(startime.getSecondsTime()));
				Timechange finishtime = new Timechange(Integer.parseInt(st[2][a]), Integer.parseInt(st[3][a]));
				nowplaying.setStopTime(Duration.seconds(finishtime.getSecondsTime()));
			}
			
			
			//スライダーの最大最小を設定
			nowplaying.setOnReady(() -> {
			  slider.setMin(nowplaying.getStartTime().toSeconds());
			  slider.setMax(nowplaying.getStopTime().toSeconds());
			  //再生中の曲名を表示
			  musicname.setText(filename.substring(0, filename.lastIndexOf('.')));//拡張子を除く
			  //音声の長さを表示
			  double t = nowplaying.getStopTime().toMinutes() - nowplaying.getStartTime().toMinutes();
			  Conversion playtime = new Conversion(t);
			  playtimer.setText("再生時間" + playtime.getTime2());
			 });
			//再生時間が進んだらスライダーの位置を変更する
			nowplaying.currentTimeProperty().addListener((Observable observable) -> slider.setValue( nowplaying.getCurrentTime().toSeconds() ));
			newplaying = null;
			//曲の速度の調整
			baisokuu(baisoku.getValue());
			/*--曲の各種設定--*/
			if(repeatst[a]){
				nowplaying.setCycleCount(20);
			}
		}
		//現在再生されているものがある時，それをストップして次のものを再生
		else {
			nowplaying.stop();
			newplaying.play();
			nowplaying = newplaying;
			
			//ボリュームの設定
			nowplaying.setVolume(volumeslider.getValue()/100);
			
			if(timest[a]) {
				//再生時間の設定
				Timechange startime = new Timechange(Integer.parseInt(st[0][a]), Integer.parseInt(st[1][a]));
				nowplaying.setStartTime(Duration.seconds(startime.getSecondsTime()));
				Timechange finishtime = new Timechange(Integer.parseInt(st[2][a]), Integer.parseInt(st[3][a]));
				nowplaying.setStopTime(Duration.seconds(finishtime.getSecondsTime()));
			}
			
			//スライダーの最大最小を設定
			nowplaying.setOnReady(() -> {
			  slider.setMin(nowplaying.getStartTime().toSeconds());
			  slider.setMax(nowplaying.getStopTime().toSeconds());
			//再生中の曲名を表示
			  musicname.setText(filename.substring(0, filename.lastIndexOf('.')));//拡張子を除く
			//音声の長さを表示
			  double t = nowplaying.getStopTime().toMinutes() - nowplaying.getStartTime().toMinutes();
			  Conversion playtime = new Conversion(t);
			  playtimer.setText("再生時間" + playtime.getTime2());
			 });
			//再生時間が進んだらスライダーの位置を変更する
			nowplaying.currentTimeProperty().addListener((Observable observable) -> slider.setValue( nowplaying.getCurrentTime().toSeconds() ));
			newplaying = null;
			//曲の速度の調整
			baisokuu(baisoku.getValue());
			/*--曲の各種設定--*/
			if(repeatst[a]){
				nowplaying.setCycleCount(20);
			}
		}
	} catch (NullPointerException e) {
	} catch (ArithmeticException e) {
	} catch (MediaException e) {
		musicname.setText("ファイルが存在しません。パスを確認してください。");
	}
}
	
	//volumesliderが調整された時のアクション
	public void volumeslide(double volume) {
		try {
			nowplaying.setVolume(volume/100);
		} catch (NullPointerException e) {
		}
	}
	
	//設定ボタンが押された時のアクション
	public void setbuttonPressed(int b) {
		setdisplay.setText("  [  " + buttonname[b] + "  ]" + "音声ファイルの追加");
		update.setFont(Font.font(10));
		/*try {
			FileInputStream fo = new FileInputStream("/Users/ryota/Desktop/Backupfile.ser");
			ObjectInputStream oo = new ObjectInputStream(fo);
			musicfile[b] = (String) oo.readObject();
			oo.close();
		} catch(IOException e) {
			System.out.println(e);
		} catch(ClassNotFoundException e) {
			System.out.println(e);
		}*/
		textfield.setText(musicfile[b]);
		borderPane.setTop(setdisplay);
		borderPane.setLeft(textfield);
		borderPane.setBottom(update);
		playtime0.setText(st[0][b]);
		playtime1.setText(st[1][b]);
		playtime2.setText(st[2][b]);
		playtime3.setText(st[3][b]);
		repeat.setSelected(repeatst[b]);
		timeset.setSelected(timest[b]);
			
		update.setOnAction((ActionEvent) -> {
			musicfile[b] = textfield.getText();
			st[0][b] = playtime0.getText();
			st[1][b] = playtime1.getText();
			st[2][b] = playtime2.getText();
			st[3][b] = playtime3.getText();
			repeatst[b] = repeat.isSelected();
			timest[b] = timeset.isSelected();
			/*/音声ファイルの保存
			try {
				FileOutputStream fs = new FileOutputStream("/Users/ryota/Desktop/Backupfile.ser");
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(musicfile[b]);
				os.close();
			} catch (IOException e) {
				System.out.println(e);
			}*/
		});
	}

	//速度調整のアクション
	public void baisokuu(String n) {
		if(n.equals("×2")) {
			nowplaying.setRate(2.0);
		}else if(n.equals("×1.5")) {
			nowplaying.setRate(1.5);
		}else if(n.equals("×1")) {
			nowplaying.setRate(1.0);
		}else if(n.equals("×0.8")) {
			nowplaying.setRate(0.8);
		}else if(n.equals("×0.5")) {
			nowplaying.setRate(0.5);
		}
	}
	

	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
