//===============================================================================
// Copyright (c) 2010 Adam C Jones
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
//===============================================================================

package com.goodreads.api.v1;

import java.io.Serializable;
import android.sax.Element;

public class UpdateObject implements Serializable
{
	private static final long serialVersionUID = 0L;
	
	private Book mBook = new Book();
	
	public void clear()
	{
		mBook.clear();
	}
	
	public UpdateObject copy()
	{
		UpdateObject updateObjectCopy = new UpdateObject();
		
		updateObjectCopy.setBook(mBook.copy());
		
		return updateObjectCopy;
	}
	
	public static UpdateObject appendListener(Element parentElement, int depth)
	{
		final Element updateObjectElement = parentElement.getChild("object");
		final UpdateObject updateObject = new UpdateObject();
		
		updateObject.setBook(Book.appendListener(updateObjectElement, depth + 1));
		
		return updateObject;
	}

	public void setBook(Book book)
	{
		mBook = book;
	}

	public Book getBook()
	{
		return mBook;
	}
}
