package test.a.b.c;

public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		test.name();
	}

	private void name() {

		Class<?> testClass = Test.class;
		System.out.println(testClass.getCanonicalName());
		System.out.println(testClass.getSimpleName());
		System.out.println(testClass.getTypeName());
	}
}
