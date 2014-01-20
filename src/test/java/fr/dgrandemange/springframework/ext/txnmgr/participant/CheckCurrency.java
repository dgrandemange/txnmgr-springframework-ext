package fr.dgrandemange.springframework.ext.txnmgr.participant;

import fr.dgrandemange.txnmgr.exception.TransactionException;
import fr.dgrandemange.txnmgr.service.IContextMgr;
import fr.dgrandemange.txnmgr.service.IParticipant;

/**
 * @author dgrandemange
 *
 */
public class CheckCurrency implements IParticipant {
	
	private String txOk;
	
	private String txNok;
	
	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#execute(com.mbs.txnmgr.service.IContextMgr)
	 */
	public String execute(IContextMgr contextMgr) throws TransactionException {
		return txOk;
	}

	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#rollback(com.mbs.txnmgr.service.IContextMgr)
	 */
	public void rollback(IContextMgr contextMgr) throws TransactionException {
	}

	public String getTxOk() {
		return txOk;
	}

	public void setTxOk(String txOk) {
		this.txOk = txOk;
	}

	public String getTxNok() {
		return txNok;
	}

	public void setTxNok(String txNok) {
		this.txNok = txNok;
	}

}
