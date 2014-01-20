package fr.dgrandemange.springframework.ext.txnmgr.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author dgrandemange
 * 
 */
public class TransactionManagerNamespaceHandler extends NamespaceHandlerSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	public void init() {
		registerBeanDefinitionParser("txnmgr",
				new TransactionManagerBeanDefinitionParser());
	}
}
