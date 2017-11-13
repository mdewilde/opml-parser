package be.ceau.opml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import be.ceau.opml.entity.Body;
import be.ceau.opml.entity.Head;
import be.ceau.opml.entity.Opml;
import be.ceau.opml.entity.Outline;

public class ModelTest {

	private Outline newOutline() {
		return new Outline(new HashMap<String, String>(), new ArrayList<Outline>());
	}
	
	private Outline newOutline2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("text", "text");
		return new Outline(map, Collections.singletonList(newOutline()));
	}

	private Body newBody() {
		return new Body(new ArrayList<Outline>(Arrays.asList(newOutline())));
	}
	
	private Head newHead() {
		return new Head(null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	private Head newHead2() {
		return new Head("head2", null, null, null, null, null, null, null, null, null, null, null, null);
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
	
	@Test(expected = IllegalArgumentException.class)
	public void nullInputStream() {
		OpmlParser parser = new OpmlParser();
		InputStream inputStream = null;
		try {
			parser.parse(inputStream);
		} catch (OpmlParseException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullReader() {
		OpmlParser parser = new OpmlParser();
		Reader reader = null;
		try {
			parser.parse(reader);
		} catch (OpmlParseException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = OpmlParseException.class)
	public void garbageString() throws OpmlParseException {
		try {
			new OpmlParser().parse("asdfasdfasdf");
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("required element <opml> but found text"));
			throw e;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void emptyString() {
		OpmlParser parser = new OpmlParser();
		String string = "";
		try {
			parser.parse(string);
		} catch (OpmlParseException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badInputStreamString() throws OpmlParseException {
		try {
			new OpmlParser().parse(new ByteArrayInputStream(new byte[0]));
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("XML invalid, no <opml> element"));
			throw e;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void outlineConstructorFirstArgNull() {
		new Outline(null, Collections.<Outline>emptyList());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void outlineConstructorSecondArgNull() {
		new Outline(Collections.<String, String>emptyMap(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void outlineGetAttributeWithoutKey() {
		new Outline(Collections.<String, String>emptyMap(), Collections.<Outline>emptyList()).getAttribute(null);
	}

	@Test
	public void outlineGetSubElementBeyondSize() {
		Assert.assertNull(new Outline(Collections.<String, String>emptyMap(), Collections.<Outline>emptyList()).getSubElement(Integer.MAX_VALUE));
	}

	@Test
	public void outline() {
		Outline outline = newOutline();
		Outline outline2 = newOutline2();
		Assert.assertFalse(outline.toString().isEmpty());
		Assert.assertFalse(outline.equals(null));
		Assert.assertEquals(outline, outline);
		Assert.assertEquals(outline, newOutline());
		Assert.assertFalse(outline2.toString().isEmpty());
		Assert.assertFalse(outline.equals(outline2));
		Assert.assertFalse(outline2.equals(outline));
		Assert.assertNotEquals(outline.hashCode(), outline2.hashCode());
		Assert.assertNotEquals(outline, new Object());
		Assert.assertNull(outline.getText());
	}

	@Test
	public void body() {
		Body body1 = newBody();
		Outline outline2 = new Outline(Collections.<String, String>emptyMap(), Collections.singletonList(newOutline()));
		Body body2 = new Body(Collections.singletonList(outline2));
		Assert.assertFalse(body1.toString().isEmpty());
		Assert.assertNotEquals(body1, null);
		Assert.assertEquals(body1, body1);
		Assert.assertFalse(body2.toString().isEmpty());
		Assert.assertNotEquals(body1, body2);
		Assert.assertNotEquals(body2, body1);
		Assert.assertNotEquals(body1.hashCode(), body2.hashCode());
		Assert.assertNotEquals(body1, new Object());
	}

	@Test
	public void head() {
		Head head1 = newHead();
		Head head2 = newHead2();
		Assert.assertFalse(head1.toString().isEmpty());
		Assert.assertNotEquals(head1, null);
		Assert.assertEquals(head1, head1);
		Assert.assertFalse(head2.toString().isEmpty());
		Assert.assertNotEquals(head1, head2);
		Assert.assertNotEquals(head2, head1);
		Assert.assertNotEquals(head1.hashCode(), head2.hashCode());
		Assert.assertNotEquals(head1, new Object());
	}

	@Test
	public void opml() {
		Opml opml1 = new Opml("2.0", newHead(), newBody());
		Opml opml2 = new Opml("2.0", newHead2(), newBody());

		Assert.assertFalse(opml1.toString().isEmpty());
		Assert.assertNotEquals(opml1, null);
		Assert.assertEquals(opml1, opml1);
		Assert.assertFalse(opml2.toString().isEmpty());
		Assert.assertNotEquals(opml1, opml2);
		Assert.assertNotEquals(opml2, opml1);
		Assert.assertNotEquals(opml1.hashCode(), opml2.hashCode());
		Assert.assertNotEquals(opml1, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void opmlConstructorFirstArgumentNull() {
		new Opml(null, newHead(), newBody());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void opmlConstructorSecondArgumentNull() {
		new Opml("2.0", null, newBody());
	}

	@Test(expected = IllegalArgumentException.class)
	public void opmlConstructorThirdArgumentNull() {
		new Opml("2.0", newHead(), null);
	}

}
