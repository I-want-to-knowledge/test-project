package com.geo.source.testmain.iterator_class;

import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class ListIteratorTest<E> implements Iterator<E> {
	int position = 0;
	private List<E> items;
	
	public ListIteratorTest(List<E> items) {
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		if (items == null || position >= items.size() || items.get(position) == null) {
			return false;
		}
		return true;
	}

	@Override
	public E next() {
		E e = items.get(position);
		position++;
		return e;
	}

	@Override
	public void remove() {
		int size = items.size();
		int index = position - 1;
		if (index >= size || items.get(index) == null) {
			return;
		}
		
		E e = items.remove(index);
		System.out.printf("已删除：%s \n", e.toString());
	}
	
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner(",");
		for (E e : items) {
			if (e != null)
				str.add(e.toString());
			else
				str.add("null");
		}
		return str.toString();
	}
}
