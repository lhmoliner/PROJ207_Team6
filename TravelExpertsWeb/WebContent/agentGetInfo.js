var xmlHttp

function showAgent(agt_id)
{ 
	xmlHttp=GetXmlHttpObject()
	if (xmlHttp == null)
	{
	   alert ("Browser does not support HTTP Request")
	   return
	} 
	var url = "http://localhost:8081/TravelExpertsWeb/GetAgent"
	url = url + "?agentid=" + agt_id
	xmlHttp.onreadystatechange = stateChanged 
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
}

function stateChanged() 
{ 
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
	{ 
	   document.getElementById("txtHint").innerHTML = xmlHttp.responseText 
	} 
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