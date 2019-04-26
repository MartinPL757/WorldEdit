package me.edit;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	private static ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1, (short) 0); {
		ItemMeta im = wand.getItemMeta();
		im.setDisplayName("§bWand");
		wand.setItemMeta(im);
	}
	
	
	
	public static ItemStack getWand() {
		return wand;
	}

	public static Main getInst() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		System.out.println("[MeEdit] Enabling..");
		instance = this;
		
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
	}

	@Override
	public void onDisable() {
		System.out.println("[MeEdit] Disabling..");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("/")) {
			if(sender.hasPermission("edit.wand")) {
				Player p = (Player) sender;
				p.getInventory().addItem(Main.getWand());
				p.sendMessage("§bDostałeś wanda!");
			} 
		}
		
		if(cmd.getName().equalsIgnoreCase("/pos1")) {
			if(sender.hasPermission("edit.pos")) {
				Player p = (Player) sender;
				PToC.setLeft(p, p.getLocation());
			}
			
		}
		
		if(cmd.getName().equalsIgnoreCase("/pos2")) {
			if(sender.hasPermission("edit.pos")) {
				Player p = (Player) sender;
				PToC.setRight(p, p.getLocation());
			}
			
		}
		
		if(cmd.getName().equalsIgnoreCase("/undo")) {
			if(sender.hasPermission("edit.undo")) {
				Player p = (Player) sender;
				LastActionPlayer.undo(p);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("/redo")) {
			if(sender.hasPermission("edit.redo")) {
				Player p = (Player) sender;
				LastActionPlayer.redo(p);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("/set")) {
			if(sender.hasPermission("edit.set")) {
				if(args.length == 0) {
					sender.sendMessage("§cUżycie: //set [MATERIALS]");
					return true;
				}
				Player p = (Player) sender;
				if(!PToC.getAll().containsKey(p)) {
					p.sendMessage("§cNajpierw ustaw dwa punkty!");
					return true;
				}
				List<MyMat> mats = Fun.convert(args[0]);
				if(mats == null) {
					p.sendMessage("§cBłąd: " + Fun.getBlad(args[0]));
					return true;
				}
				PToC.get(p).set(mats);
				
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("/replace")) {
			if(sender.hasPermission("edit.replace")) {
				if(args.length != 2) {
					sender.sendMessage("§cUżycie: //replace [FROM] [TO]");
					return true;
				}
				
				Player p = (Player) sender;
				if(!PToC.getAll().containsKey(p)) {
					p.sendMessage("§cNajpierw ustaw dwa punkty!");
					return true;
				}
				
				List<MyMat> from = Fun.convert(args[0]);
				List<MyMat> to = Fun.convert(args[1]);
				if(from == null) {
					p.sendMessage("§cBłąd: " + Fun.getBlad(args[0]));
					return true;
				}
				
				if(to == null) {
					p.sendMessage("§cBłąd: " + Fun.getBlad(args[1]));
					return true;
				}
				
				PToC.get(p).replace(from, to);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("/border")) {
			if(sender.hasPermission("edit.border")) {
				if(args.length == 0) {
					sender.sendMessage("§cUżycie: //border [MATERIALS]");
					return true;
				}
				Player p = (Player) sender;
				if(!PToC.getAll().containsKey(p)) {
					p.sendMessage("§cNajpierw ustaw dwa punkty!");
					return true;
				}
				
				List<MyMat> mats = Fun.convert(args[0]);
				if(mats == null) {
					p.sendMessage("§cBłąd: " + Fun.getBlad(args[0]));
					return true;
				}
				
				PToC.get(p).setBorder(mats, true);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("/walls")) {
			if(sender.hasPermission("edit.walls")) {
				if(args.length == 0) {
					sender.sendMessage("§cUżycie: //walls [MATERIALS]");
					return true;
				}
				Player p = (Player) sender;
				if(!PToC.getAll().containsKey(p)) {
					p.sendMessage("§cNajpierw ustaw dwa punkty!");
					return true;
				}
				
				List<MyMat> mats = Fun.convert(args[0]);
				if(mats == null) {
					p.sendMessage("§cBłąd: " + Fun.getBlad(args[0]));
					return true;
				}
				
				PToC.get(p).setAllWalls(mats);
			}
		}
		return false;				
	}
		
}
