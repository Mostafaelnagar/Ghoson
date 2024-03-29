package app.grand.a8oson.base;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.grand.a8oson.R;


public class ParentActivity extends AppCompatActivity {


    public void showMessage(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_home_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

}
