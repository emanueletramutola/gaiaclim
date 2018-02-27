function loadFilters(ignoreUrlParams){
    var filter = getFilter(ignoreUrlParams);
    if (filter != ''){
        var xmlhttp = new XMLHttpRequest();
        var url = baseURL + '&typeName=station:station&CQL_FILTER=' + filter;

        xmlhttp.open("GET", url, true);
        xmlhttp.send();

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var myArr = JSON.parse(this.responseText);

                loadEcv(myArr);
                loadNetwork(myArr,ignoreUrlParams);
                loadFeatureType(myArr);
                loadMM_metadata(myArr);
                loadMM_documentation(myArr);
                loadMM_uncertainty(myArr);
                loadMM_public(myArr);
                loadMM_sustainability(myArr);
            }
        };
    }
    else {
        loadFilters_base(ignoreUrlParams);
    }

    loadSatelliteWeather();
    loadSatelliteSpaceAndEarthScienceList();
    coordinatesOnMouse();
}

function loadFilters_base(ignoreUrlParams){
    loadFilters_base_ECV();
    loadFilters_base_Network(ignoreUrlParams);
    loadFilters_base_FeatureType();
    loadFilters_base_MM_Metadata();
    loadFilters_base_MM_Documentation();
    loadFilters_base_MM_Uncertainty();
    loadFilters_base_MM_Public();
    loadFilters_base_MM_Sustainability();
}

function loadFilters_base_ECV(){
    var xmlhttp = new XMLHttpRequest();
    var url = baseURL + '&typeName=station:ecv';

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);

            loadEcv(myArr);
        }
    };
}

function loadFilters_base_Network(ignoreUrlParams){
    var xmlhttp = new XMLHttpRequest();
    var url = baseURL + '&typeName=station:network';

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);

            loadNetwork(myArr,ignoreUrlParams);
        }
    };
}

function loadFilters_base_FeatureType(){
    var xmlhttp = new XMLHttpRequest();
    var url = baseURL + '&typeName=station:featuretype';

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);

            loadFeatureType(myArr);
        }
    };
}

function loadFilters_base_MM_Metadata(){
    var myArr = {features:[5]};

    myArr.features[0] = {properties:{mm_metadata:1}};
    myArr.features[1] = {properties:{mm_metadata:2}};
    myArr.features[2] = {properties:{mm_metadata:3}};
    myArr.features[3] = {properties:{mm_metadata:4}};
    myArr.features[4] = {properties:{mm_metadata:5}};
    myArr.features[5] = {properties:{mm_metadata:6}};

    loadMM_metadata(myArr);
}

function loadFilters_base_MM_Documentation(){
    var myArr = {features:[5]};

    myArr.features[0] = {properties:{mm_documentation:1}};
    myArr.features[1] = {properties:{mm_documentation:2}};
    myArr.features[2] = {properties:{mm_documentation:3}};
    myArr.features[3] = {properties:{mm_documentation:4}};
    myArr.features[4] = {properties:{mm_documentation:5}};
    myArr.features[5] = {properties:{mm_documentation:6}};

    loadMM_documentation(myArr);
}

function loadFilters_base_MM_Uncertainty(){
    var myArr = {features:[5]};

    myArr.features[0] = {properties:{mm_uncertainty:1}};
    myArr.features[1] = {properties:{mm_uncertainty:2}};
    myArr.features[2] = {properties:{mm_uncertainty:3}};
    myArr.features[3] = {properties:{mm_uncertainty:4}};
    myArr.features[4] = {properties:{mm_uncertainty:5}};
    myArr.features[5] = {properties:{mm_uncertainty:6}};

    loadMM_uncertainty(myArr);
}

function loadFilters_base_MM_Public(){
    var myArr = {features:[5]};

    myArr.features[0] = {properties:{mm_public:1}};
    myArr.features[1] = {properties:{mm_public:2}};
    myArr.features[2] = {properties:{mm_public:3}};
    myArr.features[3] = {properties:{mm_public:4}};
    myArr.features[4] = {properties:{mm_public:5}};
    myArr.features[5] = {properties:{mm_public:6}};

    loadMM_public(myArr);
}

