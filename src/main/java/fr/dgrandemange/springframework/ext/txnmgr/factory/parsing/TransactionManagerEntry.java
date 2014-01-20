package fr.dgrandemange.springframework.ext.txnmgr.factory.parsing;

import org.springframework.beans.factory.parsing.ParseState;

/**
 * @author dgrandemange
 *
 */
public class TransactionManagerEntry implements ParseState.Entry {

	private String name;

	public TransactionManagerEntry(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Transaction manager '" + this.name + "'";
	}
	
}
