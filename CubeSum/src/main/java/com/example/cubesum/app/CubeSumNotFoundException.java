package com.example.cubesum.app;

public class CubeSumNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CubeSumNotFoundException(Long id) {
		super("Could not find store data " + id);
	}
}
