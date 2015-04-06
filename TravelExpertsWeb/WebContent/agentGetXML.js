var xmlHttp

function showAgent(agt_id)
{ 
	xmlHttp=GetXmlHttpObject()
	if (xmlHttp == null)
	{
	   alert ("Browser does not support HTTP Request")
	   return
	} 
	var url = "http://localhost:8081/TravelExpertsWeb/GetAgentXML"
	url = url + "?agentid=" + agt_id
	xmlHttp.onreadystatechange = stateChanged 
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
}

function stateChanged() 
{ 
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
	{ 
	   //retrieve the xml, extract and format it into html and display it
	   document.getElementById("txtHint").innerHTML = extractXML(xmlHttp.responseXML);
	} 
} 

function extractXML(xml)
{
  var loopIndex;
  var htmlResult = "<table border='1'>";
  var headingRow = "<tr>";
  rootNode = xml.documentElement;
  //process each row
  for (row = 0; row < rootNode.childNodes.length; row++)
  {
	  var currentRow = rootNode.childNodes[row];
      if (currentRow.nodeType == 1) //then it is an element
	  {
    	  var dataRow = "<tr>";
		  for (column = 0; column < currentRow.childNodes.length; column++)
		  {
	
	         var currentNode = currentRow.childNodes[column];
	
	         if (currentNode.nodeType == 1) //then it is an element
		     {
	            textNode = currentNode.firstChild;
	            //if (row == 1)
	            //{
			       headingRow += "<td>" + currentNode.nodeName + "</td>";
	            //}
			    dataRow += "<td>" + textNode.nodeValue + "</td>";
	         }
		  }
          if (row == 1)
          {
    	      htmlResult += headingRow + "</tr>";
          }
          htmlResult += dataRow + "</tr>" ;
	  }
  }
  htmlResult += "</table>" ;
  //alert(htmlResult);
  return htmlResult;
}

function GetXmlHttpObject()
{ 
	var objXMLHttp = null
	if (window.XMLHttpRequest)
	{
	   objXMLHttp = new XMLHttpRequest()
	}
	else if (window.ActiveXObject)
	{
	   objXMLHttp = new ActiveXObject("Microsoft.XMLHTTP")
	}
	return objXMLHttp
}