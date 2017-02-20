package com.saleschannel.api.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SalesChannelBeanLocator {

	private static SalesChannelBeanLocator beanLocator_ = new SalesChannelBeanLocator();
	
	@Autowired
	private ApplicationContext appContext;
	
	/**
	 * private constructor
	 *
	 */
	private SalesChannelBeanLocator(){
	}
	
	/**
	 * Returns an instance of the SalesChannelBeanLocator
	 * 
	 * @return
	 */
	public static SalesChannelBeanLocator getInstance(){
		return beanLocator_;
	}
	 
	/**
	 * Returns an instance of a bean
	 * 
	 * @param beanId - the id of the bean to be located
	 * @return
	 */
	public Object findBean(String beanId){
		return ApplicationContextProvider.getApplicationContext().getBean(beanId);
	}
}
