<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Dojo Ajax test by dhani in php</title>
<head> 
<!-- dojo starts---->
<script type="text/javascript">function ajaxFunction()
 {  var xmlHttp;
  try
    {    // Firefox, Opera 8.0+, Safari    xmlHttp=new XMLHttpRequest();    }
  catch (e)
      {    // Internet Explorer    try
        {      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      }
    catch (e)
        {      try
         {        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        }
      catch (e)
        {        alert("Your browser does not support AJAX!");       
		 return false;     
		    }     
		}    
	}  
 }
	</script>

<script type="text/javascript">
	var djConfig = {isDebug: true, debugAtAllCosts: false};
</script>			


    <script type="text/javascript" src="dojo/dojo.js"></script>
    <script type="text/javascript">
        dojo.require("dojo.event.*");       // sophisticated AOP event handling
        dojo.require("dojo.io.*");          // for Ajax requests
        dojo.require("dojo.storage.*");     // a persistent local data cache
        dojo.require("dojo.json");          // serialization to JSON
        dojo.require("dojo.dnd.*");         // drag-and-drop
        dojo.require("dojo.lfx.*");         // animations and eye candy
        dojo.require("dojo.widget.Editor2");// stable, portable HTML WYSIWYG
	 </script>
<!-- dojo  ---->

<script type="text/javascript" src="dojo/dojo.js"></script>
<script type="text/javascript">
	dojo.require("dojo.widget.*");
</script>

<script>
	function load_app(id){
		alert('Icon '+id+' was clicked');
	}
</script>

<script type="text/javascript">
		var djConfig={
			isDebug: true,
			debugAtAllCosts:true,
			// http://localhost/dojo/
			googleMapKey:"ABQIAAAAjpykZ8mLzBIiymPn96gLgRQkyPhR_ofk78j0FHWQ604rEd25XhR6rysuznEueoNw7xWWWoHh2kgx_Q"
		};
	</script>
	<script type="text/javascript" src="dojo/dojo.js"></script>
	<script type="text/javascript">
		dojo.require("dojo.widget.GoogleMap");
	</script>
	<script>dojo.hostenv.writeIncludes();</script>
	<style type="text/css">
		.map{
			width:100%;
			height:480px;
			border:1px solid black;
		}
		#pos{
			position:absolute;
			bottom:0;
			left:0;
			width:200px;
			height:200px;
		}
		#mapTest2{
			width:100%;
			height:100%;
			border:1px solid black;
		}
	</style>

        </head>
        <body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="273" height="92" align="left" valign="top" bgcolor="#EDD63B"><p><img src="images/setsat_01_r1_c1.jpg" width="273" height="92" align="top" /></td>
            <td width="36%" height="92" bgcolor="#EDD63B">&nbsp;</td>
            <td width="39%" height="92" align="right" valign="middle" bgcolor="#EDD63B"><img src="images/setsat_01_r1_c3.jpg" width="429" height="92" /></td>
          </tr>
          <tr>
            <td colspan="3">


iiugdisbgj</td>
          </tr>
          <tr>
            <td colspan="3">
	

	
	
	<div dojoType="googlemap" id="mapTest" class="map" datasrc="mapData"></div>
	<table id="mapData">
		<thead><tr><th>Lat</th><th>Long</th><th>Icon</th><th>Description</th></tr></thead>
		<tbody>
			<tr><td>37.4419</td><td>-122.14193</td><td></td><td><div>This is the first place on the map.</div></td></tr>
			<tr><td>37.42189</td><td>-122.084692</td><td></td><td><div>This is the second place on the map.</div></td></tr>
			<tr><td>37.3318</td><td>-122.029009</td><td></td><td><div>This is the third place on the map.</div></td></tr>
			<tr><td>37.832912</td><td>-122.284055</td><td></td><td><div>This is the fourth place on the map.</div></td></tr>
			<tr><td>37.688643</td><td>-122.0703753</td><td></td><td><div>This is the fifth place on the map.</div></td></tr>
			<tr><td>37.413775</td><td>-122.060369</td><td></td><td><div>This is the sixth place on the map.</div></td></tr>
		</tbody>
	</table>
	<h2>More tests: the Google Geocoder!</h2>
	<input type="button" value="Plot the Dojo Foundation Offices" onclick="dojo.widget.byId('mapTest2').plotAddress('1000 Elwell Ct., Palo Alto, CA 94303');return false;" />
	<div style="position:relative;height:240px;">
		<div id="pos"><div dojoType="googlemap" id="mapTest2" controls="smallmap"></div></div>
	</div>
</td>
          </tr>
          <tr>
            <td colspan="3">dfdf</td>
          </tr>
          <tr>
            <td colspan="3">
 fdf
  </td>
          </tr>
        </table>
		<h4>&nbsp;</h4>
                <?
 echo "how are you";
?>



</body>
</html>
