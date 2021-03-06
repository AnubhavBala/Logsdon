package org.uiowa.logsdon.genespot.NCBI;

import org.uiowa.logsdon.genespot.JobInformation.Gene;
import org.uiowa.logsdon.genespot.JobInformation.Hit;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 * Created on Dec 7, 2016
 * Author: austinward 
 *
 */

public class XMLHandler extends DefaultHandler {

	private final Gene currentGene;

	private String id;
	private String from;
	private String to;

	private String hold;

	private boolean hit_Id_found = false;
	private boolean Hsp_hit_from_Found = false;
	private boolean Hsp_hit_to_Found = false;
	private boolean Hsp_hit_frame_Found = false;

	public XMLHandler(Gene gene) {

		this.currentGene = gene;
	}

	@Override
	public void startDocument() {
		System.out.println("begin parsing document..");
	}

	@Override
	public void endDocument() {
		System.out.println("end parsing document..");
	}

	@Override
	public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts) {

		switch (qName) {

		case "Hit_id":
			hit_Id_found = true;
			break;
		case "Hsp_hit-from":
			Hsp_hit_from_Found = true;
			break;
		case "Hsp_hit-to":
			Hsp_hit_to_Found = true;
			break;
		case "Hsp_hit-frame":
			Hsp_hit_frame_Found = true;
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {

		hold = new String(ch, start, length);

		if (hit_Id_found) {
			System.out.println("Hit ID: " + hold);
			id = hold.trim();
		}

		else if (Hsp_hit_from_Found) {
			System.out.println("Hit Start: " + hold);
			from = hold.trim();
		}

		else if (Hsp_hit_to_Found) {
			System.out.println("Hit End: " + hold);
			to = hold.trim();
		}

		else if (Hsp_hit_frame_Found) {
			System.out.println("Hit frame: " + hold);
		}
	}

	@Override
	public void endElement(String nameSpaceURI, String localName, String qName) {

		switch (localName) {

		case "Hit_id":
			hit_Id_found = false;
			break;
		case "Hsp_hit-from":
			Hsp_hit_from_Found = false;
			break;
		case "Hsp_hit-to":
			Hsp_hit_to_Found = false;
			break;
		case "Hsp_hit-frame":
			Hsp_hit_frame_Found = false;

			/*
			// first hit added
			if (currentGene.getHits().length == 0) {
				//System.out.println("Here1 (should only see this one)");
				currentGene.addHit(new Hit(id, from, to));
			}

			else {
				currentGene.compareHits(new Hit(id, from, to));
			}
			*/

			break;
		}
	}
}
