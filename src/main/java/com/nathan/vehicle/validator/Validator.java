package com.nathan.vehicle.validator;

public interface Validator<T> {

	/**
	 * @param t
	 * 
	 * @return
	 *         <ul>
	 *         <li>{@link Boolean#TRUE True} - If t is valid</li>
	 *         <li>{@link Boolean#FALSE False} - If t is NOT valid</li>
	 *         </ul>
	 */
	Boolean isValid(final T t);
}