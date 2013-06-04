/*
 * Copyright (C) 2013 Fabrizio Lungo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.flungo.bukkit.randomwarp;

/**
 *
 * @author Fabrizio
 */
public class InvalidWorldException extends Exception {

	/**
	 * Creates a new instance of
	 * <code>InvalidWorldException</code> without detail message.
	 */
	public InvalidWorldException() {
	}

	/**
	 * Constructs an instance of
	 * <code>InvalidWorldException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public InvalidWorldException(String msg) {
		super(msg);
	}
}
