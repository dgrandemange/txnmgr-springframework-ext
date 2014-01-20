package fr.dgrandemange.springframework.ext.txnmgr.factory.parsing;

import org.springframework.beans.factory.parsing.ParseState;

/**
 * @author dgrandemange
 *
 */
public class GroupEntry implements ParseState.Entry {

	private String name;

	public GroupEntry(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group '" + this.name + "'";
	}
	
}
