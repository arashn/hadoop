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
		assertEquals(clr.getReferenceCount(), 1);
	}
	
	@Test
	public void testUnreference() throws ClosedChannelException {
		CloseableReferenceCount clr = new CloseableReferenceCount();
		clr.reference();
		clr.reference();
		assertFalse(clr.unreference());
		assertEquals(clr.getReferenceCount(), 1);
	}
	
	@Test
	public void testUnreferenceCheckClosed() throws ClosedChannelException {
		CloseableReferenceCount clr = new CloseableReferenceCount();
		clr.reference();
		clr.reference();
		clr.unreferenceCheckClosed();
		assertEquals(clr.getReferenceCount(), 1);
	}
	
	@Test
	public void testSetClosed() throws ClosedChannelException {
		CloseableReferenceCount clr = new CloseableReferenceCount();
		assertTrue(clr.isOpen());
		clr.setClosed();
		assertFalse(clr.isOpen());
	}
	
	@Test(expected = ClosedChannelException.class)
	public void testReferenceClosedReference() throws ClosedChannelException {
		CloseableReferenceCount clr = new CloseableReferenceCount();
		clr.setClosed();
		assertFalse(clr.isOpen());
		clr.reference();
	}
	
	@Test(expected = ClosedChannelException.class)
	public void testUnreferenceClosedReference() throws ClosedChannelException {
		CloseableReferenceCount clr = new CloseableReferenceCount();
		clr.reference();
		clr.setClosed();
		assertFalse(clr.isOpen());
		clr.unreferenceCheckClosed();
	}
	
	@Test(expected = ClosedChannelException.class)
	public void testDoubleClose() throws ClosedChannelException {
		CloseableReferenceCount clr = new CloseableReferenceCount();
		assertTrue(clr.isOpen());
		clr.setClosed();
		assertFalse(clr.isOpen());
		clr.setClosed();
	}
}
