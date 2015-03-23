package myutil;

public class angle {
	public static void main(String[] args) {
		// Random ra = new Random();

		int angle = 50;
		double rad = angle * Math.PI / 180;
		double sinresult = Math.sin(rad);
		double cosresult = Math.cos(rad);
		double tanresult = Math.tan(rad);

		System.out.println("각도 : " + angle);
		System.out.println("라디안 : " + rad);
		System.out.println("사인 : " + sinresult);
		System.out.println("코사인 : " + cosresult);
		System.out.println("탄젠트 : " + tanresult);
	}
}
