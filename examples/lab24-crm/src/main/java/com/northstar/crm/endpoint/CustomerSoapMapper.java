package com.northstar.crm.endpoint;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



@Component
public class CustomerSoapMapper {
  private final String NAMESPACE ="http://northstar.com/crm/customers";

  public String customerIdFromGetRequest(Object request) {
    Element el = (Element) request;
    NodeList nodes = el.getElementsByTagNameNS(NAMESPACE, "customerId");
    return nodes.item(0).getTextContent();
  }

  public Object toGetCustomerResponse(Customer customer) {
    Document doc;
    try {
      doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    }

    Element response = doc.createElementNS(NAMESPACE, "GetCustomerResponse");
    doc.appendChild(response);

    Element customerId = doc.createElementNS(NAMESPACE, "customerId");
    customerId.setTextContent(customer.getId());
    response.appendChild(customerId);

    Element name = doc.createElementNS(NAMESPACE, "name");
    name.setTextContent(customer.getName());
    response.appendChild(name);

    Element email = doc.createElementNS(NAMESPACE, "email");
    email.setTextContent(customer.getEmail());
    response.appendChild(email);

    Element status = doc.createElementNS(NAMESPACE, "status");
    status.setTextContent(customer.getStatus());
    response.appendChild(status);

    return response;
  }
}
