function getXmlHttpRequestObject()
{
    var xmlHttp = false;
    if (window.XMLHttpRequest)
    {
        //IE7+, Firefox, Chrome 
        return new XMLHttpRequest(); 
    }
    else if (window.ActiveXObject)
    {
        //Why IE6, IE5????
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
    else
    {
        alert("Really? Get Chrome!");
    }
};

var xmlHttp = getXmlHttpRequestObject();

function SuggestionProvider(){
}


SuggestionProvider.prototype.requestSuggestions = function(oAutoSuggestControl, bTypeAhead) {
    var sTextboxValue = oAutoSuggestControl.textbox.value;
    var request = "suggest?q=" + encodeURI(sTextboxValue);

    xmlHttp.open("GET", request);
    xmlHttp.onreadystatechange = this.handleServletGet(oAutoSuggestControl, bTypeAhead);
    xmlHttp.send(null);
};

SuggestionProvider.prototype.handleServletGet = function (oAutoSuggestControl, bTypeAhead) {
    return function() {
        if (xmlHttp.readyState == 4){
            var suggestions = xmlHttp.responseXML.getElementsByTagName('CompleteSuggestion');
            var aSuggestions = new Array();
    
            //suggestion string array for SuggestionProvider
            var testHTML = "";
            for(i = 0; i < suggestions.length; i++) {
                var text = suggestions[i].childNodes[0].getAttribute("data");
                testHTML += text;
                aSuggestions.push(text);
            }
            
            oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
        }
    }
};


