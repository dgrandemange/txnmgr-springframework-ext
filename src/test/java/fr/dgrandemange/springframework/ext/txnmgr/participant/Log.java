package fr.dgrandemange.springframework.ext.txnmgr.participant;

import fr.dgrandemange.txnmgr.exception.TransactionException;
import fr.dgrandemange.txnmgr.service.IContextMgr;
import fr.dgrandemange.txnmgr.service.IParticipant;

/**
 * @author dgrandemange
 *
 */
public class Log implements IParticipant{
	
	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#execute(com.mbs.txnmgr.service.IContextMgr)
	 */
	public String execute(IContextMgr contextMgr) throws TransactionException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#rollback(com.mbs.txnmgr.service.IContextMgr)
	 */
	public void rollback(IContextMgr contextMgr) throws TransactionException {
	}

}
