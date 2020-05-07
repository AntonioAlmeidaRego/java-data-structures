package br.com.java.data.structures;

public interface DataStructures<T extends Object> {
	public void add(T value);
	public void remove() throws Exception;
	public int size();
	public boolean isEmpty() throws Exception;
	public T[] extractValues();
}
