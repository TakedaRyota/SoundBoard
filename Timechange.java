package SoundBoard;

public class Timechange {
	private double Min;//分数
	private double Sec;//秒数
	
	Timechange(int min1, int sec1){
		Min = sec1 / 60 + min1;
		Sec = min1 * 60 + sec1;
	}
	
	Double getSecondsTime() {
		return Sec;
	}

	Double getMinutesTime() {
		return Min;
	}
}
