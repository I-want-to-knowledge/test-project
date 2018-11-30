package com.geo.source.testmain.iterator_class;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayIteratorTest<E> implements Iterator<E> {

	private E[] items;
	int position = 0;
	
	public ArrayIteratorTest(E[] items) {
		this.items = items;
	}
	
	@Override
	public boolean hasNext() {
		if (position >= items.length || items[position] == null) {
			return false;
		}
		return true;
	}

	@Override
	public E next() {
		E value = items[position];
		position++;
		return value;
	}

	@Override
	public void remove() {
		if (position <= 0) {
			// Iterator.super.remove();
			throw new IllegalStateException("you can't remove an item until you've done at least one next()!");
		}
		
		int index = position-1;
		if (items[index] == null) {
			return;
		}
		
		int len = items.length - 1;
		for (int i = index; i < len; i++) {
			items[i] = items[i + 1];
		}
		items[len] = null;
		items = items.clone();
	}
	
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner(",");
		for (E e : items) {
			if (e != null)
				str.add(e.toString());
			else
				str.add(e + "");
		}
		return str.toString();
	}
}
