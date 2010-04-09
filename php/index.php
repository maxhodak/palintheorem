<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Infinite Palin Theorem</title>
	<style>
		body {background:#fff;font-family:"lucida grande",tahoma,verdana,arial,sans-serif;}
		h1 {font-size:26px;}
		h3 {font-size:18px;}
		h1 {font-size:12px;font-weight:bold;}
		p {line-height:16px;font-size:11px;}
		a {color:#3b5998;text-decoration:none;}
		a:hover {text-decoration:underline;}
	</style>
</head>

<body>
<center>
	<div style="width:900px;position:relative;height:640px;">
		<div style="font-size:36px;text-align:left;">Infinite Palin Theorem</div>
		<div style="padding-top:20px;font-size:26px;text-align:left;">There exists <a href="http://en.wikipedia.org/wiki/Infinite_monkey_theorem">a thought experiment</a>, which says that if one gives enough monkeys enough time (namely, infinite of each), one will eventually end up with the complete works of Shakespeare, among other things.  I'm not sure if that generalizes for Sarah Palins.</div>
	<?php /*	<div style="padding-top:20px;font-size:18px;text-align:left;">One of these quotes is real, and one is randomly generated.  Can you tell which is real?</div> 

		<div style="padding-top:40px;font-size:18px;text-align:left;width:420px;position:absolute;left:0;">
			<strong>Quote A:</strong><br />
			<?php
			$out = "";
			$words = "";
			$file = fopen("palinWords.txt", "r");
			while(!feof($file)) {
			  $words .= fgets($file, 4096);
			}
			fclose($file);
			$words = split("[ ]+",$words);
			$foo = rand(0,count($words)-80);
			for($i=0;$i<80;$i++)
			{
				$out .= stripslashes($words[$foo+$i])." ";
			}
			
			$place = rand(0,100);
			if($place > 50)
			{
				echo $out;
			} else {
				require "./thrift/palin/php/PhpClient.php";
			}
			?>
		</div>
		<div style="padding-top:40px;font-size:18px;text-align:left;width:420px;position:absolute;right:20px;">
			<strong>Quote B:</strong><br />
			<?php
			if($place > 50)
			{
				require "./thrift/palin/php/PhpClient.php";
			} else {
				echo $out;
			}
		?>
		</div>
		
	*/ ?>
		<div style="padding-top:40px;font-size:18px;text-align:left;width:450px;position:absolute;left:200px;line-height:26px;">
			<strong>The wisdom:</strong><br />
			<?php /*
			So, you guys actually managed to hang the server.  That's a pretty impressive amount of traffic.  I'll enable the quote generator again soon once I have that figured out.
			*/
				 require "./thrift/palin/php/PhpClient.php";
			?>
		</div>
		<div style="font-size:12px;text-align:left;width:900px;position:absolute;bottom:75px;left:0;vertical-align:bottom;" id="footer">
			The random quote is generated (<a href="#" class="toggleDescription">full description</a>) from a small part of the Couric interview, her RNC speech, and her debate with Biden.  This is only mildly interesting right now because >85% of the source text was prewritten by strategists, and isn't <a href="http://www.youtube.com/watch?v=cP12aNzocSc">spontaneous Palin</a>.  I'll enter the rest of the Couric interviews and such soon to make it more interesting.
		</div>
		<div style="font-size:12px;text-align:left;width:900px;position:absolute;bottom:75px;left:0;display:none;" id="longDescription">
			The "random quote" is generated from a 3-word Markov Model trained on <a href="http://lab.ics-af.net/infinitepalintheorem/palinWords.txt">this text.</a>  That means, the model uses the previous 3 words to predict the next one; it then moves the window forward, and repeats.  This leads to usually coherent individual sentences, but longer clauses that are complete madness.  We believe that this makes the Markov chain a surprisingly faithful abstraction of the original process being modeled in this case.  Some otherwise short passages, such as Couric interview one-line segments, are duplicated multiple times in the training text to weight them more equally with the longer, strategist-written monologues.  The Markov model is implemented in Java and stored using memcached; the web service is in PHP; they communicate using <a href="http://developers.facebook.com/thrift/">Facebook's Thrift</a>.  <a href="#" class="toggleDescription">(Go back >)</a>
		</div>
		<div style="font-size:12px;text-align:right;width:900px;position:absolute;bottom:0;left:0;">
			<div style="float:right;">
				<script type="text/javascript">
				digg_url = 'http://lab.ics-af.net/infinitepalintheorem/index.php';
				digg_title = 'Great Random Palin Quotes - Infinite Palin Theorem';
				digg_bodytext = 'Randomly generates a new Sarah Palin quote from her past speeches.  Terrifying.';
				digg_media = 'news';
				digg_topic = '2008_us_elections';
				</script>
				<script src="http://digg.com/tools/diggthis.js" type="text/javascript"></script>
			</div>
		</div>		
	</div>
</center>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"> </script>
<script language="javascript">
$(document).ready(function(){
	$('.toggleDescription').click(function(){
		$('#longDescription').slideToggle("slow");
		$('#footer').slideToggle("slow");
	})
});
</script>
</body>
</html>