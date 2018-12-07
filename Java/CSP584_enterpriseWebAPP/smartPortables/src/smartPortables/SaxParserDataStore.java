package smartPortables;


/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
	Wearable_Technology wearable_Technology;
    Phone phone;
    Laptop laptop;
    Accessory accessory;
    Warranty warranty;
    VoiceAssistant_SmartSpeaker voiceassistant_smartspeaker;
    static HashMap<String,Wearable_Technology> wearable_Technologies;
    static HashMap<String,Phone> phones;
    static HashMap<String,Laptop> laptops;
    static HashMap<String,VoiceAssistant_SmartSpeaker> voiceassistant_smartspeakers;
    static HashMap<String,Accessory> accessories;
    static HashMap<String,Warranty> warranties;
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
	HashMap<String,String> warrantyHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
	    this.consoleXmlFileName = consoleXmlFileName;
	    wearable_Technologies = new HashMap<String, Wearable_Technology>();
	    phones=new  HashMap<String, Phone>();
	    laptops=new HashMap<String, Laptop>();
		accessories=new HashMap<String, Accessory>();
		warranties = new HashMap<String, Warranty>();
		accessoryHashMap=new HashMap<String,String>();
		warrantyHashMap=new HashMap<String,String>();
		parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("wearable_Technology")) 
		{
			currentElement="wearable_Technology";
			wearable_Technology = new Wearable_Technology();
			wearable_Technology.setId(attributes.getValue("id"));

		}
        if (elementName.equalsIgnoreCase("laptop"))
		{
			currentElement="laptop";
			laptop = new Laptop();
			laptop.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("phone"))
		{
			currentElement="phone";
			phone= new Phone();
			phone.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("wearable_Technology"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }
        if (elementName.equals("warranty") &&  !currentElement.equals("wearable_Technology"))
		{
			currentElement="warranty";
			warranty=new Warranty();
			warranty.setId(attributes.getValue("id"));
	    }

    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
    	
        if (element.equals("wearable_Technology")) {
        	wearable_Technologies.put(wearable_Technology.getId(),wearable_Technology);
        	
			return;
        }
 
        if (element.equals("laptop")) {	
        	laptops.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("phone")) {	  
        	phones.put(phone.getId(),phone);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
        if (element.equals("warranty") && currentElement.equals("warranty")) {
        	warranties.put(warranty.getId(),warranty);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("wearable_Technology")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
		if (element.equals("warranty") && currentElement.equals("wearable_Technology")) 
		{
			warrantyHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("wearable_Technology")) {
      		wearable_Technology.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
      	if (element.equalsIgnoreCase("warranties") && currentElement.equals("wearable_Technology")) {
      		wearable_Technology.setWarranties(warrantyHashMap);
      		warrantyHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("wearable_Technology"))
		    	wearable_Technology.setImage(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);
            if(currentElement.equals("warranty"))
            	warranty.setImage(elementValueRead); 
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
        		phone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead)); 
            if(currentElement.equals("warranty"))
            	warranty.setDiscount(Double.parseDouble(elementValueRead));
			return;
	    }
		
		if (element.equalsIgnoreCase("quantiltiesOfAvailable")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setQuantiltiesOfAvailable(Integer.parseInt(elementValueRead));
        	if(currentElement.equals("phone"))
        		phone.setQuantiltiesOfAvailable(Integer.parseInt(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setQuantiltiesOfAvailable(Integer.parseInt(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setQuantiltiesOfAvailable(Integer.parseInt(elementValueRead));
            if(currentElement.equals("warranty"))
            	warranty.setQuantiltiesOfAvailable(Integer.parseInt(elementValueRead));
			return;
	    }

		if (element.equalsIgnoreCase("numberOfItems")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setNumberOfItems(Integer.parseInt(elementValueRead));
        	if(currentElement.equals("phone"))
        		phone.setQuantiltiesOfAvailable(Integer.parseInt(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setNumberOfItems(Integer.parseInt(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setNumberOfItems(Integer.parseInt(elementValueRead));
            if(currentElement.equals("warranty"))
            	warranty.setNumberOfItems(Integer.parseInt(elementValueRead));
			return;
	    }
		
		if (element.equalsIgnoreCase("totalSales")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setTotalSales(Integer.parseInt(elementValueRead));
        	if(currentElement.equals("phone"))
        		phone.setTotalSales(Integer.parseInt(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setTotalSales(Integer.parseInt(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setTotalSales(Integer.parseInt(elementValueRead));
            if(currentElement.equals("warranty"))
            	warranty.setTotalSales(Integer.parseInt(elementValueRead));
			return;
	    }

		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setCondition(elementValueRead);
        	if(currentElement.equals("phone"))
        		phone.setCondition(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);
            if(currentElement.equals("warranty"))
            	warranty.setCondition(elementValueRead); 
			return;  
		}
		
		if (element.equalsIgnoreCase("saleDate")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setSaleDate(elementValueRead);
        	if(currentElement.equals("phone"))
        		phone.setSaleDate(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setSaleDate(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setSaleDate(elementValueRead);
            if(currentElement.equals("warranty"))
            	warranty.setSaleDate(elementValueRead); 
			return;  
		}

		if (element.equalsIgnoreCase("label")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setLabel(elementValueRead);
        	if(currentElement.equals("phone"))
        		phone.setLabel(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setLabel(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setLabel(elementValueRead);
            if(currentElement.equals("warranty"))
            	warranty.setLabel(elementValueRead); 
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("wearable_Technology"))
            	wearable_Technology.setName(elementValueRead);
        	if(currentElement.equals("phone"))
        		phone.setName(elementValueRead);
            if(currentElement.equals("laptop"))
            	laptop.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);   
            if(currentElement.equals("warranty"))
            	warranty.setName(elementValueRead);
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("wearable_Technology"))
				wearable_Technology.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
        		phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
            	laptop.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));  
            if(currentElement.equals("warranty"))
            	warranty.setPrice(Double.parseDouble(elementValueRead)); 
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore("/Users/michael/Codes/Java-eclipse/smartPortables/WebContent/ProductCatalog.xml");
    } 
}
