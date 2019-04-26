package me.edit;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

public class MyBlock {
	
	private Location location;
	private World world;
	
	private BlockData blockData;
	
	public MyBlock(Block b) {
		this.location = b.getLocation();
		this.world = b.getWorld();
		
		this.blockData = b.getBlockData();
	}

	public BlockData getBlockData() {
		return blockData;
	}

	public void setBlockData(BlockData blockData) {
		this.blockData = blockData;
	}

	public Location getLocation() {
		return location;
	}
	
	
	
	
}
