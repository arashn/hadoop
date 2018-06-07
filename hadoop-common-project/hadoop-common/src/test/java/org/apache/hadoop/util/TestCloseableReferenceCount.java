package org.apache.hadoop.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.channels.ClosedChannelException;

import org.junit.Test;

public class TestCloseableReferenceCount {
  @Test
  public void testReference() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    clr.reference();
    assertEquals("Incorrect reference count", 1, clr.getReferenceCount());
  }
  
  @Test
  public void testUnreference() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    clr.reference();
    clr.reference();
    assertFalse("New reference count should not equal STATUS_CLOSED_MASK", clr.unreference());
    assertEquals("Incorrect reference count", 1, clr.getReferenceCount());
  }
  
  @Test
  public void testUnreferenceCheckClosed() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    clr.reference();
    clr.reference();
    clr.unreferenceCheckClosed();
    assertEquals("Incorrect reference count", 1, clr.getReferenceCount());
  }
  
  @Test
  public void testSetClosed() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    assertTrue("Reference count should be open", clr.isOpen());
    clr.setClosed();
    assertFalse("Reference count should be closed", clr.isOpen());
  }
  
  @Test(expected = ClosedChannelException.class)
  public void testReferenceClosedReference() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    clr.setClosed();
    assertFalse("Reference count should be closed", clr.isOpen());
    clr.reference();
  }
  
  @Test(expected = ClosedChannelException.class)
  public void testUnreferenceClosedReference() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    clr.reference();
    clr.setClosed();
    assertFalse("Reference count should be closed", clr.isOpen());
    clr.unreferenceCheckClosed();
  }
  
  @Test(expected = ClosedChannelException.class)
  public void testDoubleClose() throws ClosedChannelException {
    CloseableReferenceCount clr = new CloseableReferenceCount();
    assertTrue("Reference count should be open", clr.isOpen());
    clr.setClosed();
    assertFalse("Reference count should be closed", clr.isOpen());
    clr.setClosed();
  }
}