function loadFilters_base_MM_Sustainability(){
    var myArr = {features:[5]};

    myArr.features[0] = {properties:{mm_sustainability:1}};
    myArr.features[1] = {properties:{mm_sustainability:2}};
    myArr.features[2] = {properties:{mm_sustainability:3}};
    myArr.features[3] = {properties:{mm_sustainability:4}};
    myArr.features[4] = {properties:{mm_sustainability:5}};
    myArr.features[5] = {properties:{mm_sustainability:6}};

    loadMM_sustainability(myArr);
}

function getArr(arrInput, filterName){
    var arr = [];
    for(i = 0; i < arrInput.features.length; i++) {
        switch(filterName){
            case "ECV":
                arr.push(arrInput.features[i].properties.ecv);
                break;
            case "NETWORK":
                arr.push(arrInput.features[i].properties.network);
                break;
            case "FEATURETYPE":
                arr.push(arrInput.features[i].properties.featuretype);
                break;
            case "MM_METADATA":
                arr.push(arrInput.features[i].properties.mm_metadata);
                break;
            case "MM_DOCUMENTATION":
                arr.push(arrInput.features[i].properties.mm_documentation);
                break;
            case "MM_UNCERTAINTY":
                arr.push(arrInput.features[i].properties.mm_uncertainty);
                break;
            case "MM_PUBLIC":
                arr.push(arrInput.features[i].properties.mm_public);
                break;
            case "MM_SUSTAINABILITY":
                arr.push(arrInput.features[i].properties.mm_sustainability);
                break;
        }
    }

    arr = removeDuplicates(arr);

    return arr.sort();
}

function getArr_MM(arr){
    var MM_Values = ["1 - 2", "3 - 4", "5 - 6"];

    var arr_MM = [];
    for(i = 0; i < arr.length; i++) {
        if (arr[i] >= 1 && arr[i] <= 2){
            arr_MM.push(MM_Values[0]);
        } else if (arr[i] >= 3 && arr[i] <= 4){
            arr_MM.push(MM_Values[1]);
        } else if (arr[i] >= 5 && arr[i] <= 6){
            arr_MM.push(MM_Values[2]);
        }
    }

    arr_MM = removeDuplicates(arr_MM);

    return arr_MM;
}

function removeDuplicates(arrDuplicates){
    var arrUnique = [];
    $.each(arrDuplicates, function(i, el){
        if($.inArray(el, arrUnique) === -1) arrUnique.push(el);
    });

    return arrUnique;
}

function loadEcv(myArr){
    var ecvSelected = getFilter_ECV();

    var arr = getArr(myArr, "ECV");

    if (arr.length > 0){
        var stringaOut = '<select id=\"ecvs\" onchange="applyFilter(true,true)" class="form-control">';
        if (ecvSelected == ''){
            stringaOut += '<option selected=\" \">Select ECV</option>';
            }
        else {
            stringaOut += '<option>Select ECV</option>';
        }

        for(i = 0; i < arr.length; i++) {
            if (ecvSelected != '' && ecvSelected == arr[i])
            {
                stringaOut += '<option selected=\" \">' + arr[i] + '</option>';
            }
            else {
                stringaOut += '<option>' + arr[i] + '</option>';
            }
        }

        stringaOut += '</select>';

        var tag = document.getElementById("ecvList");
        tag.innerHTML = stringaOut;
    }
}

