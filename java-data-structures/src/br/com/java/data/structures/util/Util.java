package br.com.java.data.structures.util;

public class Util<T extends Object> {
	public long parseNumberLong(T value) {
		Number number = (Number) value;
		return number.longValue();
	}
}
