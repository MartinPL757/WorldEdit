package me.edit;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class LastActionPlayer {
	private static Map<Player, LA> all = new HashMap<Player, LA>();
	
	
	public static void clear(Player p) {
		if(contains(p)) {
			all.remove(p);
		}
	}
	
	
	public static boolean undo(Player p) {
		if(contains(p)) {
			if(all.get(p).canUndo()) {
				int i = all.get(p).undoLast();
				p.sendMessage("�aCofni�to �b" + i + " �ablok�w");
				return true;
			} else {
				p.sendMessage("�cCofni�cie niedost�pne!");
				return false;
			}
		} else {
			p.sendMessage("�cCofni�cie niedost�pne!");
			return false;
		}
	}
	
	public static boolean redo(Player p) {
		if(contains(p)) {
			if(all.get(p).canRedo()) {
				int i = all.get(p).redoLast();
				p.sendMessage("�aPrzywr�cono �b" + i + " �ablok�w");
				return true;
			} else {
				p.sendMessage("�cPrzywr�cenie niedost�pne!");
				return false;
			}
		} else {
			p.sendMessage("�cPrzywr�cenie niedost�pne!");
			return false;
		}
	}
	
	public static boolean contains(Player p) {
		return all.containsKey(p);
	}
	
	
	public static void addToUndo(Player p, LastAction la) {
		if(!contains(p)) {
			all.put(p, new LA(la));
			return;
		}
		
		all.get(p).addActionToUndo(la);
	}
	
	public static void addToRedo(Player p, LastAction la) {
		all.get(p).addActionToRedo(la);
	}
}
