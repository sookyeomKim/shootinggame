package myutil;

public class angle {
	public static void main(String[] args) {
		// Random ra = new Random();

		int angle = 50;
		double rad = angle * Math.PI / 180;
		double sinresult = Math.sin(rad);
		double cosresult = Math.cos(rad);
		double tanresult = Math.tan(rad);

		System.out.println("���� : " + angle);
		System.out.println("���� : " + rad);
		System.out.println("���� : " + sinresult);
		System.out.println("�ڻ��� : " + cosresult);
		System.out.println("ź��Ʈ : " + tanresult);
	}
}
