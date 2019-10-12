package com.dag4004.nombreschinois;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer[] sound;
    MediaPlayer sOk;
    MediaPlayer sError;
    Boolean bResult = null;
    Random r = new Random();
    int number = 0;

    TextView tvNumber;
    EditText etFrom;
    EditText etTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound = new MediaPlayer[]{
                MediaPlayer.create(this, R.raw.s0),
                MediaPlayer.create(this, R.raw.s1),
                MediaPlayer.create(this, R.raw.s2),
                MediaPlayer.create(this, R.raw.s3),
                MediaPlayer.create(this, R.raw.s4),
                MediaPlayer.create(this, R.raw.s5),
                MediaPlayer.create(this, R.raw.s6),
                MediaPlayer.create(this, R.raw.s7),
                MediaPlayer.create(this, R.raw.s8),
                MediaPlayer.create(this, R.raw.s9),
                MediaPlayer.create(this, R.raw.s10),
        };
        sOk = MediaPlayer.create(this, R.raw.ok);
        sError = MediaPlayer.create(this, R.raw.erreur);

        setContentView(R.layout.activity_main);

        tvNumber = this.findViewById(R.id.tvNumber);
        etFrom = this.findViewById(R.id.etFrom);
        etTo = this.findViewById(R.id.etTo);

        generateNumber();

        BackgroundTask backgroundTask = new BackgroundTask ();
        backgroundTask.execute();
        Button bOne = this.findViewById(R.id.button1);
        bOne.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("1");
            }
        });
        Button bTwo = this.findViewById(R.id.button2);
        bTwo.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("2");
            }
        });
        Button bTree = this.findViewById(R.id.button3);
        bTree.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("3");
            }
        });
        Button bFour = this.findViewById(R.id.button4);
        bFour.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("4");
            }
        });
        Button bFive = this.findViewById(R.id.button5);
        bFive.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("5");
            }
        });
        Button bSix = this.findViewById(R.id.button6);
        bSix.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("6");
            }
        });
        Button bSeven = this.findViewById(R.id.button7);
        bSeven.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("7");
            }
        });
        Button bHeigth = this.findViewById(R.id.button8);
        bHeigth.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("8");
            }
        });
        Button bNine = this.findViewById(R.id.button9);
        bNine.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("9");
            }
        });
        Button bNull = this.findViewById(R.id.button0);
        bNull.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tvNumber.append("0");
            }
        });
        Button bBack = this.findViewById(R.id.buttonBack);
        bBack.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                int l = tvNumber.getText().length();
                if(l > 0) {
                    tvNumber.setText(tvNumber.getText().subSequence(0, l-1));
                }
            }
        });
        Button bOk = this.findViewById(R.id.buttonOK);
        bOk.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                String testNumber = tvNumber.getText().toString();

                if ((testNumber.length() > 0) && (Integer.parseInt(testNumber) == number)) {
                    bResult = true;
                    generateNumber();
                } else {
                    bResult = false;
                }
                tvNumber.setText("");
                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute();
            }
        });
    }

    private void generateNumber() {
        int min = Integer.parseInt(etFrom.getText().toString());
        int max = Integer.parseInt(etTo.getText().toString());

        number = r.nextInt(max - min + 1) + min;
    }


    private class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {


        @Override
        protected Integer doInBackground(Integer... integers) {

            if( bResult != null) {
                if( bResult ) {
                    playSound(sOk);

                } else {
                    playSound(sError);
                }
            }
            if (number <= 10) {
                playSound(sound[number]);
            } else if( number <= 99 ) {
                int dec = number / 10;
                if( dec >= 2 ) {
                    playSound(sound[dec]);
                }
                playSound(sound[10]);
                int num = number % 10;
                if( num > 0) {
                    playSound(sound[num]);
                }
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer aLong) {
            super.onPostExecute(aLong);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        private void playSound(MediaPlayer mp) {
            mp.start();
            try {
                Thread.sleep(mp.getDuration());
                while (mp.isPlaying()) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
