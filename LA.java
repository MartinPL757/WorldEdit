package me.edit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public class LA {
	
	private List<LastAction> undo = new ArrayList<LastAction>();
	private List<LastAction> redo = new ArrayList<LastAction>();
	public LA(LastAction la) {
		this.undo.add(la);
	}
	
	
	public void addActionToUndo(LastAction la) {
		this.undo.add(la);
	}
	
	public void addActionToRedo(LastAction la) {
		this.redo.add(la);
	}
	
	
	public boolean canUndo() {
		if(undo.size() == 0) return false;
		return true;
	}
	
	public boolean canRedo() {
		if(redo.size() == 0) return false;
		return true;
	}
	
	public int undoLast() {
		if(!canUndo()) return 0;
		this.addActionToRedo(undo.get(undo.size()-1));
		int i = undo.get(undo.size()-1).getBack();
		undo.remove(undo.size()-1);
		
		return i;
	}
	
	public int redoLast() {
		if(!canRedo()) return 0;
		this.addActionToUndo(redo.get(redo.size()-1));
		int i = redo.get(redo.size()-1).getBack();
		redo.remove(redo.size()-1);
		return i;
	}
}
