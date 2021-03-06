package br.com.java.data.structures.util;

public class Search<T extends Object> {
	private T[] array = null;
	private Util<T> util = new Util<T>();
	
	public Search(T[] array) {
		this.array = array;
	}
	
	private void sortByBiggest() {
		for(int i = 0; i < this.array.length; i++) {
			for(int j = i+1; j < this.array.length;j++) {
				if(util.parseNumberLong(this.array[i]) < util.parseNumberLong(this.array[j])) {
					T aux = this.array[i];
					this.array[i] = this.array[j];
					this.array[j] = aux;
				}
			}
		}
	}
	
	private void sortBySmallest() {
		for(int i = 0; i < this.array.length; i++) {
			for(int j = i+1; j < this.array.length;j++) {
				if(util.parseNumberLong(this.array[i]) > util.parseNumberLong(this.array[j])) {
					T aux = this.array[i];
					this.array[i] = this.array[j];
					this.array[j] = aux;
				}
			}
		}
	}
	
	private boolean loopFim(int loop){
		if(loop == array.length){
			return true;
		}
		return false;
	}
	
	public T searchForNextValue(T value) {
		sortByBiggest();
		int loop = 0;
		while(true){
			T elemento = array[loop];
			if(value.equals(elemento)){
				return array[loop-1];
			}
			else{
				if(util.parseNumberLong(value) > util.parseNumberLong(elemento)){
					if(loop > 0) {
						loop = loop - 1;
					}
					break;
				} 
				if(util.parseNumberLong(value) <  util.parseNumberLong(elemento)){
					loop = loop + 1;	
				}
				if(loopFim(loop)){
					loop = loop + 1;
					break;
				}
			}
		}
		return array[loop];
	}
	
	public T searchForPreviousValue(T value) {
		sortBySmallest();
		int loop = 0;
		while(true){
			T elemento = array[loop];
			if(value.equals(elemento)){
				return array[loop-1];
			}
			else{
				if(util.parseNumberLong(value) > util.parseNumberLong(elemento)){
					loop = loop + 1;
				} 
				if(util.parseNumberLong(value) <  util.parseNumberLong(elemento)){
					if(loop > 0) {
						loop = loop - 1;	
					}
					break;
				}
				if(loopFim(loop)){
					loop = loop - 1;
					break;
				}
			}
		}
		return array[loop];
	}
}