function loadNetwork(myArr,ignoreUrlParams){
    var networkSelected = getFilter_Network(ignoreUrlParams);

    var arr = getArr(myArr, "NETWORK");

    if (arr.length > 0){
        var stringaOut = '<select id=\"networks\" onchange="applyFilter(false,true)" class="form-control">';
        if (networkSelected == ''){
            stringaOut += '<option selected=\" \">Select Network</option>';
            }
        else {
            stringaOut += '<option>Select Network</option>';
        }

        for(i = 0; i < arr.length; i++) {
            if (networkSelected != '' && networkSelected == arr[i])
            {
                stringaOut += '<option selected=\" \">' + arr[i] + '</option>';
            }
            else {
                stringaOut += '<option>' + arr[i] + '</option>';
            }
        }

        stringaOut += '</select>';

        var tag = document.getElementById("networkList");
        tag.innerHTML = stringaOut;
    }
}

function loadFeatureType(myArr){
    var featureTypeSelected = getFilter_FeatureType();

    var arr = getArr(myArr, "FEATURETYPE");

    if (arr.length > 0){
        var stringaOut = '<select id=\"featuretypes\" onchange="applyFilter(false,true)" class="form-control">';
        if (featureTypeSelected == ''){
            stringaOut += '<option selected=\" \">Select Feature Type</option>';
            }
        else {
            stringaOut += '<option>Select Feature Type</option>';
        }

        for(i = 0; i < arr.length; i++) {
            if (featureTypeSelected != '' && featureTypeSelected == arr[i])
            {
                stringaOut += '<option selected=\" \">' + arr[i] + '</option>';
            }
            else {
                stringaOut += '<option>' + arr[i] + '</option>';
            }
        }

        stringaOut += '</select>';

        var tag = document.getElementById("featureTypeList");
        tag.innerHTML = stringaOut;
    }
}

function loadMM(myArr, mmSelected, metadataName, metadataTagID, metadataNameDisplay, mmTagIDTarget){
    var arr = getArr(myArr, metadataName);
    var arr_MM = getArr_MM(arr);

    var stringaOut = '<select id=\"' + metadataTagID + '\" onchange="applyFilter(false,true)" class="form-control">';
    if (mmSelected == ''){
        stringaOut += '<option selected=\" \">Select ' + metadataNameDisplay + '</option>';
        }
    else {
        stringaOut += '<option>Select ' + metadataNameDisplay + '</option>';
    }

    for(i = 0; i < arr_MM.length; i++) {
        if (mmSelected != '' && mmSelected == arr_MM[i])
        {
            stringaOut += '<option selected=\" \">' + arr_MM[i] + '</option>';
        }
        else {
            stringaOut += '<option>' + arr_MM[i] + '</option>';
        }
    }

    stringaOut += '</select>';

    var tag = document.getElementById(mmTagIDTarget);
    tag.innerHTML = stringaOut;
}

function loadMM_metadata(myArr){
    loadMM(myArr, getFilter_MM_Metadata(), "MM_METADATA", "mm_metadata", "Metadata", "metadataList");
}
function loadMM_documentation(myArr){
    loadMM(myArr, getFilter_MM_Documentation(), "MM_DOCUMENTATION", "mm_documentation", "Documentation", "documentationList");
}
function loadMM_uncertainty(myArr){
    loadMM(myArr, getFilter_MM_Uncertainty(), "MM_UNCERTAINTY", "mm_uncertainty", "Uncertainty", "uncertaintyList");
}
function loadMM_public(myArr){
    loadMM(myArr, getFilter_MM_Public(), "MM_PUBLIC", "mm_public", "Public", "publicList");
}
function loadMM_sustainability(myArr){
    loadMM(myArr, getFilter_MM_Sustainability(), "MM_SUSTAINABILITY", "mm_sustainability", "Sustainability", "sustainabilityList");
}

