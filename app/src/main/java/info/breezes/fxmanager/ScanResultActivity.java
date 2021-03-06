package info.breezes.fxmanager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import info.breezes.fxapi.countly.CountlyActivity;
import info.breezes.toolkit.ui.LayoutViewHelper;
import info.breezes.toolkit.ui.Toast;
import info.breezes.toolkit.ui.annotation.LayoutView;


public class ScanResultActivity extends CountlyActivity {

    @LayoutView(R.id.textView)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        LayoutViewHelper.initLayout(this);
        setupSupportActionBar();
        setupActionBar();
        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        textView.setText(text);
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scan_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_copy) {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText("", textView.getText()));
            Toast.showText(this, "已复制到剪贴板");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
