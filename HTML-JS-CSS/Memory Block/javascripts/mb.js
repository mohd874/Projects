$(document).ready(function(){
	//????			
	var timer;
	
	var waiting = false;
	var tries = 0;
	var numOpenedBlocks = 0;
	var openedBlocks = new Array(2);

	var mode = 'easy';
	
	var size = 4;
	
	var numbers = new Array(size*size);
	
	for(var i=0;i<(size*size);i=i+2){
			numbers[i]=i;
			numbers[i+1]=i;	
	}

	for(var i=0;i<(size*size);i++){
		$('.main').append('<div id='+numbers[i]+' class="block" style="opacity:0"><img class="img" src="./images/'+numbers[i]+'.ico" /></div>');
	}				
	
	$('#tries').html('Tries: '+tries);	
	$('.main').shuffle();
	
	$('.block').click(function(){
		if(!waiting && $(this).attr('class') == 'block' && numOpenedBlocks < 2){
			waiting = true;			
			$blk = $(this);

			openedBlocks[numOpenedBlocks] = $blk;
			numOpenedBlocks++;
			
			$blk.attr('class','opened').fadeTo('slow',1);
				
			if(numOpenedBlocks > 1){
				tries++;
				$('#tries').html('Tries: '+tries);	
							
				var $b1 = openedBlocks[0];
				var $b2 = openedBlocks[1];
				
				if($b1.html() == $b2.html() 
					&& $b1.attr('class') == 'opened' && $b2.attr('class') == 'opened' ){
					
					$b1.wait(1000)
					.animate({opacity:0.5},500)
					.animate({opacity:1},500)
					.animate({opacity:0.5},500)
					.animate({opacity:1},500,function(){$b1.attr({class : 'solved'})});
					
					$b2.wait(1000)
					.animate({opacity:0.5},500)
					.animate({opacity:1},500)
					.animate({opacity:0.5},500)
					.animate({opacity:1},500,function(){
						$b2.attr({class : 'solved'});
						openedBlocks[0] = null;
						openedBlocks[1] = null;
						numOpenedBlocks = 0;
					});
					
				}else{
					$b1.wait(1000).fadeTo('slow',0,function(){
						$(this).attr({class : 'block'});
					});
					$b2.wait(1000).fadeTo('slow',0,function(){
						$(this).attr({class : 'block'});
						openedBlocks[0] = null;
						openedBlocks[1] = null;
						numOpenedBlocks = 0;
					});	
				}			
			}
			waiting = false;
		}
	});
});