package Pacote;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author SCHARLES
 */
public class ManipulaXML {

    Document doc;

    public ManipulaXML(String caminho) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(caminho);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex);
        }
    }
/*
    public void colocaData() {
        Element raiz = doc.getDocumentElement();
        Element data = doc.createElement("data");
        Text noTexto = doc.createTextNode(LocalDate.now().toString());
        data.appendChild(noTexto);
        raiz.appendChild(data);
    }
*/
    public String PegarPorAutor(String autor){
        Node noLivro = null;
        NodeList autores=doc.getElementsByTagName("autor");
        int tam = autores.getLength();
        for(int i=tam-1;i>=0;i--){
            Node noAutor = autores.item(i);
            if(noAutor!=null){
                if(!noAutor.getFirstChild().getNodeValue().equals(autor)){
                    noLivro=noAutor.getParentNode();
                    noLivro.getParentNode().removeChild(noLivro);
                }
            }
            
        }
        return serealizar(doc);
    }
    public String PegarPorTag(String tag, String valor){
        Node noLivro = null;
        NodeList autores=doc.getElementsByTagName(tag);
        int tam = autores.getLength();
        for(int i=tam-1;i>=0;i--){
            Node noElemento = autores.item(i);
            if(noElemento!=null){
                if(!noElemento.getFirstChild().getNodeValue().equals(valor)){
                    noLivro=noElemento.getParentNode();
                    noLivro.getParentNode().removeChild(noLivro);
                }
            }
            
        }
        return serealizar(doc);
    }
    
    public String pegaDados() {
        Element raiz = doc.getDocumentElement();
        NodeList filhos = raiz.getChildNodes();
        String texto = "";
        int tam = filhos.getLength();
        for (int i = 0; i < tam; i++) {
            if (filhos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Node filho = filhos.item(i);
                NodeList netos = filho.getChildNodes();
                
                for(int j=0;j<netos.getLength();j++){
                    if (netos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Node neto = netos.item(j);
                        texto += "<b>" + neto.getNodeName() + ":</b> ";
                        texto += neto.getFirstChild().getNodeValue() + "<br>\n";
                    }
                }
                texto+= "<hr>\n";
            }
        }
        return texto;
    }
    
    

    
public String pegaAtributos(){
        Element raiz = doc.getDocumentElement();
        NodeList livros = raiz.getChildNodes();
        String texto = "";
        for(int i=0; i< livros.getLength();i++){
            if (livros.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap atributos = livros.item(i).getAttributes();
                int tam = atributos.getLength();
            
                for (int j = 0; j < tam; j++) {
                    if(atributos.item(j).getNodeType() == Node.ATTRIBUTE_NODE){
                        Node atributo = atributos.item(j);
                        texto += atributo.getNodeName() + ": " + atributo.getNodeValue() + "<br>\n";
                    }

                }
            }
        }
        
        //Node atributo=atributos.getNamedItem("carta");
        //texto+=atributo.getNodeName()+": "+atributo.getNodeValue()+"\n";
        return texto;
    }
/*
    public void criaClasse() {
        Element raiz = doc.getDocumentElement();
        raiz.setAttribute("class", "teste");

    }
*/
    public void serealizar(Writer saida) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trans = tFactory.newTransformer();
            DOMSource fonte = new DOMSource(doc);
            StreamResult resultado = new StreamResult(saida);
            trans.transform(fonte, resultado);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
    }

    public void serealizar(OutputStream saida) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trans = tFactory.newTransformer();
            DOMSource fonte = new DOMSource(doc);
            StreamResult resultado = new StreamResult(saida);
            trans.transform(fonte, resultado);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
    }
    public String serealizar(Node no) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trans = tFactory.newTransformer();
            DOMSource fonte = new DOMSource(no);
            ByteArrayOutputStream fluxo = new ByteArrayOutputStream();
            StreamResult saida = new StreamResult(fluxo);
            trans.transform(fonte, saida);
            return fluxo.toString();
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
        return "dados não encontrado";
    }

    public static void main(String[] args) {
        ManipulaXML manipulador = new ManipulaXML("src/main/webapp/WEB-INF/livraria.xml");

        System.out.println(manipulador.PegarPorTag("titulo","Harry Potter"));
        System.out.println(manipulador.PegarPorTag("autor","Per Bothner"));
        //System.out.println(manipulador.pegaDados());
        //.out.println(manipulador.pegaAtributos());
        //System.out.println("\n\n\n");
        //manipulador.serealizar(System.out);
    }

}
