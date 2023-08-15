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

/**
 * An exception indicating an unrecoverable issue during parsing an OPML document
 */
public class OpmlParseException extends Exception {

	private static final long serialVersionUID = 1501268262332L;

	public OpmlParseException(Throwable cause) {
		super(cause);
	}

	public OpmlParseException(String message) {
		super(message);
	}

}
