import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class main {
	public static void main(String[] args){
		try {
			File fXmlFile = new File("DataFiles/RaceOptions.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("race");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					System.out.println("Race id : " + eElement.getAttribute("id"));

					NodeList nListS = nNode.getChildNodes();
					for (int tempS = 0; tempS < nListS.getLength(); tempS++) {
						Node nNodeS = nListS.item(tempS);
						if (nNodeS.getNodeType() == Node.ELEMENT_NODE) {
							Element eElementS = (Element) nNodeS;
							if(eElementS.getNodeName() == "subRace")
								System.out.println("Node : " + eElementS.getNodeName() + ":" + eElementS.getAttribute("id"));
							else
								System.out.println("Node : " + eElementS.getNodeName() + ":" + eElementS.getTextContent());
						}
					}
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}
}
