package mockspay.android.samsung.com.mocksamsungpay;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        findViewById(R.id.samsung_pay_button).setOnClickListener(this);
        findViewById(R.id.google_wallet_button).setOnClickListener(this);
        findViewById(R.id.paypal_button).setOnClickListener(this);

        Intent intent = new Intent(this, SPayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int)e.getX(), (int)e.getY()))
            SPayActivity.stopActivity();

        return super.dispatchTouchEvent(e);
    }

    @Override
    public void onClick(View v) {
        String uri = "http://www.google.com";
        if (v.getId() == R.id.samsung_pay_button)
            uri = "http://www.samsung.com/sec/samsung-pay";
        else if (v.getId() == R.id.google_wallet_button)
            uri = "http://wallet.google.com";
        else if (v.getId() == R.id.paypal_button)
            uri = "http://www.paypal.com";

        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
        intent.setPackage("com.sec.android.app.sbrowser");
        startActivity(intent);
        SPayActivity.stopActivity();
    }
}
