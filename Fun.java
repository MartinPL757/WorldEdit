package me.edit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class Fun {
	
	public static List<MyMat> convert(String s) {
		List<MyMat> mat = new ArrayList<MyMat>();
		String[] poj = s.split(",");
		
		for(String block : poj) {
			
			if(fromString(block) == null) {
				
				return null;
			}
			
			mat.add(new MyMat(fromString(block), ""));
		}
		
		return mat;
		
	}
	
	
	public static Material fromString(String mat) {
		Material m = Material.getMaterial(mat.toUpperCase());
		return m;
	}
	
	public static String getBlad(String s) {
		String[] poj = s.split(",");
		
		for(String block : poj) {		
			if(fromString(block) == null) {
				return block + " <--- Nie znaleziono takiego materia³u!";
			}
		}
		
		return "";
	}
}
