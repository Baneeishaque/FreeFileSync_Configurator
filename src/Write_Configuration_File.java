
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Write_Configuration_File {

    public static void main(String argv[]) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("FreeFileSync");
            rootElement.setAttribute("XmlType", "GUI");
            rootElement.setAttribute("XmlFormat", "14");
            doc.appendChild(rootElement);

            // Compare elements
            Element compare = doc.createElement("Compare");
            rootElement.appendChild(compare);

            // Variant elements
            Element variant = doc.createElement("Variant");
            variant.appendChild(doc.createTextNode("TimeAndSize"));
            compare.appendChild(variant);

            Element Symlinks = doc.createElement("Symlinks");
            Symlinks.appendChild(doc.createTextNode("Exclude"));
            compare.appendChild(Symlinks);

            Element IgnoreTimeShift = doc.createElement("IgnoreTimeShift");
            compare.appendChild(IgnoreTimeShift);

            // Synchronize elements
            Element Synchronize = doc.createElement("Synchronize");
            rootElement.appendChild(Synchronize);

            // Variant elements
            Element variant2 = doc.createElement("Variant");
            variant2.appendChild(doc.createTextNode("Mirror"));
            Synchronize.appendChild(variant2);

            Element DetectMovedFiles = doc.createElement("DetectMovedFiles");
            DetectMovedFiles.appendChild(doc.createTextNode("true"));
            Synchronize.appendChild(DetectMovedFiles);

            Element DeletionPolicy = doc.createElement("DeletionPolicy");
            DeletionPolicy.appendChild(doc.createTextNode("RecycleBin"));
            Synchronize.appendChild(DeletionPolicy);

            Element VersioningFolder = doc.createElement("VersioningFolder");
            VersioningFolder.setAttribute("Style", "Replace");
            Synchronize.appendChild(VersioningFolder);

            // Synchronize elements
            Element Filter = doc.createElement("Filter");
            rootElement.appendChild(Filter);

            // Include elements
            Element Include = doc.createElement("Include");
            Element Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("*"));
            Include.appendChild(Item);
            Filter.appendChild(Include);

            Element Exclude = doc.createElement("Exclude");

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("\\System Volume Information\\"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("\\$Recycle.Bin\\"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("\\RECYCLER\\"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("\\RECYCLED\\"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("*\\desktop.ini"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("*\\thumbs.db"));
            Exclude.appendChild(Item);

            // Linux
            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("/.Trash-*/"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("/.recycle/"));
            Exclude.appendChild(Item);

            // Mac
            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("/.fseventsd/"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("/.Spotlight-V100/"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("/.Trashes/"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("*/.DS_Store"));
            Exclude.appendChild(Item);

            Item = doc.createElement("Item");
            Item.appendChild(doc.createTextNode("*/._.*"));
            Exclude.appendChild(Item);

            Filter.appendChild(Exclude);

//            Element DetectMovedFiles = doc.createElement("DetectMovedFiles");
//            DetectMovedFiles.appendChild(doc.createTextNode("true"));
//            Synchronize.appendChild(DetectMovedFiles);
//            
//            Element DeletionPolicy = doc.createElement("DeletionPolicy");
//            DeletionPolicy.appendChild(doc.createTextNode("RecycleBin"));
//            Synchronize.appendChild(DeletionPolicy);
//            
//            Element VersioningFolder = doc.createElement("VersioningFolder");
//            VersioningFolder.setAttribute("Style", "Replace");
//            Synchronize.appendChild(VersioningFolder);
//            // lastname elements
//            Element lastname = doc.createElement("lastname");
//            lastname.appendChild(doc.createTextNode("mook kim"));
//            staff.appendChild(lastname);
//
//            // nickname elements
//            Element nickname = doc.createElement("nickname");
//            nickname.appendChild(doc.createTextNode("mkyong"));
//            staff.appendChild(nickname);
//
//            // salary elements
//            Element salary = doc.createElement("salary");
//            salary.appendChild(doc.createTextNode("100000"));
//            staff.appendChild(salary);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("FreeFileSync.xml"));

            // Output to console for testing
//            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
        }
    }
}
