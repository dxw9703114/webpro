
var chinaSource = new ol.source.Vector({
    projection: 'EPSG:3857',
    url: "js/geojson/100000_full.json",
    format: new ol.format.GeoJSON()
});

var chinaLayer = new ol.layer.Vector({
    source: chinaSource,
    zIndex: 1
});

var mvoeInteraction = new ol.interaction.Pointer({
    handleMoveEvent: function (event) {
        var box = document.getElementById("box");
        var left = event.pointerEvent.clientX;
        var top = event.pointerEvent.clientY;
        var lonlat = ol.proj.transform(event.coordinate, 'EPSG:3857', 'EPSG:4326');
        box.style.left = (left + 10) + 'px';
        box.style.top = (top + 10) + 'px';
        box.innerHTML = "经度: " + lonlat[0] + "<br>" + "纬度: " + lonlat[1];
    },
    handleDownEvent: function (event) {
        console.log(event);
        var feature = map.getFeaturesAtPixel(event.pixel);
        if (feature) {
            var featureYype = feature[0].getGeometryName();
            var featureInfo = feature[0].getProperties();
            var box = document.getElementById("box");
            box.innerHTML = "类型: " + featureYype + "<br>名称: " + featureInfo.name + "<br>级别: " + featureInfo.level;
            box.style.display = 'block';
        }
    }
});
var singleClickInteraction = new ol.interaction.Select({
    condition: ol.events.singleClick
});
var map = new ol.Map({
    target: 'map',
    layers: [chinaLayer],
    view: new ol.View({
        projection: ol.proj.get('EPSG:3857'),
        center: ol.proj.fromLonLat([116.40, 39.90]),
        zoom: 4
    })
});
map.addInteraction(mvoeInteraction);
map.addInteraction(singleClickInteraction);

function upload() {
    var file = document.getElementById('file').files[0];
    console.log(file);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/file/json/actions/upload', true);
    xhr.onload = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
            } else {
                console.error(xhr.statusText);
            }
        }
    }
    xhr.onerror = function () {
        console.log(xhr.statusText);
    }
    xhr.upload.onprogress = function(event) {
        var per = Math.round(event.loaded/event.total*100);
        console.log(per);
    }
    var data = new FormData();
    data.append("file", file);
    xhr.send(data);
}

function download() {
    if (filename != null) {
        window.location.href = "/file/json/actions/download?filename=" + filename;
    }
}

function req(method, url, data, success, error, async) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                success(xhr.responseText);
            } else {
                error(xhr.responseText);
            }
        }
    }
    xhr.onerror = function () {
        console.error(xhr.statusText);
    }
    var sendData = null;
    if (!method) {
        method = 'GET';
    }
    if (method.toLowerCase() === 'get') {
        if (data) {
            if (typeof data === 'object' && !Array.isArray(data)) {
                var params = '';
                var keys = Object.keys(data);
                for (var key in keys) {
                    var value = data[key];
                    params += ('&' + key + '=' + value);
                }
                var parIndex = url.lastIndexOf('?');
                if (parIndex === -1) {
                    url += ('?' + params);
                } else {
                    url += params;
                }
            }
        }
    } else if (method.toLowerCase() === 'post') {
        if (data && typeof data === 'object' && !Array.isArray(data)) {
            sendData = new FormData();
            var keys = Object.keys(data);
            for (var key in keys) {
                var value = data[key];
                sendData.append(key, value);
            }
        }
    } else if (method.toLowerCase() === 'put') {
        if (data && typeof data === 'object' && !Array.isArray(data)) {
            sendData = new FormData();
            var keys = Object.keys(data);
            for (var key in keys) {
                var value = data[key];
                sendData.append(key, value);
            }
        }
    } else if (method.toLowerCase() === 'delete') {
        if (data && typeof data === 'object' && !Array.isArray(data)) {
            sendData = new FormData();
            var keys = Object.keys(data);
            for (var key in keys) {
                var value = data[key];
                sendData.append(key, value);
            }
        }
    }
    xhr.open(method, url, async);
    xhr.send(sendData);
}

function testXMLHttpRequest() {
    var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xhr.open("POST", "feature", true);
    xhr.onreadystatechange = function (e) {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
            } else {
                console.log(xhr.statusText);
            }
        }
    }
    xhr.onerror = function (e) {
        console.log(xhr.statusText);
    }
    var data = new FormData();
    data.append('adcode', '100000');
    data.append('name', '中华人民共和国');
    data.append('type', 'Feature');
    data.append('properties', '{adcode:100000,name:"中华人民共和国",childrenNum:34,level:"country"}');
    data.append('childrenNum', '34');
    data.append('level', 'country');
    data.append('geometryType', 'MultiPolygon');
    xhr.send(data);
}
// testXMLHttpRequest();

function testAjax() {
    $.ajax({
        type: 'GET',
        url: 'feature/all',
        dataType: 'json',
        success: function (response) {
            console.log(response);
        },
        error: function (error) {
            console.log(error);
        }
    });
}
// testAjax();

function doPrint() {
    var iframe = document.getElementById('printIframe');
    iframe.contentWindow.print();
}
