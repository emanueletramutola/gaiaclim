function footprint(){
    var dateStart = document.getElementById("date_timepicker_start").value;
    var dateEnd = document.getElementById("date_timepicker_end").value;

    dateStart = dateStart.replace(/\//g, "-");
    dateStart = dateStart.replace(" ", "T");
    dateEnd = dateEnd.replace(/\//g, "-");
    dateEnd = dateEnd.replace(" ", "T");

    var weatherSatellites = document.getElementById("weatherSatellites");
    var ixWeatherSatellite = weatherSatellites.selectedIndex;
    var spaceAndEarthScienceSatellites = document.getElementById("spaceAndEarthScienceSatellites");
    var ixSpaceAndEarthScience = spaceAndEarthScienceSatellites.selectedIndex;

    if (ixWeatherSatellite > 0){
        var ws_satelliteName = weatherSatellites.options[ixWeatherSatellite].text;
        var ws_noradId = weatherSatellites.options[ixWeatherSatellite].value;

        drawFootprint(ws_noradId, ws_satelliteName, dateStart, dateEnd, Cesium.Color.RED);
    }

    if (ixSpaceAndEarthScience > 0){
        var ses_satelliteName = spaceAndEarthScienceSatellites.options[ixSpaceAndEarthScience].text;
        var ses_noradId = spaceAndEarthScienceSatellites.options[ixSpaceAndEarthScience].value;

        drawFootprint(ses_noradId, ses_satelliteName, dateStart, dateEnd, Cesium.Color.YELLOW);
    }
}

function drawFootprint(noradId, satelliteName, dateStart, dateEnd, colorFootprint){
    var xmlhttp = new XMLHttpRequest();
    var url = satelliteURL + 'satelliteGroundtrack?noradId=' + noradId + '&dateStart=' + dateStart + '&dateEnd=' + dateEnd + '&sampling=60';

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);

            var longitude;
            var latitude;
            var passTime;
/*
            var west;
            var south;
            var east;
            var north;

            function getLatLongAtBearingAndDistance(bearing, distance, latitude, longitude){
                distance = distance/1000;
                var R = 6378.1; //Radius of the Earth

                var bearingRadians = Cesium.Math.toRadians(bearing); //Bearing is 90 degrees converted to radians.

                var lat1 = Cesium.Math.toRadians(latitude);
                var lon1 = Cesium.Math.toRadians(longitude);

                var lat2 = Math.asin(Math.sin(lat1) * Math.cos(distance/R) + Math.cos(lat1) * Math.sin(distance/R) * Math.cos(bearingRadians));
                var lon2 = lon1 + Math.atan2(Math.sin(bearingRadians) * Math.sin(distance/R) * Math.cos(lat1), Math.cos(distance/R)-Math.sin(lat1) * Math.sin(lat2));

                lat2 = Cesium.Math.toDegrees(lat2)
                lon2 = Cesium.Math.toDegrees(lon2)
                return [lon2, lat2];
            }
*/
            for(i = 0; i < myArr.length; i += 3) {
                passTime = myArr[i];
                latitude = myArr[i + 1];
                longitude = myArr[i + 2];

/*
                west = getLatLongAtBearingAndDistance(90.0, -1000000, latitude, longitude);
                south = getLatLongAtBearingAndDistance(0.0, -100000, latitude, longitude);
                east = getLatLongAtBearingAndDistance(90.0, 1000000, latitude, longitude);
                north = getLatLongAtBearingAndDistance(0.0, 100000, latitude, longitude);


                viewer.entities.add({
                    name : 'Green translucent, rotated, and extruded rectangle at height with outline',
                    rectangle : {
                        coordinates : Cesium.Rectangle.fromDegrees(west[0], south[1], east[0], north[1]),
                        material : Cesium.Color.BLUE.withAlpha(1),
                        height : 0.0,
                        outline : true,
                        outlineColor : Cesium.Color.GREEN
                    }
                });
*/
                viewer.entities.add({
                    position: Cesium.Cartesian3.fromDegrees(longitude, latitude, 0.0),
                    name : satelliteName,
                    description : 'TIME: ' + passTime.toString().substring(0,passTime.toString().lastIndexOf(".")) + ' UTC',
                    ellipse : {
                        semiMinorAxis : 50000.0,
                        semiMajorAxis : 50000.0,
                        height: 0.0,
                        material : colorFootprint
                    }
                });

            }
        }
    }
}

