package fr.dgrandemange.springframework.ext.txnmgr.factory.parsing;

import org.springframework.beans.factory.parsing.ParseState;

/**
 * @author dgrandemange
 *
 */
public class ParticipantEntry implements ParseState.Entry {

	private String name;

	public ParticipantEntry(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Participant '" + this.name + "'";
	}
	
}
