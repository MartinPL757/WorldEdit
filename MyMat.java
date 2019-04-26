package me.edit;

import org.bukkit.Material;

public class MyMat {
	
	private Material mat;
	private String data;
	
	public MyMat(Material mat, String data) {
		this.mat = mat;
		this.data = data;
	}

	public Material getMat() {
		return mat;
	}

	public void setMat(Material mat) {
		this.mat = mat;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
