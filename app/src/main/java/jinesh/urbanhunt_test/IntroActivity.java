package jinesh.urbanhunt_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by Jinesh on 24/08/15.
 */
public class IntroActivity extends AppIntro {

    @Override
    public void init(Bundle bundle) {
        addSlide(SampleIntroFragment.newInstance(R.layout.intro));
        addSlide(SampleIntroFragment.newInstance(R.layout.intro2));
        addSlide(SampleIntroFragment.newInstance(R.layout.intro3));
        addSlide(SampleIntroFragment.newInstance(R.layout.intro4));

    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(), "Skip Pressed", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDonePressed() {
        loadMainActivity();

    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
