package com.example.cubesum.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Point3D {
	private @Id @GeneratedValue long id;
	private int x;
	private int y;
	private int z;
	private long value;
	
	public Point3D() {
		
	}
	
	public Point3D(int x, int y, int z, long value) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
