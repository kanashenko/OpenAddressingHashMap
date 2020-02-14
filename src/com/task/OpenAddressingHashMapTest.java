package com.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Random;

import org.junit.jupiter.api.Test;

class OpenAddressingHashMapTest {
	
	@Test
	void Test() {		
		OpenAddressingHashMap map = new OpenAddressingHashMap();	
		
		assertEquals(0, map.size());
		
		map.put(77, 7000);		
		map.put(44, 12000);
		map.put(35, 1000);
		map.put(50, 5000);
		
		assertEquals(map.size(), 4); 

		assertEquals(map.get(77), new Long(7000));		
		assertEquals(map.get(44), new Long(12000));
		assertEquals(map.get(35), new Long(1000));
		assertEquals(map.get(50), new Long(5000));
				
		map.put(50, 144);
		assertEquals(map.get(50), new Long(144));
		
		assertNull(map.get(99));
	}
	
	@Test
	void Test2() {
		OpenAddressingHashMap map1 = new OpenAddressingHashMap();
		OpenAddressingHashMap map2 = new OpenAddressingHashMap();
		map1.put(77, 7000);		
		map2.put(77, 7000);	
		assertEquals(map1.size(), map2.size());
		assertEquals(map1.get(77), map2.get(77));					
	}
	
	@Test
	void TestTime() {
		OpenAddressingHashMap map = new OpenAddressingHashMap();
		long start1 = System.currentTimeMillis();
		for(int i = 0; i < 1000001; i++) {				
			map.put(i, i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("put time: "+ (end1-start1)+" ms");
		System.out.println("map size: "+map.size());
		
		long start2 = System.currentTimeMillis();
		for(int i = 0; i < 1000001; i++) {				
			assertEquals(map.get(i), new Long(i));
		}
		long end2 = System.currentTimeMillis();
		System.out.println("get time: "+ (end2-start2)+" ms");
		System.out.println("map size: "+map.size());
	}
		
	@Test
	void TestCollisions() {
		OpenAddressingHashMap map = new OpenAddressingHashMap();
		
		map.put(5, 1);		
		map.put(10, 2);
		map.put(15, 3);
		
		map.put(20, 4);		
		map.put(30, 5);
		map.put(40, 6);
		
		assertEquals(map.get(5), new Long(1));		
		assertEquals(map.get(10), new Long(2));
		assertEquals(map.get(15), new Long(3));
		assertEquals(map.get(20), new Long(4));
		assertEquals(map.get(30), new Long(5));
		assertEquals(map.get(40), new Long(6));
	}

}
