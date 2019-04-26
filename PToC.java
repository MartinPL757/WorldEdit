package me.edit;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PToC {
	private static Map<Player, Cuboid> all = new HashMap<Player, Cuboid>();
	private static Map<Player, Location> left = new HashMap<Player, Location>();
	private static Map<Player, Location> right = new HashMap<Player, Location>();
	
	
	public static Map<Player, Location> getLeft() {
		return left;
	}
	public static Map<Player, Location> getRight() {
		return right;
	}
	public static Map<Player, Cuboid> getAll() {
		return all;
	}
	
	
	public static void clear(Player p) {
		if(all.containsKey(p)) {
			all.remove(p);
		}
		
		if(left.containsKey(p)) {
			left.remove(p);
		}
		
		if(right.containsKey(p)) {
			right.remove(p);
		}
	}
	
	
	public static void setLeft(Player p, Location l) {
		p.sendMessage("§aUstawiono §cLeft§4Point §ana: §b(§3" + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ() + "§b)");
		if(left.containsKey(p)) {
			left.remove(p);
		}
		
		left.put(p, l);
		createCuboid(p);
	}
	
	public static void setRight(Player p, Location l) {
		p.sendMessage("§aUstawiono §cRight§4Point §ana: §b(§3" + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ() + "§b)");
		if(right.containsKey(p)) {
			right.remove(p);
		}
		
		right.put(p, l);
		createCuboid(p);
	}
	
	
	private static void createCuboid(Player p) {
		if(left.containsKey(p) && right.containsKey(p)) {
			int x, y, z, x2, y2, z2;
			Location l = left.get(p);
			Location l2 = right.get(p);
			x = l.getBlockX();
			y = l.getBlockY();
			z = l.getBlockZ();
			
			x2 = l2.getBlockX();
			y2 = l2.getBlockY();
			z2 = l2.getBlockZ();
			
			
			if(all.containsKey(p)) {				
				all.get(p).setLeft(x, y, z);
				all.get(p).setRight(x2, y2, z2);
				
			} else {
				all.put(p, new Cuboid(x, y, z, x2, y2, z2, l.getWorld(), p));
			}
		}
	}
	
	
	public static Cuboid get(Player p) {
		return all.get(p);
	}
	
}