function loadSatelliteWeather(){
    var satelliteWeatherSelected = getFilter_SatelliteWeather();

    var xmlhttp = new XMLHttpRequest();
    var url = satelliteURL + 'satellitesByClassification?classification=weather';

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);

            var stringaOut = '<select id=\"weatherSatellites\" onchange="applyFilter(false)" class="form-control">';
            if (satelliteWeatherSelected == ''){
                stringaOut += '<option selected=\" \">Weather operational satellites</option>';
                }
            else {
                stringaOut += '<option>Weather operational satellites</option>';
            }

            var selectedIndex;
            for(i = 0; i < myArr.length; i++) {
                if (satelliteWeatherSelected != '' && satelliteWeatherSelected == myArr[i].satellite.name)
                {
                    stringaOut += '<option selected=\" \" value=\"' + myArr[i].satellite.noradId + '\">' + myArr[i].satellite.name + '</option>';
                    selectedIndex = i;
                }
                else {
                    stringaOut += '<option value=\"' + myArr[i].satellite.noradId + '\">' + myArr[i].satellite.name + '</option>';
                }
            }

            stringaOut += '</select>';

            var tag = document.getElementById("satelliteWeatherList");
            tag.innerHTML = stringaOut;

            if (satelliteWeatherSelected != ''){
                loadSatelliteWeatherInstruments(myArr[selectedIndex]);
            }
        }
    };
}

function loadSatelliteWeatherInstruments(myArr){
    var satelliteWeatherInstrumentSelected = getFilter_SatelliteWeatherInstrument();

    var label = '<span class=\"label label-default lblInstruments\">instruments</span>';
    var stringaOut = label + '<select id=\"weatherSatelliteInstruments\" onchange="applyFilter(false)" class="form-control">';

    for(i = 0; i < myArr.instrumentList.length; i++) {
        if (myArr.instrumentList[i].instrument.swath > 0){
            if (satelliteWeatherInstrumentSelected == myArr.instrumentList[i].instrument.name)
            {
                stringaOut += '<option selected=\" \" value=\"' + myArr.instrumentList[i].instrument.swath + '\">' + myArr.instrumentList[i].instrument.name + '</option>';
            }
            else {
                stringaOut += '<option value=\"' + myArr.instrumentList[i].instrument.swath + '\">' + myArr.instrumentList[i].instrument.name + '</option>';
            }
         }
    }

    stringaOut += '</select>';

    var tag = document.getElementById("satelliteWeatherInstrumentsList");
    tag.innerHTML = stringaOut;
}

function loadSatelliteSpaceAndEarthScienceList(){
    var satelliteSpaceAndEarthScienceSelected = getFilter_SatelliteSpaceAndEarthScience();

    var xmlhttp = new XMLHttpRequest();
    var url = satelliteURL + 'satellitesByClassification?classification=science';

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);

            var stringaOut = '<select id=\"spaceAndEarthScienceSatellites\" onchange="applyFilter(false)" class="form-control">';
            if (satelliteSpaceAndEarthScienceSelected == ''){
                stringaOut += '<option selected=\" \">Space & Earth Science satellites</option>';
                }
            else {
                stringaOut += '<option>Space & Earth Science satellites</option>';
            }

            var selectedIndex;
            for(i = 0; i < myArr.length; i++) {
                if (satelliteSpaceAndEarthScienceSelected != '' && satelliteSpaceAndEarthScienceSelected == myArr[i].satellite.name)
                {
                    stringaOut += '<option selected=\" \" value=\"' + myArr[i].satellite.noradId + '\">' + myArr[i].satellite.name + '</option>';
                    selectedIndex = i;
                }
                else {
                    stringaOut += '<option value=\"' + myArr[i].satellite.noradId + '\">' + myArr[i].satellite.name + '</option>';
                }
            }

            stringaOut += '</select>';

            var tag = document.getElementById("satelliteSpaceAndEarthScienceList");
            tag.innerHTML = stringaOut;

            if (satelliteSpaceAndEarthScienceSelected != ''){
                loadSatelliteSpaceAndEarthScienceInstruments(myArr[selectedIndex]);
            }
        }
    };
}

