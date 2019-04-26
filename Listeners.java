package me.edit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Listeners implements Listener {
	public Listeners() {}
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(e.getPlayer().hasPermission("edit.pos")) {
			if(e.getPlayer().getInventory().getItemInHand().equals(Main.getWand())) {
				e.setCancelled(true);
				PToC.setLeft(e.getPlayer(), e.getBlock().getLocation());
				
			}
		}
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getPlayer().hasPermission("edit.pos")) {
			if(e.getPlayer().getInventory().getItemInHand().equals(Main.getWand())) {
				if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if(e.getHand().equals(EquipmentSlot.HAND)) {
						e.setCancelled(true);
						PToC.setRight(e.getPlayer(), e.getClickedBlock().getLocation());
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void changeWorld(PlayerChangedWorldEvent e) {
		PToC.clear(e.getPlayer());
	}
	
}
