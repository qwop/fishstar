package cn.nsccsz.stub;

import org.junit.Test;
import org.mockito.Mockito;

public class TestStub {
	static interface USB {
		void work();
	}
	@Test
	public void testMockObject() {
		USB usb = Mockito.mock( USB.class );
		usb.work();
	}
}
