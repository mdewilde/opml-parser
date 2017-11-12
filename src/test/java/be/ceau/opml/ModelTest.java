package be.ceau.opml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import be.ceau.opml.entity.Body;
import be.ceau.opml.entity.Head;
import be.ceau.opml.entity.Outline;

public class ModelTest {

	private Outline newOutline() {
		return new Outline(new HashMap<String, String>(), new ArrayList<Outline>());
	}
	
	private Body newBody() {
		return new Body(new ArrayList<Outline>(Arrays.asList(newOutline())));
	}
	
	private Head newHead() {
		return new Head(null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void outlineAttributesAreUnmodifiable() {
		Outline outline = newOutline();
		outline.getAttributes().put("test", "test");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void outlineSubElementsAreUnmodifiable() {
		Outline outline = newOutline();
		outline.getSubElements().add(newOutline());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void bodyOutlinesAreUnmodifiable() {
		Body body = newBody();
		body.getOutlines().add(newOutline());
	}

	@Test(expected = IllegalArgumentException.class)
	public void bodyMustContainAtLeastOneOutline() {
		new Body(new ArrayList<Outline>());
	}

	@Test
	public void bodyOutlineByIndexTest() {
		Body body = newBody();
		Assert.assertNotNull(body.getOutline(0));
		Assert.assertNull(body.getOutline(Integer.MAX_VALUE));
	}	

	@Test(expected = UnsupportedOperationException.class)
	public void headIsUnmodifiable() {
		Head head = newHead();
		head.getExpansionState().add(2);
	}
	
}
