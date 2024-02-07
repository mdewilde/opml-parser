/*
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
		https://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package be.ceau.opml;

import org.junit.Assert;
import org.junit.Test;

import be.ceau.opml.entity.Opml;

public class GitHubIssue3 {

	@Test
	public void customNamespacedElementsAreOkInBody() throws OpmlParseException {
		Opml opml = SampleReader.parse("/github_issue_3_body.opml");
		Assert.assertNotNull(opml);
	}

	@Test
	public void customNamespacedElementsAreOkInHead() throws OpmlParseException {
		Opml opml = SampleReader.parse("/github_issue_3_head.opml");
		Assert.assertNotNull(opml);
	}

	@Test
	public void customNamespacedElementsAreOkInOpml() throws OpmlParseException {
		Opml opml = SampleReader.parse("/github_issue_3_opml.opml");
		Assert.assertNotNull(opml);
	}

}
