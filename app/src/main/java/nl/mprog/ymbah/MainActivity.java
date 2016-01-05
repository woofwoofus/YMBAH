package nl.mprog.ymbah;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAnim();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void playAnim(){
        ImageView Sand = (ImageView) findViewById(R.id.Sand_Object);
//        Animator sandAnimator = AnimatorInflater.loadAnimator(this, R.animator.sand_object_anim);
//        ObjectAnimator sandAnimator = ObjectAnimator.ofFloat(Sand,"rotation", 0,1800)
        float X = randInt(-500, 500);
        float Y = randInt(-600, -100);

        Sand.animate().rotationBy(1800).alpha(0).translationX(X).translationY(Y).scaleX(0).scaleY(0).setDuration(2000);
//        sandAnimator.setDuration(2000);
//        sandAnimator.setTarget(Sand);
//        sandAnimator.start();

        ImageView Sand_Plus = (ImageView) findViewById(R.id.Sand_Plus);
//        Animator sandPlusAnimator = AnimatorInflater.loadAnimator(this,R.animator.sand_plus_anim);
        ObjectAnimator sandPlusAnimator = ObjectAnimator.ofFloat(Sand_Plus,"alpha", 1,0);
        sandPlusAnimator.setDuration(1000);
//        sandPlusAnimator.setTarget(Sand_Plus);
        sandPlusAnimator.start();


//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(sandAnimator,sandPlusAnimator);
//        animatorSet.start();
    }

    public static float randInt(int min, int max){
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        float interim = randomFloat*(Math.abs(min)+Math.abs(max));
        return interim - Math.abs(min);
    }
}
