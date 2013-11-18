package cn.nsccsz.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockitoTest  {
  @Mock
  MyDatabase databaseMock;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testQuery()  {
    // 需要测试的类
    ClassToTest t  = new ClassToTest(databaseMock);

    // 调用方法
    boolean check = t.query("* from t");

    // 验证结果
    assertTrue(check);

    // 模拟对象是否调用了该方法
    Mockito.verify( databaseMock ).query("* from t");
  }
  
  @Test
  public void testList() {
	  List mock = Mockito.mock( List.class );
	  
	  Mockito.when( mock.get( 0 ) ).thenReturn( 1 );
	  
	  assertEquals( "预期返回1", 1, mock.get( 0 ) );
  }
  
  @Test
  public void testMap() {
	  Map mock = Mockito.mock( Map.class );
	  Mockito.when( mock.get( "city" ) ).thenReturn( "深圳" );
	  
	  // test code
	  assertEquals( "城市测试", "深圳", mock.get( "city" ) );
	  
	  Mockito.verify(mock).get( Matchers.eq( "city" ) );
	  Mockito.verify( mock, Mockito.times( 2 ) );
  }
  
  @Test
  public void testSpy() {
	// Lets mock a LinkedList
	  List list = new LinkedList();
	  list.add( "yes" );
	  List spy = Mockito.spy(list);

	  //You have to use doReturn() for stubbing
	  assertEquals( "yes", spy.get( 0 ) );
	  Mockito.doReturn("foo").when(spy).get(0);
	  assertEquals( "foo", spy.get( 0 ) );
	  
  }
  
  @Test( expected = IndexOutOfBoundsException.class)
  public void testSpy2() {
	// Lets mock a LinkedList
	  List list = new LinkedList();
	  List spy = Mockito.spy(list);

	  
	  // this would not work
	  // real method is called so spy.get(0) 
	  // throws IndexOutOfBoundsException (list is still empty)
	  Mockito.when(spy.get(0)).thenReturn("foo"); 
	  assertEquals( "foo", spy.get( 0 ) );
  }
} 
