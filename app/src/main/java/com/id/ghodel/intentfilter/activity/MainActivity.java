package com.id.ghodel.intentfilter.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.id.ghodel.intentfilter.R;
import android.net.Uri;
import android.content.Intent;
import android.widget.Toast;
import java.util.Set;


public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
	
	private String shared_data;
	private String shared_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        initLogic();
        initListener();
   } 
   
    @Override
    public void setupView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
    }

    @Override
    public void initLogic() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
		
//		if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
//            Uri data = getIntent().getData();
//            if (data != null) {
//				 showMsg(data.getPath());
//				}
//			}
			
		try{
			Intent intent = getIntent();
			Uri data = intent.getData();
			shared_data = data.toString();
			
			Uri uri = Uri.parse(shared_data);
			String server = uri.getAuthority();
			String path = uri.getPath();
			String protocol = uri.getScheme();
			Set<String> args = uri.getQueryParameterNames();

			shared_key = uri.getQueryParameter("id");
			
			showMsg("Toast 1: " +protocol);
			showMsg("Toast 2: " +path);
			showMsg("Toast 3: " +server);
			showMsg("Toast 4: " +args);
		} catch (Exception e) {
			// The app is not opened via link
		}
		
		
			
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View _v) {
                    onBackPressed();
                }
         });
    }
	
	private void showMsg(String msg){
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
	}
}
