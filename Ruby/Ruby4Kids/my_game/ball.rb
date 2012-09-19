class Ball
	def initialize (game)
		@game = game
		@icon = Gosu::Image.new(@game,'./ball.png',true)
		reset!
	end
	
	def update
		@y = @y + 8
		if @y > @game.height
			reset!
		end
	end
	
	def draw
		@icon.draw(@x,@y,2)
	end
	
	def x
		@x
	end
	
	def y
		@y
	end
	
	def width
		@icon.width
	end
	
	def reset!
		@x = rand(@game.width)
		@y = 0 - @icon.height
	end
end
