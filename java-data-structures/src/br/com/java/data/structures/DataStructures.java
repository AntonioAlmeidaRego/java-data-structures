package br.com.java.data.structures;

public interface DataStructures<T extends Object> {
	public void add(T value);
	public void remove();
	public int size();
	public boolean isEmpty();
}
