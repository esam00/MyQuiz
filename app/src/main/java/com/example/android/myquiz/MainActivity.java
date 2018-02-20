package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int points=0 ;
    int percent=50 ;
    boolean allAnswered ; // to check if all questions have been answered

    boolean blue , yellow , red , natural , confused , curious,mountain , desert , lava ;
    boolean faces , dog , zero , white, cream , smoke , fire , sun , sphere ,cells , circles , brown ,light, electric, hair ;

    String quotation;
    String lastShowing;

    /**
     saving and restoring data when rotation
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("save1",points);
        outState.putString("sav2",lastShowing);
        outState.putInt("sav3",percent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        points = savedInstanceState.getInt("sav1");
        lastShowing = savedInstanceState.getString("sav2");
        percent = savedInstanceState.getInt("sav3");

        displayPercent(percent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * method to show the result when Result button is clicked
     */
    public void showQuizResult (View view) {
        points = countPoints(); // giving every answer a number:  1 ,2 or 3 then get back the count of all numbers

        if (!allAnswered){
            Toast.makeText(this, "please answer all questions!", Toast.LENGTH_SHORT).show();
            return;
        }

            EditText playerName = findViewById(R.id.name);
            String name = playerName.getText().toString();
            String empty ="";
            if (name.equals(empty)){
                Toast.makeText(this, "please Enter your name!", Toast.LENGTH_SHORT).show();
                return;
            }

            quotation = getQuotation(points);

            // the string variable that receives the last result to be send to the result  activity
            lastShowing = lastQuot(name, quotation);

            // send all information to the result activity
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("result", lastShowing);
            startActivity(intent);
    }

    /**
     * method that counts the total number of points
     */
    public int countPoints (){
        int count =0;

        RadioButton Q1R1 = findViewById(R.id.Q1R1);
        blue = Q1R1.isChecked();
        RadioButton Q1R2 = findViewById(R.id.Q1R2);
        yellow = Q1R2.isChecked();
        RadioButton Q1R3 = findViewById(R.id.Q1R3);
        red = Q1R3.isChecked();

        RadioButton Q2R1 = findViewById(R.id.Q2R1);
        natural = Q2R1.isChecked();
        RadioButton Q2R2 = findViewById(R.id.Q2R2);
        confused = Q2R2.isChecked();
        RadioButton Q2R3 = findViewById(R.id.Q2R3);
        curious= Q2R3.isChecked();

        RadioButton Q3R1 = findViewById(R.id.Q3R1);
        mountain = Q3R1.isChecked();
        RadioButton Q3R2 = findViewById(R.id.Q3R2);
        desert = Q3R2.isChecked();
        RadioButton Q3R3 = findViewById(R.id.Q3R3);
        lava = Q3R3.isChecked();

        RadioButton Q4R1 = findViewById(R.id.Q4R1);
        faces = Q4R1.isChecked();
        RadioButton Q4R2 = findViewById(R.id.Q4R2);
        dog = Q4R2.isChecked();
        RadioButton Q4R3 = findViewById(R.id.Q4R3);
        zero = Q4R3.isChecked();

        RadioButton Q5R1 = findViewById(R.id.Q5R1);
        white = Q5R1.isChecked();
        RadioButton Q5R2 = findViewById(R.id.Q5R2);
        cream = Q5R2.isChecked();
        RadioButton Q5R3 = findViewById(R.id.Q5R3);
        smoke = Q5R3.isChecked();

        RadioButton Q6R1 = findViewById(R.id.Q6R1);
        fire = Q6R1.isChecked();
        RadioButton Q6R2 = findViewById(R.id.Q6R2);
        sun = Q6R2.isChecked();
        RadioButton Q6R3 = findViewById(R.id.Q6R3);
        sphere = Q6R3.isChecked();

        RadioButton Q7R1 = findViewById(R.id.Q7R1);
        light = Q7R1.isChecked();
        RadioButton Q7R2 = findViewById(R.id.Q7R2);
        electric = Q7R2.isChecked();
        RadioButton Q7R3 = findViewById(R.id.Q7R3);
        hair = Q7R3.isChecked();

        CheckBox Q8C1 = findViewById(R.id.Q8C1);
        cells= Q8C1.isChecked();
        CheckBox Q8C2 = findViewById(R.id.Q8C2);
        circles = Q8C2.isChecked();
        CheckBox Q8C3 = findViewById(R.id.Q8C3);
        brown = Q8C3.isChecked();

        count += getPoints(blue,yellow,red);
        count += getPoints(natural,confused,curious);
        count += getPoints(mountain,desert,lava);
        count += getPoints(faces,dog,zero);
        count += getPoints(white,cream,smoke);
        count += getPoints(fire,sun,sphere);
        count += getPoints(light,electric,hair);
        count += getPointsForCheckBox(cells,circles,brown);
        count += getPointsForPercent(percent);

        return count;
    }

    /**
     * method to give a number for each solution (Radio button questions )
     */
    public int getPoints (boolean x , boolean y , boolean z){
        int degree=0 ;

        if (!x && !y&& !z) { // user skipped that question !
            allAnswered = false;
        }
                if (x){
                    degree=3;
                    allAnswered = true;
                } else if (y){
                    degree =2 ;
                    allAnswered = true;
                }
                else  if(z) {
                    degree = 1;
                    allAnswered = true;
                }
                 return degree;
    }

    /**
     * method to give a number for each checkbox question 8
     */
    public int getPointsForCheckBox (boolean x , boolean y , boolean z){
        int degree=0 ;

        if (!x && !y && !z) { // user skipped that question !
            allAnswered = false;
        }
        if (x){
            degree+=1;
            allAnswered = true;
        }
        if (y){
            degree +=1 ;
            allAnswered = true;
        }

        if(z) {
            degree += 1;
            allAnswered = true;
        }
        return degree;
    }

    /**
     * method to give a number to specific range question 9
     */
    public int getPointsForPercent (int percent){
        int degree ;
        if(percent <30){
            degree=1;
        }else if ( percent>=30 && percent <65){
            degree =2 ;
        }else {
            degree =3 ;
        }
        return degree;
    }

    /**
     * method to give a quotation depending on the given number of points
     */
    private String getQuotation (int points){
        String quot;
            if(points<16){
            quot=getString(R.string.quotation1);
        }
        else if (points>=16 && points<20){
            quot=getString(R.string.quotation2);
        }
        else {
            quot = getString(R.string.quotation3);
        }

         return quot;
    }

    /**
     * method to summary the last output to the user
     */
    private String lastQuot (String name , String quot ){
        String text = "Hi "+name+" ^_^" ;
        text+="\n"+quot;
        return text;
    }

    /**
     * method To Add 10 %
     */
    public void addPercent (View view){
        if (percent==100){
            Toast.makeText(this ,"maximum percent is 100%",Toast.LENGTH_SHORT).show();
            return;
        }
        percent+=10;
        displayPercent(percent);
    }

    /**
     * method To sub 10 %
     */
    public void subPercent (View view){
        if (percent==0){
            Toast.makeText(this ,"minimum percent is 0%",Toast.LENGTH_SHORT).show();
            return;
        }
        percent-=10;
        displayPercent(percent);
    }

    /**
     * method to display percent
     */
    public void displayPercent (int percent){
        TextView Result = findViewById(R.id.percent);
        Result.setText(String.valueOf(percent));
    }
}
