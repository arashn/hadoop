package org.apache.hadoop.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.junit.Test;

public class TestLimitInputStream {
  class TestInputStream extends InputStream {
      private Random rn = new Random(0);

      @Override
      public int read() { return rn.nextInt(); }
  }
  
  @Test
  public void testRead() throws IOException {
    LimitInputStream limitInputStream = new LimitInputStream(new TestInputStream(), 0);
    assertEquals("Reading byte after reaching limit should return -1", -1, limitInputStream.read());
    limitInputStream = new LimitInputStream(new TestInputStream(), 4);
    assertEquals("Incorrect byte returned", new Random(0).nextInt(), limitInputStream.read());
    limitInputStream.close();
  }
  
  @Test(expected = IOException.class)
  public void testResetWithoutMark() throws IOException {
    LimitInputStream limitInputStream = new LimitInputStream(new TestInputStream(), 128);
    limitInputStream.reset();
    limitInputStream.close();
  }
  
  @Test
  public void testReadBytes() throws IOException {
    LimitInputStream limitInputStream = new LimitInputStream(new TestInputStream(), 128);
    Random r = new Random(0);
    byte[] data = new byte[4];
    byte[] expected = { (byte) r.nextInt(), (byte) r.nextInt(), (byte) r.nextInt(), (byte) r.nextInt() };
    limitInputStream.read(data, 0, 4);
    assertArrayEquals("Incorrect bytes returned", expected, data);
    limitInputStream.close();
  }
}