function swath(){
    var ec;
    var satWeather;
    var satScience;

    var weatherSatellites = document.getElementById("weatherSatellites");
    var ixWeatherSatellite = weatherSatellites.selectedIndex;
    var weatherSatelliteInstruments = document.getElementById("weatherSatelliteInstruments");
    var ixWeatherSatelliteInstruments = -1;
    if (weatherSatelliteInstruments != null) {
        ixWeatherSatelliteInstruments = weatherSatelliteInstruments.selectedIndex;
    }

    var spaceAndEarthScienceSatellites = document.getElementById("spaceAndEarthScienceSatellites");
    var ixSpaceAndEarthScience = spaceAndEarthScienceSatellites.selectedIndex;
    var spaceAndEarthScienceSatelliteInstruments = document.getElementById("spaceAndEarthScienceSatelliteInstruments");
    var ixSpaceAndEarthScienceSatelliteInstruments = -1;
    if (spaceAndEarthScienceSatelliteInstruments != null) {
        ixSpaceAndEarthScienceSatelliteInstruments = spaceAndEarthScienceSatelliteInstruments.selectedIndex;
    }

    for (var i = 0; i < viewer.dataSources.length; i++) {
        ec = viewer.dataSources._dataSources[i]._entityCollection;

        if (ixWeatherSatellite > 0){
            //satWeather = ec.getById('Satellite/' + weatherSatellites.options[ixWeatherSatellite].value);
            satWeather = ec.getById(weatherSatellites.options[ixWeatherSatellite].value);
        }
        if (ixSpaceAndEarthScience > 0){
            //satScience = ec.getById('Satellite/' + spaceAndEarthScienceSatellites.options[ixSpaceAndEarthScience].value);
            satScience = ec.getById(spaceAndEarthScienceSatellites.options[ixSpaceAndEarthScience].value);
        }

        if (satWeather != null){
            drawSwath(satWeather, weatherSatelliteInstruments.options[ixWeatherSatelliteInstruments].value * 1000, weatherSatelliteInstruments.options[ixWeatherSatelliteInstruments].text, true);
        }

        if (satScience != null){
            drawSwath(satScience, spaceAndEarthScienceSatelliteInstruments.options[ixSpaceAndEarthScienceSatelliteInstruments].value * 1000, spaceAndEarthScienceSatelliteInstruments.options[ixSpaceAndEarthScienceSatelliteInstruments].text,false);
        }
    }


}

function setHiddenSatelliteButtons(hidden){
    /*
    if(hidden) {
        document.getElementById('swathButton').style.visibility = 'hidden';
        document.getElementById('footprintButton').style.visibility = 'hidden';
    } else {
        document.getElementById('swathButton').style.visibility = 'visible';
        document.getElementById('footprintButton').style.visibility = 'visible';
    }
    */

    if(hidden) {
        document.getElementById('satelliteWeatherInstrumentsList').style.display = 'none';
        document.getElementById('satelliteButtons').style.display = 'none';
    } else {
        document.getElementById('satelliteWeatherInstrumentsList').style.display = 'block';
        document.getElementById('satelliteButtons').style.display = 'block';
    }
}

function showSatellite(idSatellite,dateStart,dateEnd){
    if (dateStart == null){
        dateStart = '';
    }
    else {
        dateStart = getDateStringForOrbit(dateStart);
    }
    if (dateEnd == null){
        dateEnd = '';
    }
    else {
        dateEnd = getDateStringForOrbit(dateEnd);
    }

    var url = satelliteURL + 'satelliteOrbit?noradId=' + idSatellite + '&dateStart=' + dateStart + '&dateEnd=' + dateEnd;

    viewer.dataSources.add(Cesium.CzmlDataSource.load(url));
}

function getDateStringForOrbit(dateInput){
    var day = dateInput.getDate();
    var month = dateInput.getMonth() + 1;
    var year = dateInput.getFullYear();
    var hours = dateInput.getHours();
    var minutes = dateInput.getMinutes();
    var seconds = dateInput.getSeconds();

    return year
        + '-' + month
        + '-' + day
        + 'T' + hours
        + ':' + minutes
        + ':' + seconds;
}

