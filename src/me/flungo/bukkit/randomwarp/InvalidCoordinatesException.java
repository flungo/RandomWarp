/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.flungo.bukkit.randomwarp;

/**
 *
 * @author Fabrizio
 */
public class InvalidCoordinatesException extends Exception {

	/**
	 * Creates a new instance of
	 * <code>InvalidCoordinatesException</code> without detail message.
	 */
	public InvalidCoordinatesException() {
	}

	/**
	 * Constructs an instance of
	 * <code>InvalidCoordinatesException</code> with the specified detail
	 * message.
	 *
	 * @param msg the detail message.
	 */
	public InvalidCoordinatesException(String msg) {
		super(msg);
	}
}
