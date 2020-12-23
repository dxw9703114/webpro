var map = new ol.Map({
    target: 'map',
    layers: [
        new ol.layer.Vector({
            source: new ol.source.Vector({
                projection: 'EPSG:4326',
                url: "js/geojson/china.js",
                format: new ol.format.GeoJSON()
            })
        })],
    view: new ol.View({
        center: ol.proj.fromLonLat([0, 0]),
        zoom: 2
    })
});
