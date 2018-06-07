package org.apache.hadoop.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestUTF8ByteArrayUtils {
  @Test
  public void testFindByte() {
    byte[] data = "Hello, world!".getBytes();
    assertEquals("Character 'a' does not exist in string", -1, UTF8ByteArrayUtils.findByte(data, 0, data.length, (byte) 'a'));
    assertEquals("Did not find first occurrence of character 'o'", 4, UTF8ByteArrayUtils.findByte(data, 0, data.length, (byte) 'o'));
  }
  
  @Test
  public void testFindBytes() {
    byte[] data = "Hello, world!".getBytes();
    assertEquals("Did not find first occurrence of pattern 'ello'", 1, UTF8ByteArrayUtils.findBytes(data, 0, data.length, "ello".getBytes()));
    assertEquals("Substring starting at position 2 does not contain pattern 'ello'", -1, UTF8ByteArrayUtils.findBytes(data, 2, data.length, "ello".getBytes()));
  }
  
  @Test
  public void testFindNthByte() {
    byte[] data = "Hello, world!".getBytes();
    assertEquals("Did not find 2nd occurrence of character 'l'", 3, UTF8ByteArrayUtils.findNthByte(data, 0, data.length, (byte) 'l', 2));
    assertEquals("4th occurrence of character 'l' does not exist", -1, UTF8ByteArrayUtils.findNthByte(data, 0, data.length, (byte) 'l', 4));
    assertEquals("Did not find 3rd occurrence of character 'l'", 10, UTF8ByteArrayUtils.findNthByte(data, (byte) 'l', 3));
  }
}
