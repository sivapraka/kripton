package kripton70;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import kripton70.core.BinderType;
import kripton70.core.KriptonLibrary2;

public class Kripton70Test {

	@Test
	public void test() throws IOException
	{
		Bean bean=new Bean();
		bean.id=25;
		bean.description="hello";
		bean.valueByteType=12;
		bean.valueCharType='a';
		bean.valueShortType=13;
		bean.valueBean=new Bean();
		bean.valueBean.id=25;
		bean.valueBean.description="hello";
		
		String output=KriptonLibrary2.getBinder(BinderType.JSON).serialize(bean);
		System.out.println(output);
		
		Bean bean2=KriptonLibrary2.getBinder(BinderType.JSON).parse(output, Bean.class);
		
		Assert.assertTrue(bean2.equals(bean));
		
		System.out.println(output);
		
	}
}
