package fr.dgrandemange.springframework.ext.txnmgr.participant;

import fr.dgrandemange.txnmgr.exception.TransactionException;
import fr.dgrandemange.txnmgr.service.IContextMgr;
import fr.dgrandemange.txnmgr.service.IParticipant;

/**
 * @author dgrandemange
 *
 */
public class Switch implements IParticipant {

	private String msg0100;
	
	private String msg0200;

	private String msg0220;

	private String msg0221;

	private String msg0420;

	private String msg0421;

	private String msg0500;

	private String msg0800;

	private String dummyProp1;

	private String dummyProp2;

	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#execute(com.mbs.txnmgr.service.IContextMgr)
	 */
	public String execute(IContextMgr contextMgr) throws TransactionException {
		return msg0200;
	}

	/* (non-Javadoc)
	 * @see com.mbs.txnmgr.service.IParticipant#rollback(com.mbs.txnmgr.service.IContextMgr)
	 */
	public void rollback(IContextMgr contextMgr) throws TransactionException {
	}
	
	public String getMsg0100() {
		return msg0100;
	}

	public void setMsg0100(String msg0100) {
		this.msg0100 = msg0100;
	}

	public String getMsg0200() {
		return msg0200;
	}

	public void setMsg0200(String msg0200) {
		this.msg0200 = msg0200;
	}

	public String getMsg0220() {
		return msg0220;
	}

	public void setMsg0220(String msg0220) {
		this.msg0220 = msg0220;
	}

	public String getMsg0221() {
		return msg0221;
	}

	public void setMsg0221(String msg0221) {
		this.msg0221 = msg0221;
	}

	public String getMsg0420() {
		return msg0420;
	}

	public void setMsg0420(String msg0420) {
		this.msg0420 = msg0420;
	}

	public String getMsg0421() {
		return msg0421;
	}

	public void setMsg0421(String msg0421) {
		this.msg0421 = msg0421;
	}

	public String getMsg0500() {
		return msg0500;
	}

	public void setMsg0500(String msg0500) {
		this.msg0500 = msg0500;
	}

	public String getMsg0800() {
		return msg0800;
	}

	public void setMsg0800(String msg0800) {
		this.msg0800 = msg0800;
	}

	public String getDummyProp1() {
		return dummyProp1;
	}

	public void setDummyProp1(String dummyProp1) {
		this.dummyProp1 = dummyProp1;
	}

	public String getDummyProp2() {
		return dummyProp2;
	}

	public void setDummyProp2(String dummyProp2) {
		this.dummyProp2 = dummyProp2;
	}

}