/* FROM http://www.orbitalpredictor.com/ */
/*
function drawVisibleFootPrint(longitude, latitude, height, color){
    var satLocation = Cesium.Cartesian3.fromDegrees(longitude, latitude, height);
    var groundPoint = new Cesium.Cartesian3.fromDegrees(longitude, latitude, 0.0);

    var radiusOfEarth = Cesium.Cartesian3.distance(new Cesium.Cartesian3(0,0,0), groundPoint);

    var satToOrignEarth = radiusOfEarth + height;  // point to origin of earth
    var PT = Math.sqrt(Math.pow(satToOrignEarth,2) - Math.pow(radiusOfEarth, 2) );  // point to tangent

    var groundPointToSatPointToTangentAngle = Cesium.Math.toDegrees(Math.asin(radiusOfEarth/satToOrignEarth));
    var groundPointToOriginToTangentAngle  = 90.0 - groundPointToSatPointToTangentAngle;

    var distanceAlongGround = Cesium.Math.TWO_PI * radiusOfEarth *(groundPointToOriginToTangentAngle/360.0);

    var directionInDegrees = 0.0;
    var coords = getLatLongAtBearingAndDistance(directionInDegrees, distanceAlongGround, latitude, longitude);

    var intersectionWithGroundPoint = new Cesium.Cartesian3.fromDegrees(coords[0], coords[1], 0);

    var angle = "Max";

    viewer.entities.add({
        name : '45 degrees view point',
        position: groundPoint,
        ellipse : {
            semiMinorAxis : distanceAlongGround,
            semiMajorAxis : distanceAlongGround,
            height: 0.0,
            outlineColor : color,
            outline: true,
            fill: false
        }
    });
}
function getLatLongAtBearingAndDistance(bearing, distance, latitude, longitude){
    distance = distance/1000;
    var R = 6378.1; //Radius of the Earth

    var bearingRadians = Cesium.Math.toRadians(bearing); //Bearing is 90 degrees converted to radians.

    var lat1 = Cesium.Math.toRadians(latitude);
    var lon1 = Cesium.Math.toRadians(longitude);

    var lat2 = Math.asin(Math.sin(lat1) * Math.cos(distance/R) + Math.cos(lat1) * Math.sin(distance/R) * Math.cos(bearingRadians));
    var lon2 = lon1 + Math.atan2(Math.sin(bearingRadians) * Math.sin(distance/R) * Math.cos(lat1), Math.cos(distance/R)-Math.sin(lat1) * Math.sin(lat2));

    lat2 = Cesium.Math.toDegrees(lat2)
    lon2 = Cesium.Math.toDegrees(lon2)
    return [lon2, lat2];
}
*/

function drawSwath(sat, swathWidth, instrumentName, isWeather){
    var clock = viewer.clock;
	var footPrintCollectionArray = [];

    if (swathWidth == null){
        swathWidth = 300000;
    }

    var intervalBetweenFootPrints = 1000.0;
    var numberOfFootPrintsAtAtime = 10;

    var cartesian = sat.position.getValue(clock.currentTime);
    var cartographic = Cesium.Cartographic.fromCartesian(cartesian);

    var longitude = Cesium.Math.toDegrees(cartographic.longitude);
    var latitude = Cesium.Math.toDegrees(cartographic.latitude);
    var height = cartographic.height;

    var satelliteName = sat._id.replace("Satellite/", "");

    var footPrintCollection = drawSwathMain(longitude, latitude, height, swathWidth, instrumentName, satelliteName, clock.currentTime);
    footPrintCollectionArray.push(footPrintCollection);

    var intervalFootprint;

    if (isWeather) {
        printFootPrint_Weather(footPrintCollectionArray, numberOfFootPrintsAtAtime, footPrintCollection, intervalBetweenFootPrints, sat, swathWidth, instrumentName, clock, satelliteName);
    } else {
        printFootPrint_Science(footPrintCollectionArray, numberOfFootPrintsAtAtime, footPrintCollection, intervalBetweenFootPrints, sat, swathWidth, instrumentName, clock, satelliteName);
    }
}

