import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ARXMLSort {
    public static void main(String[] args) {
        // Check if input file was passed from command line 
        if (args.length == 0){
            System.out.println("File not found.");
            return;
        }
        
        try {
	    // Store input file name
            String inputFileName = args[0];
          
            // Check if input file ends with valid extension ".arxml"
            if(!inputFileName.endsWith(".arxml")){
                throw new NotValidAutosarFileException("Invalid file extension.");
            }
		
	    // Read input file
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFileName);
            doc.getDocumentElement().normalize();
            
            // Check if input file is empty
            if(doc.getChildNodes().getLength() == 0){
                throw new EmptyAutosarFileException("Empty file.");
            }
            
            // Extract containers with their ID and "SHORT-NAME"
            NodeList containerList = doc.getElementsByTagName("CONTAINER");
            List<Element> containers = new ArrayList<Element>();
            for (int i = 0; i < containerList.getLength(); i++) {
                containers.add((Element)containerList.item(i));
            }

            // Sort the containers list alphabetically by "SHORT-NAME"
            Collections.sort(containers, new Comparator<Element>() {
                @Override
                public int compare(Element e1, Element e2) {
                    String s1 = e1.getElementsByTagName("SHORT-NAME").item(0).getTextContent();
                    String s2 = e2.getElementsByTagName("SHORT-NAME").item(0).getTextContent();
                    return s1.compareToIgnoreCase(s2);
                }
            });

            // Update existing input ARXML file and write the sorted containers to it
            Element rootElement = doc.getDocumentElement();
            for (Element container : containers) {
                rootElement.appendChild(container);
            }

            // Save and close the new ARXML file with new name
            String outputFileName = inputFileName.replace(".arxml", "_mod.arxml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputFileName));
            transformer.transform(source, result);
            System.out.println("ARXML containers sorted and saved to " + outputFileName);
        } catch (SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        catch (NotValidAutosarFileException e){
            System.out.println("Error: " + e.getMessage());  
        }
        catch(EmptyAutosarFileException e){
            System.out.println("Error: " + e.getMessage());
        }
		catch(Exception e){
            e.printStackTrace();
        }
        
    }
}

    
   
class NotValidAutosarFileException extends Exception{
    public NotValidAutosarFileException(String msg){
        super(msg);
    }
}

class EmptyAutosarFileException extends RuntimeException{
    public EmptyAutosarFileException(String msg){
        super(msg);
    }
}
