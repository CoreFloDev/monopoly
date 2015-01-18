package org.polytech.nantes.monopoly.manager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class ProprietesJoueurModel extends DefaultComboBoxModel<CaseAchetable> {

	private List<CaseAchetable> props;
	 
	public ProprietesJoueurModel(){
		super();
 
		props = new ArrayList<CaseAchetable>();
	}

	public CaseAchetable getSelectedPropriete(){
		return (CaseAchetable)getSelectedItem();
	}
 
	@Override
	public CaseAchetable getElementAt(int index) {
		return props.get(index);
	}
 
	@Override
	public int getSize() {
		return props.size();
	}
 
	@Override
	public int getIndexOf(Object element) {
		return props.indexOf(element);
	}
	
	public void addProp(CaseAchetable prop){
		props.add(prop);
		int size = props.size();
		fireIntervalAdded(this, size, size);
	}
}
