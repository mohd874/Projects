require 'rubygems'
require 'gosu'
require 'player'
require 'ball'

class MyGame < Gosu::Window
	def initialize
		super(600,400,false)
		@player = Player.new(self)
		@balls = 3.times.map{Ball.new(self)}
		@running = true
	end
	
	def update
		if @running
			if button_down? Gosu::Button::KbLeft
				@player.move_left
			end
			if button_down? Gosu::Button::KbRight
				@player.move_right
			end
			if button_down? Gosu::Button::KbUp
				@player.move_up
			end
			if button_down? Gosu::Button::KbDown
				@player.move_down
			end
			if @player.hit_by? @balls
				@running = false
			end
			
			@balls.each {|ball| ball.update}
		else
			#game has stopped
			if button_down? Gosu::Button::KbEscape
				restart_game
			end
		end
	end
	
	def draw
		@player.draw
		@balls.each {|ball| ball.draw}
	end
	
	def restart_game
		@running = true
		@balls.each{|ball| ball.reset!}
	end
end

@game = MyGame.new
@game.show
