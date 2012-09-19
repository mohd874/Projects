/*!
 * 
 *   melonJS
 *   http://www.melonjs.org
 *		
 *   Step by step game creation tutorial
 *
 **/

// game resources
var g_resources= [{
	name:"area01_level_tiles",
	type:"image",
	src:"data/area01_tileset/area01_level_tiles.png"
},{
	name:"area01",
	type:"tmx",
	src:"data/area03.tmx"
},{
	name:"area02",
	type:"tmx",
	src:"data/area02.tmx"
},{
	name:"gripe_run_right",
	type:"image",
	src:"data/sprite/gripe_run_right.png"
},{
	name:"area01_bkg0",
	type:"image",
	src:"data/area01_parallax/area01_bkg0.png"
},{
	name:"area01_bkg1",
	type:"image",
	src:"data/area01_parallax/area01_bkg1.png"
},{
	name:"spinning_coin_gold",
	type:"image",
	src:"data/sprite/spinning_coin_gold.png"
},{
	name:"wheelie_right",
	type:"image",
	src:"data/sprite/wheelie_right.png"
},{
    name:"32x32_font",
    type:"image",
    src:"data/sprite/32x32_font.png"
}, 
// audio resources
{
    name: "cling",
    type: "audio",
    src: "data/audio/",
    channel: 2
}, {
    name: "stomp",
    type: "audio",
    src: "data/audio/",
    channel: 1
}, {
    name: "jump",
    type: "audio",
    src: "data/audio/",
    channel: 1
}, {
    name: "DST-InertExponent",
    type: "audio",
    src: "data/audio/",
    channel: 1
}];


var jsApp	= 
{	
	/* ---
	
		Initialize the jsApp
		
		---			*/
	onload: function()
	{
		
		// init the video
		if (!me.video.init('jsapp', 640, 480, false, 1.0))
		{
			alert("Sorry but your browser does not support html 5 canvas.");
			return;
		}
				
		// initialize the "audio"
		me.audio.init("mp3,ogg");
		
		// set all resources to be loaded
		me.loader.onload = this.loaded.bind(this);
		
		// set all resources to be loaded
		me.loader.preload(g_resources);

		// load everything & display a loading screen
		me.state.change(me.state.LOADING);
	},
	
	
	/* ---
	
		callback when everything is loaded
		
		---										*/
	loaded: function ()
	{
		// set the "Play/Ingame" Screen Object
		me.state.set(me.state.PLAY, new PlayScreen());
      
		//add the player to the EntityPool
		me.entityPool.add('mainPlayer', PlayerEntity)
		me.entityPool.add('CoinEntity', CoinEntity)
		me.entityPool.add('EnemyEntity', EnemyEntity)
		
		//enable keyboard
		me.input.bindKey(me.input.KEY.LEFT, 'left')
		me.input.bindKey(me.input.KEY.RIGHT, 'right')
		me.input.bindKey(me.input.KEY.X, 'jump', true)
		
		// start the game 
		me.state.change(me.state.PLAY);
		
	}

}; // jsApp

/* the in game stuff*/
var PlayScreen = me.ScreenObject.extend(
{

	init: function(){
		this.parent(true)
	},
	
   onResetEvent: function()
	{	
      // stuff to reset on state change
	  //load a level
	  me.levelDirector.loadLevel("area01");
	  
	  me.audio.playTrack("DST-InertExponent");
	  
	  me.game.addHUD(0, 430, 640, 60)
	  
	  me.game.HUD.addItem("score", new ScoreObject(640, 10))
	  
	  me.game.sort()
	},
	
	
	/* ---
	
		 action to perform when game is finished (state change)
		
		---	*/
	onDestroyEvent: function()	{
		me.game.diableHUD()
		
		me.audio.stopTrack();
	}

});


//bootstrap :)
window.onReady(function() 
{
	jsApp.onload();
});
