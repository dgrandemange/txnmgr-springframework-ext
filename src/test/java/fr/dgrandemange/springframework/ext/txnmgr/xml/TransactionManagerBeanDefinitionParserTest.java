package fr.dgrandemange.springframework.ext.txnmgr.xml;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.qos.logback.classic.Level;

import fr.dgrandemange.springframework.ext.txnmgr.participant.TransactionProcessEnd;
import fr.dgrandemange.txnmgr.exception.TransactionException;
import fr.dgrandemange.txnmgr.exception.TxMgrException;
import fr.dgrandemange.txnmgr.model.ParticipantsGroup;
import fr.dgrandemange.txnmgr.model.ParticipantsGroupRegistry;
import fr.dgrandemange.txnmgr.model.Transaction;
import fr.dgrandemange.txnmgr.service.ITransactionHandler;
import fr.dgrandemange.txnmgr.service.support.TransactionMgrImpl;

/**
 * @author dgrandemange
 * 
 */
public class TransactionManagerBeanDefinitionParserTest {

	@Before
	public void setUp() {
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.DEBUG);
	}

	@Test
	public void testDoParseElementParserContextBeanDefinitionBuilder()
			throws TxMgrException, TransactionException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				String.format("%s/%s", this.getClass().getPackage().getName()
						.replace('.', '/'), "some-app-context__txnmgr.xml"));

		Object bean = ctx.getBean("kdoTxn");
		assertThat(bean).isInstanceOf(TransactionMgrImpl.class);
		TransactionMgrImpl txMgr = (TransactionMgrImpl) bean;
		assertThat(txMgr.getName()).isEqualTo("kdoTxn");

		ParticipantsGroupRegistry registry = txMgr.getRegistry();
		ParticipantsGroup rootGroup = registry
				.getGroup(ParticipantsGroupRegistry.GROUPS_REGISTRY__ROOT_GROUPNAME);
		assertThat(rootGroup).isNotNull();

		Object bean2 = ctx.getBean("kdoTxn.TransactionProcessEnd");
		assertThat(bean2).isInstanceOf(TransactionProcessEnd.class);
		TransactionProcessEnd tpePtp = (TransactionProcessEnd) bean2;
		assertThat(tpePtp.getDummyProp3()).containsExactly("item1", "item2",
				"item3");

		try {
			txMgr.start();
			Map<String, Object> context = new HashMap<String, Object>();
			ITransactionHandler submit = txMgr.submit(context);
			Transaction result = submit.getResult(1000L);
			System.out.println(result.getState());
			TransactionException exception = result.getException();
			if (null != exception) {
				exception.printStackTrace(System.err);
			}
		} finally {
			txMgr.stop();
		}
	}

}
