package com.example.insaniquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class PlaySampleActivity extends Activity implements OnCheckedChangeListener {

	private TextView playsample_tvQuestions;
	private RadioButton playsample_rb1;
	private RadioButton playsample_rb2;
	private RadioButton playsample_rb3;
	private RadioButton playsample_rb4;
	private RadioGroup playsample_rgChoices;
	private Button playsample_btnOK;
	public static int ScoreCount;
	private int scoring;
	private String sc;
	public int x;
	private int Question = 0;
	//public static String filename = "MySharedString";
	//private SharedPreferences someData;
	MediaPlayer mpButton;
	MediaPlayer mpCorrect;
	MediaPlayer mpWrong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playsample);
		
		//someData = getSharedPreferences(filename, MODE_PRIVATE);
		mpButton = MediaPlayer.create(this,R.raw.btn);
		mpCorrect = MediaPlayer.create(this, R.raw.correct);
		mpWrong = MediaPlayer.create(this, R.raw.wrong);
		
		
		final SharedPreferences jdSharedPreferences = getSharedPreferences("JDData", Context.MODE_PRIVATE);
		String score = jdSharedPreferences.getString("score", "0");
		
		scoring = Integer.parseInt(score);
		//scoring = Integer.parseInt(sc);
		
		Toast a = Toast.makeText(PlaySampleActivity.this, score , Toast.LENGTH_SHORT);
		a.show();
		//Log.v("sharedpref test: ", someData.getString("sharedString", "1000"));
		
		final String[][] QuestionsAndAnswers = new String[][]{
				{"What is your name?", "JD", "Luigi","Camille","Sam"},
				{"1. Two people were walking in opposite directions. Both of them walked 6 miles forward then took right and walked 8 miles. How far is each from starting positions?",
					"14 miles and 14 miles","10 miles 10 miles"/*answer*/,"6 miles 6 miles","4 miles 4 miles"},
				{"2. A person has certain number of cows and birds. They have 172 eyes and 344 legs. How many cows and birds does he have?", 
					"0 birds 86 cows"/*answer*/,"12 birds 12 cows" ,"8 birds 16 cows","4 birds 8 cows"},
				{"3. A person has 14 red socks and 14 white socks in a drawer. What is the minimum number of socks that he should take to get a correct pair?", 
					"4 Pairs", "2 Pairs","6 Pairs","3 Pairs"/*answer*/},
				{"4. When a number is multiplied by 13, it becomes greater to 105 by an amount with which it is lesser to 105 by now. What is the number?", 
					"5", "10","15"/*answer*/,"20"},
				{"5. When asked in an exam how much time is left, the teacher answered that the amount of time left is 1/5 of the time already completed. How much time is left?", 
					"5 minutes", "10 minutes"/*answer*/,"15 minutes","20 minutes"},
				{"6. Two people on cycle are traveling at 10 miles/hour in opposite direction. When they are at a distance of 50 miles, a housefly lands on the first cyclist and then flies to the other at a speed of 16 miles/hour. What is the distance covered by fly?", 
					"33.33 miles", "113.33 miles","143.33 miles","133.33 miles"/*answer*/},
				{"7. My successor's father is my father's son. and I don't have any brothers or sons. Who is my successor?", 
					"Nephew", "Daughter"/*answer*/,"Niece","Myself"},
				{"8. A fast train leaves London for Brighton and at the same time a slow train leaves Brighton for London. The fast train leaves at 80 mph and the slow train travels at 35 mph. When they meet, which is farther from London?", 
					"Fast train is 80 miles away from London", "Slow train is 35 miles away from london","Same distance away from London"/*answer*/,"Fast train is 115 miles away from London"},
				{"9. A girl, a boy, and a dog start walking down a road. They start at the same time, from the same point, in the same direction. The boy walks at 5 km/h, the girl at 6 km/h. The dog runs from boy to girl and back again with a constant speed of 10 km/h. The dog does not slow down on the turn. How far does the dog travel in 1 hour?", 
					"5 km", "6 km","21 km","10 km"/*answer*/},
				{"10. A pool has four taps. The first tap takes two days to fill the pool, the second tap three days, the third four days and the last one only 6 hours. How long will it take to fill the pool using all 4 taps at once?", 
					"4 hrs 43 mins and 17 secs"/*answer*/, "5 hrs 43 mins and 17 secs","4 hrs 33 mins and 27 secs","4 hrs 53 mins and 17 secs"},
				{"11. This was a burglary in the Silver City jewelry store last Sunday. Three suspects: Robert, Scott, and Tommy were caught and questioned. Each person said, \"One of the other two stole it. I did not do it.\" Later on the police found out that Tommy was lying and there was only one thief. Who was the thief?", 
					"Scott", "Tommy"/*answer*/,"Robert","All of the above"},
				{"12. James visited an island. There were 2 tribes living on this island. The east tribal people always tell a lie. The west tribal people always tell the truth. James saw a guy passing him. He asked the tour guide to ask that guy where he lives. The tour guide asked the guy and came back with the answer: he lives over west. Did the tour guide tell the truth or tell a lie?", 
					"Not sure", "Lie","Truth"/*answer*/,"Invalid"},
				{"13. I have a horse. Do you know what color it is? Allan said, \"I guess it is not black\". Brian said, \"It is either brown, or gray\". Charlie said \"I know it is brown\". I said, \"At least one of you is right and at least one of you is wrong.\" What is the color of my horse if the color is one of the above?",
					"brown", "gray"/*answer*/,"white","black"},
				{"14. A lady and a gentleman are sister and brother. We do not know who is older. Someone asked them: Who is older? The sister said: I am older. The brother said: I am younger. At least one of them was lying. Who is older?",					
					"brother"/*answer*/, "sister","father","mother"},
				{"15. A six digit number 312132 has two 1's, two 2's and two 3's. This number has a very interesting attribute: 1 digit exists between two 1's, 2 digits exist between two 2's and 3 digits exist between two 3's. Can we add two more 4s to become an eight digit number and still holding the above attributes plus 4 digits exist between two 4s?",
					"No", "Yes"/*answer*/,"Invalid","Infinite"},
				{"17. Suppose 8 monkeys take 8 minutes to eat 8 bananas. (a) How many minutes would it take 3 monkeys to eat 3 bananas? (b) How many monkeys would it take to eat 48 bananas in 48 minutes?", 
					"(a) 3 mins (b)6 monkeys", "(a) 8 mins (b)48 monkeys","(a) 8 mins (b)6 monkeys"/*answer*/,"(a) 3 mins (b)48 monkeys"},
				{"18. A group of friends went to a hotel for Dinner. After having their dinner the Bill Amount was of Rs 2400. So they decided to distribute it into equal amount for each. In the group two friends forget to bring their purses along with them. So later on it has been decided that Rs 100 has to be paid more by the other friends on calculated amount. So total how many friends were there in the Group?", 
					"3", "8"/*answer*/,"6","9"},
				{"End of game. Thanks for playing","End of game. Thanks for playing","End of game. Thanks for playing","End of game. Thanks for playing"/*asnwer*/,"End of game. Thanks for playing"}
		};
		
		// question numbers divisible by only 1 and not 2, 3, or 4 -> answer is rb2
		// question numbers divisible by only 2 and not 3, or 4 except for 1 -> answer is rb 1
		// question numbers divisible by only 3 and not 2, or 4 except for 1 -> answer is 4
		// question numbers divisible by only 4 and not 2, or 3 except for 1 -> answer 3
		
		// divisible only by 1 -> rb2
		// divisible only by 2 -> rb 1
		// divisible by 2 and 4 -> rb 4
		// 
		
		playsample_rgChoices = (RadioGroup) findViewById(R.id.playsample_rgChoices);
		playsample_rgChoices.setOnCheckedChangeListener(this);
		
		setLinksETC();
		
		Question = 1;
		ScoreCount = 0;
		
		playsample_tvQuestions.setText(QuestionsAndAnswers[Question][0]);
		playsample_rb1.setText(QuestionsAndAnswers[Question][1]);
		playsample_rb2.setText(QuestionsAndAnswers[Question][2]);
		playsample_rb3.setText(QuestionsAndAnswers[Question][3]);
		playsample_rb4.setText(QuestionsAndAnswers[Question][4]);
		
		playsample_btnOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(Question == 17){ //QuestionsAndAnswers.length-1
					if(ScoreCount >= scoring){
						ScoreActivity.HighScore = ScoreCount;
						String newScore = String.valueOf(ScoreCount);
						
						SharedPreferences.Editor jdEditor=jdSharedPreferences.edit();
						jdEditor.putString("score", String.valueOf(ScoreCount));
						jdEditor.commit();
						
						Toast m = Toast.makeText(PlaySampleActivity.this, "newscore" , Toast.LENGTH_SHORT);
						m.show();
						
						ToastEnd();
						startActivity(new Intent(PlaySampleActivity.this, HighscoreActivity.class));
						finish();
					}
					else if(ScoreCount < scoring){
						Toast m = Toast.makeText(PlaySampleActivity.this, "old" , Toast.LENGTH_SHORT);
						m.show();
						ToastEnd();
						startActivity(new Intent(PlaySampleActivity.this, MainscreenActivity.class));
						finish();
					}
					
				}//Question length shit if. end
				else{
					if (((Question % 1 == 0) && (Question % 2 != 0) && (Question % 3 != 0) && (Question % 4 != 0)) && (x == 2) ){
						mpCorrect.start();
						ScoreCount++;
						ToastCorrect();
		                Question++;  
					}
					else if (((Question % 2 == 0) && (Question % 1 == 0) && (Question % 3 != 0) && (Question % 4 != 0)) && (x == 1)){
						mpCorrect.start();
						ScoreCount++;
						ToastCorrect();
		                Question++;
					}
					
					else if (((Question % 3 == 0)&&(Question % 2 != 0)&&(Question % 4 != 0)&&(Question % 1 == 0)) || ((Question % 3 == 0)&&(Question % 2 == 0)&&(Question % 4 != 0)&&(Question % 1 == 0)) && (x == 4)){
						mpCorrect.start();
						ScoreCount++;
						ToastCorrect();
		                Question++;
					}
					
					else if ( (((Question % 4 == 0)&&(Question % 2 == 0))||((Question % 4 == 0)&&(Question % 3 == 0)&&(Question % 2 == 0))) && (x == 3)){
						mpCorrect.start();
						ScoreCount++;
						ToastCorrect();
		                Question++;
					}
						
					else if (x==0){
						ToastNA();
		                
					}
					
					else{
						mpWrong.start();
						ToastWrong();
						Question++;
					}
					
					
					
					playsample_rgChoices.clearCheck();
						
					playsample_tvQuestions.setText(QuestionsAndAnswers[Question][0]);
					playsample_rb1.setText(QuestionsAndAnswers[Question][1]);
					playsample_rb2.setText(QuestionsAndAnswers[Question][2]);
					playsample_rb3.setText(QuestionsAndAnswers[Question][3]);
					playsample_rb4.setText(QuestionsAndAnswers[Question][4]);
					
				}//Question length shit if. end
				
				
			}
		});
		
	
	}

	@Override
	public void onBackPressed() {
	    // TODO Auto-generated method stub
	    super.onBackPressed();
	    finish();
	    startActivity(new Intent("com.example.insaniquiz.MainscreenActivity"));
	}
	
	@Override
	public void onCheckedChanged(RadioGroup Choices, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
		case R.id.playsample_rb1:
			mpButton.start();
			x = 1 ;
			break;
		case R.id.playsample_rb2:
			mpButton.start();
			x = 2;
			break;
		case R.id.playsample_rb3:
			mpButton.start();
			x = 3;
			break;
		case R.id.playsample_rb4:
			mpButton.start();
			x = 4;
			break;
		default:
			x = 0;
			break;
		}
	}
	
	private void setLinksETC(){
		playsample_tvQuestions = (TextView) findViewById(R.id.playsample_tvQuestions);
		//playsample_tvQuestions.setTextColor(Color.parseColor("#FFFFFF"));
		playsample_rb1 = (RadioButton) findViewById(R.id.playsample_rb1);
		playsample_rb2 = (RadioButton) findViewById(R.id.playsample_rb2);
		playsample_rb3 = (RadioButton) findViewById(R.id.playsample_rb3);
		playsample_rb4 = (RadioButton) findViewById(R.id.playsample_rb4);
		playsample_btnOK = (Button) findViewById(R.id.playsample_btnOK);
	}
	private void ToastWrong(){
		Toast m = Toast.makeText(PlaySampleActivity.this, "Wrong" , Toast.LENGTH_SHORT);
		m.setGravity(Gravity.CENTER, 0, 100);
		m.show();
	}
	private void ToastNA(){
		Toast m = Toast.makeText(PlaySampleActivity.this, "Please select a choice" , Toast.LENGTH_SHORT);
		m.setGravity(Gravity.CENTER, 0, 100);
		m.show();
	}
	private void ToastCorrect(){
		Toast m = Toast.makeText(PlaySampleActivity.this, String.valueOf(ScoreCount) , Toast.LENGTH_SHORT);
		m.setGravity(Gravity.CENTER, 0, 100);
		m.show();
	}
	private void ToastEnd(){
		Toast m = Toast.makeText(PlaySampleActivity.this, "End" , Toast.LENGTH_SHORT);
		m.setGravity(Gravity.CENTER, 0, 100);
		m.show();
	}
}

