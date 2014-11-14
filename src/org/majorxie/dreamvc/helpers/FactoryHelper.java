package org.majorxie.dreamvc.helpers;

import org.majorxie.dreamvc.exception.NoIocInstanceException;
import org.majorxie.dreamvc.exception.NoTemplateFactory;
import org.majorxie.dreamvc.ioc.factory.IocFactory;
import org.majorxie.dreamvc.template.TemplateFactory;

/**
 * ����ģʽ,������Ӧ������
 * 
 * @author xiezhaodong 2014-10-29
 */
public class FactoryHelper {

	private FactoryHelper() {
	}

	private static class SingletonClassInstance {S
		private static final FactoryHelper instance = new FactoryHelper();
	}

	public static FactoryHelper getInstance() {
		return SingletonClassInstance.instance;
	}

	/**
	 * 
	 * @param clazzName
	 *            org.majorxie.dreamvc.ioc.factory.SpringIocFactory
	 * @return
	 * @throws Exception
	 */
	public static IocFactory createIocFactory(String clazzName)
			throws Exception {
		IocFactory iocFactory = null;
		Class clazz=null;
		//�����ļ��Ҳ���
		try {
			clazz= Class.forName(clazzName);
		} catch (Exception e) {
			throw new NoIocInstanceException("No IOC container in your project,check your ioc class file url");
		}
		//����������(�Ƿ��ܹ�ǿ��ת��)
		Object obj = clazz.newInstance();
		if (obj instanceof IocFactory) {
			iocFactory = (IocFactory) obj;
		}
		
		if (iocFactory == null) {
			throw new NoIocInstanceException("Your class must be a subclass of IocFactory");
		}

		return iocFactory;
	}
	
	
	/**
	 * ����ģ�幤��
	 * @param clazzName ��ȫ����
	 * @return
	 */
	public static TemplateFactory createTemplateFactory(String clazzName)throws Exception{
		TemplateFactory templateFactory=null;
		Class clazz=null;
		try {
			clazz= Class.forName(clazzName);
		} catch (Exception e) {
			throw new NoTemplateFactory("Your template factory class not found");
		}
		
		Object obj = clazz.newInstance();
		if (obj instanceof TemplateFactory) {
			templateFactory = (TemplateFactory) obj;
		}
		if (templateFactory == null) {
			throw new NoIocInstanceException("Your class must be a subclass of TemplateFactory");
		}
			
		
		return templateFactory;
	}

}