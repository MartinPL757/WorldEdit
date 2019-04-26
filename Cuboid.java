package me.edit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Cuboid {
	private Random r = new Random();
	private int ox1, ox2, oy1, oy2, oz1, oz2;
	private int x1, x2, y1, y2, z1, z2;
	private World world;
	private Player p;
	
	public Cuboid(int x1, int y1, int z1, int x2, int y2, int z2, World world, Player p) {
		this.world = world;
		this.ox1 = x1;
		this.ox2 = x2;
		
		this.oy1 = y1;
		this.oy2 = y2;
		
		this.oz1 = z1;
		this.oz2 = z2;
		this.p = p;
		update();
	}
	
	public void setLeft(int x, int y, int z) {
		this.ox1 = x;
		this.oy1 = y;
		this.oz1 = z;
		update();
	}
	
	public void setRight(int x, int y, int z) {
		this.ox2 = x;
		this.oy2 = y;
		this.oz2 = z;
		update();
	}
	
	private void update() {
		if(ox1 > ox2) {
			this.x1 = ox2;
			this.x2 = ox1;
		} else {
			this.x1 = ox1;
			this.x2 = ox2;
		}
		
		if(oz1 > oz2) {
			this.z1 = oz2;
			this.z2 = oz1;
		} else {
			this.z1 = oz1;
			this.z2 = oz2;
		}
		
		if(oy1 > oy2) {
			this.y1 = oy2;
			this.y2 = oy1;
		} else {
			this.y1 = oy1;
			this.y2 = oy2;
		}
	}
	
	
	public void set(List<MyMat> mat) {
		setLastAction();
		int blocks = 0;
		for(int x = x1; x <= x2; x++) {
			for(int z = z1; z <= z2; z++) {
				for(int y = y1; y <= y2; y++) {
					int ra = this.r.nextInt(mat.size());
					blocks++;
					this.world.getBlockAt(new Location(this.world, x, y, z)).setBlockData(Bukkit.createBlockData(mat.get(ra).getMat(), mat.get(ra).getData()));
				}
			}
		}

		this.p.sendMessage("§aZmieniono §b" + blocks + " §abloków");
		
	}
	
	public void replace(List<MyMat> from, List<MyMat> to) {
		setLastAction();
		
		List<Material> fromMats = new ArrayList<Material>();
		
		for(int i = 0; i < from.size(); i++) {
			fromMats.add(from.get(i).getMat());
		}
		
		int blocks = 0;
		for(int x = x1; x <= x2; x++) {
			for(int z = z1; z <= z2; z++) {
				for(int y = y1; y <= y2; y++) {
					if(fromMats.contains(this.world.getBlockAt(new Location(this.world, x, y, z)).getType())) {
						int ra = this.r.nextInt(to.size());
						blocks++;
						this.world.getBlockAt(new Location(this.world, x, y, z)).setBlockData(Bukkit.createBlockData(to.get(ra).getMat(), to.get(ra).getData()));					
					}
				}
			}
		}
		this.p.sendMessage("§aZmieniono §b" + blocks + " §abloków");
	}
	
	
	
	public int setBorder(List<MyMat> mat, boolean msg) {
		if(msg) setLastAction();
		int blocks = (this.y2-this.y1)*(-4);
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				int ra = this.r.nextInt(mat.size());
				int ra2 = this.r.nextInt(mat.size());
				blocks++;
				this.world.getBlockAt(new Location(this.world, x, y, this.z1)).setBlockData(Bukkit.createBlockData(mat.get(ra).getMat(), mat.get(ra).getData()));
				this.world.getBlockAt(new Location(this.world, x, y, this.z2)).setBlockData(Bukkit.createBlockData(mat.get(ra2).getMat(), mat.get(ra2).getData()));
			}
		}
		
		for(int z = z1; z <= z2; z++) {
			for(int y = y1; y <= y2; y++) {
				int ra = this.r.nextInt(mat.size());
				int ra2 = this.r.nextInt(mat.size());
				blocks++;
				this.world.getBlockAt(new Location(this.world, this.x1, y, z)).setBlockData(Bukkit.createBlockData(mat.get(ra).getMat(), mat.get(ra).getData()));
				this.world.getBlockAt(new Location(this.world, this.x2, y, z)).setBlockData(Bukkit.createBlockData(mat.get(ra2).getMat(), mat.get(ra2).getData()));
			}
		}
		
		if(msg) this.p.sendMessage("§aZmieniono §b" + blocks + " §abloków");
		return blocks;
	}
	
	
	public void setAllWalls(List<MyMat> mat) {
		setLastAction();
		int blocks = setBorder(mat, false);
		for(int x = x1+1; x < x2; x++) {
			for(int z = z1+1; z < z2; z++) {
				int ra = this.r.nextInt(mat.size());
				int ra2 = this.r.nextInt(mat.size());
				blocks++;
				this.world.getBlockAt(new Location(this.world, x, this.y1, z)).setBlockData(Bukkit.createBlockData(mat.get(ra).getMat(), mat.get(ra).getData()));
				this.world.getBlockAt(new Location(this.world, x, this.y2, z)).setBlockData(Bukkit.createBlockData(mat.get(ra2).getMat(), mat.get(ra2).getData()));
			}
		}
		
		this.p.sendMessage("§aZmieniono §b" + blocks + " §abloków");
		
	}
	
	
	private List<MyBlock> getBefore() {
		List<MyBlock> bl = new ArrayList<MyBlock>();
		
		for(int x = this.x1; x <= this.x2; x++) {
			for(int z = this.z1; z <= this.z2; z++) {
				for(int y = this.y1; y <= this.y2; y++) {
					bl.add(new MyBlock(this.world.getBlockAt(new Location(this.world, x, y, z))));
				}
			}
		}
		
		return bl;
	}
	
	
	private void setLastAction() {
		LastActionPlayer.addToUndo(this.p, new LastAction(this.getBefore(), this.world));
	}
}
