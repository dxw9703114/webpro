
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
    xhr.onload = function (e) {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
            } else {
                console.error(xhr.statusText);
            }
        }
    }
    xhr.onerror = function (e) {
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

function testSaveJsonFile() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "json", true);
    xhr.onload = function (e) {
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
    data.append('type', 'FeatureCollection');
    data.append('name', '100000.json');
    data.append('url', 'D:/temp/100000.json');
    xhr.send(data);
}
// testSaveJsonFile();
