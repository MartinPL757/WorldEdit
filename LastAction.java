package me.edit;

import java.util.List;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class LastAction {
	private World w;
	private List<MyBlock> l;
	
	
	public LastAction(List<MyBlock> list, World world) {
		
		this.l = list;
		this.w = world;
	}
	
	
	public int getBack() {
		int blocks = 0;	
		
		for(int i = 0; i < l.size(); i++) {
			blocks++;
			this.w.getBlockAt(this.l.get(i).getLocation()).setBlockData(this.l.get(i).getBlockData());
		}
		return blocks;
	}
	
	public List<MyBlock> getBlocks() {
		return this.l;
	}
}
