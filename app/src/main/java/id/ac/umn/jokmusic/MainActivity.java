package id.ac.umn.jokmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView playButton, imageView;
    Button about;
    ScrollView scrollLyric;
    TextView lyric;
    void setLyric(){
        lyric.setText("Oh, begini rasanya kehilangan dirimu kekasih\n" +
                "\n" +
                "Tak pernah kubayangkan sakitnya akan seperti ini\n" +
                "\n" +
                "Kau telah pergi dari hidupku\n" +
                "\n" +
                "Oh, mengapakah\n" +
                "\n" +
                "Kau tinggalkan aku seperti ini\n" +
                "\n" +
                "Saat aku masih berharap\n" +
                "\n" +
                "Cinta ini masih bertahan untuk kita?\n" +
                "\n" +
                "Oh, mengapakah\n" +
                "\n" +
                "Kau membawa semua kenangan indah\n" +
                "\n" +
                "bersama kita dulu\n" +
                "\n" +
                "Kini berakhir untuk selamanya\n" +
                "\n" +
                "\n" +
                "\n" +
                "Oh, begini rasanya \n" +
                "\n" +
                "Kehilangan dirimu kekasih\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Tak pernah kubayangkan\n" +
                "\n" +
                "Sakitnya akan seperti ini\n" +
                "\n" +
                "\n" +
                "\n" +
                "Kau telah pergi dari sisiku\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Kita tak mungkin lagi untuk bersama \n" +
                "\n" +
                "(tak mungkin bersama)\n" +
                "\n" +
                "Kenyataan ini telah memisahkan kita \n" +
                "\n" +
                "(tlah memisahkan kita)\n" +
                "\n" +
                "Biarkan cinta ini\n" +
                "\n" +
                "Jadi kenangan indah untukku\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Oh, mengapakah\n" +
                "\n" +
                "Kau tinggalkan aku seperti ini\n" +
                "\n" +
                "Saat aku masih berharap\n" +
                "\n" +
                "Cinta ini masih bertahan untuk selamanya\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Oh, mengapakah\n" +
                "\n" +
                "Kau membawa semua kenangan indah\n" +
                "\n" +
                "bersama kita dulu\n" +
                "\n" +
                "Kini berakhir,\n" +
                "\n" +
                "ooooh, harus berakhir,\n" +
                "\n" +

                "ooooh uuuuu\n" +
                "\n" +
                "kini berakhir untuk selamanya\n" +
                "\n" +
                "Oooh...");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        imageView = findViewById((R.id.imageView));
        about = findViewById(R.id.about);
        scrollLyric = findViewById(R.id.scrollLyric);
        lyric = findViewById(R.id.lyric);
        int totalHeight = scrollLyric.getChildAt(0).getHeight();
        final ObjectAnimator scrollAnim = ObjectAnimator.ofFloat(lyric, "translationY", -4200f);
        final ObjectAnimator scrollAnim2 = ObjectAnimator.ofFloat(lyric, "translationY", 0f);
        scrollAnim.setDuration(218000);
        scrollAnim.setStartDelay(45000);
//        scrollAnim.setDuration(218000);
//        scrollAnim.setStartDelay(45000);

        final ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, View.ROTATION, 0f, 360f).setDuration(8000);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setInterpolator(new LinearInterpolator());
        mediaPlayer = MediaPlayer.create(this, R.raw.takmungkinbersama);
        mediaPlayer.setLooping(false);
        lyric = findViewById(R.id.lyric);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                anim.end();
                scrollAnim.end();
                //setLyric();
                scrollAnim2.setStartDelay(0);
                scrollAnim2.end();
                //scrollAnim.pause();
                playButton.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
            }
        });

        setLyric();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!anim.isStarted()) {
                    mediaPlayer.start();
                    anim.start();
                    setLyric();
                    scrollAnim.setStartDelay(45000);
                    scrollAnim.start();
                    scrollLyric.fullScroll(ScrollView.FOCUS_UP);
                    playButton.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                }
                else if(anim.isPaused()){
                    anim.resume();
                    mediaPlayer.start();
                    //scrollAnim.setStartDelay(0);
                    scrollAnim.resume();
                    scrollLyric.fullScroll(ScrollView.FOCUS_UP);
                    playButton.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                }else{
                    mediaPlayer.pause();
                    anim.pause();

                    scrollAnim.pause();
                    playButton.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                }
            }
        });
    }

}
