package com.week3.BookStore.Exceptions;

public class NoObjectFoundException extends RuntimeException
{
	public NoObjectFoundException(String mssg)
	{
		super(mssg);
	}
}
