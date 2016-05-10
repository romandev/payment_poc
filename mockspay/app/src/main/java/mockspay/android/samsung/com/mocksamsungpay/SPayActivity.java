package mockspay.android.samsung.com.mocksamsungpay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class SPayActivity extends AppCompatActivity {

    private static Activity m_activity;

    public static void stopActivity() {
        m_activity.finishAffinity();
    }

    private void setLayout() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        m_activity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spay);

        setLayout();

        if (!getSharedPreferences("dont_show_me", MODE_PRIVATE).getBoolean("value", false)) {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getY() == 0.0)
            finish();
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}