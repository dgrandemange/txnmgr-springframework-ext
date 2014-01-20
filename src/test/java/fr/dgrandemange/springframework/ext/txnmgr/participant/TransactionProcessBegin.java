package fr.dgrandemange.springframework.ext.txnmgr.participant;

import fr.dgrandemange.txnmgr.exception.TransactionException;
import fr.dgrandemange.txnmgr.service.IContextMgr;
import fr.dgrandemange.txnmgr.service.IParticipant;

/**
 * @author dgrandemange
 *
 */
public class TransactionProcessBegin implements IParticipant {
	private String dummyProp1;

	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#execute(com.mbs.txnmgr.service.IContextMgr)
	 */
	public String execute(IContextMgr contextMgr) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#rollback(com.mbs.txnmgr.service.IContextMgr)
	 */
	public void rollback(IContextMgr contextMgr) throws TransactionException {
		// TODO Auto-generated method stub
		
	}
	
	public String getDummyProp1() {
		return dummyProp1;
	}

	public void setDummyProp1(String dummyProp1) {
		this.dummyProp1 = dummyProp1;
	}
	
}
