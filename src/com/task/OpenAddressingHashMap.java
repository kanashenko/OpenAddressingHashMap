package com.task;

public class OpenAddressingHashMap {

	private class Entry {
		int key;
		long value;

		public Entry(int key, long value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public long getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "Entry [key=" + key + ", value=" + value + "]";
		}
	}
		
	private final int DEFAULT_TABLE_SIZE = 5;	
	private int threshold = (int) (0.75f * DEFAULT_TABLE_SIZE);
	private int num_of_elements = 0;
	private Entry[] table;
	

	OpenAddressingHashMap() {
		table = new Entry[DEFAULT_TABLE_SIZE];
		for (int i = 0; i < DEFAULT_TABLE_SIZE; i++) {
			table[i] = null;
		}
	}
	
	public void put(int key, long value) {
		int probeNumber = 0;
		int index;
		do {
			index = hash(key, probeNumber);
			if (table[index] != null && table[index].key == key) {
					table[index].value = value;
					break;
			}else if(table[index] == null){
				table[index] = new Entry(key, value);
				num_of_elements++;
				if (num_of_elements >= threshold) {
					resize();
				}
				break;
			}
			probeNumber++;
		} while (true);
	}

	public Long get(int key) {
		int probeNumber = 0;
		int index = hash(key, probeNumber);
		while (table[index] != null) {
			if(probeNumber >= table.length) {
				return null;
			}
			if (table[index] != null && table[index].key == key) {
				return table[index].value;
			} 
			probeNumber++;
			index = hash(key, probeNumber);
		}
		return null;
	}

	public int size() {
		return num_of_elements;
	}	
	
	private int hash(int key, int probeNumber) {
		
		return (key + probeNumber) % table.length;
	}

	private void resize() {
		int newSize = 2 * table.length;
		threshold = (int) (0.75f * newSize);
		Entry[] oldTable = table;
		table = new Entry[newSize];
		num_of_elements = 0;
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null) {
				put(oldTable[i].getKey(), oldTable[i].getValue());
			}
		}
	}
}
