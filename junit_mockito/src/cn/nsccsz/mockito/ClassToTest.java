package cn.nsccsz.mockito;

public class ClassToTest {

	private MyDatabase db;
	public ClassToTest(MyDatabase db) {
		this.db = db;
	}

	public boolean query(String string) {
		db.query(string);
		return true;
	}

}
