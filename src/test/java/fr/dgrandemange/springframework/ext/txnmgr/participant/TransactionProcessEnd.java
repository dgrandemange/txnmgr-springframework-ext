package fr.dgrandemange.springframework.ext.txnmgr.participant;

import java.util.List;

import fr.dgrandemange.txnmgr.exception.TransactionException;
import fr.dgrandemange.txnmgr.service.IContextMgr;
import fr.dgrandemange.txnmgr.service.IParticipant;

/**
 * @author dgrandemange
 * 
 */
public class TransactionProcessEnd implements IParticipant {
	private String dummyProp1;

	private List<String> dummyProp2;
	
	private List<String> dummyProp3;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mbs.txnmgr.service.IParticipant#execute(com.mbs.txnmgr.service.
	 * IContextMgr)
	 */
	public String execute(IContextMgr contextMgr) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mbs.txnmgr.service.IParticipant#rollback(com.mbs.txnmgr.service.
	 * IContextMgr)
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

	public List<String> getDummyProp2() {
		return dummyProp2;
	}

	public void setDummyProp2(List<String> dummyProp2) {
		this.dummyProp2 = dummyProp2;
	}

	public List<String> getDummyProp3() {
		return dummyProp3;
	}

	public void setDummyProp3(List<String> dummyProp3) {
		this.dummyProp3 = dummyProp3;
	}

}
