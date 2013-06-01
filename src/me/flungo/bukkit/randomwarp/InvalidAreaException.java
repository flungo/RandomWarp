/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.flungo.bukkit.randomwarp;

/**
 *
 * @author Fabrizio
 */
public class InvalidAreaException extends Exception {

	/**
	 * Creates a new instance of
	 * <code>InvalidAreaException</code> without detail message.
	 */
	public InvalidAreaException() {
	}

	/**
	 * Constructs an instance of
	 * <code>InvalidAreaException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public InvalidAreaException(String msg) {
		super(msg);
	}
}
