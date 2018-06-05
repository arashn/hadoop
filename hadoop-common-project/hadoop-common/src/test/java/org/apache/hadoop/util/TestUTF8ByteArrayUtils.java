package org.apache.hadoop.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestUTF8ByteArrayUtils {
	@Test
	public void testFindByte() {
		byte[] data = "Hello, world!".getBytes();
		assertEquals(UTF8ByteArrayUtils.findByte(data, 0, data.length, (byte) 'a'), -1);
		assertEquals(UTF8ByteArrayUtils.findByte(data, 0, data.length, (byte) 'o'), 4);
	}
	
	@Test
	public void testFindBytes() {
		byte[] data = "Hello, world!".getBytes();
		assertEquals(UTF8ByteArrayUtils.findBytes(data, 0, data.length, "ello".getBytes()), 1);
		assertEquals(UTF8ByteArrayUtils.findBytes(data, 2, data.length, "ello".getBytes()), -1);
	}
	
	@Test
	public void testFindNthByte() {
		byte[] data = "Hello, world!".getBytes();
		assertEquals(UTF8ByteArrayUtils.findNthByte(data, 0, data.length, (byte) 'l', 2), 3);
		assertEquals(UTF8ByteArrayUtils.findNthByte(data, 0, data.length, (byte) 'l', 4), -1);
		assertEquals(UTF8ByteArrayUtils.findNthByte(data, (byte) 'l', 3), 10);
	}
}
