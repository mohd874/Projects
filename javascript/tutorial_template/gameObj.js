/*------------------- 
a player entity
-------------------------------- */
 var PlayerEntity = me.ObjectEntity.extend({
	//constructor
	init: function (x,y,setting){
		//call the constructor
		this.parent(x,y,setting);
		
		//set the walking and jump speed
		this.setVelocity(4,16);
		
		//set collosion rectangle
		this.updateColRect(8, 48, -1, 0);
		
		// set the display to follow our position on both axis
		me.game.viewport.follow(this.pos, me.game.viewport.AXIS.BOTH);
		
	},
	
	
	//update player pos
	update: function(){
		if(me.input.isKeyPressed('left')){
			this.doWalk(true)
		}else if(me.input.isKeyPressed('right')){
			this.doWalk(false)
		}else if(me.input.isKeyPressed('jump')){
			if(this.doJump()){
				me.audio.play("jump")
			}
		}else{
			this.vel.x = 0;
		}
		
		if(me.input.isKeyPressed('jump')){
			this.doJump()
		}
		//check & update player movement
		this.updateMovement();
		
		var res = me.game.collide(this)
		
		if(res){
			if(res.obj.type == me.game.ENEMY_OBJECT){
				if((res.y > 0) && !this.jumping){
					me.audio.play("stomp")
					this.forceJump();
				}else{
					this.flicker(45);
				}
			}
		}
		//update animation if necessary
		if(this.vel.x != 0 || this.vel.y != 0){
			//update object animation
			this.parent(this)
			return true;
		}
		return false;
	}
 
 })
 
 var CoinEntity = me.CollectableEntity.extend({
	// extending the init function is not mandatory
    // unless you need to add some extra initialization
    init: function(x,y,setting){
		
		this.parent(x,y,setting)
		
	},	
	
	// this function is called by the engine, when
    // an object is destroyed (here collected)
    onDestroyEvent: function(){
		me.audio.play("cling")
		me.game.HUD.updateItemValue("score", 250);
	}
 })
 
 var EnemyEntity = me.ObjectEntity.extend({
	init: function(x,y,setting) {
		setting.image = "wheelie_right";
		setting.spritewidth = 64;
		
		this.parent(x,y,setting);
		
		this.startX = x;
		this.endX = x + setting.width - setting.spritewidth;
		
		this.pos.x = x + setting.width - setting.spritewidth;
		this.walkLeft = true;
		
		this.setVelocity(4,6);
		
		this.collidable = true;
		
		this.type = me.game.ENEMY_OBJECT;
		
		this.updateColRect(4,56,8,56);
	},
	
	onCollosion: function(res, obj){
		if (this.alive && (res.y > 0) && obj.falling) {
			this.flicker(45);
		}
	},
	
	update: function(){
		if(!this.visible) return false;
		
		if(this.alive){
			if(this.walkLeft && this.pos.x <= this.startX){
				this.walkLeft = false;
			}else if (!this.walkLeft && this.pos.x >= this.endX){
				this.walkLeft = true;
			}
			this.doWalk(this.walkLeft);
		}else{
			this.vel.x = 0;
		}
		
		this.updateMovement();
		
		if(this.vel.x!=0 || this.vel.y!=0){
			this.parent();
			return true;
		}
		return false;
	}
})
 
var ScoreObject = me.HUD_Item.extend({
	init: function(x,y){
		this.parent(x,y)
		this.font = new me.BitmapFont("32x32_font",32)
	},
	
	draw: function(context, x, y){
		this.font.draw(context, this.value, this.pos.x + x, this.pos.y + y)
	}
})
