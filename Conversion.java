package SoundBoard;

//再生時間を「○分○秒」という表示に変える
public class Conversion {
	private String time2;
	private int min;//分数
	private int sec;//秒数
	private int mil;//
	private double time;
	
	Conversion(double time1) {
		time = time1;//再生時間を分で受け取る
		min = (int) time;//分
		double a = time - min;
		sec = (int)(a*60);//秒
		double b = a * 60 - sec;
		mil = (int)(b*60);
	}
	String getTime2() {
		time2 = (min + " : " + sec + " : " + mil);
		return time2;
	}

}
