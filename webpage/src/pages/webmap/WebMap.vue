<template>
  <div>
    <h1>{{title}}</h1>
    <div id='map' class='map'></div>
  </div>
</template>

<script>
import ol from '../../../static/ol/ol'
export default {
  name: 'WebMap',
  data () {
    return {
      title: 'Web Map Page'
    }
  },
  mounted () {
    this.init()
  },
  methods: {
    init () {
      debugger
      const chinaSource = new ol.source.Vector({
        projection: 'EPSG:3857',
        url: '../../../static/geojson/100000_full.json',
        format: new ol.format.GeoJSON()
      })
      const chinaLayer = new ol.layer.Vector({
        source: chinaSource,
        zIndex: 1
      })
      // const mvoeInteraction = new ol.interaction.Pointer({
      //   handleMoveEvent: function (event) {
      //     var box = document.getElementById('box')
      //     var left = event.pointerEvent.clientX
      //     var top = event.pointerEvent.clientY
      //     var lonlat = ol.proj.transform(event.coordinate, 'EPSG:3857', 'EPSG:4326')
      //     box.style.left = (left + 10) + 'px'
      //     box.style.top = (top + 10) + 'px'
      //     box.innerHTML = '经度: ' + lonlat[0] + '<br>' + '纬度: ' + lonlat[1]
      //   },
      //   handleDownEvent: function (event) {
      //     console.log(event)
      //     var feature = map.getFeaturesAtPixel(event.pixel)
      //     if (feature) {
      //       var featureYype = feature[0].getGeometryName()
      //       var featureInfo = feature[0].getProperties()
      //       var box = document.getElementById('box')
      //       box.innerHTML = '类型: ' + featureYype + '<br>名称: ' + featureInfo.name + '<br>级别: ' + featureInfo.level
      //       box.style.display = 'block'
      //     }
      //   }
      // })
      // const singleClickInteraction = new ol.interaction.Select({
      //   condition: ol.events.singleClick
      // })
      const map = new ol.Map({
        target: 'map',
        layers: [chinaLayer],
        view: new ol.View({
          projection: ol.proj.get('EPSG:3857'),
          center: ol.proj.fromLonLat([116.40, 39.90]),
          zoom: 4
        })
      })
      console.log(map)
      // map.addInteraction(mvoeInteraction)
      // map.addInteraction(singleClickInteraction)
    }
  }
}
</script>

<style>
@import '../../assets/css/webmap.less';
</style>
