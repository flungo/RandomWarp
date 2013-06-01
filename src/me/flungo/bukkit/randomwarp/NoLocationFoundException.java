/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.flungo.bukkit.randomwarp;

/**
 *
 * @author Fabrizio
 */
public class NoLocationFoundException extends Exception {

	/**
	 * Creates a new instance of
	 * <code>NoLocationFoundException</code> without detail message.
	 */
	public NoLocationFoundException() {
	}

	/**
	 * Constructs an instance of
	 * <code>NoLocationFoundException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public NoLocationFoundException(String msg) {
		super(msg);
	}
}