function printFootPrint_Weather(footPrintCollectionArray, numberOfFootPrintsAtAtime, footPrintCollection, intervalBetweenFootPrints, sat, swathWidth, instrumentName, clock, satelliteName){
    var cartesian = sat.position.getValue(clock.currentTime);
    var cartographic = Cesium.Cartographic.fromCartesian(cartesian);

    var longitude = Cesium.Math.toDegrees(cartographic.longitude);
    var latitude = Cesium.Math.toDegrees(cartographic.latitude);
    var height = cartographic.height;

    if (height < 30000000) {
        intervalFootprint_Weather = setInterval(function() {
            if(footPrintCollectionArray.length == numberOfFootPrintsAtAtime){
                var entities = footPrintCollectionArray[0].values;
                footPrintCollectionArray.splice(0,1);

                for(var i = 0; i < entities.length; i++){

                    viewer.entities.remove(entities[i]);
                }
            }

            cartesian = sat.position.getValue(clock.currentTime);
            cartographic = Cesium.Cartographic.fromCartesian(cartesian);

            longitude = Cesium.Math.toDegrees(cartographic.longitude);
            latitude = Cesium.Math.toDegrees(cartographic.latitude);
            height = cartographic.height;

            footPrintCollection = drawSwathMain(longitude, latitude, height, swathWidth, instrumentName, satelliteName, clock.currentTime);
            footPrintCollectionArray.push(footPrintCollection);

        }, intervalBetweenFootPrints);
	} else {
        footPrintCollection = drawSwathMain(longitude, latitude, height, swathWidth, instrumentName, satelliteName, clock.currentTime);
        footPrintCollectionArray.push(footPrintCollection);
	}
}

function printFootPrint_Science(footPrintCollectionArray, numberOfFootPrintsAtAtime, footPrintCollection, intervalBetweenFootPrints, sat, swathWidth, instrumentName, clock, satelliteName){
    intervalFootprint_Science = setInterval(function() {
		if(footPrintCollectionArray.length == numberOfFootPrintsAtAtime){
			var entities = footPrintCollectionArray[0].values;
        	footPrintCollectionArray.splice(0,1);

        	for(var i = 0; i < entities.length; i++){

        		viewer.entities.remove(entities[i]);
        	}
		}

       	var cartesian = sat.position.getValue(clock.currentTime);
        var cartographic = Cesium.Cartographic.fromCartesian(cartesian);

        var longitude = Cesium.Math.toDegrees(cartographic.longitude);
        var latitude = Cesium.Math.toDegrees(cartographic.latitude);
        var height = cartographic.height;

        footPrintCollection = drawSwathMain(longitude, latitude, height, swathWidth, instrumentName, satelliteName, clock.currentTime);
        footPrintCollectionArray.push(footPrintCollection);

	}, intervalBetweenFootPrints);
}

