package com.ncq.devstudio.workflows.beans;


/**
 * Represents a response of all HTTP web services.
 * It encapsulates the following information:
 * <ul>
 * <li>The response: in case of success</li>
 * <li>The message: the error message, or null</li>
 * <li>The errorCode: the error code, or 0. For more details, see 
 * {@link ErrorCodes} </li>
 * </ul>
 * @author Aroua Souabni
 * @param <T> Any HTTP response
 */
public class Response<T>
{
	private T object;
	private String message;
	private int errorCode;

	/**
	 * Returns the web service response in case of success.
	 * 
	 * @return the web service response or null.
	 */
	public T getObject()
	{
		return object;
	}

	/**
	 * Sets the web service response in case of success.
	 * 
	 * @param object the web service response or null.
	 */
	public void setObject(T object)
	{
		this.object = object;
	}
	
	/**
	 * Returns the error message in case of failure.
	 * 
	 * @return the error message or null.
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the error message in case of failure.
	 * 
	 * @param message the error message or null.
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Returns the error code in case of failure.
	 * 
	 * @return the error code or null.
	 */
	public int getErrorCode()
	{
		return errorCode;
	}

	/**
	 * Sets the error errorCode in case of failure.
	 * 
	 * @param errorCode the error errorCode or zero.
	 */
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
}