function loadSatelliteSpaceAndEarthScienceInstruments(myArr){
    var satelliteSelected = getFilter_SatelliteSpaceAndEarthScienceInstrument();

    var label = '<span class=\"label label-default lblInstruments\">instruments</span>';
    var stringaOut = label + '<select id=\"spaceAndEarthScienceSatelliteInstruments\" onchange="applyFilter(false)" class="form-control">';

    for(i = 0; i < myArr.instrumentList.length; i++) {
        if (myArr.instrumentList[i].instrument.swath > 0){
            if (satelliteSelected == myArr.instrumentList[i].instrument.name)
            {
                stringaOut += '<option selected=\" \" value=\"' + myArr.instrumentList[i].instrument.swath + '\">' + myArr.instrumentList[i].instrument.name + '</option>';
            }
            else {
                stringaOut += '<option value=\"' + myArr.instrumentList[i].instrument.swath + '\">' + myArr.instrumentList[i].instrument.name + '</option>';
            }
         }
    }

    stringaOut += '</select>';

    var tag = document.getElementById("satelliteSpaceAndEarthScienceInstrumentsList");
    tag.innerHTML = stringaOut;
}

function getFilter(ignoreUrlParams){
    var filter = '';

    var filter_ECV = getFilter_ECV();
    var filter_Network = getFilter_Network(ignoreUrlParams);
    var filter_FeatureType = getFilter_FeatureType();

    var filter_metadata = getFilter_MM_Metadata();
    var filter_documentation = getFilter_MM_Documentation();
    var filter_uncertainty = getFilter_MM_Uncertainty();
    var filter_public = getFilter_MM_Public();
    var filter_sustainability = getFilter_MM_Sustainability();

    if (filter_ECV != '') filter += 'ecv=\'' + filter_ECV + '\'';
    if (filter_Network != '') {
        if (filter.length > 0) { filter += ' AND '}
        filter += 'network=\'' + filter_Network + '\'';
    }
    if (filter_FeatureType != '') {
        if (filter.length > 0) { filter += ' AND '}
        filter += 'featuretype=\'' + filter_FeatureType + '\'';
    }
    if (filter_metadata != '') {
        if (filter.length > 0) { filter += ' AND '}

        filter += 'mm_metadata ' + getFilter_MM(filter_metadata);
    }
    if (filter_documentation != '') {
        if (filter.length > 0) { filter += ' AND '}

        filter += 'mm_documentation ' + getFilter_MM(filter_documentation);
    }
    if (filter_uncertainty != '') {
        if (filter.length > 0) { filter += ' AND '}

        filter += 'mm_uncertainty ' + getFilter_MM(filter_uncertainty);
    }
    if (filter_public != '') {
        if (filter.length > 0) { filter += ' AND '}

        filter += 'mm_public ' + getFilter_MM(filter_public);
    }
    if (filter_sustainability != '') {
        if (filter.length > 0) { filter += ' AND '}

        filter += 'mm_sustainability ' + getFilter_MM(filter_sustainability);
    }

    return filter;
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function getFilter_MM(value){
    if (value == "1 - 2"){
        return " IN (1,2) ";
    }
    else if (value == "3 - 4"){
        return " IN (3,4) ";
    }
    else if (value == "5 - 6"){
        return " IN (5,6) ";
    }
}

function getFilter_ECV(){
    var filter = '';

    //ECV
    var ecvs = document.getElementById("ecvs");
    if (ecvs != null) {
        var ixEcv = ecvs.selectedIndex;
        if (ixEcv > 0){
            filter = ecvs.options[ixEcv].text;
        }
    }

    return filter;
}

function getFilter_Network(ignoreUrlParams){
    var filter = '';

    //Network
    if (!ignoreUrlParams){
        filter = getParameterByName('network');
    }

    if (filter == null || filter == '') {
        filter = '';
        var networks = document.getElementById("networks");
        if (networks != null) {
            var ixNetwork = networks.selectedIndex;
            if (ixNetwork > 0){
                filter = networks.options[ixNetwork].text;
            }
        }
    }

    return filter;
}

function getFilter_FeatureType(){
    var filter = '';

    //FeatureType
    var featuretypes = document.getElementById("featuretypes");
    if (featuretypes != null) {
        var ixFeaturetype = featuretypes.selectedIndex;
        if (ixFeaturetype > 0){
            filter = featuretypes.options[ixFeaturetype].text;
        }
    }

    return filter;
}

function getFilter_MM_Metadata(){
    var filter = '';

    //MM_Metadata
    var MM_Metadata = document.getElementById("mm_metadata");
    if (MM_Metadata != null) {
        var ixMM_Metadata = MM_Metadata.selectedIndex;
        if (ixMM_Metadata > 0){
            filter = MM_Metadata.options[ixMM_Metadata].text;
        }
    }

    return filter;
}

function getFilter_MM_Documentation(){
    var filter = '';

    //MM_Documentation
    var MM_Documentation = document.getElementById("mm_documentation");
    if (MM_Documentation != null) {
        var ixMM_Documentation = MM_Documentation.selectedIndex;
        if (ixMM_Documentation > 0){
            filter = MM_Documentation.options[ixMM_Documentation].text;
        }
    }

    return filter;
}

function getFilter_MM_Uncertainty(){
    var filter = '';

    //MM_Uncertainty
    var MM_Uncertainty = document.getElementById("mm_uncertainty");
    if (MM_Uncertainty != null) {
        var ixMM_Uncertainty = MM_Uncertainty.selectedIndex;
        if (ixMM_Uncertainty > 0){
            filter = MM_Uncertainty.options[ixMM_Uncertainty].text;
        }
    }

    return filter;
}

function getFilter_MM_Public(){
    var filter = '';

    //MM_Public
    var MM_Public = document.getElementById("mm_public");
    if (MM_Public != null) {
        var ixMM_Public = MM_Public.selectedIndex;
        if (ixMM_Public > 0){
            filter = MM_Public.options[ixMM_Public].text;
        }
    }

    return filter;
}

function getFilter_MM_Sustainability(){
    var filter = '';

    //MM_Sustainability
    var MM_Sustainability = document.getElementById("mm_sustainability");
    if (MM_Sustainability != null) {
        var ixMM_Sustainability = MM_Sustainability.selectedIndex;
        if (ixMM_Sustainability > 0){
            filter = MM_Sustainability.options[ixMM_Sustainability].text;
        }
    }

    return filter;
}

function getFilter_SatelliteWeather(){
    var filter = '';

    var weatherSatellites = document.getElementById("weatherSatellites");
    if (weatherSatellites != null) {
        var ixWeatherSatellites = weatherSatellites.selectedIndex;
        if (ixWeatherSatellites > 0){
            filter = weatherSatellites.options[ixWeatherSatellites].text;
        }
    }

    return filter;
}

function getFilter_SatelliteWeatherInstrument(){
    var filter = '';

    var weatherSatelliteInstruments = document.getElementById("weatherSatelliteInstruments");
    if (weatherSatelliteInstruments != null) {
        var ix = weatherSatelliteInstruments.selectedIndex;
        if (ix > 0){
            filter = weatherSatelliteInstruments.options[ix].text;
        }
    }

    return filter;
}

function getFilter_SatelliteSpaceAndEarthScience(){
    var filter = '';

    var spaceAndEarthScienceSatellites = document.getElementById("spaceAndEarthScienceSatellites");
    if (spaceAndEarthScienceSatellites != null) {
        var ixSpaceAndEarthScience = spaceAndEarthScienceSatellites.selectedIndex;
        if (ixSpaceAndEarthScience > 0){
            filter = spaceAndEarthScienceSatellites.options[ixSpaceAndEarthScience].text;
        }
    }

    return filter;
}

function getFilter_SatelliteSpaceAndEarthScienceInstrument(){
    var filter = '';

    var spaceAndEarthScienceSatelliteInstruments = document.getElementById("spaceAndEarthScienceSatelliteInstruments");
    if (spaceAndEarthScienceSatelliteInstruments != null) {
        var ix = spaceAndEarthScienceSatelliteInstruments.selectedIndex;
        if (ix > 0){
            filter = spaceAndEarthScienceSatelliteInstruments.options[ix].text;
        }
    }

    return filter;
}