function drawSwathMain(longitude, latitude, height, instrumentFOV, instrumentName, satelliteName, currentTime){
	var footPrintCollection = new Cesium.EntityCollection();

	//drawLineGroundToSatellite(longitude, latitude, height);

	var color = Cesium.Color.ORANGE;
	drawVisibleFootPrint(longitude, latitude, height, color);

    if (height < 30000000){
	    drawInstrumentFootPrintSwathWidth(instrumentFOV, longitude, latitude, height, instrumentName, satelliteName, currentTime);
        //drawInstrumentFootPrintAngle(instrumentFOV, longitude, latitude, height, Cesium.Color.RED.withAlpha(0.3));
    }

	// Draws ellipse over satellites visible footprint
	function drawVisibleFootPrint(longitude, latitude, height, color){
		var satLocation = Cesium.Cartesian3.fromDegrees(longitude, latitude, height);
		var groundPoint = new Cesium.Cartesian3.fromDegrees(longitude, latitude, 0.0);

		var radiusOfEarth = Cesium.Cartesian3.distance(new Cesium.Cartesian3(0,0,0), groundPoint);

		var satToOrignEarth = radiusOfEarth + height;  // point to origin of earth
		var PT = Math.sqrt(Math.pow(satToOrignEarth,2) - Math.pow(radiusOfEarth, 2) );  // point to tangent

		var groundPointToSatPointToTangentAngle = Cesium.Math.toDegrees(Math.asin(radiusOfEarth/satToOrignEarth));
		var groundPointToOriginToTangentAngle  = 90.0 - groundPointToSatPointToTangentAngle;

		var distanceAlongGround = Cesium.Math.TWO_PI * radiusOfEarth *(groundPointToOriginToTangentAngle/360.0);

		var directionInDegrees = 0.0;
		var coords = getLatLongAtBearingAndDistance(directionInDegrees, distanceAlongGround, latitude, longitude);

		var intersectionWithGroundPoint = new Cesium.Cartesian3.fromDegrees(coords[0], coords[1], 0);

		var angle = "Max";

		var entity = viewer.entities.add({
			name : '45 degrees Vview point',
			position: groundPoint,
			ellipse : {
				semiMinorAxis : distanceAlongGround,
				semiMajorAxis : distanceAlongGround,
				height: 0.0,
				outlineColor : color,
				outline: true,
				material: Cesium.Color.ORANGE.withAlpha(0.05),
				fill: true
			}
		});

		footPrintCollection.add(entity);
	}

    function drawInstrumentFootPrintSwathWidth(instrumentFOV, longitude, latitude, height, instrumentName, satelliteName, currentTime){
        var coords1 = getLatLongAtBearingAndDistance(90.0, -instrumentFOV/2, latitude, longitude);
        var coords2 = getLatLongAtBearingAndDistance(90.0, instrumentFOV/2, latitude, longitude);

        var swathLine = viewer.entities.add({
            name : satelliteName + ' ' + instrumentName + ' swath',
            description : 'TIME: ' + currentTime.toString().substring(0,currentTime.toString().lastIndexOf(".")) + ' UTC',
            polyline : {
                positions : Cesium.Cartesian3.fromDegreesArray([coords1[0], coords1[1],
                                                                coords2[0], coords2[1]]),
                width : 30,
                material : new Cesium.PolylineOutlineMaterialProperty({
                    outlineWidth : 2,
                    outlineColor : Cesium.Color.PURPLE,
                    color : Cesium.Color.RED
                })
            }
        });

        footPrintCollection.add(swathLine);
    }

	// draws footprint of satellite based on its angle looking down towards the earth
	function drawInstrumentFootPrintAngle(instrumentFOV, longitude, latitude, height, color){
		instrumentFOV = instrumentFOV/2;
		var satLocation = new Cesium.Cartesian3.fromDegrees(longitude, latitude, height);
		var groundPoint = new Cesium.Cartesian3.fromDegrees(longitude, latitude, 0.0);
		var distanceAlongGround = getDistanceOfIntersectionWithGlobeAtAngleFromHeight(instrumentFOV, height);  // gets the length of the opposite side of a right angle triangle, which runs along the earth, from the pov of the satellite

		drawPointAndLineInFourDirections(distanceAlongGround, longitude, latitude, satLocation);

		var entity = viewer.entities.add({
			position: groundPoint,
			name : instrumentFOV * 2 + ' degrees View point',
			ellipse : {
				semiMinorAxis : distanceAlongGround,
				semiMajorAxis : distanceAlongGround,
				height: 0.0,
				material : color
			}
		});
		footPrintCollection.add(entity);
	}


	// draws footprint of satellite based on its swath width of its instrument along the ground
	function drawInstrumentFootPrintSwathWidth_Shane(instrumentFOV, longitude, latitude, height, color){
		var satLocation = new Cesium.Cartesian3.fromDegrees(longitude, latitude, height);
		var groundPoint = new Cesium.Cartesian3.fromDegrees(longitude, latitude, 0.0);

		var distanceAlongGround = instrumentFOV;

		var redCone = viewer.entities.add({
		    name : 'Red cone',
		    position: Cesium.Cartesian3.fromDegrees(longitude, latitude, height/2),
		    cylinder : {
		        length : height,
		        topRadius : 0.0,
		        bottomRadius : instrumentFOV,
		        material : color,
		        outline: true,
		        outlineColor: Cesium.Color.BLACK
		    }
		});
		footPrintCollection.add(redCone);
	}

/*
	function drawPointAndLineInTwoDirections(distanceAlongGround, longitude, latitude, satLocation){
		var directions = [90, 180];

		for(var i = 0; i < directions.length; i++){
			var coords = getLatLongAtBearingAndDistance(directions[i], distanceAlongGround, latitude, longitude); // gets the coords for a point at a distance in a particular direction, which is the limit to where the satellites view intersects with the earth
			var intersectionWithGroundPoint = new Cesium.Cartesian3.fromDegrees(coords[0], coords[1], 0);

			var entity = viewer.entities.add({
				position: intersectionWithGroundPoint,
				point: {
					pixelSize: 4,
					color : Cesium.Color.RED
				}
			});

			footPrintCollection.add(entity);

			var entity = viewer.entities.add({
				polyline: {
					positions: [
						satLocation,
						intersectionWithGroundPoint
					],
					width: 1,
					material : Cesium.Color.YELLOW,
					followSurface: false
				}
			});
			footPrintCollection.add(entity);
		}
	}
	*/
	function drawPointAndLineInFourDirections(distanceAlongGround, longitude, latitude, satLocation){
		var directions = [0.0, 90, 180, 270];

		for(var i = 0; i < directions.length; i++){
			var coords = getLatLongAtBearingAndDistance(directions[i], distanceAlongGround, latitude, longitude); // gets the coords for a point at a distance in a particular direction, which is the limit to where the satellites view intersects with the earth
			var intersectionWithGroundPoint = new Cesium.Cartesian3.fromDegrees(coords[0], coords[1], 0);

			var entity = viewer.entities.add({
				position: intersectionWithGroundPoint,
				point: {
					pixelSize: 4,
					color : Cesium.Color.RED
				}
			});

			footPrintCollection.add(entity);

			var entity = viewer.entities.add({
				polyline: {
					positions: [
						satLocation,
						intersectionWithGroundPoint
					],
					width: 1,
					material : Cesium.Color.YELLOW,
					followSurface: false
				}
			});
			footPrintCollection.add(entity);
		}
	}

/*
	function drawLineGroundToSatellite(longitude, latitude, height){
		var satLocation = new Cesium.Cartesian3.fromDegrees(longitude, latitude, height);  // position of satellite in sky
		var groundPoint = new Cesium.Cartesian3.fromDegrees(longitude, latitude, 0.0);

		var entity = viewer.entities.add({
			position: satLocation,
			point: {
				pixelSize : 2,
				color : Cesium.Color.RED
			}
		});

		footPrintCollection.add(entity);

		var entity = viewer.entities.add({
			position: groundPoint,
			point: {
				pixelSize : 2,
				color : Cesium.Color.RED
			}
		});

		footPrintCollection.add(entity);

		var entity = viewer.entities.add({
			polyline : {
				positions : [
					satLocation,
					groundPoint
				],
				width : 1,
				material : Cesium.Color.YELLOW
			}
		});

		footPrintCollection.add(entity);
	}
*/
	function getDistanceOfIntersectionWithGlobeAtAngleFromHeight(theta, height){
		return Math.tan(Cesium.Math.toRadians(theta)) * height;
	}


	function getLatLongAtBearingAndDistance(bearing, distance, latitude, longitude){
		distance = distance/1000;
		var R = 6378.1; //Radius of the Earth

		var bearingRadians = Cesium.Math.toRadians(bearing); //Bearing is 90 degrees converted to radians.

		var lat1 = Cesium.Math.toRadians(latitude);
		var lon1 = Cesium.Math.toRadians(longitude);

		var lat2 = Math.asin(Math.sin(lat1) * Math.cos(distance/R) + Math.cos(lat1) * Math.sin(distance/R) * Math.cos(bearingRadians));
		var lon2 = lon1 + Math.atan2(Math.sin(bearingRadians) * Math.sin(distance/R) * Math.cos(lat1), Math.cos(distance/R)-Math.sin(lat1) * Math.sin(lat2));

		lat2 = Cesium.Math.toDegrees(lat2)
		lon2 = Cesium.Math.toDegrees(lon2)
		return [lon2, lat2];
	}

return footPrintCollection;
}
