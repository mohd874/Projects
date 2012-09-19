require 'gosu'

class Player
	def initialize (game)
		@game = game
		@icon = Gosu::Image.new(@game,'./player.png',true)
		@x = 150
		@y = 330
		
		@left = 0
		@up   = 1
		@right= 2
		@down = 3
	end
	
	def move_left
		if @x <= 0
			@x = 0
		else
			@x = @x - 10
		end
	end
	
	def move_right
		if @x >= (@game.width - @icon.width)
			@x = (@game.width - @icon.width)
		else
			@x = @x + 10
		end
	end
	
	def move_up
		if @y <= 0
			@y = 0
		else
			@y = @y - 10
		end
	end
	
	def move_down
		if @y >= (@game.height - @icon.height)
			@y = (@game.height - @icon.height)
		else
			@y = @y + 10
		end
	end
	
	def hit_by? (balls)
		balls.any? {|ball| Gosu.distance(@x,@y,ball.x,ball.y) < ball.width}
	end
	
	def draw
		@icon.draw(@x,@y,1)
	end
end	
